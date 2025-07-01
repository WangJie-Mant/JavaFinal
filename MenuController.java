package JavaFinal;

import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.IOException;

public class MenuController {
    Scanner scanner;
    deviceManager deviceManager;
    LogManager logManager;
    boolean running;

    public void start() {
        scanner = new Scanner(System.in);
        running = true;

        if (this.deviceManager == null || this.logManager == null) {
            this.deviceManager = new deviceManager();
            this.logManager = new LogManager();
        }

        initTestDevices();

        while (running) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewDeviceList();
                    break;
                case 2:
                    addNewDevice();
                    break;

                case 3:
                    operateDevice();
                    break;

                case 4:
                    viewSensorData();
                    break;

                case 5:
                    checkDeviceFault();
                    break;

                case 6:
                    exportDeviceLog();
                    break;

                case 0:
                    exitSystem();
                    break;
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("============= 工业设备监控系统 =============");
        System.out.println("1.查看设备列表");
        System.out.println("2.添加新设备");
        System.out.println("3.设备操作（启动/停止）");
        System.out.println("4.查看设备传感器数据");
        System.out.println("5.设备故障检测");
        System.out.println("6.导出设备运行日志");
        System.out.println("0.退出系统");
        System.out.println("==========================================");
        System.out.print("请输入您的选择: ");
    }

    private void viewDeviceList() {
        deviceManager.displayDeviceList();
        logManager.logSystemMessage("查看设备列表");
    }

    private void addNewDevice() {
        System.out.println("请输入设备的设备类型（Motor/Compressor）:");
        String deviceType = scanner.nextLine();
        System.out.println("请输入设备ID:");
        String deviceID = scanner.nextLine();
        System.out.println("请输入设备型号:");
        String deviceModel = scanner.nextLine();
        System.out.println("请输入设备功率:");
        int devicePower = scanner.nextInt();
        scanner.nextLine();

        deviceManager.createDevice(deviceType, deviceID, deviceModel, devicePower);
        IndustrialDevice createdDevice = deviceManager.getDeviceById(deviceID);
        if (createdDevice != null) {
            System.out.println("已创建设备：");
            System.out.println("==========================================");
            System.out.println("设备ID\t设备型号\t设备功率\t");
            System.out.println(createdDevice.getDeviceID() + "\t" + createdDevice.getModel() + "\t"
                    + createdDevice.getPower());
            System.out.println("==========================================");
            logManager.logOperation("添加新设备", createdDevice);
        } else {
            System.out.println("设备创建失败，请检查输入信息是否正确。");
            logManager.logSystemMessage(deviceModel + "设备创建失败");
        }
    }

    private void operateDevice() {
        System.out.println("当前所有设备状态如下：");
        deviceManager.displayDeviceList();
        System.out.println("输入要操作的设备ID:");
        String operationDeviceID = scanner.nextLine();
        IndustrialDevice operationDevice = deviceManager.getDeviceById(operationDeviceID);
        if (operationDevice != null) {
            System.out.println("请选择操作：1.启动设备 2.停止设备");
            int operationChoice = scanner.nextInt();
            scanner.nextLine();
            if (operationChoice == 1) {
                String startMessage = operationDevice.deviceStart();
                System.out.println(startMessage);
                logManager.logOperation(startMessage, operationDevice);
            } else if (operationChoice == 2) {
                String stopMessage = operationDevice.deviceStop();
                System.out.println(stopMessage);
                logManager.logOperation(stopMessage, operationDevice);
            } else {
                System.out.println("无效的操作选择，请重新输入。");
            }
        } else {
            System.out.println("设备ID不存在，请检查输入。");
        }
    }

    private void viewSensorData() {
        System.out.println("请输入要查看传感器数据的设备ID:");
        String sensorDeviceID = scanner.nextLine();
        IndustrialDevice sensorDevice = deviceManager.getDeviceById(sensorDeviceID);
        if (sensorDevice != null) {
            sensorDevice.simulateOperation();
            logManager.logSystemMessage(sensorDeviceID + "查看设备传感器数据");
        } else {
            System.out.println("设备ID不存在，请检查输入。");
        }
    }

    private void checkDeviceFault() {
        System.out.println("请输入要检测故障的设备ID:");
        String faultDeviceID = scanner.nextLine();
        IndustrialDevice faultDevice = deviceManager.getDeviceById(faultDeviceID);
        if (faultDevice != null) {
            faultDevice.checkFault();
            logManager.logSystemMessage(faultDeviceID + "设备故障检测");
        } else {
            System.out.println("设备ID不存在，请检查输入。");
        }
    }

    private void exportDeviceLog() {
        System.out.println("导出设备运行日志...");
        logManager.logSystemMessage("导出设备运行日志");
        System.out.println("请输入导出日志文件的路径：");
        String logFilePath = scanner.nextLine();
        try {
            String sourcePath = logManager.getLogFilePath();

            Path source = Paths.get(sourcePath);
            Path destination = Paths.get(logFilePath);

            Files.createDirectories(destination.getParent());
            Files.copy(
                    source,
                    destination,
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println("日志文件已导出到: " + logFilePath);
            logManager.logSystemMessage("日志文件已导出到: " + logFilePath);
        } catch (IOException e) {
            System.out.println("导出日志文件失败: " + e.getMessage());
            logManager.logSystemMessage("导出日志文件失败: " + e.getMessage());
        }
    }

    private void exitSystem() {
        System.out.println("退出系统...");
        logManager.logSystemMessage("退出系统");
        running = false;
    }

    private void initTestDevices() {
        deviceManager.createDevice("Motor", "MOTOR-01", "HIGH-POWER", 1500);
        deviceManager.createDevice("Compressor", "COMPRESSOR-01", "MEDIUM-POWER", 1000);
    }

    private boolean isValidDeviceType(String deviceType) {
        return deviceType.equals(Enumcenter.DeviceType.MOTOR.getTypeName())
                || deviceType.equals(Enumcenter.DeviceType.COMPRESSOR.getTypeName());
    }
}
