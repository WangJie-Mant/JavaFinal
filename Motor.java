package JavaFinal;

public class Motor extends IndustrialDevice {
    double rotationSpeed;
    double lodeRate;

    public Motor(String deviceID, String model, double power) {
        super(deviceID, model, power);
    }

    public void initSensors() {
        this.addSensor(new VibrationSensor(this, "电动机振动传感器", "m/s^2", 5.0));
        this.addSensor(new LoadSensor(this, "电动机负载传感器", "N", 100.0));
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
