package com.hr.management.controller;

import com.hr.management.exception.JobHasAssociatedEmployeeException;
import com.hr.management.request.JobsRequest;
import com.hr.management.response.ResponseHandler;
import com.hr.management.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/{jobId}")
    public ResponseEntity<Object> getJobById(@PathVariable("jobId") Long id) {
        return ResponseHandler.responseBuilder("Requested job is given here",
                HttpStatus.OK,
                jobService.getJobById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllJobs(){
        return ResponseHandler.responseBuilder("Requested jobs list is given here",
                HttpStatus.OK,
                jobService.getAllJobs());
    }

    @PostMapping("")
    public ResponseEntity<Object> createJob(@RequestBody @Valid JobsRequest jobsRequest,
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
        return ResponseHandler.responseBuilder("Job has been created successfully",
                HttpStatus.OK,
                jobService.createJob(jobsRequest));

    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Object> updateJob(@PathVariable("jobId") Long id,
                                            @RequestBody @Valid JobsRequest jobsRequest,
                                            BindingResult result)  {
        if(result.hasErrors()){
            List<String> errorMessages =  result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            return ResponseHandler.responseBuilder("There some errors while inputting data",
                    HttpStatus.BAD_REQUEST,
                    errorMessages);
        }

        return ResponseHandler.responseBuilder("Job has been updated successfully",
                HttpStatus.OK,
                jobService.updateJob(id, jobsRequest));
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable("jobId") Long id) throws JobHasAssociatedEmployeeException {
            jobService.deleteJob(id);
            return ResponseEntity.ok("Delete successfully job with id " + id);

    }


}
