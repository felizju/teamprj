package com.isechotel.reservation.cli.room.repository;

import com.isechotel.reservation.cli.room.domain.SearchCondition;
import com.isechotel.reservation.cli.room.domain.Room;

import java.util.*;

public class MemoryRoomRepository implements RoomRepository {

    // 객실 정보 저장할 자료 구조
    private static final Map<Integer, Room> roomDb = new HashMap<>();

    // 자료 초기화
    static {
        insertRoomData();
    }

    // 자료 생성
    private static void insertRoomData() {
        Room room1 = new Room("스탠다드", 30000);
        Room room2 = new Room("디럭스",  50000);
        Room room3 = new Room("스탠다드", 30000);
        Room room4 = new Room("디럭스",  50000);
        Room room5 = new Room("스탠다드", 30000);
        Room room6 = new Room("스탠다드", 30000);
        Room room7 = new Room( "스위트룸", 100000);
        Room room8 = new Room( "스위트룸",  100000);
        Room room9 = new Room("비즈니스룸", 20000);
        Room room10 = new Room( "비즈니스룸", 20000);

        roomDb.put(room1.getRoomNumber(), room1);
        roomDb.put(room2.getRoomNumber(), room2);
        roomDb.put(room3.getRoomNumber(), room3);
        roomDb.put(room4.getRoomNumber(), room4);
        roomDb.put(room5.getRoomNumber(), room5);
        roomDb.put(room6.getRoomNumber(), room6);
        roomDb.put(room7.getRoomNumber(), room7);
        roomDb.put(room8.getRoomNumber(), room8);
        roomDb.put(room9.getRoomNumber(), room9);
        roomDb.put(room10.getRoomNumber(), room10);
    }


    // 객실 추가
    @Override
    public void addRoom(Room room) {
        roomDb.put(room.getRoomNumber(), room);
    }

/*    // 객실 삭제
    @Override
    public void removeRoom(int roomNumber) {
        roomDb.remove(roomNumber);
    }*/


    // 객실 검색
    @Override
    public List<Room> searchRoom(String keyword, SearchCondition condition) {
        List<Room> results = null;

        switch (condition) {
            case ROOM_NUMBER:
                results = search(keyword, (k,r) -> Integer.parseInt(k) == r.getRoomNumber());
                break;
            case ROOM_NAME:
                results = search(keyword, (k,r) -> k.equals(r.getRoomName()));
                break;
            case ALL:
                results = search(keyword, (k, r) -> true);
                break;
            case RESERVATION_FLAG:
//                roomDb.get(1).setReservation(true);
                results = search(keyword, (k, r) -> !r.isReservation());
                break;
            case RESERVATION_NUMBER:
                results = search(keyword, (k, r) -> Integer.parseInt(k) == r.getReservationNumber());
                break;
            default:
                return null;
        }
        return results;
    }

    // 객실 하나 검색
    @Override
    public Room searchRoomOne(int roomNumber) {
        return roomDb.get(roomNumber);
    }

    // 검색
    private List<Room> search(String keyword, RoomPredicate rp) {
        List<Room> roomList = new ArrayList<>();
        for (Integer key : roomDb.keySet()) {
            Room room = roomDb.get(key);

            if (rp.test(keyword, room)) {
                roomList.add(room);
            }
        }
        return roomList;
    }

    // 검색 조건을 위한 인터페이스 (내부)
    @FunctionalInterface // 람다식 가능한지 오류 확인해줌
    interface RoomPredicate{
        boolean test(String keyword, Room room);
    }

}
