package test;

import javax.sound.midi.Soundbank;

public class WorkDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.print("1");
                    try {
                        lock.wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("2");
                    lock.notify();
                    System.out.print("3");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.print("4");
                    lock.notify();
                    System.out.print("5");
                    try {
                        lock.wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("6");
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.print("7");
    }
}
