package com.report.service;

import com.report.model.Report;
import com.report.repositories.ReportRepository;
import com.report.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    private ReportRepository repository;

    @Autowired
    private JwtUtils jwtUtils;

    private HttpRequestHelper helper = new HttpRequestHelper();

    @Override
    public List<Report> getEveryReport() {
        return repository.getEveryReport();
    }

    @Override
    public Report getReportById(UUID reportId) {
        Optional<Report> reportOptional=repository.findById(reportId);
        if (reportOptional.isPresent())
            return reportOptional.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Report Not Found");
    }

    @Override
    public List<Report> getReportsByReviewId(UUID reviewId) throws IOException, InterruptedException {

        if(helper.reviewExistence(reviewId)){
            return repository.getReportsByReviewId(reviewId);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Review Not Found");
        }
    }

    @Override
    public Report createReport(UUID reviewId, String text) throws IOException, InterruptedException {
        if (helper.reviewExistence(reviewId)){
            Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));
            Report report = new Report(reviewId,text, userId);
            repository.save(report);
            return report;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Review Not Found");
        }
    }

    @Override
    public String deleteReport(UUID reportId) {
        Optional<Report> reportOptional=repository.findById(reportId);
        if (reportOptional.isPresent()) {
            repository.delete(reportOptional.get());
            return "Report Deleted";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Report Not Found");
        }
    }
}
