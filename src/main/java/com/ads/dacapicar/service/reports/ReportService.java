package com.ads.dacapicar.service.reports;

import com.ads.dacapicar.entities.Car;
import com.ads.dacapicar.exception.CarNotFoundException;
import com.ads.dacapicar.repository.CarRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportService {

    @Autowired
    private CarRepository carRepository;

    public void generateCarReportPdf(String filePath) throws FileNotFoundException {
        List<Car> cars = carRepository.findAll();

        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Relatório de Carros"));

        float[] columnWidths = {1, 4, 3, 2, 3, 4};
        Table table = new Table(columnWidths);

        table.addHeaderCell("ID");
        table.addHeaderCell("Nome");
        table.addHeaderCell("Preço");
        table.addHeaderCell("Ano");
        table.addHeaderCell("Cidade");
        table.addHeaderCell("Empresa");

        cars.forEach(car -> {
            table.addCell(String.valueOf(car.getId()));
            table.addCell(car.getName());
            table.addCell(String.valueOf(car.getPrice()));
            table.addCell(String.valueOf(car.getYear()));
            table.addCell(car.getCity());
            table.addCell(car.getCompany());
        });

        document.add(table);
        document.close();
    }

    public void generateSingleCarReportPdf(Long carId, String filePath) throws FileNotFoundException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Carro não encontrado com o ID: " + carId));

        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Relatório do Carro: " + car.getName()));

        float[] columnWidths = {1, 4, 3, 2, 3, 4};
        Table table = new Table(columnWidths);

        table.addHeaderCell("ID");
        table.addHeaderCell("Nome");
        table.addHeaderCell("Preço");
        table.addHeaderCell("Ano");
        table.addHeaderCell("Cidade");
        table.addHeaderCell("Empresa");

        table.addCell(String.valueOf(car.getId()));
        table.addCell(car.getName());
        table.addCell(String.valueOf(car.getPrice()));
        table.addCell(String.valueOf(car.getYear()));
        table.addCell(car.getCity());
        table.addCell(car.getCompany());

        document.add(table);
        document.close();
    }
    @Async
    public CompletableFuture<String> generateCarReportAsync(String filePath) throws FileNotFoundException {
        generateCarReportPdf(filePath);
        return CompletableFuture.completedFuture("Relatório gerado com sucesso.");
    }
}