package xmlprocessing.goldeneagle.utils.interfaces;

import java.io.IOException;

public interface FileUtil {
    String getContent(String path) throws IOException;
    void writeToFile(String content, String fileName) throws IOException;
}
