package com.flym.hrdh.config;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkScPublisherInfoSaveRequest;
import com.taobao.api.request.TopAuthTokenCreateRequest;
import com.taobao.api.response.TbkScPublisherInfoSaveResponse;
import com.taobao.api.response.TopAuthTokenCreateResponse;
import net.sf.json.JSONObject;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:淘宝客接口管理</p>
 * <p>Copyright: Copyright (c) 2020-06-08</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class TaoBaoApi {

    /**
     * taobao.top.auth.token.create( 获取Access Token )
     * @param code
     * @return
     * @throws ApiException
     */
    public static  String  getTaoBaoClient(String code) throws ApiException {

        String content = "绑定失败，请重新操作";

        TaobaoClient client = new DefaultTaobaoClient(HrdhRestfulConfig.server_https_url, HrdhRestfulConfig.app_key, HrdhRestfulConfig.app_secret);

        TopAuthTokenCreateRequest req = new TopAuthTokenCreateRequest();
        req.setCode(code);

        TopAuthTokenCreateResponse rsp = client.execute(req);
        System.out.println("获取Access Token=" + rsp.getBody());

        JSONObject js = JSONObject.fromObject(rsp.getBody());

        if(js.has("top_auth_token_create_response")){

            JSONObject jss = JSONObject.fromObject(js.getString("top_auth_token_create_response"));

            JSONObject result = JSONObject.fromObject(jss.getString("token_result").replace("\"{","{").replace("}\"","}"));

            content = result.getString("access_token");
        }
        return content;
    }

    /**
     * taobao.tbk.sc.publisher.info.save( 淘宝客-公用-私域用户备案 )
     * @param sessionKey
     * @return
     * @throws ApiException
     */
    public static  String  getTbkScPublisherInfoSaveRequest(String sessionKey) throws ApiException {

        String content = "绑定失败，请重新操作";

        TaobaoClient client = new DefaultTaobaoClient(HrdhRestfulConfig.server_url, HrdhRestfulConfig.app_key, HrdhRestfulConfig.app_secret);

        TbkScPublisherInfoSaveRequest req = new TbkScPublisherInfoSaveRequest();
        //渠道备案 - 淘宝客邀请渠道的邀请码
        req.setInviterCode(HrdhRestfulConfig.inviter_code);
        //类型
        req.setInfoType(1L);
        //媒体侧渠道备注
        req.setNote("传播物");
        TbkScPublisherInfoSaveResponse rsp = client.execute(req, sessionKey);

        System.out.println("淘宝客-公用-私域用户备案=" + rsp.getBody());

        JSONObject js = JSONObject.fromObject(rsp.getBody());

        if(js.has("tbk_sc_publisher_info_save_response")){

            JSONObject jss = JSONObject.fromObject(js.getString("tbk_sc_publisher_info_save_response"));

            content = jss.getString("data");
        }
        return content;
    }

}
