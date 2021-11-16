package com.test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

//выводит последний элемент файла (последнюю строку)
public class Main {
    public static void main(String[] args) {
        new Main().tail(new File("E://1.txt"));

    }
    public String tail(File file) {
        RandomAccessFile fileHandler = null;

        try {
            fileHandler = new RandomAccessFile(file, "r");
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();

            for (long filePointer = fileLength; filePointer != -1; filePointer--) {
                fileHandler.seek(filePointer);
                int readByte = fileHandler.readByte();

                if (readByte == 0xA) {
                    if (filePointer == fileLength) {
                        continue;
                    }
                    break;

                } else if (readByte == 0xD) {
                    if (filePointer == fileLength - 1) {
                        continue;
                    }
                    break;
                }

                sb.append((char) readByte);
            }

            return sb.reverse().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileHandler != null)
                try {
                    fileHandler.close();
                } catch (IOException e) {
                    /* ignore */
                }
        }
    }
}
