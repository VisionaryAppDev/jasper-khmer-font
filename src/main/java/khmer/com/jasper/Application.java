package khmer.com.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws JRException, FileNotFoundException {
        SpringApplication.run(Application.class, args);

        ///
        generateSamplePdf();
    }



    private static void generateSamplePdf() throws FileNotFoundException, JRException {
        ///Load template
        File file = ResourceUtils.getFile("classpath:static_khmer_font_sample.jrxml");
        InputStream input = new FileInputStream(file);


        // Load data from data source
        List<String> listOfData = Arrays.asList("1", "2", "3");
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(listOfData);


        /// Compile
        JasperReport jasperReport = JasperCompileManager.compileReport(input);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, source);


        /// Generate
        JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources/generate-khmer-font.pdf" );
        System.out.println("PDF File Generated !!");
    }
}