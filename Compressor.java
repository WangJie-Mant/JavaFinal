package JavaFinal;

public class Compressor extends IndustrialDevice {
    double pressure;
    double flowRate;

    public Compressor(String deviceID, String model, double power) {
        super(deviceID, model, power);
    }

    public void initSensors() {
        this.addSensor(new PressureSensor(this, "压缩机压力传感器", "Pa", 100000));
    }

    public void updateSensorData() {
        for (I_Sensor sensor : this.getSensors()) {
            sensor.updateValue();
        }
    }

    public String getDeviceType() {
        return "Compressor";
    }

    public double getPressure() {
        return this.pressure;
    }

    public double getFlowRate() {
        return this.flowRate;
    }
}
