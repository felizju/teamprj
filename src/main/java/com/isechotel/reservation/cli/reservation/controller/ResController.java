package com.isechotel.reservation.cli.reservation.controller;

import com.isechotel.reservation.cli.main.AppController;
import com.isechotel.reservation.cli.reservation.domain.Reservation;
import com.isechotel.reservation.cli.reservation.repository.MemoryResRepository;
import com.isechotel.reservation.cli.reservation.repository.ResRepository;
import com.isechotel.reservation.cli.room.domain.Room;
import com.isechotel.reservation.cli.room.domain.SearchCondition;
import com.isechotel.reservation.cli.room.repository.MemoryRoomRepository;
import com.isechotel.reservation.cli.room.repository.RoomRepository;
import com.isechotel.reservation.cli.ui.AppUI;
import com.isechotel.reservation.cli.user.domain.User;
import com.isechotel.reservation.cli.user.repository.MemoryUserRepository;
import com.isechotel.reservation.cli.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ResController implements AppController {

    private UserRepository userRepository = new MemoryUserRepository();
    private RoomRepository roomRepository = new MemoryRoomRepository();
    private ResRepository resRepository = new MemoryResRepository();

    @Override
    public void start() {
        while (true) {
            AppUI.reservationManagementScreen();
            System.out.println("원하시는 메뉴 번호를 선택해주세요.");
            int selection = AppUI.inputInteger("---▶  ");

            switch (selection) {
                case 1:
                    reservation();
                    break;
                case 2:
                    reservationCancel();
                    break;
                case 3:
                    checkReservation();
                case 4:
                    return;
                default:
                    System.out.println("[ 메뉴를 잘못 선택하셨습니다! ]");
            }
            System.out.println("---▶  [ENTER]를 누르면 계속 진행됩니다!");
            AppUI.inputString("");
        }
    }

    private void reservation() {
        while (true) {
            List<Room> roomList = roomRepository.searchRoom("", SearchCondition.RESERVATION_FLAG);
            int count = roomList.size();
            if (count > 0) {
                System.out.printf("\n======= 예약가능 객실 목록  =========\n[ 총 %d개의 객실이 조회되었습니다! ]\n", count);
                for (Room room : roomList) {
                    System.out.println(room);
                }
                System.out.println("\n투숙할 [객실번호]를 입력하세요!");
                System.out.println("---▶  [0]을 누르면 이전으로 돌아갑니다!");
                int roomNum = AppUI.inputInteger("--—▶  객실번호 : ");
                if (roomNum == 0) return;

                Room rentRoom = roomRepository.searchRoomOne(roomNum);
                System.out.printf("\n[%s]를 예약합니다.", rentRoom.getRoomName());

                System.out.println("\n투숙할 [예약자명]을 입력하세요!");
                String resName = AppUI.inputString("--—▶  예약자명 : ");

                System.out.println("\n투숙할 [기간]을 입력하세요!");
                int rentDay = AppUI.inputInteger("--—▶  숙박기간(일) : ");

                List<User> userList = userRepository.searchUserList(resName, SearchCondition.USER_NAME);
                User resUser = null;
                List<Integer> userNum = new ArrayList<>();
                int count2 = userList.size();
                if (count2 > 0) {
                    for (User user : userList) {
                        if (user.getUserName().equals(resName)) {
                            resUser = user;
                        }
                    }
                    //User newUser = new User(resName, "");
                    Reservation newRes = new Reservation(resUser, rentRoom, rentDay);

                    userRepository.addUser(resUser);
                    resRepository.resAdd(newRes);

                    System.out.printf("\n[%s]님 예약이 완료되었습니다. 감사합니다.\n",resUser.getUserName());
                    System.out.printf("예약번호 : %d  |  결제금액 : %d원\n", newRes.getResNumber(), ChargeMoney(rentDay, rentRoom.getRoomName()));
                    System.out.println("\n---▶  [ENTER]를 누르면 계속 진행됩니다!");
                    AppUI.inputString("");
                } else {
                    System.out.println("\n[ 고객 정보 검색 결과가 없습니다! ]");
                    break;
                }
            }else {
                System.out.println("\n[ 예악자 정보 검색 결과가 없습니다! ]");
            }

        }
    }

    private void reservationCancel() {
        System.out.println("\n============ 예약 취소 ============");
        System.out.println("\n[예약자명]을 입력하세요!");
        System.out.println("---▶  [0]을 누르면 이전으로 돌아갑니다!");

        String resName = AppUI.inputString("--—▶  예약자명 : ");
        if (resName.equals("0")) return;

        List<User> resUserList = userRepository.searchUserList(resName, SearchCondition.USER_NAME);
        int count = resUserList.size();

        if (count > 0) {
            System.out.printf("\n============ 검색 결과  ============\n[ 총 %d건 조회되었습니다! ]\n", count);
            for (User user : resUserList) {
                System.out.println(user);
            }
            System.out.println("==================================");

            int resNum = 0;
            String roomNum = "";

            try {
                System.out.println("\n취소할 [예악번호]를 입력하세요!");
                resNum = AppUI.inputInteger("--—▶  예약번호 : ");

                System.out.println("\n취소할 [객실번호]를 입력하세요!");
                roomNum = AppUI.inputString("--—▶  객실번호 : ");

            }catch (InputMismatchException e){
                System.out.println("[ 숫자만 기입해주세요! (예 : 1) ]");
            }

            List<Room> resRoomList = roomRepository.searchRoom(roomNum, SearchCondition.ROOM_NUMBER);
            User resUser = null;
            Room resRoom = null;

            for (User user : resUserList) {
                if (user.getResNumber() == resNum) {
                    resUser = user;
                    break;
                }
            }

            for (Room room : resRoomList) {
                if (room.getRoomNumber() == Integer.parseInt(roomNum)) {
                    resRoom = room;
                    break;
                }
            }
            resRepository.resCancel(new Reservation(resUser, resRoom, 0));

            System.out.println("\n취소 완료되었습니다!");

        } else {
            System.out.println("[ 예악자 정보 검색 결과가 없습니다! ]");
        }
    }


    private void checkReservation() {
        System.out.println("\n============ 예약 확인 ============");
        System.out.println("\n[예약자명]을 입력하세요!");
        System.out.println("---▶  [0]을 누르면 이전으로 돌아갑니다!");
        String resName = AppUI.inputString("--—▶  예약자명 : ");
        String resNum = "";

        try{
            System.out.println("\n[예약번호]를 입력하세요!");
            resNum = AppUI.inputString("--—▶  예약번호 : ");
            if (resName.equals("0")) return;
        } catch (Exception e) {
            System.out.println("[ 숫자만 기입해주세요! (예 : 1) ]");
        }

        List<User> resUserList = userRepository.searchUserList(resNum, SearchCondition.RESERVATION_NUMBER);
        int count = resUserList.size();
        if (count > 0) {
            System.out.printf("\n============ 검색 결과  ============\n[ 총 %d건 조회되었습니다! ]\n", count);
            for (User user : resUserList) {
                System.out.println(user);
            }
            System.out.println("\n---▶  [ENTER]를 누르면 이전으로 돌아갑니다!");
            AppUI.inputString("");
        }
    }

    private int ChargeMoney(int rentNum, String roomName) {
        if (roomName.equals("스위트룸")) {
            return rentNum * 100000;
        } else if(roomName.equals("디럭스룸")) {
            return rentNum * 50000;
        } else if(roomName.equals("비즈니스룸")) {
            return rentNum * 20000;
        } else {
            return rentNum * 30000;
        }
    }

}