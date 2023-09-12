import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileExtractor {
    private final String filename;

    public FileExtractor(String filename) {
        this.filename = filename;
    }

    public String getFileContents() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            StringBuilder fileContentsBuilder = new StringBuilder();
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                fileContentsBuilder.append(line);
            }
            return fileContentsBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
