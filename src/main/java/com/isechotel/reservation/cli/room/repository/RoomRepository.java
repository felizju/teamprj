package com.isechotel.reservation.cli.room.repository;

import com.isechotel.reservation.cli.room.domain.SearchCondition;
import com.isechotel.reservation.cli.room.domain.Room;

import java.util.List;

// 역할 : 저장소 기능의 명세
public interface RoomRepository {

    // 객실 추가
    void addRoom(Room room);

    // 객실 삭제
    void removeRoom(int roomNumber);

    // 객실 검색 기능
    List<Room> searchRoom(String keyword, SearchCondition condition);

    // 객실 1개 검색 기능
    Room searchRoomOne(int roomNumber);

}
