package com.isechotel.reservation.cli.reservation.repository;

import com.isechotel.reservation.cli.reservation.domain.Reservation;
import com.isechotel.reservation.cli.room.domain.SearchCondition;
import com.isechotel.reservation.cli.user.domain.User;

import java.util.List;

public interface ResRepository {

    // 예약 추가
    void resAdd(Reservation reservation);

    // 예약 취소
    void  resCancel(Reservation reservation);


}
