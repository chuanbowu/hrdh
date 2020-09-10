package com.flym.hrdh.api.service.system;

import com.flym.hrdh.api.model.system.SysRoleVm;
import com.flym.hrdh.pojo.system.SysRole;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统角色管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISysRoleService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SysRole get(Long id);

    /**
     * 保存信息
     * @param sysRole
     */
    SysRole save(SysRole sysRole);

    /**
     * 获取系统角色列表
     * @param roleName 角色名称
     * @param roleRemark 角色备注
     * @param beginNum 开始条数
     * @param pageSize 每页显示条数
     * @returnm
     */
    List<SysRole> findSysRoleByLimit(String roleName, String roleRemark, int beginNum, int pageSize);

    /**
     * 获取系统角色总数量
     * @param roleName 角色名称
     * @param roleRemark 角色备注
     * @return
     */
    Long getValidSysRoleNum(String roleName, String roleRemark);

    /**
     * 查询角色名是否存在
     * @return
     */
    int findRoleNameExist(String roleName);


    /**
     * 根据角色IDs 更新角色状态
     * @param idList 角色id集合
     * @param status 状态
     * @param modifySysUser 修改人
     * @param modifyDate 修改时间
     * @return
     */
    void updateStatusByIds(List<Long> idList, int status, Long modifySysUser, Date modifyDate);

    /**
     * 根据用户ID获取到相关角色列表
     * @param userId
     * @return
     */
    List<SysRoleVm> findSysUserRoleListByUserId(Long userId);

}
