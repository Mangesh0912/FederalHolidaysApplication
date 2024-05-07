package com.example.federalholidaysapplication.service;

import com.example.federalholidaysapplication.model.Holiday;
import com.example.federalholidaysapplication.repository.HolidayRepository;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HolidayService {
    private final HolidayRepository holidayRepository;

    private List<String> countryList = new ArrayList<>(Arrays.asList("USA", "Canada"));

    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public Holiday addHoliday(Holiday holiday) {
        try {
            if (countryList.contains(holiday.getCountry())) {
                return this.holidayRepository.save(holiday);
            } else {
                throw new ServiceException("Currently this country is not supported");
            }
        } catch (DataIntegrityViolationException ex) {
            throw new ServiceException("A holiday with the same key already exists.", ex);
        }
    }

    @Transactional
    public Holiday updateHoliday(Long id, Holiday updatedHoliday) {
        return this.holidayRepository.findById(id)
                .map(holiday -> {
                    if (!countryList.contains(updatedHoliday.getCountry())) {
                        throw new ServiceException("Currently this country is not supported");
                    }
                    holiday.setDate(updatedHoliday.getDate());
                    holiday.setName(updatedHoliday.getName());
                    holiday.setCountry(updatedHoliday.getCountry());
                    try {
                        return this.holidayRepository.save(holiday);
                    } catch (DataIntegrityViolationException ex) {
                        throw new ServiceException("A holiday with the same key already exists.", ex);
                    }
                })
                .orElseThrow(() -> new ServiceException("Holiday not found", new Throwable("Not Found")));
    }

    public List<Holiday> listHolidays() {
        return this.holidayRepository.findAll();
    }

    public List<Holiday> getHolidaysByCountry(String countryName) {
        return this.holidayRepository.findByCountry(countryName);
    }


}
