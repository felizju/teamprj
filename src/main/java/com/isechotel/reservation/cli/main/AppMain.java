package com.isechotel.reservation.cli.main;

import com.isechotel.reservation.cli.ui.AppUI;

public class AppMain {

    public static void main(String[] args) {

        while(true){

            AppUI.asciiArt();
            AppUI.startScreen();
            System.out.println("원하시는 메뉴 번호를 선택해주세요.");
            int selection = AppUI.inputInteger("---▶  ");
            FrontController.managementSystem(selection);
        }
    }

}