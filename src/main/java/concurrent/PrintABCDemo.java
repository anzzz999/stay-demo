package concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 要求创建三个线程，输出轮流输出ABC，
 * 最开始第一个线程输出A，第二个输出B，第三个输出C
 * 接着再第一个线程输出A...就这样循环下去，直到打印出100个数
 *
 * @author zhanmingwei
 */
public class PrintABCDemo {

    private static Object object = new Object();
    private static int number = 0;
    private static int total = 100;

    /**  synchronized 基础写法*/
    @Test
    public void Test(){
        Thread thread1 = new Thread(() -> {
            while (number < total) {
                synchronized (object) {
                    if (number % 3 == 0 && number < total) {
                        number++;
                        System.out.println("A-"+ number);
                    object.notifyAll();
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (number < total) {
                synchronized (object) {
                    if (number % 3 == 1 && number < total) {
                        number++;
                        System.out.println("B");
                    }
                    object.notifyAll();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            while (number < total) {
                synchronized (object) {
                    if (number % 3 == 2&& number < total) {
                        number++;
                        System.out.println("C");
                    }
                    object.notifyAll();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();

    }

    /** 将runnable 抽象出来 */
    @Test
    public void Tests(){
        new Thread(new TestsPrinter(0, 'A')).start();
        new Thread(new TestsPrinter(1, 'B')).start();
        new Thread(new TestsPrinter(2, 'C')).start();

    }

    private static class TestsPrinter implements Runnable{
        private int condition;
        private Character character;

        public TestsPrinter(int condition, Character character) {
            this.condition = condition;
            this.character = character;
        }

        @Override
        public void run() {
            while (number < total){
                synchronized (object){
                    if (number%3 == condition && number < total){
                        number++;
                        System.out.println(character);
                    }
                    object.notifyAll();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }




    /**  ReentrantLock 基础写法*/
    private static ReentrantLock  lock = new ReentrantLock();
    @Test
    public void Test1(){
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        Thread threadA = new Thread(() -> {
            while (number < total) {
                lock.lock();
                try {
                    if (number <total){
                        System.out.println("A");
                        number++;

                    }
                    conditionB.signal();
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread threadB = new Thread(() -> {
            while (number < total) {
                lock.lock();
                try {
                    if (number < total){
                        System.out.println("B");
                        number++;
                    }
                    conditionC.signal();
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread threadC = new Thread(() -> {
            while (number < total) {
                lock.lock();
                try {
                    if (number < total){
                        System.out.println("C");
                        number++;
                    }

                    conditionA.signal();
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }

    /**  ReentrantLock 将runnable抽象起来*/
    @Test
    public void Test2() {
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        new Thread(new Printer(conditionA, conditionB, "A")).start();
        new Thread(new Printer(conditionB, conditionC, "B")).start();
        new Thread(new Printer(conditionC, conditionA, "C")).start();
    }
    private class Printer implements Runnable{
        private Condition curCondition;
        private Condition nextCondition;
        private String printChar;

        public Printer(Condition curCondition, Condition nextCondition, String printChar) {
            this.curCondition = curCondition;
            this.nextCondition = nextCondition;
            this.printChar = printChar;
        }

        @Override
        public void run() {
            while (number < total){
                lock.lock();
                try {
                    if (number < total){
                        System.out.println(printChar);
                        number++;
                    }
                    nextCondition.signal();
                    curCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
