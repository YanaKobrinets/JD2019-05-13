package by.it.akhmelev.jd01_14;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskA {
    public static void main(String[] args) {
        String fileData = getPath(TaskA.class, "dataTaskA.bin");
        String fileTxt = getPath(TaskA.class, "resultTaskA.txt");

        writeData(fileData);
        List<Integer> list = new ArrayList<>();
        readData(fileData, list);
        printConsole(list);
        printText(fileTxt, list);


    }

    private static void printText(String fileTxt, List<Integer> list) {
        try (
                PrintWriter out = new PrintWriter(
                        new FileWriter(fileTxt)
                )
        ) {
            double sum = 0;
            for (Integer value : list) {
                out.printf("%d ", value);
                sum += value;
            }
            out.printf("\navg=%f", sum / list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printConsole(List<Integer> list) {
        double sum = 0;
        for (Integer value : list) {
            System.out.printf("%d ", value);
            sum += value;
        }
        System.out.printf("\navg=%f", sum / list.size());
    }

    private static void readData(String fileData, List<Integer> list) {
        try (
                DataInputStream dis = new DataInputStream(
                        new BufferedInputStream(
                                new FileInputStream(fileData)
                        ))) {
            while (dis.available() > 0) {
                list.add(dis.readInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeData(String fileData) {
        try (
                DataOutputStream dos = new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(fileData)
                        )
                )
        ) {

            for (int i = 0; i < 20; i++) {
                dos.writeInt((int) (Math.random() * 100));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String getPath(Class<?> aClass, String filename) {
        return getPath(aClass) + filename;
    }

    private static String getPath(Class<?> aClass) {
        String root = System.getProperty("user.dir");
        String name = aClass.getName();
        String simpleName = aClass.getSimpleName();
        String separator = File.separator;
        String path = name.replace(simpleName, "").replace(".", separator);
        path = root + separator + "src" + separator + path;
        return path;
    }
}
