package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int i = inputStream.read();
            if (i == -1) {
                break;
            }
            stringBuilder.append((char) i);
        }
        return Arrays.asList(stringBuilder.toString().split(System.getProperty("line.separator")));
    }

    public static List<String> readFile2(File file) throws IOException {
        String s = FileUtils.readFileToString(file, Charset.defaultCharset());
        return Arrays.asList(s.split(System.getProperty("line.separator")));
    }

    public static List<String> readFile3(File file) throws IOException {
        List<String> res = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s;
        while ((s = reader.readLine()) != null) {
            if (!s.equals("")) {
                res.add(s);
            }
        }
        return res;
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        try (OutputStream output = new FileOutputStream(file)) {
            String resource = StringUtils.join(lines, System.getProperty("line.separator"));
            for (int i = 0; i < resource.length(); i++) {
                output.write(resource.charAt(i));
            }
        }
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        FileUtils.writeLines(file, lines);
    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
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
