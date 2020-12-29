package concurrent.threadLocal;

public class testMapHashCode {
    /**
     * 证实了一个thread对应一个ThreadLocalMap
     * @param args
     */
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        threadLocal.set(1);
        threadLocal1.set("123");
        threadLocal.remove();
        System.out.println("111");

    }
}
