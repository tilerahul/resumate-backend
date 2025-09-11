package com.resumate.resumate_backend.Service;

import com.resumate.resumate_backend.Model.User;
import com.resumate.resumate_backend.Repo.UserRepo;
import com.resumate.resumate_backend.utility.JWTutil;
import com.resumate.resumate_backend.utility.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private JWTutil jwtUtil;


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public User getUserById(String id) {
        return userRepo.findById(id).orElse(null);
    }


    public String register(User request) {
        if (request.getFirstName() == null || request.getLastName() == null ||
                request.getEmail() == null || request.getPhone() == null ||
                request.getPassword() == null || request.getCpassword() == null) {
            return "All fields are mandatory";
        }

        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        if (!request.getPassword().equals(request.getCpassword())) {
            return "Password does not match";
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        String imgUrl = "https://ui-avatars.com/api/?name=" + request.getFirstName() + " +" + request.getLastName();
        request.setPassword(hashedPassword);
        request.setImgUrl(imgUrl);

        userRepo.save(request);
        System.out.println("User Register");

        return "User registered successfully!";
    }

    public String deleteUser(String id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) {
            userRepo.deleteById(id);
            return "User deleted successfully";
        }
        return "User Not found";
    }


    public LoginResponse login(String email, String password){

        LoginResponse res = new LoginResponse();
        if(email==null || password==null || email.isEmpty() || password.isEmpty()){
            res.setMessage("All Fields Are mandatory !!");
            return res;
        }

        Optional<User> user = userRepo.findByEmail(email);
        if(user.isEmpty()){
            res.setMessage("User Not Found, Please Register First !!");
            return res;
        }



        if(passwordEncoder.matches(password, user.get().getPassword())){
//            System.out.println("_____________________________________________________________");
//            System.out.println("-->"+JWTutil.SECRET_KEY);
//            String token = jwtUtil.generateToken(user.get().getId(), user.get().getIsAdmin());
//            System.out.println("------------------------------>"+token);
            res.setSuccess(true);
            res.setMessage("User Login Successful !!");
            res.setUser(user.get());
            res.setToken("token");
            return res;
        }else{
            res.setMessage("Invalid Credentials !!");
            return res;
        }
    }
}
