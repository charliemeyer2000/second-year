public class Main {
    public static void main(String[] args) {
        var filename = "example.txt";
        var fileExtractor = new FileExtractor(filename);
        var contents = fileExtractor.getFileContents();
        var lineCount = contents.split("\n").length;
        System.out.printf("%s contains %d lines", filename, lineCount);
    }
}
