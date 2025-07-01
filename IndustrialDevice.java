package JavaFinal;

import java.util.List;
import java.util.ArrayList;

public class IndustrialDevice {
    private Enumcenter.DeviceType deviceType;
    private Enumcenter.DeviceState state;
    private String deviceID;
    private String deviceModel;
    private int devicePower;
    private long deviceRunningTime;
    private List<I_Sensor> sensors;

    public IndustrialDevice(String deviceID, String deviceModel, int devicePower) {
        this.deviceID = deviceID;
        this.deviceModel = deviceModel;
        this.devicePower = devicePower;
        this.state = Enumcenter.DeviceState.STANDBY;
        this.sensors = new ArrayList<>();
    }

    public String deviceStart() {
        this.state = Enumcenter.DeviceState.RUNNING;
        return this.deviceType + this.deviceModel + "启动成功！";
    }

    public String deviceStop() {
        this.state = Enumcenter.DeviceState.STANDBY;
        return this.deviceType + this.deviceModel + "停止成功！";
    }

    public void simulateOperation() {
        System.out.println("正在运行设备：" + this.deviceType + this.deviceModel + "\n");

        this.state = Enumcenter.DeviceState.RUNNING;

        for (I_Sensor sensor : sensors) {
            if (!sensor.isAbnormal()) {
                System.out.println("来自传感器" + sensor.getName() + "的数据：" + sensor.getValue() + " " + sensor.getUnit());
            }
        }
    }

    public void checkFault() {
        for (I_Sensor sensor : sensors) {
            if (sensor.isAbnormal()) {
                System.out.println("传感器" + sensor.getName() + "异常: " + sensor.getFaultDescription());
                this.state = Enumcenter.DeviceState.ERROR;
            } else {
                System.out.println("传感器" + sensor.getName() + "正常");
            }
        }
    }

    public void displayState() {
        System.out.println("设备" + this.deviceModel + "当前状态：" + this.state.getDescription());
    }

    public String getDeviceType() {
        return this.deviceType.getTypeName();
    }

    public String getDeviceID() {
        return this.deviceID;
    }

    public String getModel() {
        return this.deviceModel;
    }

    public double getPower() {
        return this.devicePower;
    }

    public Enumcenter.DeviceState getState() {
        return this.state;
    }

    public void setState(Enumcenter.DeviceState state) {
        this.state = state;
    }

    public long getRunningTime() {
        return this.deviceRunningTime;
    }

    public List<I_Sensor> getSensors() {
        return this.sensors;
    }

    public void addSensor(I_Sensor sensor) {
        this.sensors.add(sensor);
    }

    public void initSensors() {
        for (I_Sensor sensor : sensors) {
            sensor.updateValue();
        }
    }

    public boolean isRunning() {
        return this.state == Enumcenter.DeviceState.RUNNING;
    }

    public void updateSensorData() {
        for (I_Sensor sensor : sensors) {
            sensor.updateValue();
        }
    }
}
