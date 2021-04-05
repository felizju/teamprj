package com.isechotel.reservation.cli.hotel.domain;

// 역할 : 이 클래스는 하나의 객실 정보를 저장
public class Hotel {

    private int roomNumber;
    private String roomName;
    private int persons;
    private int charge;
    private String checkInDate;
    private String checkOutDate;
    private int reservationNumber;
    private boolean reservation; // 객실 예약 여부
    private String userName;
    private String userPhoneNumber;

    // 생성자
    // persons, checkInDate, checkOutDate, userName, userPhoneNumber


    @Override
    public String toString() {
        return "Hotel{" +
                "roomNumber=" + roomNumber +
                ", roomName='" + roomName + '\'' +
                ", persons=" + persons +
                ", charge=" + charge +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", reservationNumber=" + reservationNumber +
                ", reservation=" + reservation +
                ", userName='" + userName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                '}';
    }

    public Hotel(int roomNumber, String roomName, int persons, int charge) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.persons = persons;
        this.charge = charge;
    }

    public Hotel(int persons, String checkInDate, String checkOutDate, String userName, String userPhoneNumber) {
        this.persons = persons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public boolean isReservation(boolean flag) {
        this.reservation = flag;
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
}
