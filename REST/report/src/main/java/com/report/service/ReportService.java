package com.report.service;

import com.report.model.Report;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ReportService {

    List<Report> getEveryReport();

    Report getReportById(UUID reportId);

    List<Report> getReportsByReviewId(UUID reviewId) throws IOException, InterruptedException;

    Report createReport(UUID reviewId, String text) throws IOException, InterruptedException;

    String deleteReport (UUID reportId);
}
