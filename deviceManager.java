package JavaFinal;

import java.util.List;

public class deviceManager {
    List<IndustrialDevice> devices;

    public void addDevice(IndustrialDevice device) {
        this.devices.add(device);
    }

    public IndustrialDevice getDeviceById(String deviceID) {
        for (IndustrialDevice device : devices) {
            if (device.getDeviceID().equals(deviceID)) {
                return device;
            }
        }
        return null;
    }

    public List<IndustrialDevice> getAllDevices() {
        return this.devices;
    }

    public void displayDeviceList() {
        System.out.println("当前设备列表:");
        for (IndustrialDevice device : devices) {
            System.out.println("设备ID\t设备型号\t设备功率\t设备状态\t");
            System.out.println(device.getDeviceID() + "\t" + device.getDeviceModel() + "\t" + device.getPower()
                    + "\t" + device.getState().getDescription());
        }
    }

    public void simulateAllDevices() {
        for (IndustrialDevice device : devices) {
            device.simulateOperation();
        }
    }

    public void createDevice(String deviceType, String deviceID, String model, double power) {
        IndustrialDevice newDevice = null;
        if (deviceType.equalsIgnoreCase("Motor")) {
            newDevice = new Motor(deviceID, model, power);
        } else if (deviceType.equalsIgnoreCase("Compressor")) {
            newDevice = new Compressor(deviceID, model, power);
        }
        if (newDevice != null) {
            this.addDevice(newDevice);
            System.err.println("设备创建成功: " + newDevice.getDeviceID() + " - " + newDevice.getModel());
        } else {
            System.err.println("设备类型错误: " + deviceType);
        }
    }
}
