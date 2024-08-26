package com.prasad.fileproc;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

@Service
public class CreatePDF {
    private static final Logger logger = LoggerFactory.getLogger(CreatePDF.class);
    public void generateNow(String custId, String custName) {
        logger.info("Inside generateNow for "+ custId +" and "+ custName);
        String pdfPath = "output/"+ custId +".pdf";
        try (PDDocument document = new PDDocument()) {

            // Add a blank page to the document
            PDPage page = new PDPage();
            document.addPage(page);

            // Start a new content stream which will "hold" the content of the page
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Set font and font size
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                // Begin the text content
                contentStream.beginText();
                // Set the position for the text
                contentStream.newLineAtOffset(100, 700);
                // Add the actual text
                contentStream.showText("Hello, World! This is a PDF created using Apache PDFBox for "+ custName);
                // End the text content
                contentStream.endText();
            }

            // Save the document to the specified path
            document.save(pdfPath);
            System.out.println("PDF created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
