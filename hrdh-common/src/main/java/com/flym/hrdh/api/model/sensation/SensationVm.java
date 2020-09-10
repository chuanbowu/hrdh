package com.flym.hrdh.api.model.sensation;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人管理</p>
 * <p>Copyright: Copyright (c) 2020-05-19</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SensationVm implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headPic;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别：1-男、2-女、3-无
     */
    private Integer sex;

    /**
     * QQ
     */
    private String qq;

    /**
     * 微信
     */
    private String weChat;

    /**
     * 省ID
     */
    private Long provinceId;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 市ID
     */
    private Long cityId;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区ID
     */
    private Long areaId;

    /**
     * 区名称
     */
    private String areaName;

    /**
     * 省市区地址
     */
    private String provincialAddress;

    /**
     * 推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
     */
    private Integer extensionStatus;

    /**
     * 余额
     */
    private Double balancePrice;

    /**
     * 累计余额
     */
    private Double totalBalancePrice;

    /**
     * 预估收入余额
     */
    private Double estimatedRevenuePrice;

    /**
     * 认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
     */
    private Integer status;

    /**
     * 收样人姓名
     */
    private String consigneeName;

    /**
     * 收样地址
     */
    private String consigneeAddress;

    /**
     * 认证链接
     */
    private String authenticationLink;

    /**
     * 认证图片
     */
    private String authenticationPic;

    /**
     * 粉丝数量
     */
    private Integer followersNum;

    /**
     * 获赞总量
     */
    private Integer totalLikedNum;

    /**
     * 单条最高获赞
     */
    private Integer singleHighestNum;

    /**
     * 内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
     */
    private Integer contentType;

    /**
     * 粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
     */
    private Integer followersSpreadType;

    /**
     * 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
     */
    private String extensionType;

    /**
     * 红人概况（文字版）
     */
    private String sensationSurvey;

    /**
     * 红人类型：1-红人、2-找红人
     */
    private Integer sensationType;

    /**
     * 状态：1-正常、2-禁用、3-删除
     */
    private Integer sensationStatus;

    /**
     * 注册时间
     */
    private Date registerDate;

    /**
     * 注册IP
     */
    private String registerIp;

    /**
     * 登录时间
     */
    private Date loginDate;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 登录次数
     */
    private Integer loginNum;

    /**
     * 创建人
     */
    private Long createSysUser;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改人
     */
    private Long modifySysUser;

    /**
     * 修改人名称
     */
    private String modifySysUserName;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * s_sensation
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return id ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 手机号码
     * @return phone 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号码
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 昵称
     * @return nick_name 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 头像
     * @return head_pic 头像
     */
    public String getHeadPic() {
        return headPic;
    }

    /**
     * 头像
     * @param headPic 头像
     */
    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 性别：1-男、2-女、3-无
     * @return sex 性别：1-男、2-女、3-无
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 性别：1-男、2-女、3-无
     * @param sex 性别：1-男、2-女、3-无
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * QQ
     * @return qq QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * QQ
     * @param qq QQ
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * 微信
     * @return we_chat 微信
     */
    public String getWeChat() {
        return weChat;
    }

    /**
     * 微信
     * @param weChat 微信
     */
    public void setWeChat(String weChat) {
        this.weChat = weChat == null ? null : weChat.trim();
    }

    /**
     * 省ID
     * @return province_id 省ID
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     * 省ID
     * @param provinceId 省ID
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 市ID
     * @return city_id 市ID
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * 市ID
     * @param cityId 市ID
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * 区ID
     * @return area_id 区ID
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * 区ID
     * @param areaId 区ID
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * 省市区地址
     * @return provincial_address 省市区地址
     */
    public String getProvincialAddress() {
        return provincialAddress;
    }

    /**
     * 省市区地址
     * @param provincialAddress 省市区地址
     */
    public void setProvincialAddress(String provincialAddress) {
        this.provincialAddress = provincialAddress == null ? null : provincialAddress.trim();
    }

    /**
     * 推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
     * @return extension_status 推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
     */
    public Integer getExtensionStatus() {
        return extensionStatus;
    }

    /**
     * 推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
     * @param extensionStatus 推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
     */
    public void setExtensionStatus(Integer extensionStatus) {
        this.extensionStatus = extensionStatus;
    }

    /**
     * 余额
     * @return balance_price 余额
     */
    public Double getBalancePrice() {
        return balancePrice;
    }

    /**
     * 余额
     * @param balancePrice 余额
     */
    public void setBalancePrice(Double balancePrice) {
        this.balancePrice = balancePrice;
    }

    /**
     * 累计余额
     * @return total_balance_price 累计余额
     */
    public Double getTotalBalancePrice() {
        return totalBalancePrice;
    }

    /**
     * 累计余额
     * @param totalBalancePrice 累计余额
     */
    public void setTotalBalancePrice(Double totalBalancePrice) {
        this.totalBalancePrice = totalBalancePrice;
    }

    /**
     * 认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
     * @return status 认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
     * @param status 认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 收样人姓名
     * @return consignee_name 收样人姓名
     */
    public String getConsigneeName() {
        return consigneeName;
    }

    /**
     * 收样人姓名
     * @param consigneeName 收样人姓名
     */
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    /**
     * 收样地址
     * @return consignee_address 收样地址
     */
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    /**
     * 收样地址
     * @param consigneeAddress 收样地址
     */
    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    /**
     * 认证链接
     * @return authentication_link 认证链接
     */
    public String getAuthenticationLink() {
        return authenticationLink;
    }

    /**
     * 认证链接
     * @param authenticationLink 认证链接
     */
    public void setAuthenticationLink(String authenticationLink) {
        this.authenticationLink = authenticationLink == null ? null : authenticationLink.trim();
    }

    /**
     * 认证图片
     * @return authentication_pic 认证图片
     */
    public String getAuthenticationPic() {
        return authenticationPic;
    }

    /**
     * 认证图片
     * @param authenticationPic 认证图片
     */
    public void setAuthenticationPic(String authenticationPic) {
        this.authenticationPic = authenticationPic == null ? null : authenticationPic.trim();
    }

    /**
     * 粉丝数量
     * @return followers_num 粉丝数量
     */
    public Integer getFollowersNum() {
        return followersNum;
    }

    /**
     * 粉丝数量
     * @param followersNum 粉丝数量
     */
    public void setFollowersNum(Integer followersNum) {
        this.followersNum = followersNum;
    }

    /**
     * 获赞总量
     * @return total_liked_num 获赞总量
     */
    public Integer getTotalLikedNum() {
        return totalLikedNum;
    }

    /**
     * 获赞总量
     * @param totalLikedNum 获赞总量
     */
    public void setTotalLikedNum(Integer totalLikedNum) {
        this.totalLikedNum = totalLikedNum;
    }

    /**
     * 单条最高获赞
     * @return single_highest_num 单条最高获赞
     */
    public Integer getSingleHighestNum() {
        return singleHighestNum;
    }

    /**
     * 单条最高获赞
     * @param singleHighestNum 单条最高获赞
     */
    public void setSingleHighestNum(Integer singleHighestNum) {
        this.singleHighestNum = singleHighestNum;
    }

    /**
     * 内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
     * @return content_type 内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
     */
    public Integer getContentType() {
        return contentType;
    }

    /**
     * 内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
     * @param contentType 内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
     */
    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    /**
     * 粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
     * @return followers_spread_type 粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
     */
    public Integer getFollowersSpreadType() {
        return followersSpreadType;
    }

    /**
     * 粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
     * @param followersSpreadType 粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
     */
    public void setFollowersSpreadType(Integer followersSpreadType) {
        this.followersSpreadType = followersSpreadType;
    }

    /**
     * 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
     * @return extension_type 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
     */
    public String getExtensionType() {
        return extensionType;
    }

    /**
     * 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
     * @param extensionType 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
     */
    public void setExtensionType(String extensionType) {
        this.extensionType = extensionType == null ? null : extensionType.trim();
    }

    /**
     * 红人概况（文字版）
     * @return sensation_survey 红人概况（文字版）
     */
    public String getSensationSurvey() {
        return sensationSurvey;
    }

    /**
     * 红人概况（文字版）
     * @param sensationSurvey 红人概况（文字版）
     */
    public void setSensationSurvey(String sensationSurvey) {
        this.sensationSurvey = sensationSurvey == null ? null : sensationSurvey.trim();
    }

    /**
     * 红人类型：1-红人、2-找红人
     * @return sensation_type 红人类型：1-红人、2-找红人
     */
    public Integer getSensationType() {
        return sensationType;
    }

    /**
     * 红人类型：1-红人、2-找红人
     * @param sensationType 红人类型：1-红人、2-找红人
     */
    public void setSensationType(Integer sensationType) {
        this.sensationType = sensationType;
    }

    /**
     * 状态：1-正常、2-禁用、3-删除
     * @return sensation_status 状态：1-正常、2-禁用、3-删除
     */
    public Integer getSensationStatus() {
        return sensationStatus;
    }

    /**
     * 状态：1-正常、2-禁用、3-删除
     * @param sensationStatus 状态：1-正常、2-禁用、3-删除
     */
    public void setSensationStatus(Integer sensationStatus) {
        this.sensationStatus = sensationStatus;
    }

    /**
     * 注册时间
     * @return register_date 注册时间
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * 注册时间
     * @param registerDate 注册时间
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 注册IP
     * @return register_ip 注册IP
     */
    public String getRegisterIp() {
        return registerIp;
    }

    /**
     * 注册IP
     * @param registerIp 注册IP
     */
    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp == null ? null : registerIp.trim();
    }

    /**
     * 登录时间
     * @return login_date 登录时间
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * 登录时间
     * @param loginDate 登录时间
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * 登录IP
     * @return login_ip 登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 登录IP
     * @param loginIp 登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * 登录次数
     * @return login_num 登录次数
     */
    public Integer getLoginNum() {
        return loginNum;
    }

    /**
     * 登录次数
     * @param loginNum 登录次数
     */
    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    /**
     * 创建人
     * @return create_sys_user 创建人
     */
    public Long getCreateSysUser() {
        return createSysUser;
    }

    /**
     * 创建人
     * @param createSysUser 创建人
     */
    public void setCreateSysUser(Long createSysUser) {
        this.createSysUser = createSysUser;
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 修改人
     * @return modify_sys_user 修改人
     */
    public Long getModifySysUser() {
        return modifySysUser;
    }

    /**
     * 修改人
     * @param modifySysUser 修改人
     */
    public void setModifySysUser(Long modifySysUser) {
        this.modifySysUser = modifySysUser;
    }

    /**
     * 修改时间
     * @return modify_date 修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 修改时间
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getModifySysUserName() {
        return modifySysUserName;
    }

    public void setModifySysUserName(String modifySysUserName) {
        this.modifySysUserName = modifySysUserName;
    }

    /**
     * 预估收入余额
     * @return estimated_revenue_price 预估收入余额
     */
    public Double getEstimatedRevenuePrice() {
        return estimatedRevenuePrice;
    }

    /**
     * 预估收入余额
     * @param estimatedRevenuePrice 预估收入余额
     */
    public void setEstimatedRevenuePrice(Double estimatedRevenuePrice) {
        this.estimatedRevenuePrice = estimatedRevenuePrice;
    }
}