package com.flym.hrdh.config;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商家接口返回码管理</p>
 * <p>Copyright: Copyright (c) 2020-05-21</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class BusinessResultCode {

	/**
	 * 成功码：0 - 100000
	 */
	public final static String result_0 = "请求成功";
	public final static String result_1 = "登录成功";
	public final static String result_2 = "退出成功";
	public final static String result_3 = "取消成功";
	public final static String result_4 = "修改成功";
	public final static String result_5 = "上传成功";
	public final static String result_6 = "删除成功";
	public final static String result_7 = "添加成功";
	public final static String result_8 = "操作成功";
	public final static String result_9 = "发送成功";


	/**
	 * 通用错误码：100000 - 101000
	 */
	public final static String result_100000 = "系统异常";
	public final static String result_100010 = "appKey无效";
	public final static String result_100020 = "签名有误";
	public final static String result_100030 = "用户令牌不能为空";
	public final static String result_100040 = "用户令牌不正确";

	/**
	 * 商家账号密码登录 -101
	 */
	public final static String result_101011 = "手机号码不能为空";
	public final static String result_101021 = "密码不能为空";
	public final static String result_101031 = "手机号码或密码有误";
	public final static String result_101041 = "该账号已被禁用，无法登陆";

	/**
	 * 商家完善信息 -104
	 */
	public final static String result_104011 = "昵称不能为空";
	public final static String result_104021 = "头像不能为空";
	public final static String result_104031 = "店铺名称不能为空";
	public final static String result_104041 = "淘宝或天猫店铺地址不能为空";
	public final static String result_104051 = "邀请码不能为空";
	public final static String result_104061 = "收样人姓名不能为空";
	public final static String result_104071 = "收样的地址不能为空";
	public final static String result_104081 = "宝贝描述不能为空";
	public final static String result_104091 = "卖家服务不能为空";
	public final static String result_104101 = "物流服务不能为空";
	public final static String result_104111 = "客服电话不能为空";
	public final static String result_104121 = "客服微信二维码不能为空";
	public final static String result_104131 = "验证码不能为空";
	public final static String result_104141 = "验证码有误";
	public final static String result_104151 = "不能重复完善信息";

	/**
	 * 商家修改信息 -105
	 */
	public final static String result_105011 = "收样人姓名不能为空";
	public final static String result_105021 = "收样的地址不能为空";
	public final static String result_105131 = "客服电话不能为空";
	public final static String result_105141 = "客服微信二维码不能为空";

	/**
	 * 修改密码 - 106
	 */
	public final static String result_106011 = "旧密码不能为空";
	public final static String result_106021 = "新密码不能为空";
	public final static String result_106031 = "确认密码不能为空";
	public final static String result_106041 = "新密码和确认密码不一致";
	public final static String result_106051 = "旧密码有误！";


	/**
	 * 发送验证码接口 -201
	 */
	public final static String result_201011 = "手机号码不能为空";
	public final static String result_201021 = "发送验证码失败";

	/**
	 * 上传图片文件  -202
	 */
	public final static String result_202011 = "只能上传图片格式文件";


	/**
	 * 新增返佣商品 -302
	 */
	public final static String result_302011 = "商品标题不能为空";
	public final static String result_302021 = "主图不能为空";
	public final static String result_302031 = "商品分类不能为空";
	public final static String result_302041 = "商品详细不能为空";
	public final static String result_302051 = "券后价价格不能为空";
	public final static String result_302061 = "优惠券金额不能为空";
	public final static String result_302071 = "佣金不能为空";
	public final static String result_302081 = "比例不能为空";
	public final static String result_302091 = "样品剩余数量不能为空";
	public final static String result_302101 = "拍摄要求不能为空";
	public final static String result_302111 = "规则说明不能为空";
	public final static String result_302121 = "取样条件数量不能为空";
	public final static String result_302131 = "押金金额不能为空";
	public final static String result_302141 = "活动开始时间不能为空";
	public final static String result_302151 = "活动结束时间不能为空";
	public final static String result_302160 = "推广链接不能为空";
	public final static String result_302171 = "当月上新数量达到限制";
	public final static String result_302180 = "宝贝ID不能为空";
	public final static String result_302191 = "宝贝链接不能为空";
	public final static String result_302201 = "当前宝贝已被使用";

	/**
	 * 修改返佣商品状态 -303
	 */
	public final static String result_303010 = "商品ID不能为空";
	public final static String result_303020 = "类型不能为空";

	/**
	 * 新增拿货商品 -305
	 */
	public final static String result_305011 = "目前您是初级商家，不能增加";
	public final static String result_305021 = "商品标题不能为空";
	public final static String result_305031 = "主图不能为空";
	public final static String result_305041 = "商品价格不能为空";
	public final static String result_305051 = "商品分类不能为空";
	public final static String result_305061 = "商品详细不能为空";
	public final static String result_305071 = "当月上新数量达到限制";

	/**
	 * 修改拿货商品状态 -306
	 */
	public final static String result_306010 = "商品ID不能为空";
	public final static String result_306020 = "类型不能为空";

	/**
	 * 编辑返佣商品 -308
	 */
	public final static String result_308011 = "商品标题不能为空";
	public final static String result_308021 = "主图不能为空";
	public final static String result_308031 = "商品分类不能为空";
	public final static String result_308041 = "商品详细不能为空";
	public final static String result_308051 = "券后价价格不能为空";
	public final static String result_308061 = "优惠券金额不能为空";
	public final static String result_308071 = "佣金不能为空";
	public final static String result_308081 = "比例不能为空";
	public final static String result_308091 = "样品剩余数量不能为空";
	public final static String result_308101 = "拍摄要求不能为空";
	public final static String result_308111 = "规则说明不能为空";
	public final static String result_308121 = "取样条件数量不能为空";
	public final static String result_308131 = "押金金额不能为空";
	public final static String result_308141 = "活动开始时间不能为空";
	public final static String result_308151 = "活动结束时间不能为空";
	public final static String result_308160 = "推广链接不能为空";
	public final static String result_308171 = "当月上新数量达到限制";
	public final static String result_308180 = "宝贝ID不能为空";
	public final static String result_308191 = "宝贝链接不能为空";
	public final static String result_308200 = "商品ID不能为空";
	public final static String result_308211 = "此商品不是原商品";

	/**
	 * 审核取样 -402
	 */
	public final static String result_402010 = "取样ID不能为空";
	public final static String result_402020 = "审核状态不能为空";
	public final static String result_402031 = "拒绝理由不能为空";
	public final static String result_402041 = "只有等待审核才能操作";

	/**
	 * 商家发样 -403
	 */
	public final static String result_403010 = "取样ID不能为空";
	public final static String result_403021 = "快递公司不能为空";
	public final static String result_403031 = "快递单号不能为空";
	public final static String result_403041 = "寄回样品说明不能为空";
	public final static String result_403051 = "待发样中才能操作";

	/**
	 * 商家收样 -404
	 */
	public final static String result_404010 = "取样ID不能为空";
	public final static String result_404021 = "红人寄回样中才能操作";

}


