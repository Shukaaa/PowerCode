package rip.shukaaa.utils;

import java.io.File;

public class FileUtil {
    public static void deleteDirectory(String path) {
        File directory = new File(path);
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (null != files) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file.getAbsolutePath());
                    } else {
                        file.delete();
                    }
                }
            }
        }
        directory.delete();
    }

    public static void createDirectory(String path) {
        File directory = new File(path);
        directory.mkdir();
    }

    public static void clearDirectory(String path) {
        FileUtil.deleteDirectory(path);
        FileUtil.createDirectory(path);
    }
}
