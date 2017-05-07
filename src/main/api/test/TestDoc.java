package test;

import com.google.common.io.Files;
import legal.Interface.LegalProcess.ILegalProcess;
import legal.Model.LegalInfo.LegalInfoModel;
import legal.Service.LegalProcess.DocFileProcessService;
import legal.Service.LegalProcess.FileTextProcessService;
import legal.Service.LegalProcess.TextProcess;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;

/**
 * Created by Son on 4/10/2017.
 */
public class TestDoc {
    public static void main(String[] args) {
        ILegalProcess processLegal = new DocFileProcessService("data\\76799\\14161981691230.doc");
        List<String> lines = processLegal.getLines();
        for(String line : lines){
            System.out.println(line);
        }
        LegalInfoModel l = processLegal.getInfo();
        System.out.println(l);
    }

    public void r() throws IOException {
        // Start with a new document
        XWPFDocument doc = new XWPFDocument(new FileInputStream(new File("C:\\Users\\Son\\Desktop\\test.docx")));

        // Add a 3 column, 3 row table
        XWPFTable table = doc.createTable(3, 3);

// Set some text in the middle
        table.getRow(1).getCell(1).setText("EXAMPLE OF TABLE");

        // table cells have a list of paragraphs; there is an initial
// paragraph created when the cell is created. If you create a
// paragraph in the document to put in the cell, it will also
// appear in the document following the table, which is probably
// not the desired result.
        XWPFParagraph p1 = table.getRow(0).getCell(0).getParagraphs().get(0);

        XWPFRun r1 = p1.createRun();
        r1.setBold(true);
        r1.setText("The quick brown fox");
        r1.setItalic(true);
        r1.setFontFamily("Courier");
        r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
        r1.setTextPosition(100);

// And at the end
        table.getRow(2).getCell(2).setText("only text");

        // Save it out, to view in word
        FileOutputStream out = new FileOutputStream("simpleTable.docx");
        doc.write(out);
        out.close();
    }
}
