package com.isechotel.reservation.cli.hotel.repository;

import com.isechotel.reservation.cli.hotel.domain.Room;
import com.isechotel.reservation.cli.hotel.domain.SearchCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class MemoryRoomRepositoryTest {

    MemoryRoomRepository roomRepository = new MemoryRoomRepository();

    @Test
    @DisplayName("전체 검색 테스트")
    public void test() {

        List<Room> roomList =  roomRepository.searchRoom("1", SearchCondition.ROOM_NUMBER);

        for (Room room : roomList) {
            room.setReservation(true);
            System.out.println(room);
        }

    }

}