package com.isechotel.reservation.cli.hotel.repository;

import com.isechotel.reservation.cli.hotel.SearchCondition;
import com.isechotel.reservation.cli.hotel.domain.Hotel;

import java.util.*;

public class MemoryRoomRepository implements RoomRepository {

    // 객실 정보 저장할 자료 구조
    private static final Map<Integer, Hotel> roomDb = new HashMap<>();

    // 자료 초기화
    static {
        insertRoomData();
    }

    // 자료 생성
    private static void insertRoomData() {
        Hotel room1 = new Hotel(1, "스탠다드", 2, 30000);
        Hotel room2 = new Hotel(2, "디럭스", 4, 50000);
        Hotel room3 = new Hotel(3, "스탠다드", 2, 30000);
        Hotel room4 = new Hotel(4, "디럭스", 2, 50000);
        Hotel room5 = new Hotel(5, "스탠다드", 2, 30000);
        Hotel room6 = new Hotel(6, "스탠다드", 2, 30000);
        Hotel room7 = new Hotel(7, "스위트룸", 2, 100000);
        Hotel room8 = new Hotel(8, "스위트룸", 2, 100000);
        Hotel room9 = new Hotel(9, "비즈니스룸", 1, 20000);
        Hotel room10 = new Hotel(10, "비즈니스룸", 1, 20000);

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

    @Override
    public void reservationRoom(Hotel hotel) {

    }

    @Override
    public void reservationCancel(int reservationNumber) {

    }

    @Override
    public void checkIn(int reservationNumber, String checkInDate) {
        List<Hotel> hotels = new ArrayList<>();
        for (Integer k : roomDb.keySet()) {
            if (reservationNumber == hotels.get(k).getReservationNumber()) {
                hotels.get(k).isReservation(true);
            }

        }

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();


    }

    @Override
    public void checkOut(int reservationNumber, String checkOutDate) {

    }

    @Override
    public List<Hotel> searchRoom(int reservationNumber, String roomName, int charge, boolean reservation
            , SearchCondition condition) {
        List<Hotel> hotels = null;

        switch (condition) {
            case ROOM_NUMBER:
                break;

            case RESERVATION:
                break;

            case CHARGE:
                break;

            case ALL:
                break;

            default:
        }
        return hotels;
    }

    private List<Hotel> search(String keyword, MoviePredicate mp) {
        List<Hotel> movieList = new ArrayList<>();
        for (Integer key : roomDb.keySet()) {
            Hotel movie = roomDb.get(key);

            // 검색 키워드와 발매연도 일치하는 movie만 리스트에 추가
            if (mp.test(keyword, movie)) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    // 검색 조건을 위한 인터페이스 (내부)
    @FunctionalInterface // 람다식 가능한지 오류 확인해줌
    interface MoviePredicate{
        boolean test(String keyword, Hotel movie);
    }

/*    public static Map<Integer, Hotel> getRoomDb() {
        return roomDb;
    }*/
}
