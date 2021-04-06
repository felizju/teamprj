package com.isechotel.reservation.cli.reservation.domain;

import com.isechotel.reservation.cli.room.domain.Room;
import com.isechotel.reservation.cli.user.domain.User;

import java.time.LocalDate;

public class Reservation {

    private static int sequence;

    private int resNumber; // 예약 번호
    private User user; // 고객 정보
    private Room room; // 객실 정보
    private LocalDate checkInDate; // 체크인 날짜
    private LocalDate checkOutDate; // 체크아웃 날짜
    private LocalDate overDay; // 연장 일 수
    private int overCharge; // 연장 금액
    private Status status; // 예약상태


    // 생성자
    public Reservation(User user, Room room) {
        this.user = user;
        this.room = room;
        this.resNumber = ++sequence;
        this.checkInDate = LocalDate.now();
        this.status = Status.IN;
        // 연장 일수 구현++
    }

    // getter & setter
    public int getResNumber() {
        return resNumber;
    }

    public void setResNumber(int resNumber) {
        this.resNumber = resNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getOverDay() {
        return overDay;
    }

    public void setOverDay(LocalDate overDay) {
        this.overDay = overDay;
    }

    public int getOverCharge() {
        return overCharge;
    }

    public void setOverCharge(int overCharge) {
        this.overCharge = overCharge;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "resNumber=" + resNumber +
                ", user=" + user +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", overDay=" + overDay +
                ", overCharge=" + overCharge +
                ", status=" + status +
                '}';
    }
}
