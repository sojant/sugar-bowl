package io.sojant.github.users.dao;

import io.sojant.github.users.model.Users;
import org.springframework.stereotype.Repository;


/**
 * Created by Sojant on 2018-10-27.
 */


@Repository
public class UsersDao extends BaseDao<Users> {

    public UsersDao(){
        setClazz(Users.class );
    }

}
