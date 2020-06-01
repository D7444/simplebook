package com.hwh.www.service;

import com.hwh.www.dao.NoticeDao;
import com.hwh.www.dao.NoticeDaoImpl;
import com.hwh.www.dao.UserDao;
import com.hwh.www.dao.UserDaoImpl;
import com.hwh.www.po.Notice;
import com.hwh.www.until.TimeUntil;

import java.util.List;

public class NoticeServiceImpl implements NoticeService {
    /*获取发起的消息*/
    @Override
    public List<Notice> getNotice(int id){
        NoticeDao noticeDao = new NoticeDaoImpl();
        UserDao userDao = new UserDaoImpl();
        List<Notice> noticeList = noticeDao.getData(id);
        //获取邀请昵称
        for(Notice notice:noticeList){
            notice.setUname(userDao.findById(notice.getInvite()).getUname());
        }
        return noticeList;
    }

    /*获取受到邀请的消息*/
    @Override
    public List<Notice> getBeNotice(int invite){
        NoticeDao noticeDao = new NoticeDaoImpl();
        UserDao userDao = new UserDaoImpl();
        List<Notice> noticeList = noticeDao.getBeData(invite);
        //获取邀请昵称
        for(Notice notice:noticeList){
            notice.setUname(userDao.findById(notice.getId()).getUname());
        }
        return noticeList;
    }

    /*发起新消息*/
    @Override
    public void addNotice(int id,int invite,String content){
        NoticeDao noticeDao = new NoticeDaoImpl();
        noticeDao.add(id,invite,content, TimeUntil.getNowTime());
    }

    /*删除新消息*/
    @Override
    public void deleteNotice(int noticeId){
        NoticeDao noticeDao = new NoticeDaoImpl();
        noticeDao.delete(noticeId);
    }

    /*更新消息*/
    @Override
    public void update(int noticeId,String choice){
        NoticeDao noticeDao = new NoticeDaoImpl();
        noticeDao.update(noticeId,choice);
    }
}
