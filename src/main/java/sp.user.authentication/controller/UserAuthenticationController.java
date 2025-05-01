package sp.user.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import sp.user.authentication.entity.UserAuth;
import sp.user.authentication.service.UserAuthenticationServiceImpl;


/**
 * User Authentication Controller
 * This controller handles user authentication requests.
 * It provides endpoints for user login, registration, and password management.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationServiceImpl userAuthenticationServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/registerUser",
            produces = "application/json",consumes = "application/json")
    public ResponseEntity<String> addUser(@RequestBody  UserAuth userAuth) {
        // Implementation will go here
        String pwd=userAuth.getPassword();
        userAuth.setPassword(passwordEncoder.encode(pwd));
        userAuthenticationServiceImpl.saveUserDetails(userAuth);
        return ResponseEntity.ok("User added successfully");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, World!");
    }

}
