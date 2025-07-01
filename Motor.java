package JavaFinal;

public class Motor extends IndustrialDevice {
    double rotationSpeed;
    double loadRate;

    public Motor(Enumcenter.DeviceType deviceType, String deviceID, String model, int power) {
        super(deviceType, deviceID, model, power);
        this.addSensor(new VibrationSensor(this, deviceID, model, power));
    }

    public void initSensors() {
        this.addSensor(new VibrationSensor(this, "电动机振动传感器", "m/s^2", 5.0));
    }

    public void updateSensorData() {
        for (I_Sensor sensor : this.getSensors()) {
            sensor.updateValue();
        }
    }

    public String getDeviceType() {
        return "Motor";
    }

    public double getRotationSpeed() {
        return this.rotationSpeed;
    }

    public double getLoadRate() {
        return this.loadRate;
    }
}
