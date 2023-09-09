package com.javafruit.StudentManagment.service.impl;

import com.javafruit.StudentManagment.model.Student;
import com.javafruit.StudentManagment.repo.StudentRepository;
import com.javafruit.StudentManagment.service.JReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
public class JReportServiceImpl implements JReportService {
    final private StudentRepository repository;

    public void exportJasperReport(HttpServletResponse response) throws IOException, JRException {
        List<Student> studentList =  repository.findAll();
        /**
         * Step 1) NOw get the JasperFile and compile it
         */
        File file  = ResourceUtils.getFile("classpath:Student_information.jrxml");
        JasperReport jasperReport =  JasperCompileManager.compileReport(file.getAbsolutePath());


        // Step 2) create data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(studentList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Sandeep Arya");

        //Step 3) Filling the data in the jasper report
        JasperPrint jasperPrint =  JasperFillManager.fillReport(jasperReport,parameters,dataSource);

        //Step 4) Exporting Jasper report to HTTPServletResponse

        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());

    }


}