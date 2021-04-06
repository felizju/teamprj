package com.isechotel.reservation.cli.user.controller;

import com.isechotel.reservation.cli.main.AppController;
import com.isechotel.reservation.cli.ui.AppUI;
import com.isechotel.reservation.cli.user.domain.SearchCondition;
import com.isechotel.reservation.cli.user.domain.User;
import com.isechotel.reservation.cli.user.repository.MemoryUserRepository;
import com.isechotel.reservation.cli.user.repository.UserRepository;

import java.util.List;

public class UserController implements AppController {

    // 의존 관계 설정
    private final UserRepository userRepository = new MemoryUserRepository();

    @Override
    public void start() {

        while (true) {
            AppUI.userManagementScreen();
            int selection = AppUI.inputInteger("---▶  ");

            switch (selection) {
                case 1:
                    add();
                    break;
                case 2:
                    searchUserData();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("★★★ 메뉴를 잘못 입력하셨습니다. ★★★");
            }
            System.out.println("계속 진행하려면 ---▶ [ENTER] 를 누르세요");
            AppUI.inputString("");
        }
    }

    private void searchUserData() {
        AppUI.searchUserConditionScreen();
        int selection = AppUI.inputInteger("---▶  ");

        SearchCondition condition = SearchCondition.ALL;
        switch (selection) {
            case 1:
                System.out.println("\n====== 고객명으로 검색합니다. ======");
                condition = SearchCondition.USER_NAME;
                break;
            case 2:
                System.out.println("\n====== 고객 핸드폰 번호로 검색합니다. (뒷번호 4자리) ======");
                condition = SearchCondition.USER_PHONE;
                break;
            case 3:
                System.out.println("\n====== 지역별로 검색합니다. ======");
                condition = SearchCondition.REGION;
                break;
            case 4:
                System.out.println("\n====== 전체 고객 검색합니다. ======");
                break;
            default:
                System.out.println("\n★★ 메뉴를 잘못 선택하셨습니다. ★★");
        }

        String keyword = "";
        if (condition != SearchCondition.ALL) {
            keyword = AppUI.inputString("# 검색어 : ");

        }
        List<User> userList = userRepository.searchUserList(keyword, condition);
        int cnt = userList.size();
        if (cnt > 0) {
            System.out.printf("\n================================검색 결과(총 %d건)===================================\n", cnt);
            for (User user : userList) {
                System.out.println(user);
            }
        }else{
            System.out.println("\n★★ 검색 결과가 없습니다. ★★");
   }
    }

    private void add() {
        System.out.println("\n ——★ 회원정보를 추가합니다. ★——");
        String name = AppUI.inputString("—▶ 회원명 : ");
        String phone = AppUI.inputString("—▶ 전화번호 : ");
        String region = AppUI.inputString("—▶ 지역명 : ");
        userRepository.addUser(new User(name, phone, region));
    }



}
