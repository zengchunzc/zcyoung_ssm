package cn.zcyoung.home.pojo;

public class UserWithBLOBs extends User {
    private String friendList;

    private String friendListTmp;

    public String getFriendList() {
        return friendList;
    }

    public void setFriendList(String friendList) {
        this.friendList = friendList == null ? null : friendList.trim();
    }

    public String getFriendListTmp() {
        return friendListTmp;
    }

    public void setFriendListTmp(String friendListTmp) {
        this.friendListTmp = friendListTmp == null ? null : friendListTmp.trim();
    }
}