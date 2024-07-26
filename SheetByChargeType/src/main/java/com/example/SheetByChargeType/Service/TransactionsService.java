package com.example.SheetByChargeType.Service;

import com.example.SheetByChargeType.DTO.NetTotalDto;
import com.example.SheetByChargeType.Repositories.TransactionRepository;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class TransactionsService {
    @Autowired
    private TransactionRepository transactionRepository;

    private static void addHeader(Table table) {
        Cell header1 = new Cell().add(new Paragraph("Charge Type"));
        header1.setBackgroundColor(ColorConstants.GRAY);
        table.addHeaderCell(header1);

        Cell header2 = new Cell().add(new Paragraph("Total Credit"));
        header2.setBackgroundColor(ColorConstants.GRAY);
        table.addHeaderCell(header2);

        Cell header3 = new Cell().add(new Paragraph("Total Debit"));
        header3.setBackgroundColor(ColorConstants.GRAY);
        table.addHeaderCell(header3);

        Cell header4 = new Cell().add(new Paragraph("Net Total"));
        header4.setBackgroundColor(ColorConstants.GRAY);
        table.addHeaderCell(header4);
    }


    public static ByteArrayOutputStream generatePdfStream(List<Map<String, Object>> queryResults, List<NetTotalDto> dtoList) throws DocumentException, DocumentException, FileNotFoundException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfDocument pdf= new PdfDocument(new com.itextpdf.kernel.pdf.PdfWriter(outputStream));
        Document document = new Document(pdf);
        float[] columnWidths = {3, 3, 3, 3};
        Table table = new Table(columnWidths);
        addHeader(table);
        for (NetTotalDto dto : dtoList) {
            table.addCell(new Cell().add(new Paragraph(dto.getChargeType())));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(dto.getTotalCredit()))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(dto.getTotalDebit()))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(dto.getNetTotal()))));
        }
        document.add(table);
        document.close();
        return outputStream;
    }

    public ByteArrayOutputStream getTransactionsTotal(String orgCode, Date startDate, Date endDate) throws DocumentException, FileNotFoundException {
        List<NetTotalDto>   listOfDto =transactionRepository.findAggregatedResults(orgCode,startDate,endDate);
        List<Map<String,Object>> list= new ArrayList<>();
        for(NetTotalDto data : listOfDto) {
            Map<String, Object> chargeTypetoValue = new HashMap<>();
            chargeTypetoValue.put("chargeType", data.getChargeType());
            chargeTypetoValue.put("totalCredit", data.getTotalCredit());
            chargeTypetoValue.put("totalDebit", data.getTotalDebit());
            chargeTypetoValue.put("netTotal", data.getNetTotal());
            list.add(chargeTypetoValue);
        }
        ByteArrayOutputStream pdfStream = generatePdfStream(list,listOfDto);
        return pdfStream ;
    }
}
