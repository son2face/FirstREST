package legal.Service.LegalProcess;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.util.*;

/**
 * Created by Son on 4/8/2017.
 */
public class FileTextProcessService extends TextProcess {
    public FileTextProcessService(Object data, String fileExtension) {
        super(data);
        switch (fileExtension){
            case "txt":
                txtFileUpload();
                break;
            case "pdf":
                pdfFileUpload();
                break;
            case "docx":
                docxFileUpload();
            case "doc":
                docFileUpload();
                break;
            default:
                break;
        }
        process();
    }

    public void txtFileUpload(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) data, "Unicode"));
            String temp = null;
            this.lines = new ArrayList<>();
            while ((temp = in.readLine()) != null) {
                this.lines.add(temp);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
    }

    public void pdfFileUpload(){
        try {
            PDDocument pdDocument = PDDocument.load((InputStream) data);
            if(!pdDocument.isEncrypted()){
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper Tstripper = new PDFTextStripper();
                String textData = Tstripper.getText(pdDocument);
                this.lines = Arrays.asList(textData.split("\\r\\n|\\n|\\r"));
                pdDocument.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void docFileUpload(){
        WordExtractor extractor = null;
        try
        {
            HWPFDocument document = new HWPFDocument((InputStream)data);
            extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            this.lines = Arrays.asList(fileData);
        }
        catch (Exception exep)
        {
            exep.printStackTrace();
        }
    }

    public void docxFileUpload(){
        try {
            this.lines = new ArrayList<>();
            XWPFDocument document = new XWPFDocument((InputStream)data);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph para : paragraphs) {
                lines.add(para.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
