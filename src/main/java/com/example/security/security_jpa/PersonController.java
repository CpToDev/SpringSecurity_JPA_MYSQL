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
    PersonRepository personRepository;

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
    public String register(@RequestBody Person person){

        Person person1=personRepository.getPersonByUsername(person.getUsername());
        if(person1!=null)
        {
            return "User with this username already exists";
        }

            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
            person.setAuthorities(PERSON_USER_AUTHORITY);
            personRepository.save(person);
            return "Successfully registered!";


    }
    @PostMapping("/post")
    public String post(){
        return "POSTED";
    }

}
