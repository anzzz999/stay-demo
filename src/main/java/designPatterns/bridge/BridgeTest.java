package designPatterns.bridge;

import designPatterns.bridge.car.AbstractCar;
import designPatterns.bridge.car.AodiCar;
import designPatterns.bridge.car.BaomaCar;
import designPatterns.bridge.transmission.AutoTransmissionImpl;
import designPatterns.bridge.transmission.ManualTransmissionImpl;
import designPatterns.bridge.transmission.Transmission;
import org.junit.jupiter.api.Test;

/**
 * @Author: zhanmingwei
 */
/**
 * @description:
*    GoF 的《设计模式》一书中对桥接模式的定义：将抽象和实现解耦，让它们可以独立变化。
*
*   关于桥接模式，还有另外一种理解方式：“一个类存在两个（或多个）独立变化的维度，我们通过组合的方式，让这两个（或多个）维度可以独立进行扩展。”
 *  通过组合关系来替代继承关系，避免继承层次的指数级爆炸。这种理解方式非常类似于，我们之前讲过的“组合优于继承”设计原则。
*
*   JDBC 驱动是桥接模式的经典应用
  */

public class BridgeTest {

    @Test
    public void test(){
        Transmission autoTransmission = new AutoTransmissionImpl();
        AbstractCar baomaCar = new BaomaCar(autoTransmission);
        baomaCar.run();

        Transmission manualTransmission = new ManualTransmissionImpl();
        AbstractCar aodiCar = new AodiCar(manualTransmission);
        aodiCar.run();
    }
}
