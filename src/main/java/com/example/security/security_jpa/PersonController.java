package com.example.security.security_jpa;

import com.example.security.security_jpa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Value("${person.authority.user}")
    String PERSON_USER_AUTHORITY;


    @Autowired
    MyUserDetailService userDetailService;

    @GetMapping("/")
    public String homePage(){
        return "Welcome User!";
    }
    @GetMapping("/admin")
    public String adminPage(@RequestParam String name){
        return "Welcome admin : "+name;
    }

    @GetMapping("/user")
    public String userPage(@RequestParam String name){
        return "Welcome User: "+name;
    }

    @PostMapping("/register")
    public void register(@RequestBody Person person) throws Exception {

            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
            person.setAuthorities(PERSON_USER_AUTHORITY);

            try {
                userDetailService.saveUser(person);

            }catch (Exception e){

                throw new Exception("User already exists!");

            }
    }


}
