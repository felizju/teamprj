
package com.isechotel.reservation.cli.user.domain;

public class User {

    private static int sequence;

    private int userNumber;
    private String userName;
    private String phone;
    private String region;
    private int resNumber;
    private int totalCharge;

    public int getResNumber() {
        return resNumber;
    }

    public void setResNumber(int resNumber) {
        this.resNumber = resNumber;
    }

    public User(String userName, String phone, String region) {
        this.userNumber = ++sequence;
        this.userName = userName;
        this.phone = phone;
        this.region = region;
    }

    public User(String userName, String region) {
        this.userNumber = ++sequence;
        this.userName = userName;
        this.phone = phone;
        this.region = region;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNumber=" + userNumber +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

}


/*
package com.isechotel.reservation.cli.user.domain;

public class User {

    private static int sequence;

    private int userNumber;
    private String userName;
    private String phone;
    private int totalCharge;

    public User(String userName, String phone) {
        this.userNumber = ++sequence;
        this.userName = userName;
        this.phone = phone;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "User{" +
                "userNumber=" + userNumber +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
*/
