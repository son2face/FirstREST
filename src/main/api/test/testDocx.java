package test;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Created by Son on 4/9/2017.
 */
public class testDocx {
        public static void readDocxFile(String fileName) {

            try {
                File file = new File(fileName);
                FileInputStream fis = new FileInputStream(file.getAbsolutePath());
                XWPFDocument document = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphs = document.getParagraphs();
                for (XWPFParagraph para : paragraphs) {
                    System.out.println(para.getText());
                    System.out.println();
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public static void main(String[] args) {
            readDocxFile("C:\\Users\\Son\\Desktop\\text.docx");
        }
}
