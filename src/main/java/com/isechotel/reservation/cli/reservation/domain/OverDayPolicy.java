package com.isechotel.reservation.cli.reservation.domain;

import java.time.LocalDate;

/**
 * 객실 연장 금액 정책
 * 1. 스탠다드 : 연장일수 * 30000
 * 2. 디럭스 : 연장일수 * 50000
 * 3. 비즈니스 : 연장일수 * 20000
 * 4. 스위트 : 연장일수 * 100000
 */
public class OverDayPolicy {

    private static final int STANDARD = 30000;
    private static final int DELUXE = 50000;
    private static final int BUSINESS = 20000;
    private static final int SUITE = 100000;

 /*   // 연장 일수 계산
    public static int calcOverDay(){
        // 현재날짜
        LocalDate curDate = LocalDate.now();



    }*/




}