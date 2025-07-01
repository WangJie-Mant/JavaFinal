package JavaFinal;

public class Enumcenter {

    public enum DeviceState {
        RUNNING("运行中"), STANDBY("已停止"), ERROR("错误"), MAINTAINANCE("维护中");

        private final String description;

        DeviceState(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum DeviceType {
        MOTOR("电机"), COMPRESSOR("压缩机");

        private final String typeName;

        DeviceType(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }
}
