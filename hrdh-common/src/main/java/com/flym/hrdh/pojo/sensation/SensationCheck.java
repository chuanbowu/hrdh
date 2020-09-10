package com.flym.hrdh.pojo.sensation;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人认证审核管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SensationCheck implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 红人ID
     */
    private Long sensationId;

    /**
     * 推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
     */
    private Integer extensionStatus;

    /**
     * 认证链接
     */
    private String authenticationLink;

    /**
     * 认证图片
     */
    private String authenticationPic;

    /**
     * 审核状态：1-审核中、2-审核拒绝、3-审核通过
     */
    private Integer checkStatus;

    /**
     * 内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
     */
    private Integer contentType;

    /**
     * 粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
     */
    private Integer followersSpreadType;

    /**
     * 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成
     */
    private String extensionType;

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
     * 红人概况（文字版）
     */
    private String sensationSurvey;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 审核人
     */
    private Long checkSysUser;

    /**
     * 拒绝理由
     */
    private String refuseContent;

    /**
     * 审核时间
     */
    private Date checkDate;

    /**
     * s_sensation_check
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
     * 红人ID
     * @return sensation_id 红人ID
     */
    public Long getSensationId() {
        return sensationId;
    }

    /**
     * 红人ID
     * @param sensationId 红人ID
     */
    public void setSensationId(Long sensationId) {
        this.sensationId = sensationId;
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
     * 审核状态：1-审核中、2-审核拒绝、3-审核通过
     * @return check_status 审核状态：1-审核中、2-审核拒绝、3-审核通过
     */
    public Integer getCheckStatus() {
        return checkStatus;
    }

    /**
     * 审核状态：1-审核中、2-审核拒绝、3-审核通过
     * @param checkStatus 审核状态：1-审核中、2-审核拒绝、3-审核通过
     */
    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
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
     * 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成
     * @return extension_type 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成
     */
    public String getExtensionType() {
        return extensionType;
    }

    /**
     * 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成
     * @param extensionType 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成
     */
    public void setExtensionType(String extensionType) {
        this.extensionType = extensionType == null ? null : extensionType.trim();
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
     * 审核人
     * @return check_sys_user 审核人
     */
    public Long getCheckSysUser() {
        return checkSysUser;
    }

    /**
     * 审核人
     * @param checkSysUser 审核人
     */
    public void setCheckSysUser(Long checkSysUser) {
        this.checkSysUser = checkSysUser;
    }

    /**
     * 拒绝理由
     * @return refuse_content 拒绝理由
     */
    public String getRefuseContent() {
        return refuseContent;
    }

    /**
     * 拒绝理由
     * @param refuseContent 拒绝理由
     */
    public void setRefuseContent(String refuseContent) {
        this.refuseContent = refuseContent == null ? null : refuseContent.trim();
    }

    /**
     * 审核时间
     * @return check_date 审核时间
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 审核时间
     * @param checkDate 审核时间
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}