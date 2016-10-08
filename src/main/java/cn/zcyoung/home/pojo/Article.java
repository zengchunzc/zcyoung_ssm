package cn.zcyoung.home.pojo;

import java.util.Date;

public class Article {
    private Integer id;

    private Integer userId;

    private String title;

    private Date wTime;

    private Date uTime;

    private Integer state;

    private Integer readpower;

    private Integer click;

    private String body;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getwTime() {
        return wTime;
    }

    public void setwTime(Date wTime) {
        this.wTime = wTime;
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getReadpower() {
        return readpower;
    }

    public void setReadpower(Integer readpower) {
        this.readpower = readpower;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }
}