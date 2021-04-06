package com.isechotel.reservation.cli.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppUI {

    private static Scanner sc = new Scanner(System.in);

    //정수 입력 메서드
    public static int inputInteger(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("정수로 입력해주세요!");
            } finally {
                sc.nextLine();
            }
        }
    }

    //문자열 입력 메서드
    public static String inputString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }



    //시작화면 출력
    public static void startScreen() {
        System.out.println("\n========= 호텔 관리 시스템 =========");
        System.out.println("### 1. 객실 관리 시스템");
        System.out.println("### 2. 고객 관리 시스템");
        System.out.println("### 3. 예약 관리 시스템");
        System.out.println("### 4. 프로그램 종료");
        System.out.println("----------------------------------------");
    }

    //관리 시스템 화면 출력
    public static void roomManagementScreen() {
        System.out.println("\n========= 객실 관리 시스템 =========");
        System.out.println("### 1. 객실 추가");
//        System.out.println("### 2. 객실 삭제");
        System.out.println("### 2. 객실 검색");
        System.out.println("### 3. 첫 화면으로 가기");
        System.out.println("----------------------------------------");
    }

    //검색 조건 화면
    public static void searchConditionScreen() {
        System.out.println("\n============== 객실 검색 ===============");
        System.out.println("[ 1. 객실명 | 2. 객실번호 | 3. 전체검색 ]");
    }


    //고객관리 시스템 화면 출력
    public static void userManagementScreen() {
        System.out.println("\n========= 고객 관리 시스템 =========");
        System.out.println("### 1. 고객 등록");
        System.out.println("### 2. 고객 검색");
        System.out.println("### 3. 첫 화면으로 가기");
        System.out.println("----------------------------------------");
    }


    // 예약 관리 시스템 화면 출력
    public static void reservationManagementScreen() {
        System.out.println("\n========= 예약 관리 시스템 =========");
        System.out.println("### 1. 체크인");
        System.out.println("### 2. 체크아웃");
        System.out.println("### 3. 첫 화면으로 가기");
        System.out.println("----------------------------------------");
    }


}
