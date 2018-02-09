/**
 * 
 */
package com.qzdsoft.eshop.vo.sys;

/**
 * 登录用户信息
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月15日
 * @see
 * @since 1.0
 */
public class LoginUserInfo
{
	private String loginId;//登录用户id
    private String name;//用户名
    private String phone;//手机号
    private Integer type;//登录类型0 代理商 1系统用户
    private Integer roleId;//角色id
   
    
    public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * @return the phone
     */
    public String getPhone()
    {
        return phone;
    }
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    /**
     * @return the type
     */
    public Integer getType()
    {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(Integer type)
    {
        this.type = type;
    }
    
    /**
     * @return the roleId
     */
    public Integer getRoleId()
    {
        return roleId;
    }
    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }
    /**
     * @see java.lang.Object#toString()
     */
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "LoginUserInfo [name=" + name + ", phone=" + phone + ", type="
                + type + ", roleId=" + roleId + "]";
    }
    
}
