package com.isechotel.reservation.cli.room.domain;

// 역할 : 이 클래스는 하나의 객실 정보를 저장
public class Room {

    private static int sequence;

    private int roomNumber;
    private String roomName;
    private int persons;
    private int charge;
    private boolean reservationFlag; // 객실 예약 여부
    private String rentUser;
    private int reservationNumber;

    // 생성자
    public Room(String roomName, int charge) {
        this.roomNumber = ++sequence;
        this.roomName = roomName;
        this.charge = charge;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getRentUser() {
        return rentUser;
    }

    public void setRentUser(String rentUser) {
        this.rentUser = rentUser;
    }

    public static int getSequence() {
        return sequence;
    }

    public static void setSequence(int sequence) {
        Room.sequence = sequence;
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

    public boolean isReservation() {
        return reservationFlag;
    }

    public void setReservation(boolean reservation) {
        this.reservationFlag = reservation;
    }

    @Override
    public String toString() {

        String reservationFlag = this.reservationFlag ? "대여중" : "대여가능";

        return  String.format(">>> 객실번호: %s, 객실명: %s, " + "객실요금: %d원, 대여상태: %s", roomNumber, roomName, charge, reservationFlag);

        /*return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomName='" + roomName + '\'' +
                ", persons=" + persons +
                ", charge=" + charge +
                ", reservation=" + reservationFlag +
                '}';*/
    }

}
