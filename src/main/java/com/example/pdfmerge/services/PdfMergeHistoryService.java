package com.example.pdfmerge.services;

import com.example.pdfmerge.model.PdfMergeHistory;
import com.example.pdfmerge.repository.PdfMergeHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdfMergeHistoryService {

    private final PdfMergeHistoryRepository repository;

    public PdfMergeHistoryService(PdfMergeHistoryRepository repository) {
        this.repository = repository;
    }

    // Método para salvar o histórico de mesclagens
    public void save(PdfMergeHistory history) {
        repository.save(history);
    }

    // Método para buscar todos os históricos de mesclagens
    public List<PdfMergeHistory> findAll() {
        return repository.findAll();
    }
}
