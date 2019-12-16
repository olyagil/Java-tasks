package com.epam.task3.controller.admin;

import com.epam.task3.entity.User;
import com.epam.task3.entity.enumerution.Roles;
import com.epam.task3.service.impl.UserServiceImpl;
import com.epam.task3.validator.Validator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class AdminUserControllerTest {

    @Mock
    private UserServiceImpl userService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminUserController userController;

    private MockMvc mockMvc;
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = User
                .builder()
                .id(1L)
                .login("login15JK")
                .password("password15JK")
                .role(Roles.USER)
                .build();
    }

    @Test
    public void testGetPaginatedListOfUsers() throws Exception {
        List<User> users = spy(new ArrayList<>());
        users.add(user);
        int expectedSize = 100;
        doReturn(10).when(users).size();
        when(userService.readPaginatedList(1, 10)).thenReturn(users);
        when(userService.getCountRows()).thenReturn((long) expectedSize);
        mockMvc.perform(get("/admin/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/users"))
                .andExpect(forwardedUrl("admin/users"))
                .andExpect(model().size(5))
                .andExpect(model().attribute("users", hasSize(10)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(user.getId())),
                                hasProperty("login", is(user.getLogin()))
                        )

                )));
        verify(userService, times(1)).readPaginatedList(1, 10);
    }

    @Test
    public void testEditUser() throws Exception {
        when(userService.read(anyLong())).thenReturn(user);
        this.mockMvc.perform(get("/admin/edit-user-{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user/edit"))
                .andExpect(forwardedUrl("admin/user/edit"))
                .andExpect(model().attribute("user", is(user)));
    }

    @Test
    public void saveUser() throws Exception {
        when(userService.create(any(User.class))).thenReturn(1L);
        when(userService.read(anyString())).thenReturn(null);
        when(Validator.checkLogin(anyString())).thenReturn(true);
        when(Validator.checkPassword(anyString())).thenReturn(true);
        when(passwordEncoder.encode(anyString())).thenReturn("test");
        mockMvc.perform(
                post("/admin/save-user"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/users?message=User+nullregistered+successfully"))
                .andExpect(header().string("location", containsString("/admin/users")))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateUser() {
        fail();
    }

    @Test
    public void addUser() throws Exception {
        mockMvc.perform(
                get("/admin/add-user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user/add"))
                .andExpect(model().size(1))
                .andExpect(model().attribute("user", new User()));
    }

    @Test
    public void deleteUser() throws Exception {
        when(userService.delete(anyLong())).thenReturn(true);
        mockMvc.perform(post("/admin/delete-user"))
                .andExpect(status().isAccepted())
                .andExpect(view().name("redirect:/admin/users"));
        verify(userService, times(1)).delete(user.getId());
        verifyNoMoreInteractions(userService);
    }
}