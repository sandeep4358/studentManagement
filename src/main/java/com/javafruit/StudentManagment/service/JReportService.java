package com.javafruit.StudentManagment.service;

import com.javafruit.StudentManagment.model.Student;
import com.javafruit.StudentManagment.repo.StudentRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface JReportService  {

    public void exportJasperReport(HttpServletResponse response) throws IOException, JRException;
}
