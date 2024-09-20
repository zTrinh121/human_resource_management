package com.hr.management.controller;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.request.JobHistoryRequest;
import com.hr.management.response.JobHistoryFullResponse;
import com.hr.management.service.JobHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    JobHistoryService jobHistoryService;

    @GetMapping("/all")
    public ResponseEntity<List<JobHistoryFullResponse>> getAllJobHistory(){
        return ResponseEntity.ok(jobHistoryService.getAllJobHistory());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<JobHistoryFullResponse>> getAllJobHistory(@PathVariable Long employeeId){
        return ResponseEntity.ok(jobHistoryService.getJobHistorByEmployeeId(employeeId));
    }

    @GetMapping("")
    public ResponseEntity<?> getJobHistorByIdAndDate(@RequestParam("employeeId") Long employeeId,
                                                                        @RequestParam("startDate")LocalDate startDate,
                                                                        @RequestParam("endDate") LocalDate endDate
    ){
        try {
            JobHistoryFullResponse jobHistoryFullResponse = jobHistoryService.getJobHistorByIdAndDate(employeeId, startDate, endDate);
            return ResponseEntity.ok(jobHistoryFullResponse);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createJobHistory(@Valid @RequestBody JobHistoryRequest jobHistoryRequest,
                                              BindingResult result){
        try {

            if(result.hasErrors()){
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }

            JobHistoryFullResponse jobHistoryFullResponse = jobHistoryService.createJobHistory(jobHistoryRequest);
            return  ResponseEntity.ok(jobHistoryFullResponse);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateJobHistory(@Valid @RequestBody JobHistoryRequest jobHistoryRequest,
    @RequestParam("employeeId") Long employeeId,
    @RequestParam("startDate")LocalDate startDate,
    BindingResult result) {
        try {

            if(result.hasErrors()){
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }

            JobHistoryFullResponse jobHistoryFullResponse = jobHistoryService.updateJobHistory(employeeId, startDate ,jobHistoryRequest);
            return  ResponseEntity.ok(jobHistoryFullResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteJobHistory(@RequestParam("employeeId") Long employeeId,
                                                                        @RequestParam("startDate")LocalDate startDate
    ){
        try {
            jobHistoryService.deleteJobHistory(employeeId, startDate);
            return ResponseEntity.ok("Delete job history successfully!");
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
