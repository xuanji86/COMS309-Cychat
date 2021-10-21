package com.example.CyChat;

public class Friends {
    private Integer friendId;
    private String fISU_Email;
    private String first_Name;
    private String last_Name;
    private String imgurl;
    private String groupName;
    private String fNickName;

    public Friends(Integer friendId, String fISU_Email, String first_Name, String last_Name, String groupName, String fNickName){
        this.friendId = friendId;
        this.fISU_Email = fISU_Email;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.groupName = groupName;
        this.fNickName = fNickName;
    }

    public String getImgurl(){
        return imgurl;
    }

    public void setImgurl(String imgurl){
        this.imgurl = imgurl;
    }

    public Integer getFriendId(){
        return friendId;
    }

    public void setFriendId(Integer friendId){
        this.friendId = friendId;
    }

    public String getfISU_Email(){
        return fISU_Email;
    }

    public void setfISU_Email(String fISU_Email){
        this.fISU_Email = fISU_Email;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getFullName(){
        return ""+ getFirst_Name() + " " + getLast_Name();
    }

    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }

    public void setfNickName(String nickName){
        this.fNickName = nickName;
    }

    public String getfNickName() {
        return fNickName;
    }
}

