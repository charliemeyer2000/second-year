package structural.decorator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.Buffer;

public class Demo {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = "myfile.txt";

        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
    }
}
