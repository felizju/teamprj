package com.isechotel.reservation.cli.hotel.repository;

import com.isechotel.reservation.cli.hotel.domain.Hotel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MemoryRoomRepositoryTest {

    private RoomRepository repository = new MemoryRoomRepository();

    @Test
    @DisplayName("DB 출력해본다.")
    void printDb(){
        Map<Integer, Hotel> roomDb = MemoryRoomRepository.getRoomDb();
        System.out.println("roomDb = " + roomDb);
    }

}