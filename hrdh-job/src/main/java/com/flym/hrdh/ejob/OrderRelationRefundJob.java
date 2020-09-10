package com.flym.hrdh.ejob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.flym.hrdh.api.service.business.ICommissionGoodsService;
import com.flym.hrdh.api.service.sensation.ISensationAccountInfoService;
import com.flym.hrdh.api.service.sensation.ISensationIncomeService;
import com.flym.hrdh.api.service.sensation.ISensationService;
import com.flym.hrdh.config.DateUtil;
import com.flym.hrdh.config.HrdhJobConfig;
import com.flym.hrdh.pojo.sensation.Sensation;
import com.flym.hrdh.pojo.sensation.SensationAccountInfo;
import com.flym.hrdh.pojo.sensation.SensationIncome;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkRelationRefundRequest;
import com.taobao.api.response.TbkRelationRefundResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:任务调度——订单维权退款订单处理：每月25号处理上个月</p>
 * <p>Copyright: Copyright (c) 2020-06-14</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class OrderRelationRefundJob implements SimpleJob {

    private Logger logger = LoggerFactory.getLogger(OrderRelationRefundJob.class);

    @Resource
    protected ISensationIncomeService sensationIncomeService;

    @Resource
    protected ISensationService sensationService;

    @Resource
    protected ICommissionGoodsService commissionGoodsService;

    @Resource
    protected ISensationAccountInfoService sensationAccountInfoService;

    @Override
    public void execute(ShardingContext shardingContext) {

        try {
            logger.info("开始执行任务调度——订单维权退款订单处理");

            //订单查询开始时间
            Date startTime = null;
            //第几页，默认1
            Integer pageNo = 1;
            //每页显示100条
            int pageSize = 100;
            //总条数
            Integer totalCount = 0;

            //总页数
            Integer totalPageNo = 0;

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat sdfO = new SimpleDateFormat("yyyy-MM-dd");

            Date date = new Date();

            //获取上个月第一天开始时间
            startTime = sf.parse(sdf.format(DateUtil.getReduceDateMonth(date, 1)) + "-01 00:00:00");
            //startTime = sf.parse("2020-06-01 00:00:00");

            //最后处理时间
            Date lastHandleDate = sf.parse(sdfO.format(DateUtil.getMonthLastDay(date)) + " 00:00:00");
            //Date lastHandleDate = sf.parse("2020-06-30 00:00:00");

            TaobaoClient client = new DefaultTaobaoClient(HrdhJobConfig.server_url, HrdhJobConfig.app_key, HrdhJobConfig.app_secret);

            TbkRelationRefundRequest req = new TbkRelationRefundRequest();
            TbkRelationRefundRequest.TopApiRefundRptOption obj1 = new TbkRelationRefundRequest.TopApiRefundRptOption();

            //页大小，默认20，1~100
            obj1.setPageSize(Long.valueOf(pageSize));
            //1-维权发起时间，2-订单结算时间（正向订单），3-维权完成时间，4-订单创建时间
            obj1.setSearchType(1L);
            //1 表示2方，2表示3方，0表示不限
            obj1.setRefundType(0L);
            //1代表渠道关系id，2代表会员关系id，3代表渠道加会员
            obj1.setBizType(1L);

            abc : while (1 == 1) {

                System.out.println("startTime= " + sf.format(startTime));

                //开始时间
                obj1.setStartTime(startTime);
                //第几页，默认1，1~100
                obj1.setPageNo(Long.valueOf(pageNo));

                req.setSearchOption(obj1);
                TbkRelationRefundResponse rsp = client.execute(req);

                System.out.println(rsp.getBody());

                JSONObject js = JSONObject.fromObject(rsp.getBody());

                if(js.has("tbk_relation_refund_response")){

                    JSONObject jss = JSONObject.fromObject(js.getString("tbk_relation_refund_response"));

                    JSONObject resultJs = JSONObject.fromObject(jss.get("result"));
                    JSONObject dataJs = JSONObject.fromObject(resultJs.get("data"));
                    JSONObject resultsJs = JSONObject.fromObject(dataJs.get("results"));

                    JSONArray resultList = JSONArray.fromObject(resultsJs.get("result"));

                    //总条数
                    totalCount = dataJs.getInt("total_count");

                    //总页数
                    totalPageNo = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);

                    for (int x=0; x < resultList.size(); x++) {

                        JSONObject j = JSONObject.fromObject(resultList.get(x));

                        if (!j.isNullObject()) {

                            //渠道关系id
                            Long relationId = j.getLong("relation_id");
                            //淘宝订单编号
                            String tbTradeParentId = j.getString("tb_trade_parent_id");

                            //维权创建(淘客结算回执) -4
                            //维权成功(淘客结算回执) -2 //要处理：扣红人钱
                            //维权失败(淘客结算回执) -3 //要处理：反红人钱
                            //发生多次维权，待处理 -11 //时间很长，先不管
                            //从淘客处补扣（钱已结给淘客） 等待扣款 -12 //需要处理：扣红人钱
                            //从淘客处补扣（钱已结给淘客） 扣款成功 -13 //需要处理：扣红人钱
                            //从卖家处补扣（钱已结给卖家） 等待扣款 -14 //不用处理
                            //从卖家处补扣（钱已结给卖家） 扣款成功 -15 //不用处理
                            Integer refundStatus = j.getInt("refund_status");

                            //获取红人ID
                            Long sensationId = sensationService.getSensationIdByRelationId(relationId);

                            if (sensationId == null) {
                                continue;
                            }

                            SensationIncome sensationIncome = sensationIncomeService.getBySensationIdOrGoodsIdOrTradeParentId(sensationId, null, tbTradeParentId);

                            if (sensationIncome != null) {

                                //类型：1-预估收入、2-已入账、3-违规处罚
                                if (sensationIncome.getType() == 1) {

                                    if(refundStatus == 2 || refundStatus == 12 || refundStatus == 13){

                                        //类型：1-预估收入、2-已入账、3-违规处罚
                                        sensationIncome.setType(3);
                                        //修改时间
                                        sensationIncome.setModifyDate(date);

                                        sensationIncomeService.save(sensationIncome);

                                        //减少红人预估收入余额
                                        sensationService.reduceEstimatedRevenuePrice(sensationId, sensationIncome.getCommissionPrice());

                                        SensationAccountInfo sensationAccountInfo = new SensationAccountInfo();

                                        Sensation sensation = sensationService.get(sensationId);

                                        //红人ID
                                        sensationAccountInfo.setSensationId(sensationId);
                                        //金额数量
                                        sensationAccountInfo.setInfoNum(sensationIncome.getCommissionPrice());
                                        //创建时间
                                        sensationAccountInfo.setCreateDate(date);
                                        //明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
                                        sensationAccountInfo.setInfoType(4);
                                        //状态：1-增加、2-减少
                                        sensationAccountInfo.setStatus(2);
                                        //红人当前余额
                                        sensationAccountInfo.setValidPrice(sensation.getEstimatedRevenuePrice());

                                        sensationAccountInfoService.save(sensationAccountInfo);

                                    }

                                }else if (sensationIncome.getType() == 2){

                                    if(refundStatus == 2){

                                        //类型：1-预估收入、2-已入账、3-违规处罚
                                        sensationIncome.setType(3);
                                        //修改时间
                                        sensationIncome.setModifyDate(date);

                                        sensationIncomeService.save(sensationIncome);

                                        //减少红人余额
                                        sensationService.reduceBalancePrice(sensationId, sensationIncome.getCommissionPrice(), 2);

                                        SensationAccountInfo sensationAccountInfo = new SensationAccountInfo();

                                        Sensation sensation = sensationService.get(sensationId);

                                        //红人ID
                                        sensationAccountInfo.setSensationId(sensationId);
                                        //金额数量
                                        sensationAccountInfo.setInfoNum(sensationIncome.getCommissionPrice());
                                        //创建时间
                                        sensationAccountInfo.setCreateDate(date);
                                        //明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
                                        sensationAccountInfo.setInfoType(1);
                                        //状态：1-增加、2-减少
                                        sensationAccountInfo.setStatus(2);
                                        //红人当前余额
                                        sensationAccountInfo.setValidPrice(sensation.getBalancePrice());

                                        sensationAccountInfoService.save(sensationAccountInfo);
                                    }
                                }
                            }
                        }
                    }

                    if(totalCount != 0 && !pageNo.equals(totalPageNo)){

                        //页码加1
                        pageNo = pageNo + 1;

                    }else {

                        if (lastHandleDate.equals(startTime)){
                            break abc;
                        }else {

                            pageNo = 1;

                            //开始时间:加1天
                            startTime = DateUtil.getFetureDateHasDate(startTime, 1);
                        }
                    }
                }
            }

            logger.info("结束执行任务调度——订单维权退款订单处理");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("订单维权退款订单处理异常：OrderSettlementJob" + e.getMessage());
        }
    }
}
