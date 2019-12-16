package com.epam.task3.controller.common;

import com.epam.task3.entity.User;
import com.epam.task3.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class UserControllerTest {


    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
    }

    @Test
    public void getAllUsers() throws Exception {
        List<User> users = spy(new ArrayList<>());
//        users.add(user);
        int expectedSize = 100;
        doReturn(10).when(users).size();
        when(userService.readPaginatedList(1, 10)).thenReturn(users);
        when(userService.getCountRows()).thenReturn((long) expectedSize);
        mockMvc.perform(get("/userList"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("userList"))
                .andExpect(forwardedUrl("userList"))
                .andExpect(model().size(3))
                .andExpect(model().attribute("users", hasSize(10)));
//                .andExpect(model().attribute("users", hasItem(
//                        allOf(
//                                hasProperty("id", is(user.getId())),
//                                hasProperty("login", is(user.getLogin()))
//                        )
//
//                )));
        verify(userService, times(1)).readPaginatedList(1, 10);
    }
}