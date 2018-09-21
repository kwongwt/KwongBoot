package com.kwong.boot.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author kwong
 *	用户表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_user") 
public class User extends Entitys implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 账号
	 */
	@Column(nullable = false, unique = true)
	private String username;
	/**
	 * 密码
	 */
	@Column(nullable = false)
	private String password;
	/**
	 * 密码盐
	 */
	private String salt;
	/**
	 * 邮箱
	 */
	@Column(nullable = false, unique = true)
	private String email;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 角色
	 */
	private Integer roleId;
	/**
	 * 部门
	 */
	private Integer deptId;
	/**
	 * 创建时间
	 */
	@Column(nullable = false)
	private Long createTime;
	/**
	 * 修改时间
	 */
	@Column(nullable = false)
	private Long editTime;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
	@Column(nullable = false)
	private Integer status;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getEditTime() {
		return editTime;
	}

	public void setEditTime(Long editTime) {
		this.editTime = editTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
