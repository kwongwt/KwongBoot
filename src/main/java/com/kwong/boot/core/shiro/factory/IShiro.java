package com.kwong.boot.core.shiro.factory;

import java.util.List;

import org.apache.shiro.authc.SimpleAuthenticationInfo;

import com.kwong.boot.core.shiro.ShiroUser;
import com.kwong.boot.system.model.User;

/**
 * 定义shirorealm所需数据的接口
 *
 * @author fengshuonan
 * @date 2016年12月5日 上午10:23:34
 */
public interface IShiro {

    /** 根据账号获取登录用户
     * @param username
     * @return
     */
    User user(String username);

    /** 根据系统用户获取Shiro的用户
     * @param user
     * @return
     */
    ShiroUser shiroUser(User user);

    /** 获取权限列表通过角色id
     * @param roleId 角色id
     */
    List<String> findResourcesByRoleId(Integer roleId);

    /** 根据角色id获取角色名称
     * @param roleId 角色id
     */
    String findRoleNameByRoleId(Integer roleId);

    /** 获取shiro的认证信息
     * @param shiroUser
     * @param user
     * @param realmName
     * @return
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName);

}
