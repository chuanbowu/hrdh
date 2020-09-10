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
import com.flym.hrdh.thread.OrderSettlementThread;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkOrderDetailsGetRequest;
import com.taobao.api.response.TbkOrderDetailsGetResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:任务调度——订单结算处理：每月25号处理上个月</p>
 * <p>Copyright: Copyright (c) 2020-06-13</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class OrderSettlementJob implements SimpleJob {

    private Logger logger = LoggerFactory.getLogger(OrderSettlementJob.class);

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
            logger.info("开始执行任务调度——订单结算处理");

            //启动线程：订单结算处理
            OrderSettlementThread orderSettlementThread = new OrderSettlementThread(sensationIncomeService, sensationService,commissionGoodsService, sensationAccountInfoService);
            Thread thread = new Thread(orderSettlementThread);
            thread.start();

           logger.info("结束执行任务调度——订单结算处理");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("订单结算处理异常：OrderSettlementJob" + e.getMessage());
        }
    }
}
