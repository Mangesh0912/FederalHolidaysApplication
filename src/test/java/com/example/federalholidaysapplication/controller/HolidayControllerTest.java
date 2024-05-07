package com.example.federalholidaysapplication.controller;

import com.example.federalholidaysapplication.model.ApiResponseObject;
import com.example.federalholidaysapplication.model.Holiday;
import com.example.federalholidaysapplication.service.HolidayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HolidayControllerTest {

    @Mock
    private HolidayService holidayService;

    @InjectMocks
    private HolidayController holidayController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addHolidayTest() {
        Holiday holiday = new Holiday("Christmas", "USA", new Date());
        when(holidayService.addHoliday(holiday)).thenReturn(holiday);

        ResponseEntity<ApiResponseObject<Holiday>> response = holidayController.addHoliday(holiday);
        assertEquals(200, response.getStatusCodeValue());
        verify(holidayService).addHoliday( holiday);
    }

    @Test
    void updateHolidayTest() {
        Holiday holiday = new Holiday("New Year", "USA", new Date());
        when(holidayService.updateHoliday(1L, holiday)).thenReturn(holiday);

        ResponseEntity<ApiResponseObject<Holiday>> response = holidayController.updateHoliday(1L, holiday);
        assertEquals(200, response.getStatusCodeValue());
        verify(holidayService).updateHoliday(1L, holiday);
    }

    @Test
    void listHolidaysTest() {
        List<Holiday> holidays = Arrays.asList(new Holiday("Christmas", "USA", new Date()));
        when(holidayService.getHolidaysByCountry("USA")).thenReturn(holidays);

        ResponseEntity<ApiResponseObject<Holiday>> response = holidayController.listHolidays("USA");
        assertEquals(200, response.getStatusCodeValue());
        verify(holidayService).getHolidaysByCountry("USA");
    }
}
