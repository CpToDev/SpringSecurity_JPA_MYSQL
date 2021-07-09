package com.example.security.security_jpa;

import com.example.security.security_jpa.model.MyUserDetail;
import com.example.security.security_jpa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

     Person person=personRepository.getPersonByUsername(username);


     if(person==null)
         throw new UsernameNotFoundException("No Such User with username "+username);

     return new MyUserDetail(person);

    }

    public void saveUser(Person person){
        personRepository.save(person);
    }
}
