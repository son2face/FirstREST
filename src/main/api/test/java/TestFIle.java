package test.java;

import legal.Entity.Utility.DataZipEntity;
import legal.Service.UtilityService.FileService;
import legal.Service.UtilityService.ZipReaderService;

import java.util.List;

/**
 * Created by Son on 4/18/2017.
 */
public class TestFIle {
    public static void main(String[] args) {
        List<String> t = FileService.read("zipdata");
        for (String x : t) {
//            System.out.println(x);
            List<DataZipEntity> dataZipEntities = ZipReaderService.read(x);
            for (DataZipEntity g : dataZipEntities) {
                System.out.println(g.name);
            }
        }
    }
}
