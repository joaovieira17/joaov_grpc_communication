package com.report.controllers;

import com.report.model.Report;
import com.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/review/report")
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping("/list")
    public Iterable<Report> getEveryReport(){
        return service.getEveryReport();
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<Report> getByReportId(@PathVariable("reportId") final UUID reportId) {
        final Report report = service.getReportById(reportId);
        return ResponseEntity.ok().body(report);
    }

    @GetMapping("/listByReview/{reviewId}")
    public Iterable<Report> getReportsByReviewId(@PathVariable("reviewId") final UUID reviewId) {
        return service.getReportsByReviewId(reviewId);
    }

    @PostMapping(value = "/create/{reviewId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Report> createReport(@PathVariable final UUID reviewId, @RequestBody final String text) {
        final Report report = service.createReport(reviewId,text);
        return ResponseEntity.status(HttpStatus.CREATED).body(report);
    }

    @DeleteMapping(value = "/delete/{reportId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteReport(@PathVariable("reportId")final UUID reportId){
        return ResponseEntity.ok(service.deleteReport(reportId));
    }
}
