import java.io.File;

public class Utils {
    public static String getFileExtension(File file){
        String path = file.getAbsolutePath();
        String[] splittedPath = path.split("\\.");

        return splittedPath[splittedPath.length - 1];
    }
}
