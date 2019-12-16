package com.epam.task3.controller.common;

import com.epam.task3.entity.Hotel;
import com.epam.task3.entity.User;
import com.epam.task3.service.HotelService;
import com.epam.task3.service.impl.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
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

public class HotelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HotelServiceImpl service;

    @InjectMocks
    private HotelController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetHotels() throws Exception {
        List<Hotel> hotels = spy(new ArrayList<>());
        doReturn(10).when(hotels).size();
        when(service.readPaginatedList(1, 10)).thenReturn(hotels);
        when(service.getCountRows()).thenReturn(100L);
        mockMvc.perform(get("/hotelList"))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(view().name("hotelList"))
//                .andExpect(forwardedUrl("hotelList"))
//                .andExpect(model().size(3))
//                .andExpect(model().attribute("hotels", hasSize(10)));
        verify(service, times(1)).readPaginatedList(1, 10);

    }
}