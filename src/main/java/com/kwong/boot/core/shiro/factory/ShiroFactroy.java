package com.kwong.boot.core.shiro.factory;


import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kwong.boot.common.constant.state.ManagerStatus;
//import com.kwong.boot.core.common.constant.factory.ConstantFactory;
import com.kwong.boot.core.shiro.ShiroUser;
import com.kwong.boot.core.util.Convert;
import com.kwong.boot.core.util.SpringContextHolder;
import com.kwong.boot.system.model.Resource;
import com.kwong.boot.system.model.Role;
import com.kwong.boot.system.model.User;
import com.kwong.boot.system.repository.ResourceRepository;
import com.kwong.boot.system.repository.RoleRepository;
import com.kwong.boot.system.repository.UserRepository;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroFactroy implements IShiro {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private ResourceRepository resourceRepository;


    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Override
    public User user(String username) {

        User user = userRepository.getByUsername(username);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());
        shiroUser.setAccount(user.getUsername());
        shiroUser.setDeptId(user.getDeptId());
        //shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptId()));
        
        List<Role> roleList = user.getRoleList();
        List<Integer> roleIdList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        for (Role role : roleList) {
        	roleIdList.add(role.getId());
            roleNameList.add(role.getName());
        }
        shiroUser.setRoleList(roleIdList);
        shiroUser.setRoleNames(roleNameList);
        return shiroUser;
    }

    public List<Resource> findResUrlsByRoleId(Integer roleId) {
        return roleRepository.getOne(roleId).getResourceList();
    }

    /*@Override
    public String findRoleNameByRoleId(Integer roleId) {
        return ConstantFactory.me().getSingleRoleTip(roleId);
    }*/

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();

        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

	@Override
	public List<String> findResourcesByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findRoleNameByRoleId(Integer roleId) {
		return roleRepository.getNameById(roleId);
	}

}
