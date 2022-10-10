package test;

//一个最简单的打印HelloWorld的程序，说说看，运行这个应用，Java至少会创建几个线程呢？
//使用的IDE是IDEA 直接运行会多一个Monitor Ctrl-break线程，这个是IDE的原因。debug模式下不会有这个线程
public class HelloWorldDemo {

    public static void main(String[] args) {
        System.out.println("hello world~");
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        int nowThreads = topGroup.activeCount();
        Thread[] lstThreads = new Thread[nowThreads];
        topGroup.enumerate(lstThreads);
        for (int i = 0; i < nowThreads; i++) {
            System.out.println("线程number：" + i + " = " + lstThreads[i].getName());
        }
    }
}


