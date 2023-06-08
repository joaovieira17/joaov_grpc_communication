package com.report.service;

import com.report.model.Report;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ReportService {

    List<Report> getEveryReport();

    Report getReportById(UUID reportId);

    List<Report> getReportsByReviewId(UUID reviewId);

    Report createReport(UUID reviewId, String text);

    String deleteReport (UUID reportId);
}
