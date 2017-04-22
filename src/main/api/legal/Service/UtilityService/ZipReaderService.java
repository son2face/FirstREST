package legal.Service.UtilityService;

import legal.Entity.Utility.DataZipEntity;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Son on 4/18/2017.
 */
public class ZipReaderService {
    public static List<DataZipEntity> read(File file) {
        List<DataZipEntity> dataZipEntities = new ArrayList<>();
        try {
            final ZipFile zipFile = new ZipFile(file);
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry entry = entries.nextElement();
                DataZipEntity dataZipEntity = new DataZipEntity(entry.getName(),"",entry.getSize(),zipFile.getInputStream(entry));
                dataZipEntities.add(dataZipEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataZipEntities;
    }

    public static List<DataZipEntity> read(String fileUrl) {
        List<DataZipEntity> dataZipEntities = new ArrayList<>();
        try {

            ZipFile zipFile = new ZipFile(new File(fileUrl));
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                int flag = fileUrl.lastIndexOf(".");
                DataZipEntity dataZipEntity = new DataZipEntity(decompose(entry.getName()),fileUrl.substring(0,flag-1).trim(),entry.getSize(),zipFile.getInputStream(entry));
                dataZipEntities.add(dataZipEntity);
            }
        } catch (IllegalArgumentException ex){
            System.out.println(fileUrl);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return dataZipEntities;
    }

    public static String decompose(String s) {
        return s.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}
