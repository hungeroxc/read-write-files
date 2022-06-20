package com.github.hcsp.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    //用inputstream实现，用换行符来判断读取
    public static List<String> readFile1(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        List<String> result = new ArrayList<>();
        StringBuilder joinIs = new StringBuilder();
        while (true) {
            int b = is.read();
            if (b == -1) {
                break;
            }
            if (b == '\n') {
                result.add(joinIs.toString());
                joinIs = new StringBuilder();
            } else {
                joinIs.append((char) b);
            }
        }
        return result;
    }

    //use bufferreader
    public static List<String> readFile2(File file) throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        while (true) {
            String str = buf.readLine();
            if (str == null) {
                break;
            }
            result.add(str);
        }
        return result;
    }

    //use files.readallbytes
    public static List<String> readFile3(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    //用outputstream实现，写入的时候增加换行符方便读取
    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        for (String sector : lines) {
            byte[] bytes = sector.getBytes(StandardCharsets.UTF_8);
            int len = bytes.length;
            os.write(bytes, 0, len);
            os.write("\n".getBytes(StandardCharsets.UTF_8));
        }
        os.close();
    }

    //use bufferedwriter
    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        for (String sector : lines) {
            bufw.write(sector);
            bufw.write('\n');
        }
        bufw.flush();
        bufw.close();
    }

    //use files.write
    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        StringBuilder result = new StringBuilder();
        for (String str : lines) {
            result.append(str).append('\n');
        }
        byte[] bytes = result.toString().getBytes(StandardCharsets.UTF_8);
        Files.write(file.toPath(), bytes);
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
