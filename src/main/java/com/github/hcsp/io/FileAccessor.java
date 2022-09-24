package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<String> content = new ArrayList<>();
            while (scanner.hasNextLine()) {
                content.add(scanner.nextLine());
            }
            scanner.close();
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<String> readFile2(File file) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> content = new ArrayList<>();
            while (reader.ready()) {
                // read next line
                String line = reader.readLine();
                content.add(line);
            }
            reader.close();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<String> readFile3(File file) throws IOException {
        try {
            return FileUtils.readLines(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        try {
            FileWriter myFileWriter = new FileWriter(file);
            for (String str : lines) {
                myFileWriter.write(str);
                myFileWriter.write(System.lineSeparator());
            }
            myFileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        try {
            // Create the string Writer
            FileWriter myFileWriter = new FileWriter(file);
            BufferedWriter buffWriter = new BufferedWriter(myFileWriter);
            for (String str : lines) {
                myFileWriter.write(str);
                myFileWriter.write(System.lineSeparator());
            }
            buffWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        FileUtils.writeLines(file, lines);
    }

    public static void main(String[] args) throws IOException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        File testFile = new File(projectDir, "target/test.txt");
//        List<String> lines = Arrays.asList("AAA", "BBB", "CCC");
        List<String> lines = Arrays.asList("a", "b", " ", "   ", "c");
        writeLinesToFile1(lines, testFile);
        writeLinesToFile2(lines, testFile);
        writeLinesToFile3(lines, testFile);

        System.out.println(readFile1(testFile));
        System.out.println(readFile2(testFile));
        System.out.println(readFile3(testFile));
    }
}
