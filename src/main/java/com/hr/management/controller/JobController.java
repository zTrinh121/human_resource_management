package com.hr.management.controller;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.model.Jobs;
import com.hr.management.request.JobsRequest;
import com.hr.management.response.JobsResponse;
import com.hr.management.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/jobs")
@RequiredArgsConstructor
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/{jobId}")
    public ResponseEntity<?> getJobById(@PathVariable("jobId") Long id) {
        JobsResponse job = jobService.getJobById(id);
        if(job == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No job found with id = " + id);
        }
        return ResponseEntity.ok(job);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobsResponse>> getAllJobs(){
        List<JobsResponse> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("")
    public ResponseEntity<JobsResponse> createJob(@RequestBody @Valid JobsRequest jobsRequest){
        JobsResponse jobsResponse = jobService.createJob(jobsRequest);
        return ResponseEntity.ok(jobsResponse);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<JobsResponse> updateJob(@PathVariable("jobId") Long id,
                                                  @RequestBody JobsRequest jobsRequest) throws Exception {
        return ResponseEntity.ok(jobService.updateJob(id, jobsRequest));
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable("jobId") Long id){

        try {
            jobService.deleteJob(id);
            return ResponseEntity.ok("Delete successfully job with id " + id);

        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No job found with id = " + id);
        }
    }


}
