package JavaFinal;

import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogManager {
    private String LOG_FILE_PATH = "logs/device_logs.txt";
    private DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogManager() {
        File dir = new File("logs");
        if (!dir.exists()) {
            dir.mkdirs(); // 创建日志目录
        }
    }

    public void logOperation(String operation, IndustrialDevice device) {
        String log = String.format("%s - 设备ID: %s, 操作: %s\n",
                DATE_TIME_FORMATTER.format(java.time.LocalDateTime.now()), device.getDeviceID(), operation);
        try (java.io.FileWriter writer = new java.io.FileWriter(LOG_FILE_PATH, true)) {
            writer.write(log);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogFilePath() {
        return LOG_FILE_PATH;
    }

    public void logSystemMessage(String message) {
        String log = formatLogMessage(message);
        writeToLogFile(log);
    }

    private String formatLogMessage(String message) {
        return String.format("%s - %s\n", DATE_TIME_FORMATTER.format(java.time.LocalDateTime.now()), message);
    }

    private void writeToLogFile(String logMessage) {
        try (java.io.FileWriter writer = new java.io.FileWriter(LOG_FILE_PATH, true)) {
            writer.write(logMessage + System.lineSeparator());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
