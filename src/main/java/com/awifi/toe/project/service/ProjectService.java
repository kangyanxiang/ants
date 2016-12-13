package com.awifi.toe.project.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.awifi.toe.account.service.AccountService;
import com.awifi.toe.auth.mapper.UserMapper;
import com.awifi.toe.base.model.Page;
import com.awifi.toe.base.util.CastUtil;
import com.awifi.toe.base.util.SessionUtil;
import com.awifi.toe.project.mapper.ProjectMapper;
import com.awifi.toe.project.model.Project;
import com.awifi.toe.system.mapper.LocationMapper;
import com.awifi.toe.system.util.SysConfigUtil;

/**   
 * @Description:  项目维护业务层
 * @Title: ProjectService.java 
 * @Package com.awifi.toe.customer.service 
 * @author 亢燕翔 
 * @date 2015年11月23日 下午5:04:40
 * @version V1.0   
 */
@SuppressWarnings({ "unchecked", "rawtypes"})
@Service("projectService")
public class ProjectService {
    
    /**用户*/
    @Resource(name = "userMapper")
    private UserMapper userMapper;
    
    /**账号业务层*/
    @Resource(name="accountService")
    private AccountService accountService;
    
    /** 项目维护持久层 */
    @Resource(name = "projectMapper")
    private ProjectMapper projectMapper;
    
    /** 地区持久层  */
    @Resource(name = "locationMapper")
    private LocationMapper locationMapper;
    
    /**
     * 项目列表
     * @param page 分页实体
     * @param keywords 查询关键字
     * @param provinceId 省 
     * @param cityId 市
     * @param areaId 区县
     * @author kangyanxiang 
     * @date 2016年10月23日 上午10:38:49
     */
    public void pageQuery(Page page, String keywords, String provinceId, String cityId, String areaId) {
        if (page.getPageSize() == null) {
            page.setPageSize(Integer.parseInt(SysConfigUtil.getParamValue("page.pageSize")));
        }
        Map<String,String> rojhtMap = accountService.getRojhtMap(SessionUtil.getCurUserId(),provinceId,cityId,areaId);
        int totalRecord = projectMapper.pageCount(keywords,rojhtMap.get("provinceId"),rojhtMap.get("cityId"),rojhtMap.get("areaId"),rojhtMap.get("projectIds"));
        page.setTotalRecord(totalRecord);
        if (totalRecord > 0) {
            List<Project> projectList = projectMapper.pageQuery(page.getBegin(),page.getPageSize(),keywords,rojhtMap.get("provinceId"),rojhtMap.get("cityId"),rojhtMap.get("areaId"),rojhtMap.get("projectIds"));
            for(Project project : projectList){
                project.setCustomerCount(userMapper.getCustomerCount(project.getProjectId()));
                if(null != project.getCountyId()){
                    project.setLocationFullName(locationMapper.getLocationFullName(project.getCountyId().toString()));
                    continue;
                }
                if(null != project.getCityId()){
                    project.setLocationFullName(locationMapper.getLocationFullName(project.getCityId().toString()));
                    continue;
                }
                if(null != project.getProvinceId()){
                    project.setLocationFullName(locationMapper.getLocationFullName(project.getProvinceId().toString()));
                    continue;
                }
            }
            page.setRecords(projectList);
        }
    }
    
    /**
     * 校验项目名称是否重复
     * @param projectName 项目名称
     * @return count
     * @author 亢燕翔 
     * @date 2016年1月5日 下午3:32:30
     */
    public int eqAddRepeat(String projectName) {
        return projectMapper.eqAddRepeat(projectName);
    }
    
    /**
     * 创建项目
     * @param projectName 项目名称
     * @param contact 联系人
     * @param contactWay 联系方式
     * @param remark 备注
     * @author 亢燕翔 
     * @param areaId 
     * @param cityId 
     * @param provinceId 
     * @date 2015年12月10日 下午2:12:50
     */
    public void add(String projectName,String contact,String contactWay,String remark,String provinceId,String cityId,String areaId) {
        projectMapper.add(projectName,contact,contactWay,remark,provinceId,StringUtils.isNotBlank(cityId) ? cityId : null,StringUtils.isNotBlank(areaId) ? areaId : null);
    }

    /**
     * 项目名称重复性校验（编辑）
     * @param projectName 项目名称
     * @param id 项目ID
     * @return count
     * @author 亢燕翔 
     * @date 2016年1月5日 下午3:42:09
     */
    public int eqEditRepeat(String projectName, String id) {
        return projectMapper.eqEditRepeat(projectName,id);
    }
    
    /**
     * 编辑项目
     * @param id 项目ID
     * @param projectName 项目名称
     * @param contact 联系人
     * @param contactWay 联系方式
     * @param remark 备注
     * @param provinceId 
     * @param cityId 
     * @param areaId 
     * @author 亢燕翔 
     * @date 2015年12月8日 下午2:53:19
     */
    public void edit(String id,String projectName,String contact,String contactWay,String remark,String provinceId,String cityId,String areaId) {
        projectMapper.edit(id,projectName,contact,contactWay,remark,provinceId,StringUtils.isNotBlank(cityId)?cityId:null,StringUtils.isNotBlank(areaId)?areaId:null);
    }

    /**
     * 删除项目
     * @param id 项目ID
     * @author 亢燕翔 
     * @return message
     * @date 2015年12月8日 下午2:37:57
     */
    public String delete(String id) {
        int count = projectMapper.queryCount(id);
        if(count >=1){
            return "该项目已经关联客户，不允许删除！";
        }
        projectMapper.delete(id);
        return "删除成功";
    }

    /**
     * 项目详情
     * @param id 项目ID
     * @return null
     * @author 亢燕翔 
     * @throws Exception 
     * @date 2015年12月8日 下午2:46:22
     */
    public Project projectShow(String id) throws Exception {
        return projectMapper.projectShow(id);
    }
    
    /**
     * 项目下拉列表
     * @return list
     * @author kangyanxiang 
     * @date Oct 31, 2016 10:04:44 AM
     */
    public List<Project> getProjectList() {
        Map<String,String> rojhtMap = accountService.getRojhtMap(SessionUtil.getCurUserId(),null,null,null);
        return projectMapper.getProjectList(rojhtMap.get("provinceId"),rojhtMap.get("cityId"),rojhtMap.get("areaId"),rojhtMap.get("projectIds"));
    }
    
    
    
    
    
    
    
    
    
    
    
    //--------------------------------------------------------------------------------
    
    
    
    
    
    /**
     * 通过项目ID获取项目名称
     * @param projectId 项目ID
     * @return ProjectName
     * @author 亢燕翔 
     * @date 2015年12月4日 上午9:58:19
     */
    public String getProjectNameById(Long projectId) {
        return projectMapper.getProjectNameById(projectId);
    }
    
    /**
     * 通过项目IDs查找项目名称
     * @param projectIds 项目id
     * @return 项目名称
     * @author 许小满  
     * @date 2016年8月24日 下午7:35:39
     */
    public String getProjectNamesByIds(String projectIds){
        //参数格式化
        if(StringUtils.isBlank(projectIds)){
            return StringUtils.EMPTY;
        }
        Long[] projectIdArray = CastUtil.toLongArray(projectIds.split(","));
        //获取并处理项目名称
        List<String> projectNameList = projectMapper.getProjectNamesByIds(projectIdArray);
        return CastUtil.toString(projectNameList);
    }

}