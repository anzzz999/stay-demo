package designPatterns.bridge.car;

import designPatterns.bridge.transmission.Transmission;

/**
 * @Author: zhanmingwei
 *
 */
public abstract class AbstractCar {
    protected Transmission gear;


    public AbstractCar(Transmission gear) {
        this.gear = gear;
    }

    public abstract void run();
}
