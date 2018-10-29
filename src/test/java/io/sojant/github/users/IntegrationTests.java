package io.sojant.github.users;

import com.google.gson.Gson;
import io.sojant.github.users.dao.UsersDao;
import io.sojant.github.users.model.Users;
import io.sojant.github.users.tasks.CreateModels;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {

    private Users userNotSaved;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Autowired
    private UsersDao dao;

	@Autowired
	private MockMvc mvc;

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
	public void contextLoads() {
	}

	@Test
	public void post_user() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(this.userNotSaved);
		this.mvc.perform(post("/user").contentType(APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isOk());
	}

    @Test
    public void get_user() throws Exception {
        dao.save(this.userNotSaved);
        this.mvc.perform(get(String.format("/user/%s", this.userNotSaved.getId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is(this.userNotSaved.getFirstName())));

    }

    @Test
    public void get_users() throws Exception{
        int target = new Random().nextInt(10);
        for (int i=0; i<target; i++) dao.save(new Users());

        this.mvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(target)));

    }



}
