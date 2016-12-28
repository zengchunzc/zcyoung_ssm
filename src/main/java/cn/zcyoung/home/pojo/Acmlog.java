package cn.zcyoung.home.pojo;

import java.util.Date;

public class Acmlog {
    private Integer id;

    private Integer state;

    private String no;

    private String contestname;

    private String mess;

    private Integer score;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getContestname() {
        return contestname;
    }

    public void setContestname(String contestname) {
        this.contestname = contestname == null ? null : contestname.trim();
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess == null ? null : mess.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}