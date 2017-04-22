package legal.Entity.Utility;

import java.io.InputStream;

/**
 * Created by Son on 4/18/2017.
 */
public class DataZipEntity {
    public String name;
    public String path;
    public long size;
    public InputStream data;

    public DataZipEntity(String name, String path, long size, InputStream data) {
        this.name = name;
        this.path = path;
        this.data = data;
        this.size = size;
    }
}
