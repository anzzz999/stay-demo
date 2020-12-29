package concurrent.threadLocal;


public class Demo1 {
    private static ThreadLocal<Student> local = new ThreadLocal<Student>();
    public static void main(String[] args) throws InterruptedException {
        new Thread(){				//A线程
            @Override
            public void run() {
                local.set(new Student(19));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  " + local.get().getAge());
            };
        }.start();
        new Thread(){				//B线程
            @Override
            public void run() {
                local.set(new Student(11));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  " +local.get().getAge());
            };
        }.start();
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "  " +local.get().getAge());
    }
}
class Student{
    private int age;

    Student(int age){
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}