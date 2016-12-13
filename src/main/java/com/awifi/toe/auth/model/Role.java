package com.awifi.toe.auth.model;
/**   
 * @Description:  角色实体类
 * @Title: Role.java 
 * @Package com.awifi.toe.auth.model 
 * @author kangyanxiang
 * @date 2015年10月14日 下午5:27:33
 * @version V1.0   
 */
public class Role {
 
	/**主键*/
    private Long id;
	
	/**别名*/
    private String aliasName;
	
	/**角色名称*/
    private String roleName;
	
	/**角色类型*/
    private String roleType;
	
	/**创建时间*/
    private String createDate;
	
	/**更新时间*/
    private String updateDate;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

	
}