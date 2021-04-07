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
            int selection = AppUI.inputInteger(">> ");

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
                    System.out.println("## 메뉴를 잘못 입력했습니다.");
            }
        }

    }

    private void reservation() {
        while (true) {
            List<Room> roomList = roomRepository.searchRoom("", SearchCondition.RESERVATION_FLAG);
            int count = roomList.size();

            if (count > 0) {
                System.out.println("------------------- 예약 가능한 객실 목록 ---------------------");
                for (Room room : roomList) {
                    System.out.println(room);
                }

                System.out.println("-------------------------------------------------------------");
                System.out.println("투숙할 객실 번호를 입력하세요. 이전으로 돌아갈려면 0을 입력하세요.");
                int roomNum = AppUI.inputInteger(">>> ");
                if (roomNum == 0) return;

                Room rentRoom = roomRepository.searchRoomOne(roomNum);
                System.out.printf("[%s]를 예약합니다.", rentRoom.getRoomName());

                System.out.println("## 예약하시는 분의 성함을 입력하세요.");
                String resName = AppUI.inputString(">>> ");

                /*System.out.println("## 예약하시는 분의 휴대전화를 입력하세요.");
                String resPhone = AppUI.inputString(">>> ");*/

                /*System.out.println("## 투숙하는 인원을 입력하세요.");
                int resPersons = AppUI.inputInteger(">>> ");*/

                System.out.println("## 투숙할 기간을 입력해주세요.");
                int rentDay = AppUI.inputInteger(">>> ");

                List<User> userList = userRepository.searchUserList(resName, SearchCondition.USER_NAME);
                User resUser = null;
                List<Integer> userNum = new ArrayList<>();
                int count2 = userList.size();
                if (count2 > 0) {
                    System.out.println("-------------------- 고객 정보 -----------------------");
                    for (User user : userList) {
                        System.out.println(user);
                        if (user.getUserName().equals(resName)) {
                            resUser = user;
                        }
                    }

                    //User newUser = new User(resName, "");
                    Reservation newRes = new Reservation(resUser, rentRoom, rentDay);

                    userRepository.addUser(resUser);
                    resRepository.resAdd(newRes);

                    System.out.printf("[%s]님 예약이 완료되었습니다. 감사합니다.\n", resUser.getUserName());
                    System.out.printf("[%s]님의 예약번호는 %d 입니다.\n", resUser.getUserName(), newRes.getResNumber());
                    System.out.printf("[%s]님의 결재 금액은 %d입니다.\n", resUser.getUserName(), ChargeMoney(rentDay, rentRoom.getRoomName()));


                } else {
                    System.out.println("고객 정보가 없습니다. 고객 등록부터 진해해주세요.");
                    break;
                }


            }else {
                System.out.println("예약자 정보가 존재하지 않습니다.");
            }

        }
    }

    private void reservationCancel() {
        System.out.println("-------------------- 예약 취소 ---------------------");
        System.out.println("에약자의 이름을 입력하세요. 이전으로 돌아가려면 0을 입력하세요.");
        String resName = AppUI.inputString(">>> ");
        if (resName.equals("0")) return;

        List<User> resUserList = userRepository.searchUserList(resName, SearchCondition.USER_NAME);
        int count = resUserList.size();

        if (count > 0) {
            System.out.printf("\n--------------------------검색 결과(총 %d건)---------------------------------\n", count);
            for (User user : resUserList) {
                System.out.println(user);
            }

            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("## 삭제할 예약 번호를 입력하세요.");
            int resNum = AppUI.inputInteger(">>> ");

            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("## 예약한 방번호를 입력하세요.");
            String roomNum = AppUI.inputString(">>> ");

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

        } else {
            System.out.println("\n## 예약자의 정보가 없습니다.");
        }
    }


    private void checkReservation() {

        System.out.println("------------------- 예약 확인하기 ---------------------");
        System.out.println("예약자명을 입력하세요. 0을 입력하면 이전으로 돌아갑니다.");
        String resName = AppUI.inputString(">>> ");
        System.out.println("예약자 번호를 입력하세요.");
        String resNum = AppUI.inputString(">>> ");
        if (resName.equals("0")) return;

        List<User> resUserList = userRepository.searchUserList(resNum, SearchCondition.RESERVATION_NUMBER);
        int count = resUserList.size();

        if (count > 0) {
            System.out.printf("\n--------------------------검색 결과(총 %d건)---------------------------------\n", count);
            for (User user : resUserList) {
                System.out.println(user);
            }
            System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
            AppUI.inputString("");
        }

    }

    int ChargeMoney(int rentNum, String roomName) {

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
