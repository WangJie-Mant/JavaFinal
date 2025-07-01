package JavaFinal;

public interface I_Sensor {

    String getName();

    String getUnit();

    double getValue();

    void updateValue();

    boolean isAbnormal();

    String getFaultDescription();

    IndustrialDevice getDevice();
}