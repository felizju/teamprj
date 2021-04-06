package com.isechotel.reservation.cli.main;

import com.isechotel.reservation.cli.room.controller.RoomController;

public class FrontController {

    private static AppController appController;

    public static void managementSystem(int selection){

        switch (selection){
            case 1:
                appController = new RoomController();
                break;
            case 2:
//                appController = new UserController();
                break;
            case 3:
//                appController = new ReservationController();
                break;
            case 4:
                System.out.println("프로그램 종료");
                System.exit(0);
            default:
                System.out.println("\n★★ 메뉴를 잘못 선택하셨습니다. ★★");
        }// end switch
        appController.start();
    }


}
