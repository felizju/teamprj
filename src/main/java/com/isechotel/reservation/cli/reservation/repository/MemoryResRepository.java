package com.isechotel.reservation.cli.reservation.repository;

import com.isechotel.reservation.cli.reservation.domain.Reservation;
import com.isechotel.reservation.cli.reservation.domain.Status;
import com.isechotel.reservation.cli.room.domain.Room;
import com.isechotel.reservation.cli.user.domain.User;


public class MemoryResRepository implements ResRepository {

    @Override
    public void resAdd(Reservation reservation) {
        Room rentRoom = reservation.getRoom();
        User rentUser = reservation.getUser();

        // 방 연계 정보 처리
        rentRoom.setReservation(true);
        rentRoom.setRentUser(rentUser.getUserName());

        // 유저 연계 정보 처리
        rentUser.setResNumber(rentUser.getUserNumber());
        rentUser.setRentRoomNumber(rentRoom.getRoomNumber());


    }

    @Override
    public void resCancel(Reservation reservation) {
        Room rentRoom = reservation.getRoom();
        User rentUser = reservation.getUser();

        // 방 정보 연계
        rentRoom.setReservation(false);
        rentRoom.setRentUser("");

        // 유저 연계 정보 처리
        rentUser.setResNumber(0);
        rentUser.setRentRoomNumber(0);

        // 예약 정보 연계 처리
        reservation.setStatus(Status.OUT);

    }
}
