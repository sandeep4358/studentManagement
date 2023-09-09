package com.javafruit.StudentManagment.controller;

import com.javafruit.StudentManagment.service.JReportService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/report")

public class ReportController {
    @Autowired
    private JReportService service;

    @GetMapping("/jasperpdf/StudentDetils")
    public void getStudentDetailsPdf(HttpServletResponse response) throws JRException, IOException {
        //set the response content type
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        //why to set this content disposition
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        service.exportJasperReport(response);

    }

}

/**
 * Reference :- https://simplifyingtechcode.wordpress.com/2023/03/04/how-to-export-pdf-using-jasperreports-spring-boot/
 */
