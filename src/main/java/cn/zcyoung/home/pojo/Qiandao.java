package cn.zcyoung.home.pojo;

import java.util.Date;

public class Qiandao {
    private Integer id;

    private String phoneno;

    private String phonepw;

    private String name;

    private Integer qtime;

    private Date rTime;

    private Date preTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getPhonepw() {
        return phonepw;
    }

    public void setPhonepw(String phonepw) {
        this.phonepw = phonepw == null ? null : phonepw.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getQtime() {
        return qtime;
    }

    public void setQtime(Integer qtime) {
        this.qtime = qtime;
    }

    public Date getrTime() {
        return rTime;
    }

    public void setrTime(Date rTime) {
        this.rTime = rTime;
    }

    public Date getPreTime() {
        return preTime;
    }

    public void setPreTime(Date preTime) {
        this.preTime = preTime;
    }
}