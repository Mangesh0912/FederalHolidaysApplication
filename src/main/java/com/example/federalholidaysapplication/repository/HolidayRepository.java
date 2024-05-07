package com.example.federalholidaysapplication.repository;

import com.example.federalholidaysapplication.model.Holiday;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    List<Holiday> findByCountry(String countryName) ;
}
