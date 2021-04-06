package com.isechotel.reservation.cli.user.repository;

import com.isechotel.reservation.cli.user.domain.SearchCondition;
import com.isechotel.reservation.cli.user.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 기능 구현
public class MemoryUserRepository implements UserRepository {

    private static final Map<Integer, User> userDb = new HashMap<>();

    @Override
    public void addUser(User user) {
        
    }

    @Override
    public List<User> searchUserList(String userName, SearchCondition condition) {
        return null;
    }

    // 고객 한명 검색
    @Override
    public User searchUserOne(int userNumber) {
        return userDb.get(userNumber);
    }

    // 고객 검색
    public List<User> searchUser(String userName, SearchCondition condition) {
        List<User> userList = new ArrayList<>();
        for (Integer key : userDb.keySet()) {
            User user = userDb.get(key);
            if(user.getUserName().equals(userName)){
                userList.add(user);
            }
        }
        return userList;
    }

}
