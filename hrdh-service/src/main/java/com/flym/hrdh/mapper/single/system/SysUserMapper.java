package com.flym.hrdh.mapper.single.system;

import com.flym.hrdh.pojo.system.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysUserMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SysUser record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SysUser record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SysUser record);

    /**
     * 根据账号和密码获取信息
     * @param userName
     * @param password
     * @return
     */
    SysUser loginUser(@Param("userName") String userName, @Param("password") String password);

    /**
     * 获取用户列表
     * @param beginNum
     * @param pageSize
     * @param userName
     * @param trueName
     * @param status
     * @return
     */
    List<SysUser> findSysUser(@Param("beginNum") int beginNum, @Param("pageSize") int pageSize, @Param("userName") String userName, @Param("trueName") String trueName, @Param("status") int status);

    /**
     * 根据用户IDs 更新用户状态
     * @param idList 用户id集合
     * @param type 操作类型
     * @param modifySysUser 修改人
     * @param modifySysDate 修改时间
     * @return
     */
    void updateStatusByIds(@Param("idList") List<Long> idList, @Param("type") int type, @Param("modifySysUser") Long modifySysUser, @Param("modifySysDate") Date modifySysDate);

    /**
     * 根据角色IDs 重置用户密码为：123456
     * @param idList 用户id集合
     * @param  defaultPassword 默认密码
     * @param modifySysUser 修改人
     * @param modifySysDate 修改时间
     * @return
     */
    void updatePasswordByIds(@Param("idList") List<Long> idList, @Param("defaultPassword") String defaultPassword, @Param("modifySysUser") Long modifySysUser, @Param("modifySysDate") Date modifySysDate);

    /**
     * 获取用户数量
     * @param userName
     * @param trueName
     * @param status
     * @return
     */
    int getSysUserTotalRow(@Param("userName") String userName, @Param("trueName") String trueName, @Param("status") int status);

    /**
     * 随机获取某一个角色的一条正常的用户数据
     * @param roleId
     * @return
     */
    SysUser randGetSysUserByRole(@Param("roleId") Long roleId);

    /**
     * 检测用户名是否存在
     * @param userName
     * @param id
     * @return
     */
    int findSysUserNameNum(@Param("userName") String userName, @Param("id") Long id);
}
