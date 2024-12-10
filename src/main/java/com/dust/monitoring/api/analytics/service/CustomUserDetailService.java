package com.dust.monitoring.api.analytics.service;

import com.dust.monitoring.api.analytics.entity.Users;
import com.dust.monitoring.api.analytics.model.PrincipalUser;
import com.dust.monitoring.api.analytics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findUserByEmailOrUsername(email, email).orElseThrow(() -> new RuntimeException("User not Exist!"));
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(users.getEmail())
                        .password(users.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
//        return userDetails;

//        List<GrantedAuthority> authorities = buildUserAuthority(users.getRoles());

        //The magic is happen in this private method !
        return buildUserForAuthentication(users);
    }

    //Fill your extended User object (CurrentUser) here and return it
//    private User buildUserForAuthentication(Users user,
//                                            List<GrantedAuthority> authorities) {
    private User buildUserForAuthentication(Users user) {
        String username = user.getUsername();
        String password = user.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new PrincipalUser(username, user.getEmail(), password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, new ArrayList<GrantedAuthority>(), user.getFirstName(), user.getLastName(), user.getRoles(), user.getCreatedAt(), user.getUpdatedAt());
        //If your database has more information of user for example firstname,... You can fill it here
        //CurrentUser currentUser = new CurrentUser(....)
        //currentUser.setFirstName( user.getfirstName() );
        //.....
        //return currentUser ;
    }

//    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
//
//        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//
//        // Build user's authorities
//        for (UserRole userRole : userRoles) {
//            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
//        }
//
//        return new ArrayList<GrantedAuthority>(setAuths);
//    }
}
