package test.Initial;

import legal.Entity.DataLink.DocumentLinkEntity;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * Created by Son on 4/18/2017.
 */
public class DownloadFile {
    public static void main(String[] args) {
        for (int i = 120000; i < 128000; i++)
        try {
            File t = new File("G:\\FirstREST\\temp8\\" + Integer.toString(i) + ".zip");
            FileUtils.copyURLToFile(new URL(downloadallfile(Integer.toString(i))), t);
//                System.out.println(documentLinkEntity1.documentLink.toString());
        } catch (Exception x) {

        }

    }

    static String downloadallfile(String itemid) {
        String url = "http://vbpl.vn/VBQPPL_UserControls/Publishing_22/pActiontkeFile.aspx?do=getallfile&ItemID=" + itemid;
        return url;
    }
}
