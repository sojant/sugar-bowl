package io.sojant.github.users.controller;

/**
 * Created by Sojant on 2018-10-24.
 */
import java.util.List;

import io.sojant.github.users.dao.UsersDao;
import io.sojant.github.users.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    @Autowired
    private UsersDao usersDao;

    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}")
    public Users users_list(@PathVariable(name = "id") String id) {
        Users user = usersDao.findOne(Long.parseLong(id));
        if (user==null){
            throw new ResourceNotFoundException();
        }
        return user;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/all")
    public List<Users> users_list() {
        return usersDao.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/user")
    public Users users_update(@RequestBody Users user) {
        usersDao.save(user);
        return user;
    }

}

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException {

}