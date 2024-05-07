package com.example.federalholidaysapplication.repository;

import com.example.federalholidaysapplication.model.Holiday;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    //Fetch holidays by country
   /* @Query("SELECT h FROM Holiday h WHERE h.holidayId.country = :country")
    List<Holiday> findHolidayByCountry(String country);*/

    List<Holiday> findByCountry(String countryName) ;
}
