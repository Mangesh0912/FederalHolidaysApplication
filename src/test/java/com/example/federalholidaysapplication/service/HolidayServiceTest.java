package com.example.federalholidaysapplication.service;

import com.example.federalholidaysapplication.controller.HolidayController;
import com.example.federalholidaysapplication.model.Holiday;
import com.example.federalholidaysapplication.repository.HolidayRepository;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HolidayServiceTest {

    @Mock
    private HolidayRepository holidayRepository;

    @InjectMocks
    private HolidayService holidayService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addHolidayTest() {
        Holiday holiday = new Holiday("USA", "Christmas", new Date());
        when(holidayRepository.save(holiday)).thenReturn(holiday);
        Holiday result = holidayService.addHoliday(holiday);
        assertEquals(holiday, result);
        //when currently currently not available is given
        Holiday holiday1 = new Holiday("USA1", "Christmas", new Date());
        assertThrows(ServiceException.class, () -> holidayService.addHoliday(holiday1));
    }

    @Test
    void updateHolidayTest() {
        Holiday existingHoliday = new Holiday("USA", "New Year", new Date());
        Holiday updatedHoliday = new Holiday("USA", "New Year", new Date());

        when(holidayRepository.findById(1L)).thenReturn(Optional.of(existingHoliday));
        when(holidayRepository.save(existingHoliday)).thenReturn(updatedHoliday);

        Holiday result = holidayService.updateHoliday(1L, updatedHoliday);
        assertEquals(updatedHoliday, result);

        Holiday existingHoliday1 = new Holiday("Mexico", "New Year", new Date());
        Holiday updatedHoliday1 = new Holiday("Mexico", "New Year", new Date());

        when(holidayRepository.findById(1L)).thenReturn(Optional.of(existingHoliday1));
        when(holidayRepository.save(existingHoliday)).thenReturn(updatedHoliday1);
        assertThrows(ServiceException.class, () -> holidayService.updateHoliday(1L, updatedHoliday1));
    }

    @Test
    void getHolidaysByCountryTest() {
        List<Holiday> holidays = Arrays.asList(new Holiday("USA", "Christmas", new Date()));
        when(holidayRepository.findByCountry("USA")).thenReturn(holidays);

        List<Holiday> holidayList = holidayService.getHolidaysByCountry("USA");
        assertEquals(holidayList.size(), 1);

        List<Holiday> holidayList1 = holidayService.getHolidaysByCountry("Canada");
        assertEquals(holidayList1.size(), 0);
    }

}
