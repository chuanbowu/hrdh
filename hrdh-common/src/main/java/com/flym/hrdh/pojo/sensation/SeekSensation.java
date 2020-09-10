package com.flym.hrdh.pojo.sensation;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:找红人管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SeekSensation implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headPic;

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
     * 粉丝数量：1、10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
     */
    private Integer followersType;

    /**
     * 粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
     */
    private Integer followersSpreadType;

    /**
     * 获赞数量：1-10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
     */
    private Integer praisedType;

    /**
     * 省份筛选：1-北京市、2-天津市、3-河北省、4-山西省、5-辽宁省、6-吉林省、7-黑龙江省、8-上海市、9-江苏省、10-浙江省、11-安徽省、12-福建省、13-江西省、14-山东省、15-河南省、16-湖北省、17-湖南省、18-广东省、19-广西壮族自治区、20-海南省、21-重庆市、22-四川省、23-贵州省、24-云南省、25-西藏自治区、26-陕西省、27-青海省、28-宁夏回族自治区、29-新疆维吾尔自治区、30-台湾省、31-香港特别行政区、32-甘肃、33-内蒙古自治区
     */
    private Integer screenType;

    /**
     * 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
     */
    private Integer extensionType;

    /**
     * 平台：1-抖音、2-快手
     */
    private Integer platformType;

    /**
     * 红人概况（文字版）
     */
    private String ensationSurvey;

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
     * 修改时间
     */
    private Date modifyDate;

    /**
     * s_seek_sensation
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
     * 粉丝数量：1、10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
     * @return followers_type 粉丝数量：1、10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
     */
    public Integer getFollowersType() {
        return followersType;
    }

    /**
     * 粉丝数量：1、10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
     * @param followersType 粉丝数量：1、10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
     */
    public void setFollowersType(Integer followersType) {
        this.followersType = followersType;
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
     * 获赞数量：1-10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
     * @return praised_type 获赞数量：1-10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
     */
    public Integer getPraisedType() {
        return praisedType;
    }

    /**
     * 获赞数量：1-10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
     * @param praisedType 获赞数量：1-10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
     */
    public void setPraisedType(Integer praisedType) {
        this.praisedType = praisedType;
    }

    /**
     * 省份筛选：1-北京市、2-天津市、3-河北省、4-山西省、5-辽宁省、6-吉林省、7-黑龙江省、8-上海市、9-江苏省、10-浙江省、11-安徽省、12-福建省、13-江西省、14-山东省、15-河南省、16-湖北省、17-湖南省、18-广东省、19-广西壮族自治区、20-海南省、21-重庆市、22-四川省、23-贵州省、24-云南省、25-西藏自治区、26-陕西省、27-青海省、28-宁夏回族自治区、29-新疆维吾尔自治区、30-台湾省、31-香港特别行政区、32-甘肃、33-内蒙古自治区
     * @return screen_type 省份筛选：1-北京市、2-天津市、3-河北省、4-山西省、5-辽宁省、6-吉林省、7-黑龙江省、8-上海市、9-江苏省、10-浙江省、11-安徽省、12-福建省、13-江西省、14-山东省、15-河南省、16-湖北省、17-湖南省、18-广东省、19-广西壮族自治区、20-海南省、21-重庆市、22-四川省、23-贵州省、24-云南省、25-西藏自治区、26-陕西省、27-青海省、28-宁夏回族自治区、29-新疆维吾尔自治区、30-台湾省、31-香港特别行政区、32-甘肃、33-内蒙古自治区
     */
    public Integer getScreenType() {
        return screenType;
    }

    /**
     * 省份筛选：1-北京市、2-天津市、3-河北省、4-山西省、5-辽宁省、6-吉林省、7-黑龙江省、8-上海市、9-江苏省、10-浙江省、11-安徽省、12-福建省、13-江西省、14-山东省、15-河南省、16-湖北省、17-湖南省、18-广东省、19-广西壮族自治区、20-海南省、21-重庆市、22-四川省、23-贵州省、24-云南省、25-西藏自治区、26-陕西省、27-青海省、28-宁夏回族自治区、29-新疆维吾尔自治区、30-台湾省、31-香港特别行政区、32-甘肃、33-内蒙古自治区
     * @param screenType 省份筛选：1-北京市、2-天津市、3-河北省、4-山西省、5-辽宁省、6-吉林省、7-黑龙江省、8-上海市、9-江苏省、10-浙江省、11-安徽省、12-福建省、13-江西省、14-山东省、15-河南省、16-湖北省、17-湖南省、18-广东省、19-广西壮族自治区、20-海南省、21-重庆市、22-四川省、23-贵州省、24-云南省、25-西藏自治区、26-陕西省、27-青海省、28-宁夏回族自治区、29-新疆维吾尔自治区、30-台湾省、31-香港特别行政区、32-甘肃、33-内蒙古自治区
     */
    public void setScreenType(Integer screenType) {
        this.screenType = screenType;
    }

    /**
     * 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
     * @return extension_type 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
     */
    public Integer getExtensionType() {
        return extensionType;
    }

    /**
     * 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
     * @param extensionType 推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
     */
    public void setExtensionType(Integer extensionType) {
        this.extensionType = extensionType;
    }

    /**
     * 平台：1-抖音、2-快手
     * @return platform_type 平台：1-抖音、2-快手
     */
    public Integer getPlatformType() {
        return platformType;
    }

    /**
     * 平台：1-抖音、2-快手
     * @param platformType 平台：1-抖音、2-快手
     */
    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
    }

    /**
     * 红人概况（文字版）
     * @return ensation_survey 红人概况（文字版）
     */
    public String getEnsationSurvey() {
        return ensationSurvey;
    }

    /**
     * 红人概况（文字版）
     * @param ensationSurvey 红人概况（文字版）
     */
    public void setEnsationSurvey(String ensationSurvey) {
        this.ensationSurvey = ensationSurvey == null ? null : ensationSurvey.trim();
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
}