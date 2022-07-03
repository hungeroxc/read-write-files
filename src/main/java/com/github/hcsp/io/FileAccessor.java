package com.github.hcsp.io;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) {
        List<String> list = new ArrayList<>();
        try (FileInputStream is = new FileInputStream(file)) {
            int i;
            StringBuilder sb = new StringBuilder();
            while ((i = is.read()) != -1) {
                if (i == 10) {
                    list.add(sb.toString());
                    sb = new StringBuilder();
                } else {
                    sb.append((char) i);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<String> readFile2(File file) {
        List<String> list = null;
        try {
            list = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<String> readFile3(File file) {
        List<String> list;
        try {
            list = IOUtils.readLines(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void writeLinesToFile1(List<String> lines, File file) {
        try (FileOutputStream os = new FileOutputStream(file)) {
            for (String line : lines) {
                os.write(line.getBytes());
                os.write(10);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeLinesToFile2(List<String> lines, File file) {
        try {
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeLinesToFile3(List<String> lines, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (String line : lines
            ) {
                IOUtils.write(line, fileWriter);
                IOUtils.write(String.valueOf((char) 10), fileWriter);
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
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
