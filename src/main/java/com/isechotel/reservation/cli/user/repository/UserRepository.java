package com.isechotel.reservation.cli.user.repository;

import com.isechotel.reservation.cli.room.domain.SearchCondition;
import com.isechotel.reservation.cli.user.domain.User;

import java.util.List;

// 명세
public interface UserRepository {

    // 고객 정보 추가
    void addUser(User user);

    // 고객 리스트 검색
    List<User> searchUserList(String userName, SearchCondition condition);

    // 고객 1명 검색 기능
    User searchUserOne(int userNumber);


}
