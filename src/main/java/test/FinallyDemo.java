package test;


public class FinallyDemo {
    public static void main(String[] args) {
        System.out.println(test1());
    }
    public static int test1() {
        Integer i = 0;
        try {
            i = new Integer(1023);
            return i;
        } finally {
            i =  new Integer(1024);
            i = 2* i;
        }
    }
}
