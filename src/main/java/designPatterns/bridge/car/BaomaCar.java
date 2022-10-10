package designPatterns.bridge.car;

import designPatterns.bridge.transmission.Transmission;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhanmingwei
 */
@Slf4j
public class BaomaCar extends AbstractCar {
    public BaomaCar(Transmission gear) {
        super(gear);
    }

    @Override
    public void run() {
        log.info("我是{}车,使用{}", "=宝马=", gear.use());
    }
}
