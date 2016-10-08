package cn.zcyoung.home.pojo;

import java.util.Date;

public class Mailyz {
    private Integer id;

    private Integer userId;

    private String code;

    private Date time;

    private Long timelimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(Long timelimit) {
        this.timelimit = timelimit;
    }
}