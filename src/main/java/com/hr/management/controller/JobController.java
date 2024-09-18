package com.hr.management.controller;

import com.hr.management.model.Jobs;
import com.hr.management.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/jobs")
@RequiredArgsConstructor
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/{jobId}")
    public ResponseEntity<?> getJobById(@PathVariable("jobId") Long id){
        Jobs job = jobService.getJobById(id);
        if(job == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No job found with id = " + id);
        }
        return ResponseEntity.ok(job);
    }
}
