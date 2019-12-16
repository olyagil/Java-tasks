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

import java.security.Principal;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class LoginControllerTest {
    @Mock
    private UserServiceImpl userService;
    @Mock
    private Principal principal;

    @InjectMocks
    private LoginController controller;


    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void login() throws Exception {
        this.mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/profile"))
                .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void addUser() throws Exception {
        this.mockMvc.perform(get("/register"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void saveUser() throws Exception {
        this.mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/profile"))
                .andExpect(redirectedUrl("/profile"))
                .andReturn(); }

    @Test
    public void accessDenied() throws Exception {
        when(principal.getName()).thenReturn(null);
        this.mockMvc.perform(get("/error"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andReturn(); }

    @Test
    public void profile() throws Exception {
        when(principal.getName()).thenReturn("admin");
        when(userService.read(anyString())).thenReturn(new User());
        this.mockMvc.perform(get("/profile"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andReturn();
    }

    @Test
    public void getAdmin() throws Exception {
        this.mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/profile"))
                .andExpect(redirectedUrl("/profile"))
                .andReturn();
    }

    @Test
    public void testHomePage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andDo(print())
                .andReturn();
    }
}