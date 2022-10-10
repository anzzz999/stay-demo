package designPatterns.bridge.transmission;

/**
 * @Author: zhanmingwei
 */
public class ManualTransmissionImpl implements Transmission {
    @Override
    public String use() {
        return "手动变速器";
    }
}
