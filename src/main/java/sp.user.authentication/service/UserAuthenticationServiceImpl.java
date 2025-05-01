package sp.user.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import sp.user.authentication.entity.UserAuth;
import sp.user.authentication.repo.UserRepo;

@Component
public class UserAuthenticationServiceImpl implements UserDetailsService {


    @Autowired
    UserRepo userRepo;

    public String saveUserDetails(UserAuth userAuth){
        // mapping dto to entity
        userRepo.save(userAuth);
        return "User saved successfully";
    }

    @Override
    public UserAuth loadUserByUsername(String username) throws UsernameNotFoundException {
         UserAuth auth= userRepo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
         return auth;
    }
}
