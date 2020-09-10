package com.flym.hrdh.config;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:接口返回码管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class ResultCode {

	/**
	 * 成功码：0 - 100000
	 */
	public final static String result_0 = "请求成功";
	public final static String result_1 = "修改成功";
	public final static String result_2 = "发送成功";
	public final static String result_3 = "上传成功";
	public final static String result_4 = "登录成功";
	public final static String result_5 = "退出成功";
	public final static String result_6 = "注册成功";
	public final static String result_7 = "操作成功";
	public final static String result_8 = "提现成功";
	public final static String result_9 = "取样成功";
	public final static String result_10 = "收样成功";


	/**
	 * 通用错误码：100000 - 101000
	 */
	public final static String result_100000 = "系统异常";
	public final static String result_100010 = "appKey无效";
	public final static String result_100020 = "签名有误";
	public final static String result_100030 = "用户令牌不能为空";
	public final static String result_100040 = "用户令牌不正确";

	/**
	 * 上传图片文件  -202
	 */
	public final static String result_202011 = "只能上传图片格式文件";

	/**
	 * 发送验证码接口 -201
	 */
	public final static String result_201011 = "手机号码不能为空";
	public final static String result_201021 = "发送验证码失败";

	/**
	 * 红人注册 -101
	 */
	public final static String result_101011 = "手机号码不能为空";
	public final static String result_101021 = "验证码不能为空";
	public final static String result_101031 = "验证码有误";
	public final static String result_101041 = "该账号已被注册";
	public final static String result_101051 = "密码不能为空";

	/**
	 * 红人账号密码登录 -102
	 */
	public final static String result_102011 = "手机号码不能为空";
	public final static String result_102021 = "密码不能为空";
	public final static String result_102031 = "手机号码或密码有误";
	public final static String result_102041 = "该账号已被禁用，无法登陆";

	/**
	 * 红人账号验证码登录 -103
	 */
	public final static String result_103011 = "手机号码不能为空";
	public final static String result_103021 = "验证码不能为空";
	public final static String result_103031 = "验证码有误";
	public final static String result_103041 = "该账号未注册";
	public final static String result_103051 = "该账号已被禁用，无法登陆";

	/**
	 * 找回密码 - 105
	 */
	public final static String result_105011 = "手机号码不能为空";
	public final static String result_105021 = "验证码不能为空";
	public final static String result_105031 = "密码不能为空";
	public final static String result_105041 = "确认密码不能为空";
	public final static String result_105051 = "密码和确认密码不一致";
	public final static String result_105061 = "验证码有误";
	public final static String result_105071 = "该账号未注册";

	/**
	 * 获取红人详情 - 106
	 */
	public final static String result_106011 = "该账户已冻结";

	/**
	 * 完善和修改红人个人资料 - 107
	 */
	public final static String result_107011 = "昵称不能为空";
	public final static String result_107021 = "头像不能为空";
	public final static String result_107031 = "性别不能为空";
	public final static String result_107041 = "QQ不能为空";
	public final static String result_107051 = "微信不能为空";
	public final static String result_107061 = "省不能为空";
	public final static String result_107071 = "市不能为空";
	public final static String result_107081 = "区不能为空";
	public final static String result_107091 = "收样人姓名不能为空";
	public final static String result_107101 = "收样详细地址不能为空";

	/**
	 * 红人认证 - 109
	 */
	public final static String result_109011 = "未完善个人资料";
	public final static String result_109021 = "认证中，请耐心等待审核";
	public final static String result_109031 = "已认证，不能重复认证";
	public final static String result_109041 = "推广类目不能为空";
	public final static String result_109051 = "认证链接不能为空";
	public final static String result_109061 = "认证图片不能为空";
	public final static String result_109071 = "粉丝数量不能为空";
	public final static String result_109081 = "获赞总量不能为空";
	public final static String result_109091 = "单条最高获赞不能为空";
	public final static String result_109101 = "内容分类不能为空";
	public final static String result_109111 = "粉丝分布不能为空";
	public final static String result_109121 = "推广方式不能为空";

	/**
	 * 红人搜索列表 -111
	 */
	public final static String result_111011 = "红人昵称不能为空";

	/**
	 * 红人详情 -112
	 */
	public final static String result_112010 = "红人ID不能为空";

	/**
	 * 红人提现 -115
	 */
	public final static String result_115011 = "余额不能为空";
	public final static String result_115021 = "支付宝账号不能为空";
	public final static String result_115031 = "支付宝真实姓名不能为空";
	public final static String result_115041 = "余额有误，请重新输入";
	public final static String result_115051 = "余额不足，请重新输入";
	public final static String result_115061 = "提现失败，请核对信息";
	public final static String result_115071 = "出现错误，请联系管理员!";

	/**
	 * 滚动宣传列表 -204
	 */
	public final static String result_204010 = "类型不能为空";

	/**
	 * 根据省份ID获取城市 -211
	 */
	public final static String result_211010 = "省份ID不能为空";

	/**
	 * 根据城市ID获取区 -212
	 */
	public final static String result_212010 = "城市ID不能为空";

	/**
	 * 返佣推荐商品列表 -213
	 */
	public final static String result_213010 = "商品分类不能为空";

	/**
	 * 拿货推荐商品列表 -215
	 */
	public final static String result_215010 = "商品分类不能为空";

	/**
	 * 商品广告列表 -218
	 */
	public final static String result_218010 = "类型不能为空";

	/**
	 * 首页搜索商品 -301
	 */
	public final static String result_301011 = "商品名称不能为空";

	/**
	 * 返佣商品列表 -302
	 */
	public final static String result_302010 = "商品分类不能为空";

	/**
	 * 返佣商品搜索列表 -303
	 */
	public final static String result_303011 = "商品名称不能为空";

	/**
	 * 返佣商品详情 -304
	 */
	public final static String result_304010 = "商品ID不能为空";

	/**
	 * 拿货商品列表 -305
	 */
	public final static String result_305010 = "商品分类不能为空";

	/**
	 * 拿货商品搜索列表 -306
	 */
	public final static String result_306011 = "商品名称不能为空";

	/**
	 * 拿货商品详情 -307
	 */
	public final static String result_307010 = "商品ID不能为空";

	/**
	 * 取样详情 -402
	 */
	public final static String result_401010 = "取样ID不能为空";

	/**
	 * 获取取样信息 -403
	 */
	public final static String result_403010 = "商品ID不能为空";
	public final static String result_403021 = "未认证，请先认证。";
	public final static String result_403031 = "未授权，请先授权。";
	public final static String result_403041 = "样品剩余数量不足";
	public final static String result_403051 = "同一个商品不能重复取样";

	/**
	 * 确认取样 -404
	 */
	public final static String result_404010 = "商品ID不能为空";
	public final static String result_404020 = "审核状态不能为空";
	public final static String result_404031 = "同一个商品不能重复取样";

	/**
	 * 红人确认收样 -405
	 */
	public final static String result_405010 = "取样ID不能为空";
	public final static String result_405021 = "取样状态有误！请联系客服";

	/**
	 * 红人寄回样 -406
	 */
	public final static String result_406010 = "取样ID不能为空";
	public final static String result_406021 = "快递公司不能为空";
	public final static String result_406031 = "快递单号不能为空";
	public final static String result_406041 = "请确认收样再操作";

	/**
	 * 获取链接 -501
	 */
	public final static String result_501011 = "已经授权，无需再操作";
	public final static String result_501021 = "重定向地址不能为空";

}