package com.example.pdfmerge.services;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PdfMergeService {

    public File mergePdfFiles(List<File> pdfFiles) throws IOException {
        PDFMergerUtility pdfMerger = new PDFMergerUtility();
        for (File file : pdfFiles) {
            pdfMerger.addSource(file);
        }
        File mergedFile = File.createTempFile("merged", ".pdf");
        pdfMerger.setDestinationFileName(mergedFile.getAbsolutePath());
        pdfMerger.mergeDocuments(null);
        return mergedFile;
    }
}
