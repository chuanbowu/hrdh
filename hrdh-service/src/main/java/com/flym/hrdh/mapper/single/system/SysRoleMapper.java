package com.flym.hrdh.mapper.single.system;

import com.flym.hrdh.api.model.system.SysRoleVm;
import com.flym.hrdh.pojo.system.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysRoleMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SysRole record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SysRole record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SysRole selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SysRole record);

    /**
     * 获取系统角色列表
     * @param roleName 角色名称
     * @param roleRemark 角色备注
     * @param beginNum 开始条数
     * @param pageSize 每页显示条数
     * @returnm
     */
    List<SysRole> findSysRoleByLimit(@Param("roleName") String roleName, @Param("roleRemark") String roleRemark, @Param("beginNum") int beginNum, @Param("pageSize") int pageSize);

    /**
     * 获取系统角色总数量
     * @param roleName 角色名称
     * @param roleRemark 角色备注
     * @return
     */
    Long getValidSysRoleNum(@Param("roleName") String roleName, @Param("roleRemark") String roleRemark);

    /**
     * 查询角色是否存在
     * @return
     */
    int findRoleNameExist(@Param("roleName") String roleName);

    /**
     * 根据角色IDs 更新角色状态
     * @param idList 角色id集合
     * @param status 状态
     * @param modifySysUser 修改人
     * @param modifyDate 修改时间
     * @return
     */
    void updateStatusByIds(@Param("idList") List<Long> idList, @Param("status") int status, @Param("modifySysUser") Long modifySysUser, @Param("modifyDate") Date modifyDate);

    /**
     * 根据用户ID获取到相关角色列表
     * @param userId
     * @return
     */
    List<SysRoleVm> findSysUserRoleListByUserId(@Param("userId") Long userId);
}
