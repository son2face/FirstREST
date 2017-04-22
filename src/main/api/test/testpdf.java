package test;

import com.google.common.io.Files;
import legal.Interface.LegalProcess.ILegalProcess;
import legal.Service.LegalProcess.FileTextProcessService;
import legal.Service.LegalProcess.TextProcess;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Son on 4/8/2017.
 */
public class testpdf {
    public static void main(String[] args) {
        File initialFile = new File("C:\\Users\\Son\\Desktop\\test.pdf");
        try {
            InputStream targetStream = Files.asByteSource(initialFile).openStream();
            ILegalProcess processLegal = new FileTextProcessService(targetStream,"pdf");
            for(String x:((TextProcess)processLegal).lines){
                System.out.println(x);
            }
            System.out.println(processLegal.getInfo().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
