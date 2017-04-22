package legal.Service.UtilityService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Son on 4/18/2017.
 */
public class FileService {
    public static List<String> read(String fileUrl) {
        List<String> result = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(fileUrl))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    result.add(filePath.toString());
                }
            });
        } catch (Exception ex) {

        }
        return result;
    }

    public static boolean createFolder(String folderUrl) {
        File file = new File(folderUrl);
        if (!file.exists()) {
            if (file.mkdir()) {
                return true;
            }
        }
        return false;
    }

    public static boolean writeFile(String fileUrl, InputStream data){
        final Path destination = Paths.get(fileUrl);
        try {
            Files.copy(data, destination);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
