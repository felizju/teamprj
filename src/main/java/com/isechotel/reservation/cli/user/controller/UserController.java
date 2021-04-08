package com.isechotel.reservation.cli.user.controller;

import com.isechotel.reservation.cli.main.AppController;
import com.isechotel.reservation.cli.room.domain.SearchCondition;
import com.isechotel.reservation.cli.ui.AppUI;
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

            System.out.println("원하시는 메뉴 번호를 선택해주세요.");
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
                    System.out.println("[ 메뉴를 잘못 선택하셨습니다! ]");
            }
            System.out.println("---▶  [ENTER]를 누르면 계속 진행됩니다!");
            AppUI.inputString("");
        }
    }

    private void searchUserData() {
        AppUI.searchUserConditionScreen();
        int selection = AppUI.inputInteger("---▶  ");

        SearchCondition condition = SearchCondition.ALL;
        switch (selection) {
            case 1:
                System.out.println("\n-------- [고객명] 으로 검색 --------");
                condition = SearchCondition.USER_NAME;

                break;
            case 2:
                System.out.println("\n-------- [연락처] 으로 검색 --------");
                System.out.println("---▶  핸드폰 번호 뒷 4자리를 입력해주세요!");
                condition = SearchCondition.USER_PHONE;
                break;
            case 3:
                System.out.println("\n-------- [지역별] 으로 검색 --------");
                condition = SearchCondition.REGION;
                break;
            case 4:
                condition = SearchCondition.ALL;
                break;
            default:
                System.out.println("[ 메뉴를 잘못 선택하셨습니다! ]");
        }

        String keyword = "";
        if (condition != SearchCondition.ALL) {
            keyword = AppUI.inputString("---▶  검색어 : ");
        }
        List<User> userList = userRepository.searchUserList(keyword, condition);
        int cnt = userList.size();
        if (cnt > 0) {
            System.out.printf("\n============ 검색 결과  ============\n[ 총 %d건 조회되었습니다! ]\n", cnt);
            for (User user : userList) {
                System.out.println(user);
            }
            System.out.println("==================================");
        }else{
            System.out.println("[ 검색 결과가 없습니다! ]");
        }
    }

    private void add() {
        System.out.println("\n============ 고객 등록 ============");
        String name = AppUI.inputString("--—▶  고객명 : ");
        String phone = AppUI.inputString("--—▶  연락처 : ");
        String region = AppUI.inputString("--—▶  지역명 : ");
        userRepository.addUser(new User(name, phone, region));

//        System.out.printf("\n [%s] 고객 정보가 정상 등록되었습니다!");

    }



}