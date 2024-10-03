package com.hr.management.controller;

import com.hr.management.request.JobHistoryRequest;
import com.hr.management.response.ResponseHandler;
import com.hr.management.service.JobHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("${apiPrefix}/job_history")
@RequiredArgsConstructor
public class JobHistoryController {
    private final JobHistoryService jobHistoryService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllJobHistory(){
        return ResponseHandler.responseBuilder("Requested job histories list is given here",
                HttpStatus.OK,
                jobHistoryService.getAllJobHistory());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Object> getAllJobHistory(@PathVariable Long employeeId){
        return ResponseHandler.responseBuilder("Requested job histories list by employee ID is given here",
                HttpStatus.OK,
                jobHistoryService.getJobHistorByEmployeeId(employeeId));
    }

    @GetMapping("")
    public ResponseEntity<?> getJobHistoryByIdAndDate(@RequestParam("employeeId") Long employeeId,
                                                                        @RequestParam("startDate")LocalDate startDate,
                                                                        @RequestParam("endDate") LocalDate endDate
    ){
            return ResponseHandler.responseBuilder("Requested job histories list by employee ID,start date and end date is given here",
                    HttpStatus.OK,
                    jobHistoryService.getJobHistorByIdAndDate(employeeId, startDate, endDate));
    }

    @PostMapping("")
    public ResponseEntity<Object> createJobHistory(@Valid @RequestBody JobHistoryRequest jobHistoryRequest,
                                              BindingResult result){
            if(result.hasErrors()){
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseHandler.responseBuilder("There some errors while inputting data",
                        HttpStatus.BAD_REQUEST,
                        errorMessages);
            }

        return ResponseHandler.responseBuilder("Job histories has been created successfully",
                HttpStatus.OK,
                jobHistoryService.createJobHistory(jobHistoryRequest));

    }

    @PutMapping("")
    public ResponseEntity<Object> updateJobHistory(@Valid @RequestBody JobHistoryRequest jobHistoryRequest,
    @RequestParam("employeeId") Long employeeId,
    @RequestParam("startDate")LocalDate startDate,
    BindingResult result) {
            if(result.hasErrors()){
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseHandler.responseBuilder("There some errors while inputting data",
                        HttpStatus.BAD_REQUEST,
                        errorMessages);
            }
        return ResponseHandler.responseBuilder("Job histories has been updated successfully",
                HttpStatus.OK,
                jobHistoryService.updateJobHistory(employeeId, startDate ,jobHistoryRequest));

    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteJobHistory(@RequestParam("employeeId") Long employeeId,
                                                                        @RequestParam("startDate")LocalDate startDate
    ){
            jobHistoryService.deleteJobHistory(employeeId, startDate);
            return ResponseEntity.ok("Delete job history successfully!");
    }
}
