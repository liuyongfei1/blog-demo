package com.fullstackboy.designpatterns.observer.notuse;

import java.util.Date;

/**
 * 定义摇号结果
 *
 * @author Liuyongfei
 * @date 2022/3/4 16:54
 */
public class LotteryResult {

    public LotteryResult(String uId, String msg, Date dateTime) {
        this.uId = uId;
        this.msg = msg;
        this.dateTime = dateTime;
    }

    /**
     * 用户id
     */
    private String uId;

    /**
     * 摇号信息
     */
    private String msg;

    /**
     * 业务时间
     */
    private Date dateTime;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
