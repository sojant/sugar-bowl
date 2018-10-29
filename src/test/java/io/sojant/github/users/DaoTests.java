package io.sojant.github.users;

import io.sojant.github.users.dao.UsersDao;
import io.sojant.github.users.model.Users;
import io.sojant.github.users.tasks.CreateModels;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * Created by Sojant on 2018-10-29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTests {

    private Users userNotSaved;

    @Autowired
    private UsersDao dao;

    @Test
    public void contextLoads() {
    }

    @Before
    public void create_structures(){
        CreateModels.main(new String[0]);
    }

    @Before
    public void init_user_not_saved(){
        Users u = new Users();
        u.setFirstName("Edmundo");
        u.setLastName("Dantes");
        u.setEmail("conde@montecristo.com");
        u.setNote("foo bar baz");
        this.userNotSaved = u;
    }

    @Test
    public void testCreate(){
        dao.save(this.userNotSaved);
        assert this.userNotSaved.getId() == 1;
    }

    @Test
    public void testFindAll(){
        int target = new Random().nextInt(10);
        for (int i=0; i<target; i++) dao.save(new Users());

        assert dao.findAll().size() == target;
    }

    @Test
    public void testFindById(){
        dao.save(this.userNotSaved);

        Users user = dao.findOne(this.userNotSaved.getId());
        assert user.getFirstName().equals(this.userNotSaved.getFirstName());
        assert user.getLastName().equals(this.userNotSaved.getLastName());
    }

    @Test
    public void testUpdate(){
        dao.save(this.userNotSaved);
        this.userNotSaved.setFirstName("Montero");
        dao.update(this.userNotSaved);

        Users user = dao.findOne(this.userNotSaved.getId());
        assert user.getFirstName().equals("Montero");
    }

    @Test
    public void testDelete(){
        dao.save(this.userNotSaved);

        assert dao.findOne(1) != null;
        dao.delete(this.userNotSaved);
        assert dao.findOne(1) == null;

    }


}
