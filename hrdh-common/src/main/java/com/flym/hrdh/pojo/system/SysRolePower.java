package com.flym.hrdh.pojo.system;

import java.io.Serializable;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:角色权限关系信息</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SysRolePower implements Serializable{

    /**
     * ID
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long powerId;

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
     * 角色ID
     * @return role_id 角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 角色ID
     * @param roleId 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 权限ID
     * @return power_id 权限ID
     */
    public Long getPowerId() {
        return powerId;
    }

    /**
     * 权限ID
     * @param powerId 权限ID
     */
    public void setPowerId(Long powerId) {
        this.powerId = powerId;
    }
}
