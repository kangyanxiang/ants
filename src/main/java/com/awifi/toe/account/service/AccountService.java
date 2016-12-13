package com.awifi.toe.account.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.awifi.toe.auth.mapper.PermissionMapper;
import com.awifi.toe.auth.mapper.RoleMapper;
import com.awifi.toe.auth.mapper.UserMapper;
import com.awifi.toe.auth.model.Permission;
import com.awifi.toe.auth.model.Role;
import com.awifi.toe.auth.model.User;
import com.awifi.toe.auth.service.RoleService;
import com.awifi.toe.base.model.Page;
import com.awifi.toe.base.util.JsonUtil;
import com.awifi.toe.base.util.SessionUtil;
import com.awifi.toe.project.mapper.ProjectMapper;
import com.awifi.toe.system.mapper.LocationMapper;
import com.awifi.toe.system.util.SysConfigUtil;


/**   
 * @Description:  
 * @Title: AccountService.java 
 * @Package com.awifi.toe.account.service 
 * @author kangyanxiang 
 * @date 2015年11月20日 下午2:11:16
 * @version V1.0   
 */
@Service("accountService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AccountService {

    /**日志*/
    private static final Log logger = LogFactory.getLog(AccountService.class);
    
    /**用户*/
    @Resource(name = "userMapper")
    private UserMapper userMapper;
    
    /**角色业务层*/
    @Resource(name="roleService")
    private RoleService roleService;
   
    /**角色*/
    @Resource(name="roleMapper")
    private RoleMapper roleMapper;
   
    /**权限*/
    @Resource(name="permissionMapper")
    private PermissionMapper permissionMapper;
    
    /** 项目维护持久层 */
    @Resource(name = "projectMapper")
    private ProjectMapper projectMapper;
    
    /** 地区持久层  */
    @Resource(name = "locationMapper")
    private LocationMapper locationMapper;
    
    /**
     * 账号列表
     * @param page 分页实体
     * @param keywords 查询关键字
     * @param roleId 角色
     * @param provinceId 省
     * @param cityId 市
     * @param areaId 区县
     * @author kangyanxiang 
     * @date Oct 26, 2016 11:00:38 AM
     */
    public void pageQuery(Page page, String keywords, String roleId, String provinceId, String cityId,
            String areaId) {
        if (page.getPageSize() == null) {
            page.setPageSize(Integer.parseInt(SysConfigUtil.getParamValue("page.pageSize")));
        }
        Map<String,String> rojhtMap = getRojhtMap(SessionUtil.getCurUserId(),provinceId,cityId,areaId);
        int count = userMapper.pageCount(keywords,roleId,rojhtMap.get("provinceId"),rojhtMap.get("cityId"),rojhtMap.get("areaId"),
                rojhtMap.get("cascadeLabel"),rojhtMap.get("cascadeLevel"));
        page.setTotalRecord(count);
        if(count>0){
            List<User> adminUserList = userMapper.pageQuery(keywords,roleId,rojhtMap.get("provinceId"),rojhtMap.get("cityId"),rojhtMap.get("areaId"),
                    rojhtMap.get("cascadeLabel"),rojhtMap.get("cascadeLevel"),page.getBegin(),page.getPageSize());
            for(User user : adminUserList){
                if(null != user.getAreaId()){
                    user.setLocation(locationMapper.getLocationFullName(user.getAreaId().toString()));
                    continue;
                }
                if(null != user.getCityId()){
                    user.setLocation(locationMapper.getLocationFullName(user.getCityId().toString()));
                    continue;
                }
                if(null != user.getProvinceId()){
                    user.setLocation(locationMapper.getLocationFullName(user.getProvinceId().toString()));
                    continue;
                }
            }
            page.setRecords(adminUserList);
        }
    }
    
    /**
     * 获得角色下拉框数据
     * @return 角色列表
     * @author kangyanxiang 
     * @date 2015年11月25日 上午10:36:41
     */
    public List<Role> getRoleList() {
        Long accountId = SessionUtil.getCurUserId();
        Long roleId = userMapper.getRoleIdByAccountId(accountId);
        if (roleId != null && roleId == 1) {// 超级管理员
            return roleMapper.getAllRole();
        }
        if (roleId != null && roleId == 6) {// 集团管理员
            return roleMapper.getAll();
        }
        return roleMapper.getRoleList(roleId);
    }
    
    /**
     * 判断该账号是否已经存在
     * @param userName 账号名称
     * @return true 存在， false 不存在
     * @author kangyanxiang 
     * @date 2015年11月24日 上午11:51:25
     */
    public boolean isUserNameExit(String userName) {
        int count=userMapper.isUserNameExit(userName);
        if(count>0){
            return true;
        }
        return false;
    }
    
    /**
     * 新建账号
     * @param user 账号
     * @author kangyanxiang 
     * @date 2015年11月24日 上午9:39:01
     */
    public void insert(User user) {
        User locatioUser = userMapper.getInfoById(SessionUtil.getCurUserId());
        Integer userType=user.getUserType();//用户类型
        if(userType==2){
            user.setCityId(null);
            user.setAreaId(null);
        }
        else if(userType==3){
            user.setAreaId(null);
        }
        userMapper.insert(user);
        Long id = user.getId();
        if(userType==5){
            int cascadeLevel = locatioUser.getCascadeLevel();
            String cascadeLabel = id+"";
            if(StringUtils.isNotBlank(locatioUser.getCascadeLabel())){
                cascadeLabel = locatioUser.getCascadeLabel()+"-"+id;
            }
            userMapper.updateUserLabel(id,locatioUser.getId(),cascadeLabel,cascadeLevel+1);
        }
        roleService.authorize(id,userType);//授权，关联用户表和角色表
    }

    /**
     * 获取权限
     * @param userId 用户ID
     * @param areaId 
     * @param cityId 
     * @param provinceId 
     * @return map
     * @author kangyanxiang 
     * @date Oct 26, 2016 11:07:56 AM
     */
    public Map<String, String> getRojhtMap(Long userId, String provinceId, String cityId, String areaId) {
        String projectIds = null;//项目IDS
        String cascadeLabel = null;//层级
        String cascadeLevel = null;//等级
        User user = userMapper.getInfoById(userId);
        int userType = user.getUserType();
        //超级管理员
        if(userType == 1 || userType == 6){
            
        }
        //省级管理员
        else if(userType == 2){
            provinceId = user.getProvinceId().toString();
        }
        //市级管理员
        else if(userType == 3){
            provinceId = user.getProvinceId().toString();
            cityId = user.getCityId().toString();
        }
        //区县级管理员
        else if(userType == 4){
            provinceId = user.getProvinceId().toString();
            cityId = user.getCityId().toString();
            areaId = user.getAreaId().toString();
        }
        //客户管理员
        else if(userType == 5){
            cascadeLabel = user.getCascadeLabel();
            cascadeLevel = user.getCascadeLevel()+"";
            List<String> projectIdsList = projectMapper.getProjectIds(cascadeLabel);
            if(projectIdsList.size() > 0){
                projectIds = JsonUtil.listToString(projectIdsList);
            }
        }
        Map<String,String> rojhtMap = new HashMap<String,String>();
        rojhtMap.put("provinceId", provinceId);
        rojhtMap.put("cityId", cityId);
        rojhtMap.put("areaId", areaId);
        rojhtMap.put("projectIds", projectIds);
        rojhtMap.put("cascadeLabel", cascadeLabel);
        rojhtMap.put("cascadeLevel", cascadeLevel);
        return rojhtMap;
    }
    
    /**
     * 修改密码
     * @param password 密码
     * @param id 账号主键id
     * @author kangyanxiang 
     * @date 2015年11月24日 上午10:11:42
     */
    public void updatePwdById(String password, Long id) {
        userMapper.updatePwdById(password,id);
    }
    
    /**
     * 删除账号（逻辑删除）
     * @param id 账号id
     * @author kangyanxiang 
     * @date 2015年11月24日 上午10:36:35
     */
    public void delete(Long id) {
        userMapper.delete(id);
    }
    
    /**
     * 编辑账号信息
     * @param accountId 账号id
     * @param contactPerson 联系人
     * @param contactWay 联系方式
     * @param remark 备注
     * @author kangyanxiang 
     * @param remark2 
     * @date 2015年11月24日 下午4:10:48
     */
    public void edit(String accountId, String projectId, String contactPerson, String contactWay, String remark) {
        userMapper.updateAccount(accountId,projectId,contactPerson,contactWay,remark);
    }
    
    /**
     * 获取账号详情
     * @param accountId 账号id主键
     * @return 账号详情
     * @author kangyanxiang 
     * @throws Exception 
     * @date 2015年11月25日 上午10:51:04
     */
    public User getInfoById(Long accountId) throws Exception {
        User account=userMapper.getInfoById(accountId);
        if(null != account.getAreaId()){
            account.setLocation(locationMapper.getLocationFullName(account.getAreaId().toString()));
            return account;
        }
        if(null != account.getCityId()){
            account.setLocation(locationMapper.getLocationFullName(account.getCityId().toString()));
            return account;
        }
        if(null != account.getProvinceId()){
            account.setLocation(locationMapper.getLocationFullName(account.getProvinceId().toString()));
            return account;
        }
        return account;
    }
    
    /**
     * 获取权限ids
     * @param accountId 账号id
     * @return 权限ids
     * @author kangyanxiang 
     * @date 2015年11月25日 上午11:20:49
     */
    public String getPermissionIdsById(Long accountId) {
        List<Long> permissionIdList=permissionMapper.getPermissionIdList(accountId);
        String permissionIds=listToString(permissionIdList,',');
        return permissionIds;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     * 客户侧创建账号
     * @param customerId 客户id
     * @param userName 用户名
     * @param userType 用户类型
     * @param contactPerson 联系人
     * @param contactWay 联系方式
     * @param remark 备注
     * @param projectId 项目id
     * @return true 保存成功，false 保存失败
     * @author kangyanxiang 
     * @date 2015年11月26日 下午2:04:02
     */
    public Long insert(Long customerId,String userName,Integer userType,String contactPerson,String contactWay, String remark,Long projectId){
        User user=new User();
        String password=SysConfigUtil.getDefaultPassword();
        user.setPassword(password);
        user.setUserName(userName);
        user.setUserType(userType);
        user.setContactPerson(contactPerson);
        user.setContactWay(contactWay);
        user.setRemark(remark);
        user.setFkProjectId(projectId);
        userMapper.insert(user);
        Long id=user.getId();//返回插入的账号主键id
        roleService.authorize(id,userType);//授权，关联用户表和角色表
        userMapper.userCustomer(id,customerId);//关联账号和客户的关系
        return id;
    }
    
    /**
     * 客户侧编辑账号
     * @param customerId 客户id
     * @param contactPerson 联系人
     * @param contactWay 联系方式
     * @param remark 备注
     * @param projectId 项目id
     * @author kangyanxiang 
     * @date 2015年12月2日 下午2:02:50
     */
    public void edit(String customerId,String contactPerson,String contactWay, String remark,Long projectId){
        Long accountId=userMapper.getAccountIdById(Long.parseLong(customerId));
        userMapper.update(accountId, contactPerson, contactWay, remark, projectId);
    }
   
    /**
     * 根据客户id查找账号名称
     * @param customerId 客户id
     * @return 账号名称
     * @author kangyanxiang 
     * @date 2015年12月3日 上午10:18:30
     */
    public String getUserName(String customerId){
        Long accountId=userMapper.getAccountIdById(Long.parseLong(customerId));
        User account=userMapper.getInfoById(accountId);
        return account.getUserName();
    }
    
    
   
    /**
     * 将列表转化成以逗号分隔的String
     * @param list 集合
     * @param separator 分隔符
     * @return 以逗号分隔的String
     * @author kangyanxiang 
     * @date 2015年11月25日 上午11:20:10
     */
    public String listToString(List list, char separator) {
        return org.apache.commons.lang.StringUtils.join(list.toArray(), separator);
    }
    
    /**
     * 获得权限集合
     * @param accountId 账号主键id
     * @return 权限集合
     * @author kangyanxiang 
     * @date 2015年11月25日 上午11:17:47
     */
    public List<Permission> getPermissionListById(Long accountId) {
        List<Permission> permissionlist = permissionMapper.getPermissionList(accountId);// 获取该登录账号的一级菜单
        if (permissionlist == null || permissionlist.size() == 0) {
            return null;
        }
        int maxlength = permissionlist.size();
        for (int i = 0; i < maxlength; i++) {
            Permission permission = permissionlist.get(i);
            List<Permission> permissiontwolist = permissionMapper.getPermissionTwoList(accountId, permission.getId());// 获得该一级菜单下的二级菜单集合
            if (permissiontwolist == null || permissiontwolist.size() == 0) {
                continue;
            }
            permission.setChildPermissions(permissiontwolist);
            for (Permission permissionTwo : permissiontwolist) {
                Long permisssionTwoId = permissionTwo.getId();
                List<Permission> permissionthree = permissionMapper.getPermissionTwoList(accountId, permisssionTwoId);// 获得该二级菜单下的三级菜单集合
                if (permissionthree == null || permissionthree.size() == 0) {
                    continue;
                }
                permissionTwo.setChildPermissions(permissionthree);
            }
        }
        return permissionlist;
    }
    
    /**
     * 移除旧的权限关系
     * @param accountId 账号id
     * @author kangyanxiang 
     * @date 2015年12月1日 下午2:19:05
     */
    public void removeOldPermission(Long accountId) {
        permissionMapper.removeOldPermission(accountId);
    }
    
    /**
     * 增加权限关系
     * @param accountId 账号id
     * @param permissionid 权限id
     * @author kangyanxiang 
     * @date 2015年12月1日 下午2:19:26
     */
    public void addPermission(Long accountId, Long permissionid) {
        permissionMapper.addPermission(accountId,permissionid);
    }
    
    /**
     * 根据用户名查找用户密码
     * @param userName 用户名
     * @return 用户密码
     * @author 苏晨斌  
     * @date 2015年11月25日 下午3:18:24
     */
    public String findPwdByUserName(String userName){
        return userMapper.findPwdByUserName(userName);
    }
    
    /**
     * 通过用户名修改密码
     * @param password 密码
     * @param userName 用户名
     * @author 苏晨斌  
     * @date 2015年11月25日 下午3:19:39
     */
    public void updatePwdByUserName(String password, String userName){
        userMapper.updatePwdByUserName(password, userName);
    }

    /**
     * 修改账号信息
     * @param userName 用户名
     * @param contactPerson 联系人
     * @param contactWay 联系方式
     * @author 苏晨斌  
     * @date 2015年11月24日 下午5:49:46
     */
    public void edit(String userName, String contactPerson, String contactWay) {
        userMapper.updateInfoModify(userName,contactPerson,contactWay);
    }

    /**
     * 创建电信侧账号时判断该区域下是否已经存在账号
     * @param user 用户
     * @return true 存在 false 不存在
     * @author kangyanxiang 
     * @date 2015年12月1日 上午11:39:09
     */
    public boolean isAccountExit(User user) {
        Integer userType=user.getUserType();
        if(userType==2){
            int count=userMapper.isProvinceAccountExit(user.getProvinceId());
            if(count>0){
                return true;
            }
        }
        else if(userType==3){
            int count=userMapper.isCityAccountExit(user.getCityId());
            if(count>0){
                return true;
            }
        }
        else if(userType==4){
            int count=userMapper.isAreaAccountExit(user.getAreaId());
            if(count>0){
                return true;
            }
        }
        else if(userType==6){
            int count=userMapper.isGroupExit();//判断集团管理员是否存在
            if(count>0){
                return true;
            }
        }
        return false;
    }

    /**
     * 分配权限 --- old
     * @param id 主键
     * @param permissionIds 权限ids
     * @author kangyanxiang 
     * @date 2015年12月14日 下午4:22:45
     */
    public void assign(Long id, String permissionIds) {
        this.removeOldPermission(id);// 移除旧的账号权限关系
        if (StringUtils.isNotBlank(permissionIds)) {//新的权限不为空
            String[] permissionids = permissionIds.split(",");
            int maxLength = permissionids.length;
            if (maxLength > 0) {
                for (int i = 0; i < maxLength; i++) {
                    Long permissionid = Long.parseLong(permissionids[i]);
                    this.addPermission(id, permissionid);
                }
            }
        }
    } 
    
    /**
     * 分配权限 -- 新
     * @param id 账号id
     * @param permissionIds 权限id
     * @throws Exception 异常
     * @author kangyanxiang 
     * @date 2016年5月31日 下午5:16:15
     */
    public void assigna(Long id, String permissionIds) throws Exception{
        long beginTime = System.currentTimeMillis();
        List<Long> accountIds = null;//getAccountIds(id);获取下级ID
        logger.debug("提示： 获取下级共花费了 " + (System.currentTimeMillis() - beginTime) + " ms.");
        String oldPermission = getPermissionIdsById(id);
        //logger.debug("旧permissionIds:"+oldPermission);
        //logger.debug("新permissionIds:"+permissionIds);
        List<String> oldPermissionList = null;
        List<String> newPermissionList = null;
        if (StringUtils.isBlank(oldPermission) && StringUtils.isNotBlank(permissionIds)) {// 都是新增的权限
            newPermissionList = new ArrayList<String>(Arrays.asList(permissionIds.split(",")));
        } else if(StringUtils.isNotBlank(oldPermission) && StringUtils.isBlank(permissionIds)){
            oldPermissionList = new ArrayList<String>(Arrays.asList(oldPermission.split(",")));
        }else if(StringUtils.isNotBlank(permissionIds) && StringUtils.isNotBlank(permissionIds)){
            String[] oldPermissionIds = oldPermission.split(",");
            String[] newPermissionIds = permissionIds.split(",");
            HashSet<String> samePermission = new HashSet<String>();
            for (String a : oldPermissionIds) {
                for (String b : newPermissionIds) {
                     if (a.equals(b)) {
                        samePermission.add(a);
                    }
                }
            }
            //logger.debug("+samePermission:" + samePermission.toString());
            oldPermissionList = new ArrayList<String>(Arrays.asList(oldPermissionIds));
            newPermissionList = new ArrayList<String>(Arrays.asList(newPermissionIds));
            Iterator it = samePermission.iterator();
            while (it.hasNext()) {
                String permission = (String) it.next();
                oldPermissionList.remove(permission);
                newPermissionList.remove(permission);
            }
        }
        //logger.debug("+++++删除的"+listToString(oldPermissionList,','));
        //logger.debug("+++++新增的"+listToString(newPermissionList,','));
        //logger.debug("提示： 权限筛选共花费了 " + (System.currentTimeMillis() - beginTime) + " ms.");
        //logger.debug("+++++删除的list"+oldPermissionList.size());
        if(oldPermissionList != null && !oldPermissionList.isEmpty()){
            //permissionMapper.deletePermission(listToString(oldPermissionList,','),listToString(accountIds,','));
            for(String o:oldPermissionList) {
                for(Long i:accountIds){
                    permissionMapper.delete(o,i);
                }
            }
        }
        if(newPermissionList != null && !newPermissionList.isEmpty()){
            for (String z : newPermissionList) {
                permissionMapper.addPermission(id, Long.valueOf(z));
            }
        }
        logger.debug("提示： 权限新建共花费了 " + (System.currentTimeMillis() - beginTime) + " ms.");
    }
    
    

    /**
     * 批量添加客户赋权限
     * @param userId 账号id
     * @author kangyanxiang 
     * @date 2016年2月29日 下午2:57:41
     */
    public void permissionAssign(Long userId) {
        String[] permissionids= new String[]{"6","7","8","12","13","14","15","16","17","18","19","20","21","23","24","25","32","33","34",
                                             "35","36","37","38","40","41","42","43","49","50","55","56","57","58","59","60",
                                             "61","64","65","66","67","68","69","86","87","88","89","90","91","92","93","94","97","98",
                                             "99","100","101","102","103","104","105","112","113","114","115","116","117","118"};
        int maxLength = permissionids.length;
        for (int i = 0; i < maxLength; i++) {
            Long permissionid = Long.parseLong(permissionids[i]);
            addPermission(userId, permissionid);
        }
    }
    
    /**
     * 添加账号和客户的关系 添加权限
     * @param userName 账号
     * @param customerId 客户id
     * @param projectId 项目id
     * @author kangyanxiang 
     * @date 2016年2月29日 下午3:22:17
     */
    public void insert(String userName, Long customerId, String projectId) {
        User user=new User();
        String password=SysConfigUtil.getDefaultPassword();
        user.setPassword(password);
        user.setUserName(userName);
        user.setUserType(5);
        user.setFkProjectId(Long.valueOf(projectId));
        userMapper.insert(user);
        Long id=user.getId();//返回插入的账号主键id
        roleService.authorize(id,5);//授权，关联用户表和角色表
        userMapper.userCustomer(id,customerId);//关联账号和客户的关系
        permissionAssign(id);
    }

    /**
     * 根据账号查找客户
     * @param userName 账号
     * @return 客户id
     * @author kangyanxiang 
     * @date 2016年7月5日 下午6:02:19
     */
    public Long findUserByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    /**
     * 查看账号和手机号是否匹配
     * @param userName 账号
     * @param cellphone 手机号
     * @return true 匹配 false 不匹配
     * @author kangyanxiang 
     * @date 2016年7月6日 上午9:42:02
     */
    public Long match(String userName, String cellphone) {
        return userMapper.match(userName,cellphone);
    }
    
    /**
     * 通过账号名称查找账号id和客户id
     * @param userName 账号名
     * @return user
     * @author kangyanxiang 
     * @date 2016年7月7日 上午10:41:08
     */
    public User match(String userName){
        return userMapper.getUserByUserName(userName);
    }

    /**
     * 通过主键查找账号名称
     * @param accountId 主键
     * @return 账号名称
     * @author kangyanxiang 
     * @date 2016年7月18日 上午9:23:41
     */
    public String getNameById(String accountId) {
        return userMapper.getNameById(Long.parseLong(accountId));
    }

}