package com.flym.hrdh.config;

import net.sf.json.JSONObject;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:接口返回信息管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class ResponseMessage {

	public String result;
	public JSONObject datas;
	public String message;

	public ResponseMessage() {
		super();
	}

	public ResponseMessage(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public String isResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public JSONObject getDatas() {
		return datas;
	}

	public void setDatas(JSONObject datas) {
		this.datas = datas;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ReturnMessage{" +
				"result='" + result + '\'' +
				", datas=" + datas +
				", message='" + message + '\'' +
				'}';
	}
}
