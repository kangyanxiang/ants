package com.awifi.toe.base.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public class Page<T> {

    /** 页码，默认是第一页 */
    @NotNull(message = "{page.pageNo.null}")
    @Min(message = "{page.pageNo.min}", value = 1)
    private Integer pageNo;

    /** 每页显示的记录数 */
    private Integer pageSize;

    /** 开始记录数 */
    private Integer begin;

    /** 总记录数 */
    private Integer totalRecord;

    /** 总页数 */
    private Integer totalPage;

    /** 对应的当前页记录 */
    private List<T> records;

    public Page() {
        super();
        this.configBegin();
    }

    public Page(Integer pageNo) {
        super();
        this.pageNo = pageNo;
        this.configBegin();
    }

    public Page(Integer pageNo, Integer pageSize) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.configBegin();
    }

    /**
     * 配置begin
     * @author 许小满  
     * @date 2015年11月18日 下午7:19:35
     */
    private void configBegin() {
        if (pageNo != null && pageSize != null) {
            this.begin = (this.pageNo - 1) * this.pageSize;
        }
    }

    public Integer getPageNo() {
        return pageNo;
    }
    
    /**
     * 设置页码
     * @param pageNo 页码
     * @author 许小满  
     * @date 2015年11月18日 下午7:19:57
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        this.configBegin();
    }

    public Integer getPageSize() {
        return pageSize;
    }
    
    /**
     * 设置每页数量
     * @param pageSize 每页数量
     * @author 许小满  
     * @date 2015年11月18日 下午7:20:21
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        this.configBegin();
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    /**
     * 设置记录总数
     * @param totalRecord 记录总数
     * @author 许小满  
     * @date 2015年12月7日 下午3:23:47
     */
    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
        // 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
        Integer totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
        this.setTotalPage(totalPage);
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获取记录集
     * @return 记录集合
     * @author 许小满  
     * @date 2016年5月25日 下午3:26:17
     */
    public List<T> getRecords() {
        if(records == null){
            records = new ArrayList<T>(); 
        }
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Page [pageNo=").append(pageNo).append(", pageSize=").append(pageSize).append(", results=")
                .append(records).append(", totalPage=").append(totalPage).append(", totalRecord=").append(totalRecord)
                .append("]");
        return builder.toString();
    }
}