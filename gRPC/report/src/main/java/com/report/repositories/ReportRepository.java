package com.report.repositories;

import com.report.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

    @Query("SELECT r FROM Report r")
    List<Report> getEveryReport();

    @Query("SELECT r FROM Report r WHERE r.reportId = :reportId")
    Report getReportById(@Param("reportId") UUID reportId);

    @Query("SELECT r FROM Report r WHERE r.reviewId = :reviewId")
    List<Report> getReportsByReviewId(@Param("reviewId") UUID reviewId);
}
