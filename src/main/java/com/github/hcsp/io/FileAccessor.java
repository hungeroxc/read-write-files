package com.github.hcsp.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        List<String> lines = new ArrayList<>();
//      initialize String word as an empty container
        String word = "";
        while (true) {
            int temp = is.read();
            if (temp == -1) {
                break;
            } else if (temp == '\n') {
                lines.add(word);
//              after this line is added, empty String word as a new container
                word = "";
                continue;
            }
            word = word + (char) temp;
        }
        return lines;
    }

    public static List<String> readFile2(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<>();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            lines.add(line);
        }
        return lines;
    }

    public static List<String> readFile3(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines = FileUtils.readLines(file, "UTF-8");
        return lines;
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        for (int i = 0; i < lines.size(); i++) {
            byte[] wordBytes = lines.get(i).getBytes();
            os.write(wordBytes);
            os.write('\n');
        }
        os.close();
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < lines.size(); i++) {
            bufferedWriter.write(lines.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        FileUtils.writeLines(file, lines);
    }

    public static void main(String[] args) throws IOException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        File testFile = new File(projectDir, "target/test.txt");
        List<String> lines = Arrays.asList("AAA", "BBB", "CCC");
        writeLinesToFile1(lines, testFile);
        writeLinesToFile2(lines, testFile);
        writeLinesToFile3(lines, testFile);

        System.out.println(readFile1(testFile));
        System.out.println(readFile2(testFile));
        System.out.println(readFile3(testFile));
    }
}
