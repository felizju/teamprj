package com.isechotel.reservation.cli.hotel.repository;

import com.isechotel.reservation.cli.hotel.SearchCondition;
import com.isechotel.reservation.cli.hotel.domain.Hotel;

import java.util.List;

// 역할 : 저장소 기능의 명세
public interface RoomRepository {

    // 예약기능
    void reservationRoom(Hotel hotel);

    // 예약 취소기능
    void reservationCancel(int reservationNumber);

    // 체크인 기능
    void checkIn(int reservationNumber, String checkInDate);

    // 체크아웃 기능
    void checkOut(int reservationNumber, String checkOutDate);

    // 검색 기능
    List<Hotel> searchRoom(int reservationNumber, String roomName, int charge, boolean reservation, SearchCondition condition);


}
