package com.hwh.www.bean;

public class Page {
    //每页显示记录数
    private int everyCount = 4;

    //总记录数
    private int totalCount;
    //总页数
    private int totalPage;

    //当前页(设置)  记录点（返回）起点
    private int currentPage;

    //起始点
    private int beginIndex = 1;
    //是否有上一页
    private int prePage;
    //是否有下一页
    private int nextPage;



    /*方法*/

    public int getEveryCount() { return everyCount; }

    public int getTotalCount() { return totalCount; }

    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

    //获得起点数据
    public int getCurrentPage() {
        //返回数据点
        return (currentPage-1)*everyCount;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }



    //计算总页数
    public int getTotalPage() {

        if (totalCount % everyCount == 0) {
            totalPage = totalCount / everyCount;
            // 假设总数是51，不能够被5整除的，那么就有11页
        }
        else {
            totalPage = totalCount / everyCount + 1;
        }

        if(0==totalPage) {
            totalPage = 1;
        }

        return totalPage;
    }

    //下一页
    public int getNextPage() {
        if(currentPage == totalPage){
            return currentPage;
        }
        return currentPage + 1;
    }

    //上一页
    public int getPrePage() {
        if(currentPage == beginIndex){
            return currentPage;
        }
        return currentPage - 1;
    }


}
