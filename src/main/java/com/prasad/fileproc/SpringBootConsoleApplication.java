package com.prasad.fileproc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;


@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Autowired
    CreatePDF createPDFService;

    private static final Logger logger = LoggerFactory.getLogger(SpringBootConsoleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
        logger.info("Application completed");
        System.exit(0);
    }


    @Override
    public void run(String... args) throws Exception {
        logger.info("Inside run method");
        String relativePath = "data/sample.txt";
        logger.info("File : " + relativePath);
        try (BufferedReader br = new BufferedReader(new FileReader(relativePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split(";");
                logger.info("Gernerating PDF for " + arrOfStr[1]);
                createPDFService.generateNow(arrOfStr[0], arrOfStr[1]);
            }
            logger.info("All PDFs generated");
        } catch (Exception e) {
            logger.info("Inside catch");
            e.printStackTrace();
        }
    }
}
