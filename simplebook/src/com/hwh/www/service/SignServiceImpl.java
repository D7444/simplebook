package com.hwh.www.service;

import com.hwh.www.bean.Dynamic;
import com.hwh.www.dao.*;
import com.hwh.www.po.*;
import com.hwh.www.until.EmailUntil;
import com.hwh.www.until.EncryptionUtil;
import com.hwh.www.until.ImageUtil;
import com.hwh.www.until.TimeUntil;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignServiceImpl implements SignService{
    /*登录判断*/
    @Override
    public String loginjudge(String email, String password, HttpSession session){
        UserDao userDao = new UserDaoImpl();
        List<User> userList = userDao.loadData();
        for(User user:userList){
            //判断邮箱和密码是否匹对(与加密后密码匹对)
            if(user.getEmail().equals(email) && user.getPassword().equals(EncryptionUtil.encryption(password))){
                if(user.getBan()!=null) {
                    if (TimeUntil.compareNowTime(user.getBan())) {
                        return "该账号已被封禁，解封日期:" + user.getBan();
                    }
                }
                if(session!=null) {
                    session.setAttribute("ownUser",user);
                }
                return "success";
            }
        }
        return "密码错误或账号不存在";
    }

    /*判断是否是邮箱地址*/
    private static Pattern p = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    @Override
    public boolean judgeEmail(String email){
        Matcher matcher = p.matcher(email);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    /*注册新用户*/
    @Override
    public String registerUser(String uname,String email,String password){
        System.out.println(email);
        if(!judgeEmail(email)){
            return "邮箱格式错误";
        }
        UserDao userDao = new UserDaoImpl();
        List<User> userList = userDao.loadData();
        for(User user:userList){
            if(user.getEmail().equals(email)){
                return "邮箱已被注册";
            }
            if(user.getUname().equals(uname)){
                return "昵称已被使用";
            }
        }

        //注册新账号
        User user = userDao.findById(0);
        user.setEmail(email);
        user.setUname(uname);
        //加密密码
        user.setPassword(EncryptionUtil.encryption(password));
        user.setFan(0);
        user.setSub(0);
        user.setLove(0);
        userDao.insert(user);

        return "success";

    }

    /*发送找回密码的验证码*/
    @Override
    public String sendBackCode(String email) {
        //创建随机验证码，仅在不关闭页面情况下有效
        Random random = new Random();
        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuffer buffer = new StringBuffer();
        int number;
        for (int i = 0; i < 5; i++) {
            number = random.nextInt(ch.length);
            buffer.append(ch[number]);
        }
        String backCode = String.valueOf(buffer).toUpperCase();
        try {
            EmailUntil.sendEmail(email, "验证码：" + backCode, "找回密码操作");
        }catch (Exception e){
            e.printStackTrace();
        }
        return backCode;
    }

    /*修改密码*/
    @Override
    public void changePassword(String email,String password){
        UserDao userDao = new UserDaoImpl();
        //修改密码(加密)
        userDao.changePassword(email,EncryptionUtil.encryption(password));
    }

    /*查询用户*/
    @Override
    public User findUser(int id){
        UserDao userDao = new UserDaoImpl();
        return userDao.findById(id);
    }

    /*上传头像*/
    @Override
    public void updateImage(int id,String path){
        //读取本地照片获取图片流
        InputStream input = ImageUtil.readImage(path);
        //更新数据库
        UserDao userDao = new UserDaoImpl();
        userDao.updatePhoto(input,id);
    }

    /*修改信息*/
    @Override
    public String updateUser(int id,String uname,String email){
        UserDao userDao = new UserDaoImpl();
        if(!judgeEmail(email)){
            return "邮箱格式错误";
        }
        List<User> userList = userDao.loadData();
        //判断邮箱是否修改，并且是否重复
        User user = userDao.findById(id);
        for(User user1:userList){
            if(uname.equals(user1.getUname())){
                if(id != user1.getId()) {
                    return "昵称已被使用";
                }
            }
            if(email.equals(user1.getEmail()) && !email.equals(user.getEmail())){
                if(id != user1.getId()) {
                    return "邮箱重复";
                }
            }
        }
        user.setUname(uname);
        user.setEmail(email);
        userDao.update(user);
        return "修改成功";
    }

    /*获取黑名单*/
    @Override
    public List<Black> getBlack(int id){
        BlackDao blackDao = new BlackDaoImpl();
        UserDao userDao = new UserDaoImpl();
        //获得原生列表
        List<Black> blackList = blackDao.getBlack(id);
        //获取黑名单昵称
        for(Black black:blackList){
            black.setUname(userDao.findById(black.getBeid()).getUname());
        }
        return blackList;
    }

    /*加入黑名单*/
    @Override
    public void addBlack(int id,int beid){
        //加入黑名单
        BlackDao blackDao = new BlackDaoImpl();
        blackDao.add(id,beid);


        FollowService followService = new FollowServiceImpl();
        //判断是否有关注关系
        for(User user:followService.getFan(id)){
            if(user.getId() == beid){
                System.out.println("有");
                //去除关注的关系
                followService.cancelSub(beid,id);
            }
        }


    }

    /*移出黑名单1*/
    @Override
    public void deleteBlack(int blackId){
        BlackDao blackDao = new BlackDaoImpl();
        blackDao.delete(blackId);
    }

    /*移出黑名单2*/
    @Override
    public void deleteBlack(int id,int beid){
        BlackDao blackDao = new BlackDaoImpl();
        blackDao.delete(id, beid);
    }

    /*判断(beid)是否处于(id)黑名单*/
    @Override
    public boolean judgeBlack(int id,int beid){
        BlackDao blackDao = new BlackDaoImpl();
        List<Black> blackList = blackDao.getBlack(id);
        //判断是否存在，存在返回true
        for(Black black:blackList){
            if(black.getBeid()==beid){
                return true;
            }
        }
        return false;
    }

    /*获取按喜欢度排列的用户*/
    @Override
    public List<User> getLove(){
        UserDao userDao = new UserDaoImpl();
        return userDao.getLove();
    }

    /*封禁账号*/
    @Override
    public String banUser(int id,int banChoice){
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findById(id);
        String time = null;
        //判断封禁时间是否存在,如果存在，判断日期是否已过
        if(user.getBan() != null && TimeUntil.compareTime(user.getBan(),TimeUntil.getNowTime())){
            time = TimeUntil.addTime(user.getBan(),banChoice);
        }
        else {
            time = TimeUntil.addTime(TimeUntil.getNowTime(),banChoice);
        }
        System.out.println(time);
        user.setBan(time);
        userDao.update(user);
        return time;
    }

    /*取消封禁*/
    @Override
    public void cancelUser(int id){
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findById(id);
        user.setBan(null);
        userDao.update(user);
    }

    /*用户动态*/
    @Override
    public List<Dynamic> getDynamic(int id){
        //接口
        WenZhangService wenZhangService = new WenZhangServiceImpl();
        UserDao userDao = new UserDaoImpl();
        FollowDao followDao = new FollowDaoImpl();
        DianZanDao dianZanDao = new DianZanDaoImpl();
        PingLunDao pingLunDao = new PingLunDaoImpl();
        List<Dynamic> dynamicList = new ArrayList<>();

        //获取名字
        String uname = userDao.findById(id).getUname();

        /*将所有数据放进列表*/
        Dynamic dynamic = null;
        //关注
        for(Follow follow:followDao.findData(id)){
            dynamic = new Dynamic();
            dynamic.setUname(uname);
            dynamic.setId(id);
            //获取订阅时间
            dynamic.setTime(follow.getTime());
            //获取用户信息
            dynamic.setFollowUser(userDao.findById(follow.getBesub()));
            dynamicList.add(dynamic);
        }

        //文章
        for(WenZhang wenZhang:wenZhangService.findMore(id)){
            dynamic = new Dynamic();
            dynamic.setUname(uname);
            dynamic.setId(id);
            dynamic.setTime(wenZhang.getTime());
            dynamic.setWenZhang(wenZhang);
            dynamicList.add(dynamic);
        }

        //评论+评论的文章
        for(PingLun pingLun:pingLunDao.foundPl(id)){
            dynamic = new Dynamic();
            dynamic.setUname(uname);
            dynamic.setId(id);
            dynamic.setTime(pingLun.getTime());
            dynamic.setPingLun(pingLun);
            //获取被评论的文章
            dynamic.setWenZhang(wenZhangService.findWz(pingLun.getWzid()));
            dynamicList.add(dynamic);
        }

        //点赞+点赞的文章
        for(DianZan dianZan:dianZanDao.getUserDz(id)){
            //去除获取除文章外的点赞
            if(dianZan.getPlid()!=0){
                continue;
            }
            dynamic = new Dynamic();
            dynamic.setUname(uname);
            dynamic.setId(id);
            dynamic.setTime(dianZan.getTime());
            dynamic.setDianZan(dianZan);
            //获取被点赞的文章
            dynamic.setWenZhang(wenZhangService.findWz(dianZan.getWzid()));
            dynamicList.add(dynamic);
        }

        /*按照时间进行排序*/
        Collections.sort(dynamicList, new Comparator<Dynamic>() {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            @Override
            public int compare(Dynamic o1, Dynamic o2) {
                try{
                    Date dateD1 = dateFormat.parse(o1.getTime());
                    Date dateD2 = dateFormat.parse(o2.getTime());
                    if(dateD1.getTime() > dateD2.getTime()){
                        return -1;//大的放前面
                    }
                    else {
                        return 1;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        //返回排序后的列表
        return dynamicList;
    }


}
