package com.in28minutes.rest.webservices.restfulwebservices.searvices;

import com.in28minutes.rest.webservices.restfulwebservices.entities.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class UserDaoServices {

    private static List<User> list = new ArrayList<>();
    private static int usersCount = 0;

    static {
        list.add(new User(++usersCount,"Mohamed", LocalDate.now().minusYears(30)));
        list.add(new User(++usersCount,"Amine", LocalDate.now().minusYears(26)));
        list.add(new User(++usersCount,"User", LocalDate.now().minusYears(18)));
    }

    public List<User> findAll() {
        return list;
    }

    public User findOne(int id){
        return list.stream().filter(user -> user.getId().equals(id))
                .findFirst().orElse(null);
    }

    public User save(User user) {
        user.setId(++usersCount);
        list.add(user);
        return user;
    }

}
