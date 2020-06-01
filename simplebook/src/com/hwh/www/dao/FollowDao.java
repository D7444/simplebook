package com.hwh.www.dao;

import com.hwh.www.po.Follow;

import java.util.List;

public interface FollowDao {
    /*查询关注*/
    public List<Follow> findData(int id);
    /*查询粉丝*/
    public List<Follow> findBeData(int besub);
    /*增加*/
    public void add(Follow follow);
    /*删除*/
    public void delete(Follow follow);

}
