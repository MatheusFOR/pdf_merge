package com.example.pdfmerge.repository;

import com.example.pdfmerge.model.PdfMergeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfMergeHistoryRepository extends JpaRepository<PdfMergeHistory, Long> {
}
