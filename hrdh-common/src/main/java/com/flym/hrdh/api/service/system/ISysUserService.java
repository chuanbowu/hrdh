package com.flym.hrdh.api.service.system;
import com.flym.hrdh.pojo.system.SysUser;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:平台用户管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISysUserService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SysUser get(Long id);

    /**
     * 保存信息
     * @param sysUser
     * @return
     */
    SysUser save(SysUser sysUser);

    /**
     * 根据账号和密码获取信息
     * @param userName
     * @param password
     * @return
     */
    SysUser loginUser(String userName, String password);

    /**
     * 获取用户列表
     * @param beginNum
     * @param pageSize
     * @param trueName
     * @param userName
     * @param status
     * @return
     */
    List<SysUser> findSysUser(int beginNum, int pageSize, String userName, String trueName, int status);

    /**
     * 根据用户IDs 更新用户状态
     * @param idList 用户id集合
     * @param type 操作类型
     * @param modifySysUser 修改人
     * @param modifySysDate 修改时间
     * @return
     */
    void updateStatusByIds(List<Long> idList, int type, Long modifySysUser, Date modifySysDate);

    /**
     * 根据角色IDs 重置用户密码为：123456
     * @param idList 用户id集合
     * @param  defaultPassword 默认密码
     * @param modifySysUser 修改人
     * @param modifySysDate 修改时间
     * @return
     */
    void updatePasswordByIds(List<Long> idList, String defaultPassword, Long modifySysUser, Date modifySysDate);

    /**
     * 获取用户数量
     * @return
     */
    int getSysUserTotalRow(String userName, String trueName, int status);

    /**
     * 随机获取某一个角色的一条正常的用户数据
     * @param roleId
     * @return
     */
    SysUser randGetSysUserByRole(Long roleId);

    /**
     * 检测用户名是否存在
     * @param userName
     * @param id
     * @return
     */
    int findSysUserNameNum(String userName, Long id);
}
