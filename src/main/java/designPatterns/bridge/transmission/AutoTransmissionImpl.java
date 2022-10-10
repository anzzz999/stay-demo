package designPatterns.bridge.transmission;

/**
 * @Author: zhanmingwei
 */
public class AutoTransmissionImpl implements Transmission {
    @Override
    public String use() {
        return "自动变速器";
    }
}
