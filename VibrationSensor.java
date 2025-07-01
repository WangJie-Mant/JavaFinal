package JavaFinal;

public class VibrationSensor implements I_Sensor {
    IndustrialDevice device;
    String name;
    String unit;
    double value;
    double faultThreshold;
    double baseTemp = 20.0;

    public VibrationSensor(IndustrialDevice device, String name, String unit, double faultThreshold) {
        this.device = device;
        this.name = name;
        this.unit = unit;
        this.faultThreshold = faultThreshold;
        this.value = 0.0;
    }

    public String getName() {
        return this.name;
    }

    public String getUnit() {
        return this.unit;
    }

    public double getValue() {
        return this.value;
    }

    public void updateValue() {
        this.value = collectData();
    }

    public boolean isAbnormal() {
        return this.value > this.faultThreshold;
    }

    public String getFaultDescription() {
        if (this.isAbnormal()) {
            return "设备" + device.getDeviceID() + "的传感器" + this.name + "异常，当前值：" + this.value + " " + this.unit;
        } else {
            return "设备" + device.getDeviceID() + "的传感器" + this.name + "正常，当前值：" + this.value + " " + this.unit;
        }
    }

    public IndustrialDevice getDevice() {
        return this.device;
    }

    public double collectData() {
        if (device.isRunning()) {
            double base = this.baseTemp + device.getPower() * 0.1;
            double fluctuation = Math.random() * 8 - 4;
            return Math.round((base + fluctuation) * 10) / 10.0;
        } else {
            return this.baseTemp;
        }
    }
}
