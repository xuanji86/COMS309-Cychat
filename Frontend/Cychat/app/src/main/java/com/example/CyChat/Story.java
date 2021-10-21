package com.example.CyChat;

public class Story {
    private String imgurl;
    private String textArea;
    private String ISU_Email;
    private String first_Name;
    private String Last_Name;
    private int SID;

    public Story(String imgurl,String textArea,String ISU_Email,String first_Name,String last_Name,Integer SID){
        this.imgurl = imgurl;
        this.textArea = textArea;
        this.ISU_Email = ISU_Email;
        this.first_Name = first_Name;
        this.Last_Name = last_Name;
        this.SID = SID;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTextArea() {
        return textArea;
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }
    public String getISU_Email(){
        return ISU_Email;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public String getFirst_Name() {
        return first_Name;
    }
    public Integer getSID(){
        return SID;
    }
    public String getFullName(){
        return getFirst_Name()+" "+getLast_Name();
    }
}
