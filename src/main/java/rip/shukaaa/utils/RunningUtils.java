package rip.shukaaa.utils;

import rip.shukaaa.executor.CommandExecutor;

import java.io.File;
import java.io.IOException;

public class RunningUtils {
    public static void runFile(File file) throws IOException, InterruptedException {
        if (file.getName().equals("Main.class")) {
            CommandExecutor.execute("java -cp src/main/resources/out Main");
        }

        if (file.getName().equals("main.js")) {
            System.out.println(ColorUtils.ANSI_BLUE + "Running JavaScript file: " + file.getName() + ColorUtils.ANSI_RESET);
            CommandExecutor.execute("node src/main/resources/out/" + file.getName());
        }

        if (file.getName().equals("main.py")) {
            System.out.println(ColorUtils.ANSI_BLUE + "Running Python file: " + file.getName() + ColorUtils.ANSI_RESET);
            CommandExecutor.execute("python src/main/resources/out/" + file.getName());
        }
    }
}
