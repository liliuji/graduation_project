package com.domain;

import java.util.Date;

public class User {

    private Integer userId;

    private String account;

    private String  userType;

    private String  password;

    private String  userName;

    private String  card;

    private String sex;

    private String  age;

    private Integer userTel;

    private String  createDate;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Integer getUserTel() {
        return userTel;
    }

    public void setUserTel(Integer userTel) {
        this.userTel = userTel;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", card=" + card
               + ", userType=" + userType + ", userTel=" + userTel + ", createDate=" + createDate + "]";
    }

    public User(Integer userId, String userName, String password, String card, String userType, Integer userTel,
                String createDate){
        super();
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.card = card;
        this.userType = userType;
        this.userTel = userTel;
        this.createDate = createDate;
    }

    public User(){
        super();
        // TODO Auto-generated constructor stub
    }

}
