package com.flym.hrdh.config;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkSpreadGetRequest;
import com.taobao.api.response.TbkSpreadGetResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:淘宝客接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-23</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class TaobaoApi {

    /**
     * taobao.tbk.spread.get( 淘宝客-公用-长链转短链 )
     * 输入一个原始的链接，转换得到指定的传播方式，如二维码，淘口令，短连接； 现阶段只支持短连接。
     * @param url
     * @return
     * @throws ApiException
     */
    public static  String  getTbkSpreadGetRequest(String url) throws ApiException {

        String content = "";

        TaobaoClient client = new DefaultTaobaoClient(HrdhAdminConfig.server_url, HrdhAdminConfig.app_key, HrdhAdminConfig.app_secret);

        TbkSpreadGetRequest req = new TbkSpreadGetRequest();
        List<TbkSpreadGetRequest.TbkSpreadRequest> list2 = new ArrayList<TbkSpreadGetRequest.TbkSpreadRequest>();
        TbkSpreadGetRequest.TbkSpreadRequest obj3 = new TbkSpreadGetRequest.TbkSpreadRequest();
        list2.add(obj3);
        obj3.setUrl(url);
        req.setRequests(list2);
        TbkSpreadGetResponse rsp = client.execute(req);

        JSONObject js = JSONObject.fromObject(rsp.getBody());

        if(js.has("tbk_spread_get_response")){

            JSONObject jss = JSONObject.fromObject(js.getString("tbk_spread_get_response"));

            JSONObject results = JSONObject.fromObject(jss.get("results"));
            JSONArray tbkSpread = JSONArray.fromObject(results.get("tbk_spread"));

            JSONObject j = JSONObject.fromObject(tbkSpread.get(0));

            if(j.getString("err_msg").equalsIgnoreCase("ok")){

                content = j.getString("content");

            }
        }
        return content;
    }

}
