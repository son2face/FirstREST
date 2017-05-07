package test.java;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;

/**
 * Created by Son on 4/18/2017.
 */
public class TestZip {
    private static final String FILE_NAME = "C:\\temp\\pics.zip";
    private static final String OUTPUT_DIR = "C:\\temp\\Images\\";
    private static final int BUFFER_SIZE = 8192;

    public static void main(String args[]) throws IOException {
        // Prefer ZipFile over ZipInputStream
        readUsingZipFile();
        // readUsingZipInputStream();
    }

    /* * Example of reading Zip archive using ZipFile class */
    private static void readUsingZipFile() throws IOException {

    } /* * Example of reading Zip file using ZipInputStream in Java. */


    private static void extractEntry(final ZipEntry entry, InputStream is) throws IOException {
        String exractedFile = OUTPUT_DIR + entry.getName();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(exractedFile);
            final byte[] buf = new byte[BUFFER_SIZE];
            int read = 0;
            int length;
            while ((length = is.read(buf, 0, buf.length)) >= 0) {
                fos.write(buf, 0, length);
            }
        } catch (IOException ioex) {
            fos.close();
        }
    }
}


