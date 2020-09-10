package com.flym.hrdh.pojo.system;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统菜单信息</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SysMenu implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单备注
     */
    private String menuRemark;

    /**
     * 菜单LOGO
     */
    private String menuLogo;

    /**
     * 链接地址
     */
    private String menuUrl;

    /**
     * 菜单等级
     */
    private Integer menuLevel;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 排序号
     */
    private Integer sorNum;

    /**
     * 状态：1-正常、2-删除
     */
    private Integer status;

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
     * 菜单名称
     * @return menu_name 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 菜单名称
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 菜单备注
     * @return menu_remark 菜单备注
     */
    public String getMenuRemark() {
        return menuRemark;
    }

    /**
     * 菜单备注
     * @param menuRemark 菜单备注
     */
    public void setMenuRemark(String menuRemark) {
        this.menuRemark = menuRemark == null ? null : menuRemark.trim();
    }

    /**
     * 菜单LOGO
     * @return menu_logo 菜单LOGO
     */
    public String getMenuLogo() {
        return menuLogo;
    }

    /**
     * 菜单LOGO
     * @param menuLogo 菜单LOGO
     */
    public void setMenuLogo(String menuLogo) {
        this.menuLogo = menuLogo == null ? null : menuLogo.trim();
    }

    /**
     * 链接地址
     * @return menu_url 链接地址
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 链接地址
     * @param menuUrl 链接地址
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * 菜单等级
     * @return menu_level 菜单等级
     */
    public Integer getMenuLevel() {
        return menuLevel;
    }

    /**
     * 菜单等级
     * @param menuLevel 菜单等级
     */
    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    /**
     * 父级ID
     * @return parent_id 父级ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父级ID
     * @param parentId 父级ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 排序号
     * @return sor_num 排序号
     */
    public Integer getSorNum() {
        return sorNum;
    }

    /**
     * 排序号
     * @param sorNum 排序号
     */
    public void setSorNum(Integer sorNum) {
        this.sorNum = sorNum;
    }

    /**
     * 状态：1-正常、2-删除
     * @return status 状态：1-正常、2-删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：1-正常、2-删除
     * @param status 状态：1-正常、2-删除
     */
    public void setStatus(Integer status) {
        this.status = status;
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
