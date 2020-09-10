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
	public final static String result_1 = "登录成功";
	public final static String result_2 = "退出成功";
	public final static String result_3 = "取消成功";
	public final static String result_6 = "修改成功";
	public final static String result_7 = "上传成功";
	public final static String result_8 = "删除成功";
	public final static String result_9 = "添加成功";
	public final static String result_10 = "操作成功";
	public final static String result_11 = "生成成功";
	public final static String result_12 = "提现成功";
	public final static String result_13 = "处理成功";
	public final static String result_16 = "重置成功";



	/**
	 * 通用错误码：100000 - 101000
	 */
	public final static String result_100000 = "系统异常";
	public final static String result_100010 = "appKey无效";
	public final static String result_100020 = "签名有误";
	public final static String result_100030 = "用户令牌不能为空";
	public final static String result_100040 = "用户令牌不正确";

	/**
	 *	登陆账号 -101
	 */
	public final static String result_101011 = "账户名不能为空";
	public final static String result_101021 = "密码不能为空";
	public final static String result_101031 = "账户名或密码有误";
	public final static String result_101041 = "该账户已冻结";
	public final static String result_101051 = "该账户不存在";

	/**
	 *	新增平台用户 -104
	 */
	public final static String result_104011 = "账户名不能为空";
	public final static String result_104021 = "密码不能为空";
	public final static String result_104031 = "用户姓名不能为空";
	public final static String result_104041 = "账户名已被占用！";

	/**
	 * 平台用户详情 -105
	 */
	public final static String result_105010 = "ID不能为空";

	/**
	 * 修改用户资料 -106
	 */
	public final static String result_106010 = "ID不能为空";
	public final static String result_106020 = "用户姓名不能为空";

	/**
	 *	修改用户状态：1-正常、2-冻结、3-删除 -107
	 */
	public final static String result_107010 = "操作类型不能为空";
	public final static String result_107021 = "用户ID不能为空";
	public final static String result_107031 = "系统管理员不能冻结和删除";
	public final static String result_107041 = "用户ID不存在";

	/**
	 *	修改平台用户密码 -108
	 */
	public final static String result_108011 = "旧密码不能为空";
	public final static String result_108021 = "新密码不能为空";
	public final static String result_108031 = "旧密码有误";

	/**
	 *	根据角色ID获取角色信息 -109
	 */
	public final static String result_109011 = "用户ID不能为空";
	public final static String result_109021 = "用户ID不存在";

	/**
	 *	新增角色 -112
	 */
	public final static String result_112011 = "角色名称不能为空";
	public final static String result_112021 = "角色名称已存在";

	/**
	 *	根据角色ID获取角色信息 -113
	 */
	public final static String result_113011 = "角色ID不能为空";
	public final static String result_113021 = "角色ID不存在";

	/**
	 *	修改角色信息 -114
	 */
	public final static String result_114011 = "角色ID不能为空";
	public final static String result_114021 = "角色ID不存在";
	public final static String result_114031 = "角色名称不能为空";
	public final static String result_114041 = "角色名称已存在";

	/**
	 *	删除角色信息 -115
	 */
	public final static String result_115011 = "角色ID不能为空";
	public final static String result_115021 = "超级管理员不能删除";
	public final static String result_115031 = "角色ID不存在";

	/**
	 * 用户分配角色列表 -116
	 */
	public final static String result_116010 = "用户ID不能为空";

	/**
	 * 授权角色给用户  -117
	 */
	public final static String result_117010 = "用户ID不能为空";
	public final static String result_117020 = "角色ID不能为空";

	/**
	 * 角色分配菜单列表  -118
	 */
	public final static String result_118010 = "角色ID不能为空";

	/**
	 * 授权菜单给角色 -119
	 */
	public final static String result_119010 = "角色ID不能为空";
	public final static String result_119020 = "菜单ID不能为空";

	/**
	 * 上传图片文件  -201
	 */
	public final static String result_201011 = "只能上传图片格式文件";

	/**
	 * 新增和编辑客服 -203
	 */
	public final static String result_203011 = "客服昵称不能为空";
	public final static String result_203021 = "客服图片不能为空";

	/**
	 * 修改客服状态 -204
	 */
	public final static String result_204010 = "客服ID不能为空";
	public final static String result_204020 = "操作类型不能为空";

	/**
	 * 新增和编辑轮播图 -206
	 */
	public final static String result_206011 = "商品ID不能为空";
	public final static String result_206021 = "轮播图不能为空";
	public final static String result_206031 = "排序号不能为空";
	public final static String result_206041 = "类型不能为空";

	/**
	 * 修改轮播图状态 -207
	 */
	public final static String result_207010 = "客服ID不能为空";
	public final static String result_207020 = "操作类型不能为空";

	/**
	 * 新增和编辑经纪人 -209
	 */
	public final static String result_209011 = "经纪人姓名不能为空";
	public final static String result_209021 = "经纪人图片不能为空";

	/**
	 * 修改经纪人状态 -210
	 */
	public final static String result_210010 = "经纪人ID不能为空";
	public final static String result_210020 = "操作类型不能为空";

	/**
	 * 新增和编辑广告 -212
	 */
	public final static String result_212011 = "商品ID不能为空";
	public final static String result_212021 = "类型不能为空";

	/**
	 * 修改广告状态 -213
	 */
	public final static String result_213010 = "广告ID不能为空";
	public final static String result_213020 = "操作类型不能为空";

	/**
	 * 新增和编辑轮常见问题配置 -215
	 */
	public final static String result_215011 = "问内容不能为空";
	public final static String result_215021 = "答内容不能为空";
	public final static String result_215031 = "排序号不能为空";

	/**
	 * 修改常见问题配置状态 -216
	 */
	public final static String result_216010 = "配置ID不能为空";
	public final static String result_216020 = "操作类型不能为空";

	/**
	 * 新增和编辑红人推荐 -218
	 */
	public final static String result_218011 = "红人ID不能为空";

	/**
	 * 删除红人推荐 -219
	 */
	public final static String result_219010 = "红人推荐ID不能为空";

	/**
	 * 新增和编辑返佣推荐商品 -221
	 */
	public final static String result_221011 = "商品ID不能为空";
	public final static String result_221021 = "排序号不能为空";

	/**
	 * 修改返佣推荐商品状态 -222
	 */
	public final static String result_222010 = "返佣推荐商品ID不能为空";
	public final static String result_222020 = "操作类型不能为空";

	/**
	 * 新增和编辑拿货推荐商品 -224
	 */
	public final static String result_224011 = "商品ID不能为空";
	public final static String result_224021 = "排序号不能为空";

	/**
	 * 修改拿货推荐商品状态 -225
	 */
	public final static String result_225010 = "拿货推荐商品ID不能为空";
	public final static String result_225020 = "操作类型不能为空";

	/**
	 * 新增和编辑底部返佣推荐商品 -227
	 */
	public final static String result_227011 = "商品ID不能为空";
	public final static String result_227021 = "排序号不能为空";

	/**
	 * 修改底部返佣推荐商品状态 -228
	 */
	public final static String result_228010 = "底部返佣推荐商品ID不能为空";
	public final static String result_228020 = "操作类型不能为空";

	/**
	 * 根据省份ID获取城市 -230
	 */
	public final static String result_230010 = "省份ID不能为空";

	/**
	 * 根据城市ID获取区 -231
	 */
	public final static String result_231010 = "城市ID不能为空";

	/**
	 * 编辑商品广告 -234
	 */
	public final static String result_234010 = "广告ID不能为空";
	public final static String result_234021 = "广告图不能为空";
	public final static String result_234031 = "商品不能为空";

	/**
	 * 编辑商品数量配置-236
	 */
	public final static String result_236011 = "初级数量不能为空";
	public final static String result_236021 = "中级数量不能为空";
	public final static String result_236031 = "高级数量不能为空";

	/**
	 * 新增和编辑分类配置  -238
	 */
	public final static String result_238011 = "分类名称不能为空";
	public final static String result_238021 = "排序号不能为空";

	/**
	 * 修改分类配置状态 -239
	 */
	public final static String result_239010 = "分类ID不能为空";

	/**
	 * 新增商家 -302
	 */
	public final static String result_302011 = "手机号不能为空";
	public final static String result_302021 = "密码不能为空";
	public final static String result_302031 = "商家等级不能为空";
	public final static String result_302041 = "淘宝信誉等级不能为空";
	public final static String result_302051 = "该手机号已经存在，不能重复添加";

	/**
	 * 修改商家 -303
	 */
	public final static String result_303010 = "商家ID不能为空";
	public final static String result_303021 = "商家等级不能为空";
	public final static String result_303031 = "淘宝信誉等级不能为空";

	/**
	 * 修改商家状态 -304
	 */
	public final static String result_304010 = "商家ID不能为空";
	public final static String result_304020 = "操作类型不能为空";

	/**
	 * 返佣商品详情 -306
	 */
	public final static String result_306010 = "商品ID不能为空";

	/**
	 * 拿货商品详情 -308
	 */
	public final static String result_308010 = "商品ID不能为空";

	/**
	 *  红人详情 -402
	 */
	public final static String result_402010 = "红人ID不能为空";

	/**
	 * 新增和编辑找红人 -403
	 */
	public final static String result_403011 = "手机号不能为空";
	public final static String result_403021 = "昵称不能为空";
	public final static String result_403031 = "头像不能为空";
	public final static String result_403041 = "性别不能为空";
	public final static String result_403051 = "省不能为空";
	public final static String result_403061 = "市不能为空";
	public final static String result_403071 = "区不能为空";
	public final static String result_403081 = "推广类目不能为空";
	public final static String result_403091 = "粉丝数量不能为空";
	public final static String result_403101 = "获赞总量不能为空";
	public final static String result_403111 = "单条最高获赞不能为空";
	public final static String result_403121 = "内容分类不能为空";
	public final static String result_403131 = "粉丝分布不能为空";
	public final static String result_403141 = "推广方式不能为空";
	public final static String result_403151 = "红人概况不能为空";
	public final static String result_403161 = "该手机号已经存在，不能重复添加";

	/**
	 * 编辑找红人 -404
	 */
	public final static String result_404010 = "红人ID不能为空";
	public final static String result_404021 = "昵称不能为空";
	public final static String result_404031 = "头像不能为空";
	public final static String result_404041 = "性别不能为空";
	public final static String result_404051 = "省不能为空";
	public final static String result_404061 = "市不能为空";
	public final static String result_404071 = "区不能为空";
	public final static String result_404081 = "推广类目不能为空";
	public final static String result_404091 = "粉丝数量不能为空";
	public final static String result_404101 = "获赞总量不能为空";
	public final static String result_404111 = "单条最高获赞不能为空";
	public final static String result_404121 = "内容分类不能为空";
	public final static String result_404131 = "粉丝分布不能为空";
	public final static String result_404141 = "推广方式不能为空";
	public final static String result_404151 = "红人概况不能为空";
	public final static String result_404161 = "不是找红人，不能修改";

	/**
	 * 修改红人状态 -405
	 */
	public final static String result_405010 = "红人ID不能为空";
	public final static String result_405020 = "操作类型不能为空";

	/**
	 * 认证审核 -407
	 */
	public final static String result_407010 = "认证ID不能为空";
	public final static String result_407021 = "审核状态不能为空";
	public final static String result_407031 = "拒绝理由不能为空";
	public final static String result_407041 = "已经审核过，不能重复操作";

	/**
	 * 提现审核 -410
	 */
	public final static String result_410010 = "提现ID不能为空";
	public final static String result_410021 = "已经审核过，不能重复操作";


}


