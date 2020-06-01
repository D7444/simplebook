package com.hwh.www.po;

public class Group {
    //组主键
    private int groupId;

    //用户id
    private int id;

    //用户自定义的组名
    private String group;

    /*方法*/

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
