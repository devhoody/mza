package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.FavView;
import com.matzalal.web.entity.Grade;
import com.matzalal.web.entity.LocCategory;
import com.matzalal.web.entity.PostUserLikeCommentView;
import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.entity.User;
import com.matzalal.web.entity.UserView;

public interface UserService {
    List<User> getList();

    List<User> getList(Integer offset, Integer page, Integer pageSize, String query);

    User signUp(User user);

    User getById(Long id); // User getById(Long userId);

    boolean hasEmail(String email);

    User getByEmail(String query);

    boolean hasIdByAlias(String nickname);

    UserView getUserViewById(Long id);

    /* 관리자 페이지 */
    Integer countUser();

    void edit(User user);

    boolean delete(Long id);

    boolean hasName(String name);

    boolean hasPhone(String phone);

    User findIdByName(String name);

    List<PostUserLikeCommentView> getPostViewById(long id);

    List<Grade> getGrade();

    List<ReportReason> getReasonList();

    List<LocCategory> getCategoryList();

    // 회원 삭제
    void deleteUser(Long id);

    boolean deleteUser(List<Long> idList);

    List<FavView> getFavViewByUserId(Long id);

    boolean hasAlias(String query);

    boolean hasForFindId(User user);

    User getByUser(User user);

    boolean hasForFindPwd(User user);
}