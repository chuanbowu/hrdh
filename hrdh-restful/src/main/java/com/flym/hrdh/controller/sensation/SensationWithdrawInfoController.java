package com.flym.hrdh.controller.sensation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.flym.hrdh.api.service.common.IAliPayWithdrawConfigService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.sensation.ISensationAccountInfoService;
import com.flym.hrdh.api.service.sensation.ISensationService;
import com.flym.hrdh.api.service.sensation.ISensationWithdrawInfoService;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.AliPayWithdrawConfig;
import com.flym.hrdh.pojo.sensation.Sensation;
import com.flym.hrdh.pojo.sensation.SensationAccountInfo;
import com.flym.hrdh.pojo.sensation.SensationWithdrawInfo;
import com.flym.hrdh.utils.IDUtils;
import com.flym.hrdh.utils.NumUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人提现明细接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-30</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "sensationWithdrawInfo")
public class SensationWithdrawInfoController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ISensationService sensationService;

    @Reference(version = "1.0.0")
    protected ISensationWithdrawInfoService sensationWithdrawInfoService;

    @Reference(version = "1.0.0")
    protected IAliPayWithdrawConfigService aliPayWithdrawConfigService;

    @Reference(version = "1.0.0")
    protected ISensationAccountInfoService sensationAccountInfoService;

    /**
     * 红人提现明细列表 -114
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationWithdrawInfoList")
    public ResponseMessage sensationWithdrawInfoList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }else{
                sensationId = Long.parseLong(returnMsg.getDatas().toString());
                returnMsg.setDatas(null);
            }

            //当前页码
            int pageNum = 1;
            String pageNumStr = map.get("pageNum");
            if (StringUtils.isNotBlank(pageNumStr)) {
                pageNum = Integer.parseInt(pageNumStr);
            }

            //每页显示数
            int pageSize = CommonConfig.RESTFUL_LIST_DEFAULT_PAGE_SIZE;
            String pageSizeStr = map.get("pageSize");
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            }

            //开始条数
            int beginNum = pageSize * (pageNum - 1);

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = sensationWithdrawInfoService.getSensationWithdrawInfoNum(sensationId);

            if (totalRow > 0) {

                List<SensationWithdrawInfo> sensationWithdrawInfoList = sensationWithdrawInfoService.findSensationWithdrawInfoList(sensationId, beginNum, pageSize);

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (SensationWithdrawInfo s : sensationWithdrawInfoList) {

                    JSONObject obj = new JSONObject();

                    //余额
                    obj.put("moneyPrice", NumUtils.doubleToScale(s.getMoneyPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //状态：1-提现中、2-已提现、3-提现失败
                    obj.put("status", s.getStatus());
                    //提现时间
                    obj.put("withdrawDate", sf.format(s.getWithdrawDate()));
                    //拒绝理由
                    obj.put("refuseContent", s.getRefuseContent() == null ? "" : s.getRefuseContent());

                    ja.add(obj);
                }
            }

            JSONObject jb = new JSONObject();
            jb.put("list", ja);
            jb.put("totalRow", totalRow);
            returnMsg.setDatas(jb);
            returnMsg.setResult("0");
            returnMsg.setMessage(ResultCode.result_0);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }

        return returnMsg;
    }

    /**
     * 红人提现 -115
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationWithdraw")
    public ResponseMessage sensationWithdraw(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }else{
                sensationId = Long.parseLong(returnMsg.getDatas().toString());
                returnMsg.setDatas(null);
            }

            //余额
            String moneyPriceStr = map.get("moneyPrice");
            if(StringUtils.isBlank(moneyPriceStr)){
                returnMsg.setResult("115011");
                returnMsg.setMessage(ResultCode.result_115011);
                return returnMsg;
            }

            //支付宝账号
            String account = map.get("account");
            if(StringUtils.isBlank(account)){
                returnMsg.setResult("115021");
                returnMsg.setMessage(ResultCode.result_115021);
                return returnMsg;
            }

            //支付宝真实姓名
            String trueName = map.get("trueName");
            if(StringUtils.isBlank(trueName)){
                returnMsg.setResult("115031");
                returnMsg.setMessage(ResultCode.result_115031);
                return returnMsg;
            }

            Double moneyPrice = Double.parseDouble(moneyPriceStr);

            if (moneyPrice <= 0){
                returnMsg.setResult("115041");
                returnMsg.setMessage(ResultCode.result_115041);
                return returnMsg;
            }

            Sensation sensation = sensationService.get(sensationId);

            //判断余额是否足够
            if(sensation.getBalancePrice() < moneyPrice){
                returnMsg.setResult("115051");
                returnMsg.setMessage(ResultCode.result_115051);
                return returnMsg;
            }

            Date date = new Date();

            int num = sensationService.reduceBalancePrice(sensationId, moneyPrice,1);

            SensationWithdrawInfo sensationWithdrawInfo = new SensationWithdrawInfo();

            //红人ID
            sensationWithdrawInfo.setSensationId(sensationId);
            //账号
            sensationWithdrawInfo.setAccount(account);
            //真实姓名
            sensationWithdrawInfo.setTrueName(trueName);
            //金额
            sensationWithdrawInfo.setMoneyPrice(moneyPrice);
            //提现时间
            sensationWithdrawInfo.setWithdrawDate(date);

            if(num > 0){

                sensation = sensationService.get(sensationId);

                SensationAccountInfo sensationAccountInfo = new SensationAccountInfo();

                //红人ID
                sensationAccountInfo.setSensationId(sensationId);
                //金额数量
                sensationAccountInfo.setInfoNum(moneyPrice);
                //创建时间
                sensationAccountInfo.setCreateDate(date);
                //明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
                sensationAccountInfo.setInfoType(2);
                //状态：1-增加、2-减少
                sensationAccountInfo.setStatus(2);
                //红人当前余额
                sensationAccountInfo.setValidPrice(sensation.getBalancePrice());

                sensationAccountInfoService.save(sensationAccountInfo);

                //自动提现配置
                AliPayWithdrawConfig aliPayWithdrawConfig = aliPayWithdrawConfigService.getNormal();

                //APP_ID
                String appId = aliPayWithdrawConfig.getAppId();
                //应用私钥
                String privateKey = aliPayWithdrawConfig.getPrivateKey();
                //支付宝公钥
                String publicKey = aliPayWithdrawConfig.getPublicKey();

                //商户转账唯一订单号
                String outVBizNo = IDUtils.getIdName();
                //收款方账户
                String payeeAccount = account;
                //转账金额，单位：元。
                Double amount = moneyPrice;
                //付款方姓名
                String payerShowName = aliPayWithdrawConfig.getPayerShowName();
                //收款方真实姓名
                String payeeRealName = trueName;
                //转账备注
                String remark = aliPayWithdrawConfig.getRemark();

                JSONObject job = new JSONObject();

                job.put("out_biz_no", outVBizNo);
                //支付宝登录号，支持邮箱和手机号格式。
                job.put("payee_type", "ALIPAY_LOGONID");
                job.put("payee_account", payeeAccount);
                job.put("amount", amount);
                job.put("payer_show_name", payerShowName);
                job.put("payee_real_name", payeeRealName);
                job.put("remark", remark);

                //支付宝转账请求
                AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appId,privateKey,"json","GBK",publicKey,"RSA2");
                AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
                request.setBizContent(job.toString());

                try {
                    AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);

                    //转账成功
                    if(response.isSuccess()){

                        //状态：1-提现中、2-已提现、3-提现失败
                        sensationWithdrawInfo.setStatus(2);

                    } else {

                        //状态：1-提现中、2-已提现、3-提现失败
                        sensationWithdrawInfo.setStatus(3);
                        //返回所有信息
                        sensationWithdrawInfo.setReturnInfo(response.getCode()+response.getSubMsg());

                        //增加金额回去给红人
                        sensationService.plusBalancePrice(sensationId, moneyPrice);

                        sensation = sensationService.get(sensationId);

                        SensationAccountInfo accountInfo = new SensationAccountInfo();

                        //红人ID
                        accountInfo.setSensationId(sensationId);
                        //金额数量
                        accountInfo.setInfoNum(moneyPrice);
                        //创建时间
                        accountInfo.setCreateDate(date);
                        //明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
                        accountInfo.setInfoType(3);
                        //状态：1-增加、2-减少
                        accountInfo.setStatus(1);
                        //红人当前余额
                        accountInfo.setValidPrice(sensation.getBalancePrice());

                        sensationAccountInfoService.save(accountInfo);

                        sensationWithdrawInfoService.save(sensationWithdrawInfo);

                        returnMsg.setResult("115061");
                        returnMsg.setMessage(ResultCode.result_115061);
                        return returnMsg;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    returnMsg.setResult("115071");
                    returnMsg.setMessage(ResultCode.result_115071);
                    return returnMsg;
                }

            }else {

                //状态：1-提现中、2-已提现、3-提现失败
                sensationWithdrawInfo.setStatus(3);

            }

            sensationWithdrawInfoService.save(sensationWithdrawInfo);

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_8);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }

        return returnMsg;
    }


    /**
     * 红人手动提现 -117
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationManualWithdraw")
    public ResponseMessage sensationManualWithdraw(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }else{
                sensationId = Long.parseLong(returnMsg.getDatas().toString());
                returnMsg.setDatas(null);
            }

            //余额
            String moneyPriceStr = map.get("moneyPrice");
            if(StringUtils.isBlank(moneyPriceStr)){
                returnMsg.setResult("115011");
                returnMsg.setMessage(ResultCode.result_115011);
                return returnMsg;
            }

            //支付宝账号
            String account = map.get("account");
            if(StringUtils.isBlank(account)){
                returnMsg.setResult("115021");
                returnMsg.setMessage(ResultCode.result_115021);
                return returnMsg;
            }

            //支付宝真实姓名
            String trueName = map.get("trueName");
            if(StringUtils.isBlank(trueName)){
                returnMsg.setResult("115031");
                returnMsg.setMessage(ResultCode.result_115031);
                return returnMsg;
            }

            Double moneyPrice = Double.parseDouble(moneyPriceStr);

            if (moneyPrice <= 0){
                returnMsg.setResult("115041");
                returnMsg.setMessage(ResultCode.result_115041);
                return returnMsg;
            }

            Sensation sensation = sensationService.get(sensationId);

            //判断余额是否足够
            if(sensation.getBalancePrice() < moneyPrice){
                returnMsg.setResult("115051");
                returnMsg.setMessage(ResultCode.result_115051);
                return returnMsg;
            }

            Date date = new Date();

            int num = sensationService.reduceBalancePrice(sensationId, moneyPrice,1);

            if(num > 0) {

                SensationWithdrawInfo sensationWithdrawInfo = new SensationWithdrawInfo();

                //红人ID
                sensationWithdrawInfo.setSensationId(sensationId);
                //账号
                sensationWithdrawInfo.setAccount(account);
                //真实姓名
                sensationWithdrawInfo.setTrueName(trueName);
                //金额
                sensationWithdrawInfo.setMoneyPrice(moneyPrice);
                //提现时间
                sensationWithdrawInfo.setWithdrawDate(date);
                //状态：1-提现中、2-已提现、3-提现失败
                sensationWithdrawInfo.setStatus(1);

                sensationWithdrawInfoService.save(sensationWithdrawInfo);

                sensation = sensationService.get(sensationId);

                SensationAccountInfo sensationAccountInfo = new SensationAccountInfo();

                //红人ID
                sensationAccountInfo.setSensationId(sensationId);
                //金额数量
                sensationAccountInfo.setInfoNum(moneyPrice);
                //创建时间
                sensationAccountInfo.setCreateDate(date);
                //明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
                sensationAccountInfo.setInfoType(2);
                //状态：1-增加、2-减少
                sensationAccountInfo.setStatus(2);
                //红人当前余额
                sensationAccountInfo.setValidPrice(sensation.getBalancePrice());

                sensationAccountInfoService.save(sensationAccountInfo);

            }else {

                returnMsg.setResult("115051");
                returnMsg.setMessage(ResultCode.result_115051);
                return returnMsg;
            }

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_7);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }

        return returnMsg;
    }

    public static void main(String[] args) throws AlipayApiException {

        //APP_ID
        String appId = "2021001164661947";
        //应用私钥
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCI5x7Tlx8UnKcs3BW1snPF79uhMk1vGV8Il/Qeak9l6GFK1XcInBWFOYK+3lgryqD2ywlko0SgquEsxDuTMF0l93ma4ACCPtaTmmYGZHTUHqhlkU/DLTgs3yzMamQF0tHkVZuBYB0h2Ch65fk8U/RCRnTvl2BY7aqlZnmGCT58yUfwwNx+Wr9KARIs4+aNsywrC+p+idaJWyNBzY7fOWWbKNe0g+JVXSVAJfQLkYD44MQ5IuTv2bM6RMKk9FlWiLj1pSIvMyVYQ5J9r/tz7AOhLhyZ2j6r/2aXVpnG3dDkbHoQAF9N+10nyXVpp1YhO/VmasBWJrt/kH0wkA4SQbPFAgMBAAECggEAGiWyYI07dW9aCppaVyKnNAXUt9voqDB7UyPajziUw4z3kBhnLLDK92/9hcDnrC1+QsSSnbMFN36LlUZBbqJNGCRFCIyB7F5eE0Do/Y2QNrdISGXRiyCG2yMg5kXnpqkWR3sGQVZ/Gei0Co0D5j7Opx3X1a7eyv1Rm1eQHEaFtcVgJIAWjjhGBiOB8bQHDYqvL+cfnKHJnTrTtWK6LQ4pIW2YZi6ThimXHmjAw4fIzyMpVAb65ILctJzlK2EfJU5NbjjKgWX1qOZfYxi8RAVrKnfw0K/i/W+EL2VrsgFRgEuNO1kfyIDYC/yv7kAk099efYZwMeCYaDh3t3lPPd2RwQKBgQDGK7/WEwK0T0oOcWoyCuHhD7EEdeWlMQsJkmqK7EqZJL9khrFTDf4Y75hP7LPmGvlwcWjR9vt1zEc8Bssq9ABaNeJ1Q26C9s2ICwtq0xqtQAy74DZZLxVl08OuzFbI/jbPbVl5fb5xewQCdJvSYT0jKLJvVULZyufzJdRgFrsP8QKBgQCw2l8vkBRckzyGVe5O2hzj5KOdswpy2lv7+/NqtIa78hJJRur3FDfQp9iIkNBiydxv6yTLlX74IMAnPmCYix8BfzA5E2+SjbgN+fjxeWmuklDSM5UOBIBvwn98hzKDFzZE8vsYvzq4kX5WpxRo5VuGCdWRmwBtVibAPc07wOS1FQKBgExld0ofAA7k9kbFIEx0d4ndE8AFd2eMlZW2FU7DMysAyKwHZPsAzPFuBIjjVqMhyQG5JtCy51QNUXTppY9Ei5I7bbcJLOReIvQMVfVztrgNlhsjn7AX/6bG0YxkiBxqLFUg8bptaGq1IuGTdLLQ+9G/EbLr6p6to2fQMKRufuTxAoGBAIlqA8/HGasc1Ehk+HfYTd5dRyw/c3zuYc6XY/mjL5VVcBPzwJoe4YeFA2CJdQFta5SilsKA9FkDyYo0k4Wzg3H8S5HtoiGzcDGb62xlNFJqadYKMmTj5o/wBnLR8dOd85L7YVNHMu86TXoy9E7U95pchc9PKrPJ3u48lv040fxJAoGAVbmIrZ97nMolLXSJ8d/MAZfZZ+KDtcVyQmcUQsb1VTp2bLvpXS3MQD+leseELxInHRCwAJ5sBErFis95gm5nWhzSSTCsxPdjHZA4GwY4VfbC8ETbO1vOjHpyPRDsN86b9fCQEsS1ACUK/SlHWxlIIfatTOepX3HXjxwBowAxedw=";
        //支付宝公钥
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiOce05cfFJynLNwVtbJzxe/boTJNbxlfCJf0HmpPZehhStV3CJwVhTmCvt5YK8qg9ssJZKNEoKrhLMQ7kzBdJfd5muAAgj7Wk5pmBmR01B6oZZFPwy04LN8szGpkBdLR5FWbgWAdIdgoeuX5PFP0QkZ075dgWO2qpWZ5hgk+fMlH8MDcflq/SgESLOPmjbMsKwvqfonWiVsjQc2O3zllmyjXtIPiVV0lQCX0C5GA+ODEOSLk79mzOkTCpPRZVoi49aUiLzMlWEOSfa/7c+wDoS4cmdo+q/9ml1aZxt3Q5Gx6EABfTftdJ8l1aadWITv1ZmrAVia7f5B9MJAOEkGzxQIDAQAB";

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appId,privateKey,"json","GBK",publicKey,"RSA2");
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();

        //商户转账唯一订单号
        String outVBizNo = IDUtils.getIdName();
        //收款方账户
        String payeeAccount = "13726918004";
        //转账金额，单位：元。
        Double amount = 0.1;
        //付款方姓名
        String payerShowName = "蔡展鹏";
        //收款方真实姓名
        String payeeRealName = "谢爵辉";
        //转账备注
        String remark = "榴莲红包平台提现";

        JSONObject job = new JSONObject();

        job.put("out_biz_no", outVBizNo);
        job.put("payee_type", "ALIPAY_LOGONID");
        job.put("payee_account", payeeAccount);
        job.put("amount", amount);
        job.put("payer_show_name", payerShowName);
        job.put("payee_real_name", payeeRealName);
        job.put("remark", remark);

        request.setBizContent(job.toString());

        try {
            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);

            //System.out.println("response"+response.getCode()+response.getMsg());
            System.out.println("response2"+response.getBody());

            if(response.isSuccess()){

                System.out.println("提现成功");

            } else {

                //返回码
                String code = response.getCode();

                System.out.println(response.getSubMsg());
                System.out.println("code:"+code);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出现错误，请联系管理员!");
        }
    }

}
