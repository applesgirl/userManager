package cn.cyp.bean;

import java.util.List;

/**
 * @Author:cyp
 * @Date:2019/8/21 21:22
 * @Desc:
 */
public class pageBean {
    //    当前页
    private int pageNum = 1;

    //    每页显示多少条
    private int pageSize = 5;

    //    总条数
    private int totalCounts;

    //    总页码 (totalCounts%pageSize)==0?totalCounts/pageSize:totalCounts/pageSize+1
    private int totalPages;

    //    显示的数据list<customer>
    private List<Customer> list;

    //    起始页(pageNum-1)*pageSize
    private int startIndex;

    //    上一页
    private int beforePage;

    //    下一页
    private int nextPage;

    //      分页栏
    private int pageBar[];

    /**
     * @return 总页码的方法
     */
    public int getTotalPages() {
        this.totalPages = (totalCounts % pageSize) == 0 ? totalCounts / pageSize : totalCounts / pageSize + 1;
        return this.totalPages;
    }

    /**
     * @return 起始页的方法
     */
    public int getStartIndex() {
        return this.startIndex = (pageNum - 1) * pageSize;
    }

    /**
     * @return 上一页
     */

    public int getBeforePage() {
        this.beforePage = this.pageNum - 1;
        if (beforePage <= 0) {
            beforePage = 1;
        }
        return this.beforePage;
    }

    /**
     * @return 下一页的方法
     */
    public int getNextPage() {
        this.nextPage = pageNum + 1;
        if (nextPage >= this.totalPages) {
            nextPage = this.totalPages;
        }
        return nextPage;
    }

    /**
     * @return 分页栏
     */

    public int[] getPageBar() {
//        仿照百度中前五后四的原则
//        12345[6]78910  6 7 8 9 10【11】12 13 14 15
//        定义变量
//        起始页
        int beginPage;
//        定义尾页
        int endPage;
//        进行判断，如果总页码<=10,不满足前五后四的原则
        if (this.totalPages <= 10) {
            beginPage = 1;
            endPage = this.totalPages;
//            大于10的时候
        } else {
            beginPage = this.pageNum - 5;//11-5=6
            endPage = this.pageNum + 4;//11+4=15
//            排除小于0大于总页码
            if (beginPage <= 0) {
                beginPage = 1;
                endPage = beginPage + 9;//1+9=10
            }
            if (endPage >= this.totalPages) {
                endPage = this.totalPages;
                beginPage = this.totalPages - 9;//15-9=6
            }
        }
        this.pageBar=new int[endPage-beginPage+1];//保证总共为10
        int index=0;
        for (int i=beginPage;i<=endPage;i++){
            this.pageBar[index++]=i;
        }
        return this.pageBar;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Customer> getList() {
        return list;
    }

    public void setList(List<Customer> list) {
        this.list = list;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setBeforePage(int beforePage) {
        this.beforePage = beforePage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public void setPageBar(int[] pageBar) {
        this.pageBar = pageBar;
    }
}
