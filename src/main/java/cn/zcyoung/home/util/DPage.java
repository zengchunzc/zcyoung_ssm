package cn.zcyoung.home.util;

import java.util.List;

public class DPage<E> {
    /** * 总共页数 */
    private int totalPages;
    /** * 总共记录数 */
    private int totalRecords;
    /** * 每页显示数量 */
    private int pageSize;
    /** * 当前页 */
    private int pageIndex;
    /** * 当前页数据集合 */
    private List<E> datas;


    //根据总数目和每页数量，计算页面数
    public void calTotalPages(){
    	totalPages=totalRecords/pageSize;
    	if(totalRecords%pageSize!=0){
    		totalPages++;
    	}
    }
    
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
        calTotalPages();
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setDatas(List<E> datas) {
        this.datas = datas;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public List<E> getDatas() {
        return datas;
    }
}
