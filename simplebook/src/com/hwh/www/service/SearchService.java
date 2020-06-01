package com.hwh.www.service;

import com.hwh.www.po.User;
import com.hwh.www.po.WenZhang;

import java.util.List;

public interface SearchService {
    /*搜索文章*/
    public List<WenZhang> findWz(String str);
    /*搜索用户*/
    public List<User> findUser(String str);
}
