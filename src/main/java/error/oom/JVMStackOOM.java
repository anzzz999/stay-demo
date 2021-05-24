package error.oom;

/**
 * JVM 虚拟机栈内存溢出测试, 注意在windows平台运行时可能会导致操作系统假死
 * VM Args: -Xss2M -XX:+HeapDumpOnOutOfMemoryError
 */

public class JVMStackOOM {

    private static int number = 0;

    private void dontStop() {
        while (true) {
            System.out.println(number++);
        }
    }

    public void stackLeakByThread() {
        Runnable runnable = this::dontStop;
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void main(String[] args) {
        JVMStackOOM jvmStackOOM = new JVMStackOOM();
        jvmStackOOM.stackLeakByThread();

    }
}
