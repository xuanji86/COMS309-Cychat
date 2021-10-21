package com.example.CyChat;

public class AddRequest {
    private String friendId;
    private String friend_ISU_Email;
    private String first_Name;
    private String last_Name;
    private String imgurl;
    private String isOk;

    public AddRequest(String friendId, String friend_ISU_Email, String first_Name, String last_Name){
        this.friendId = friendId;
        this.friendId = friend_ISU_Email;
        this.first_Name = first_Name;
        this.last_Name =last_Name;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriend_ISU_Email(){
        return friend_ISU_Email;
    }

    public void setFriend_ISU_Email(String friend_ISU_Email) {
        this.friend_ISU_Email = friend_ISU_Email;
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
        return ""+getFirst_Name()+""+getLast_Name();
    }
}
