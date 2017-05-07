package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * Created by Son on 4/9/2017.
 */
public class testMethod {
    /** Path to the resulting PDF file. */
    public static final String RESULT
            = "C:\\Users\\Son\\Desktop\\hello.pdf";

    /**
     * Creates a PDF file: hello_narrow.pdf
     * @param    args    no arguments needed
     */
    public static void main(String[] args)
    {
        System.out.println(decompose("Ph? L?c.doc"));
    }

    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
    }
    public static String decompose(String s) {
        return s.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}
