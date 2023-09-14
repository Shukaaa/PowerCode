package rip.shukaaa.utils;

import rip.shukaaa.executor.CommandExecutor;

public class CompilerUtils {
    public static void compileJavaFile(String path) {
        try {
            if (CommandExecutor.execute("javac " + path)) {
                System.out.println(ColorUtils.ANSI_GREEN + "Successfully compiled " + path + ColorUtils.ANSI_RESET);
            } else {
                throw new RuntimeException("Failed to compile java file: " + path);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to compile java file: " + path);
        }
    }
}
