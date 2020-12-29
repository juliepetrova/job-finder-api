package com.webapp.ui.controller;

import com.webapp.ui.model.JobReport;
import com.webapp.ui.repository.JobReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/reports")
public class JobReportController {

    @Autowired
    JobReportRepository jobReportRepository;

    @GetMapping
    public List<JobReport> getAll() {
        return jobReportRepository.findAll();
    }

    @GetMapping (path = "/{reportId}")
    public ResponseEntity<JobReport> getById(@PathVariable int reportId) {
        JobReport jr = jobReportRepository.findById(reportId);
        if(jr != null){
            return ResponseEntity.ok(jr);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<JobReport> saveJobReport(@RequestBody JobReport jobReport){
        try {
            return ResponseEntity.ok(jobReportRepository.save(jobReport));
        }catch (Exception e){
            return new ResponseEntity("Save failed!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping (path = "/{reportId}")
    public void deleteJobReport(@PathVariable int reportId){
        jobReportRepository.deleteById(reportId);
    }

}
