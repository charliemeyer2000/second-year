import java.io.*;
import java.util.*;

public class CSVVoteReader {
    private String filename;

    public CSVVoteReader(String filename) {
        this.filename = filename;
    }

    public List<CandidateVotes> readStates() {
        File f = new File(filename);
        FileReader r = null;
        try {
            r = new FileReader(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader b = new BufferedReader(r);

        //ignore first line
        try {
            b.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //read rest of line
        List<CandidateVotes> list = new ArrayList<>();
        String x = null;
        try {
            x = b.readLine();
            while (x != null) {
                String k  = x;
                String[] v = k.strip().split(",");
                CandidateVotes s = null;
                try {
                    s = new CandidateVotes(v[0], Integer.parseInt(v[1]));
                } catch (NumberFormatException e) {
                    //I don't know lul
                }
                if (s != null) {
                    list.add(s);
                }
                x = b.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}