package com.matzalal.web.config.auth;

import com.matzalal.web.entity.GradeView;
import com.matzalal.web.entity.User;
import com.matzalal.web.entity.UserView;
import com.matzalal.web.repository.GradeRepository;
import com.matzalal.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatzalalUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MatzalalUserDetails userDetails = new MatzalalUserDetails();
        UserView userView = userRepository.findUserViewByEmail(username);

        userDetails.setUsername(username); // 이메일 
        userDetails.setPassword(userView.getPwd());
        userDetails.setId(userView.getId()); // 유저 아이디 
//        userDetails.setAlias(userView.getAlias());
//        userDetails.setPhone(userView.getPhone());
//        userDetails.setCreatedDate(userView.getDate());
//        userDetails.setProfileImg(userView.getProfileImg());
//        userDetails.setPostCount(userView.getPostCount());
//        userDetails.setCommentCount(userView.getCommentCount());
//        userDetails.setFavCount(userView.getFavCount());

        // 권한 부여
        List<GradeView> gradeViewList = gradeRepository.findViewAllById(userView.getEmail());


        List<GrantedAuthority> authorities = new ArrayList<>();
        for(GradeView gv : gradeViewList)
            authorities.add(new SimpleGrantedAuthority(gv.getGrade()));
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
