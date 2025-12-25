package exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

public class LogWriter {
    private static String dir = System.getProperty("user.dir");
    private static String defaultPath = dir + "\\logs\\";
    private static int logNumber = 0;

    public static void writeToFile(Exception excp) {
        String logname = "log" + logNumber + ".txt";
        Path fileName = Paths.get(defaultPath + logname);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName.toString(), StandardCharsets.UTF_8, false))) {
            writer.write(Calendar.getInstance().getTime().toString());
            writer.newLine();
            writer.write(excp.getMessage());
            writer.newLine();
            for (StackTraceElement el : excp.getStackTrace()) {
                writer.write(el.toString());
                writer.newLine();
            }
            logNumber++;
        } catch (IOException e) {
            throw new WorkWithFileErrorException(e.getMessage());
        }
    }
}

