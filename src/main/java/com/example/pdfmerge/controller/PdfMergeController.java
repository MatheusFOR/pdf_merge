package com.example.pdfmerge.controller;

import com.example.pdfmerge.services.PdfMergeService;
import com.example.pdfmerge.services.PdfMergeHistoryService; // Adicione o serviço para o histórico
import com.example.pdfmerge.model.PdfMergeHistory; // Adicione o modelo para o histórico
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PdfMergeController {

    private final PdfMergeService pdfMergeService;
    private final PdfMergeHistoryService pdfMergeHistoryService; // Serviço para gerenciar o histórico

    public PdfMergeController(PdfMergeService pdfMergeService, PdfMergeHistoryService pdfMergeHistoryService) {
        this.pdfMergeService = pdfMergeService;
        this.pdfMergeHistoryService = pdfMergeHistoryService;
    }

    @PostMapping("/api/merge-pdfs")
    public ResponseEntity<FileSystemResource> mergePdfs(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("email") String email,
            @RequestParam("fileName") String fileName) {

        // Validar o e-mail
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Retornar erro se o e-mail não for fornecido
        }

        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                File tempFile = File.createTempFile("temp", ".pdf");
                file.transferTo(tempFile);
                fileList.add(tempFile);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }

        try {
            File mergedFile = pdfMergeService.mergePdfFiles(fileList);
            FileSystemResource resource = new FileSystemResource(mergedFile);

            // Salvar o histórico
            PdfMergeHistory history = new PdfMergeHistory();
            history.setEmail(email);
            history.setFileName(fileName);
            history.setCreatedDate(LocalDateTime.now());
            pdfMergeHistoryService.save(history);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf");
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/api/merge-history")
    public ResponseEntity<List<PdfMergeHistory>> getMergeHistory() {
        List<PdfMergeHistory> history = pdfMergeHistoryService.findAll();
        return ResponseEntity.ok(history);
    }
}
