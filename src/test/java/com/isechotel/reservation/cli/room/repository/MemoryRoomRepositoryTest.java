package com.isechotel.reservation.cli.room.repository;

import com.isechotel.reservation.cli.room.domain.Room;
import com.isechotel.reservation.cli.room.domain.SearchCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryRoomRepositoryTest {

    MemoryRoomRepository roomRepository = new MemoryRoomRepository();

    @Test
    @DisplayName("전체 검색 테스트")
    public void test() {

        List<Room> roomList =  roomRepository.searchRoom("", SearchCondition.RESERVATION_FLAG);

        for (Room room : roomList) {
            System.out.println(room);
        }

    }

}