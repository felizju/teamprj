package com.isechotel.reservation.cli.main;

import com.isechotel.reservation.cli.ui.AppUI;

public class AppMain {

    public static void main(String[] args) {

        while(true){

            AppUI.asciiArt();
            AppUI.startScreen();
            int selection = AppUI.inputInteger("---▶  ");
            FrontController.managementSystem(selection);
        }
    }

}
