package com.hwh.www.dao;

import com.hwh.www.po.Group;

import java.util.List;

public interface GroupDao {
    /*获取用户自定义列表*/
    public List<Group> getGroup(int id);

    /*寻找*/
    public Group findById(int groupId);

    /*增加自定义列表*/
    public void add(int id,String group);

    /*删除自定义列表*/
    public void delete(int groupId);

    /*更新列表*/
    public void update(int groupId,String group);
}
