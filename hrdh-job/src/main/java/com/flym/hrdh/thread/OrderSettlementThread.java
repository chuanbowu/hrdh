package com.flym.hrdh.thread;

import com.flym.hrdh.api.service.business.ICommissionGoodsService;
import com.flym.hrdh.api.service.sensation.ISensationAccountInfoService;
import com.flym.hrdh.api.service.sensation.ISensationIncomeService;
import com.flym.hrdh.api.service.sensation.ISensationService;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.DateUtil;
import com.flym.hrdh.config.HrdhJobConfig;
import com.flym.hrdh.pojo.sensation.Sensation;
import com.flym.hrdh.pojo.sensation.SensationAccountInfo;
import com.flym.hrdh.pojo.sensation.SensationIncome;
import com.flym.hrdh.utils.NumUtils;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkOrderDetailsGetRequest;
import com.taobao.api.response.TbkOrderDetailsGetResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:任务调度——订单结算处理：每月25号处理上个月</p>
 * <p>Copyright: Copyright (c) 2020-06-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class OrderSettlementThread implements Runnable{

    Logger logger = LoggerFactory.getLogger(OrderSettlementThread.class);

    @Resource
    protected ISensationIncomeService sensationIncomeService;

    @Resource
    protected ISensationService sensationService;

    @Resource
    protected ICommissionGoodsService commissionGoodsService;

    @Resource
    protected ISensationAccountInfoService sensationAccountInfoService;

    public OrderSettlementThread(ISensationIncomeService sensationIncomeService, ISensationService sensationService,
                                 ICommissionGoodsService commissionGoodsService, ISensationAccountInfoService sensationAccountInfoService) {

        this.sensationIncomeService = sensationIncomeService;
        this.sensationService = sensationService;
        this.commissionGoodsService = commissionGoodsService;
        this.sensationAccountInfoService = sensationAccountInfoService;
    }

    @Override
    public void run() {

        try {

            logger.info("开始执行任务调度——订单结算处理");

            //订单查询结束时间
            String endTime = "";
            //订单查询开始时间
            String startTime = "";
            //第几页，默认1
            Long pageNo = 1L;

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat sdfO = new SimpleDateFormat("yyyy-MM-dd");

            Date date = new Date();

            //获取上个月第一天开始时间
            startTime = sdf.format(DateUtil.getReduceDateMonth(date, 1)) + "-01 00:00:00";
            //加20分钟再减1秒
            endTime = sf.format(DateUtil.getFetureDateSecond(sf.parse(startTime), 1199));

            //最后处理时间
            String lastHandleDate = sdfO.format(DateUtil.getMonthLastDay(date)) + " 23:59:59";

            TaobaoClient client = new DefaultTaobaoClient(HrdhJobConfig.server_url, HrdhJobConfig.app_key, HrdhJobConfig.app_secret);

            TbkOrderDetailsGetRequest req = new TbkOrderDetailsGetRequest();

            //查询时间类型，1：按照订单淘客创建时间查询，2:按照订单淘客付款时间查询，3:按照订单淘客结算时间查询
            req.setQueryType(2L);
            //页大小，默认20，1~100
            req.setPageSize(100L);
            //场景订单场景类型，1:常规订单，2:渠道订单，3:会员运营订单，默认为1
            req.setOrderScene(2L);

            abc : while (1 == 1) {

                System.out.println("开始时间："+startTime + "," + endTime);

                //订单查询结束时间
                req.setEndTime(endTime);
                //订单查询开始时间
                req.setStartTime(startTime);
                //第几页，默认1，1~100
                req.setPageNo(pageNo);

                TbkOrderDetailsGetResponse rsp = client.execute(req);

                System.out.println(rsp.getBody());

                JSONObject js = JSONObject.fromObject(rsp.getBody());

                if(js.has("tbk_order_details_get_response")){

                    JSONObject jss = JSONObject.fromObject(js.getString("tbk_order_details_get_response"));

                    JSONObject dataJs = JSONObject.fromObject(jss.get("data"));
                    JSONObject resultsJs = JSONObject.fromObject(dataJs.get("results"));
                    System.out.println("resultsJs"+ resultsJs);
                    JSONArray publisherOrderDtoList = JSONArray.fromObject(resultsJs.get("publisher_order_dto"));

                    //是否还有下一页
                    boolean hasNext =  dataJs.getBoolean("has_next");

                    for (int x=0; x < publisherOrderDtoList.size(); x++) {

                        JSONObject j = JSONObject.fromObject(publisherOrderDtoList.get(x));

                        if (!j.isNullObject()) {

                            //渠道关系id
                            Long relationId = j.getLong("relation_id");
                            //商品id
                            Long itemId = j.getLong("item_id");
                            //淘客订单状态，12-付款，13-关闭，14-确认收货，3-结算成功
                            Integer tkStatus = j.getInt("tk_status");
                            //买家在淘宝后台显示的订单编号
                            String tradeParentId = j.getString("trade_parent_id");
                            //订单创建的时间
                            String tkCreateTime = j.getString("tk_create_time");
                            //付款预估收入
                            Double pubSharePreFee = j.getDouble("pub_share_pre_fee");
                            //技术服务费
                            Double alimamaShareFee = j.getDouble("alimama_share_fee");

                            Double price = NumUtils.doubleScale(pubSharePreFee - alimamaShareFee, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES);

                            //获取红人ID
                            Long sensationId = sensationService.getSensationIdByRelationId(relationId);
                            //获取商品ID
                            Long goodsId = commissionGoodsService.getIdByItemId(itemId);

                            if (sensationId == null || goodsId == null) {
                                continue;
                            }

                            SensationIncome sensationIncome = sensationIncomeService.getBySensationIdOrGoodsIdOrTradeParentId(sensationId, goodsId, tradeParentId);

                            if (sensationIncome != null) {

                                //类型：1-预估收入、2-已入账、3-违规处罚
                                if (sensationIncome.getType() == 1) {

                                    //类型：1-预估收入、2-已入账、3-违规处罚
                                    sensationIncome.setType((tkStatus == 12 || tkStatus == 14) ? 1 : tkStatus == 3 ? 2 : tkStatus == 13 ? 3 : 0);
                                    //修改时间
                                    sensationIncome.setModifyDate(date);

                                    sensationIncomeService.save(sensationIncome);

                                    Sensation sensation = null;

                                    //淘客订单状态，12-付款，13-关闭，14-确认收货，3-结算成功
                                    if (tkStatus == 3) {

                                        //减少红人预估收入余额
                                        sensationService.reduceEstimatedRevenuePrice(sensationId, sensationIncome.getCommissionPrice());
                                        //增加红人余额
                                        sensationService.plusBalancePrice(sensationId, sensationIncome.getCommissionPrice());

                                        SensationAccountInfo sensationAccountInfo = new SensationAccountInfo();

                                        sensation = sensationService.get(sensationId);

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

                                        SensationAccountInfo s = new SensationAccountInfo();

                                        //红人ID
                                        s.setSensationId(sensationId);
                                        //金额数量
                                        s.setInfoNum(sensationIncome.getCommissionPrice());
                                        //创建时间
                                        s.setCreateDate(date);
                                        //明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
                                        s.setInfoType(1);
                                        //状态：1-增加、2-减少
                                        s.setStatus(1);
                                        //红人当前余额
                                        s.setValidPrice(sensation.getBalancePrice());

                                        sensationAccountInfoService.save(s);

                                    } else if (tkStatus == 13) {

                                        //减少红人预估收入余额
                                        sensationService.reduceEstimatedRevenuePrice(sensationId, sensationIncome.getCommissionPrice());

                                        SensationAccountInfo sensationAccountInfo = new SensationAccountInfo();

                                        sensation = sensationService.get(sensationId);

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
                                }

                            } else {

                                //淘客订单状态，12-付款，13-关闭，14-确认收货，3-结算成功：13不用处理
                                if (tkStatus != 13) {

                                    if (tkStatus == 12 || tkStatus == 14) {

                                        //增加红人预估收入余额
                                        sensationService.plusEstimatedRevenuePrice(sensationId, price);

                                    } else if (tkStatus == 3) {
                                        //增加红人余额
                                        sensationService.plusBalancePrice(sensationId, price);
                                    }

                                    sensationIncome = new SensationIncome();

                                    //红人ID
                                    sensationIncome.setSensationId(sensationId);
                                    //商品ID
                                    sensationIncome.setGoodsId(goodsId);
                                    //淘宝订单ID
                                    sensationIncome.setOrderId(tradeParentId);
                                    //佣金
                                    sensationIncome.setCommissionPrice(price);
                                    //类型：1-预估收入、2-已入账、3-违规处罚
                                    sensationIncome.setType((tkStatus == 12 || tkStatus == 14) ? 1 : tkStatus == 3 ? 2 : 0);
                                    //收入时间
                                    sensationIncome.setIncomeDate(date);
                                    //订单创建的时间
                                    sensationIncome.setTkCreateDate(sf.parse(tkCreateTime));
                                    //最后处理时间
                                    sensationIncome.setLastHandleDate(date);

                                    sensationIncomeService.save(sensationIncome);

                                    SensationAccountInfo sensationAccountInfo = new SensationAccountInfo();

                                    Sensation sensation = sensationService.get(sensationId);

                                    //红人ID
                                    sensationAccountInfo.setSensationId(sensationId);
                                    //金额数量
                                    sensationAccountInfo.setInfoNum(price);
                                    //创建时间
                                    sensationAccountInfo.setCreateDate(date);
                                    //明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
                                    sensationAccountInfo.setInfoType((tkStatus == 12 || tkStatus == 14) ? 4 : tkStatus == 3 ? 1 : 0);
                                    //状态：1-增加、2-减少
                                    sensationAccountInfo.setStatus(1);
                                    //红人当前余额
                                    sensationAccountInfo.setValidPrice((tkStatus == 12 || tkStatus == 14) ? sensation.getEstimatedRevenuePrice() : tkStatus == 3 ? sensation.getBalancePrice() : 0.00);

                                    sensationAccountInfoService.save(sensationAccountInfo);

                                }
                            }
                        }
                    }

                    if(hasNext){

                        //页码加1
                        pageNo = pageNo + 1;

                    }else {

                        if (lastHandleDate.equals(endTime)){
                            break abc;
                        }else {

                            pageNo = 1L;

                            //开始时间:加1秒
                            startTime = sf.format(DateUtil.getFetureDateSecond(sf.parse(endTime), 1));
                            //结束时间:加20分钟
                            endTime = sf.format(DateUtil.getFetureDateMinute(sf.parse(endTime), 20));

                            //0.1秒再通知一次
                            Thread.sleep(100);
                            System.out.println("时间："+sf.format(new Date()));
                        }
                    }
                }
            }

            logger.info("结束执行任务调度——订单结算处理");

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("订单结算处理异常：OrderSettlementJob" + e.getMessage());
        }
    }
}
