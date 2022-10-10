package designPatterns.bridge.car;

import designPatterns.bridge.transmission.Transmission;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhanmingwei
 */
@Slf4j
public class AodiCar extends AbstractCar {
    public AodiCar(Transmission gear) {
        super(gear);
    }

    @Override
    public void run() {
        log.info("我是{}车,使用{}", "=奥迪=", gear.use());
    }
}
