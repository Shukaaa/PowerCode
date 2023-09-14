package rip.shukaaa;


import rip.shukaaa.handler.PowerpointHandler;
import rip.shukaaa.utils.ColorUtils;
import rip.shukaaa.utils.CompilerUtils;
import rip.shukaaa.utils.FileUtil;
import rip.shukaaa.utils.RunningUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws Exception {
        PowerpointHandler powerpointHandler = new PowerpointHandler("src/main/resources/presentation.pptx");
        HashMap<String, ArrayList<String>> slideMap = powerpointHandler.getSlideMap();

        // Clear the out directory
        FileUtil.clearDirectory("src/main/resources/out");

        for (String title : slideMap.keySet()) {
            FileWriter writer = new FileWriter("src/main/resources/out/" + title);
            for (String line : slideMap.get(title)) {
                writer.write(line + "\n");
            }
            writer.close();
        }

        powerpointHandler.dispose();

        for (File file : Objects.requireNonNull(new File("src/main/resources/out").listFiles())) {
            if (file.getName().endsWith(".java")) {
                System.out.println(ColorUtils.ANSI_BLUE + "Compiling Java file: " + file.getName() + ColorUtils.ANSI_RESET);
                CompilerUtils.compileJavaFile("src/main/resources/out/" + file.getName());
            }
        }

        for (File file : Objects.requireNonNull(new File("src/main/resources/out").listFiles())) {
            RunningUtils.runFile(file);
        }
    }
}