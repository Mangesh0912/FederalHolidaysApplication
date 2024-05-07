package com.example.federalholidaysapplication.controller;

import com.example.federalholidaysapplication.model.ApiResponseObject;
import com.example.federalholidaysapplication.model.Holiday;
import com.example.federalholidaysapplication.service.HolidayService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {
    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @PostMapping
    @Operation(summary = "Add a new holiday", description = "Creates a new holiday entry in the database.")
    @ApiResponse(responseCode = "200", description = "Holiday created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseObject.class)))
    public ResponseEntity<ApiResponseObject<Holiday>> addHoliday(@RequestBody Holiday holiday) {
        Holiday savedHoliday = this.holidayService.addHoliday(holiday);
        ApiResponseObject<Holiday> response = new ApiResponseObject<>(savedHoliday, null, null);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing holiday", description = "Updates an existing holiday identified by its ID.")
    @ApiResponse(responseCode = "200", description = "Holiday updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = com.example.federalholidaysapplication.model.ApiResponseObject.class)))
    public ResponseEntity<ApiResponseObject<Holiday>> updateHoliday(@PathVariable Long id, @RequestBody Holiday holiday) {
         Holiday updatedHoliday = this.holidayService.updateHoliday(id, holiday);
         ApiResponseObject<Holiday> response = new ApiResponseObject<>(updatedHoliday, null, null);
         return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "List all holidays by country",
            description = "Retrieve a list of holidays filtered by country name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseObject.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid country name supplied"),
                    @ApiResponse(responseCode = "404", description = "Holidays not found")
            })
    public ResponseEntity<ApiResponseObject<Holiday>> listHolidays(@RequestParam String countryName) {
        List<Holiday> holidayList  = this.holidayService.getHolidaysByCountry(countryName);
        ApiResponseObject<Holiday> response = new ApiResponseObject<>(null, null, holidayList);
        return ResponseEntity.ok(response);
    }


}
