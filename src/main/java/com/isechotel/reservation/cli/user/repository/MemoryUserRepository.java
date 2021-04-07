package com.isechotel.reservation.cli.user.repository;

import com.isechotel.reservation.cli.room.domain.Room;
import com.isechotel.reservation.cli.room.domain.SearchCondition;
import com.isechotel.reservation.cli.user.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 기능 구현
public class MemoryUserRepository implements UserRepository {

    private static final Map<Integer, User> userDb = new HashMap<>();

//    static {
//        User user1 = new User("김가가","010-9283-7863","대전서구");
//        User user2 = new User("김가나","010-1283-7862","서울서구");
//        User user3 = new User("김가바","010-2283-7865","대구서구");
//        userDb.put(0, user1);
//        userDb.put(1, user2);
//        userDb.put(2, user3);
//    }


    //고객 추가
    @Override
    public void addUser(User user) {
        userDb.put(user.getUserNumber(), user);
    }


    @Override
    public List<User> searchUserList(String keyword, SearchCondition condition) {

        List<User> results;

        switch (condition){
            case USER_NAME:
                results = searchUser(keyword, (k,u) -> k.equals(u.getUserName()));
                break;
            case USER_PHONE:
                // 핸드폰 뒷번호 4자리만 입력하는 걸로 변경하기!!!!!!!
                results = searchUser(keyword, (k,u) -> k.equals(u.getPhone().substring(u.getPhone().lastIndexOf("-") + 1)));
                break;
            case REGION:
                results = searchUser(keyword, (k,u) -> k.equals(u.getRegion()));
                break;
            case ALL:
                results = searchUser(keyword, (k,u) -> true);
                break;
            case RESERVATION_NUMBER:
                results = searchUser(keyword, (k ,u) -> Integer.parseInt(k) == (u.getResNumber()));
                break;
            default:
                return null;
        }
        return results;
    }

    // 고객 한명 검색
    @Override
    public User searchUserOne(int userNumber) {
        return userDb.get(userNumber);
    }

    // 고객 검색
    public List<User> searchUser(String keyword, UserPredicate up) {
        List<User> userList = new ArrayList<>();
        for (Integer key : userDb.keySet()) {
            User user = userDb.get(key);
            if(up.test(keyword, user)){
                userList.add(user);
            }
        }
        return userList;
    }

    // 검색 조건을 위한 인터페이스
    @FunctionalInterface
    interface UserPredicate{
        boolean test(String keyword, User user);
    }

}
