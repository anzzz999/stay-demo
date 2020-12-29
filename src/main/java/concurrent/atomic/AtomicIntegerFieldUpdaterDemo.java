package concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/*
** 原子更新字段类型
** 如果需要更新对象的某个字段，并在多线程的情况下，能够保证线程安全
 */
public class AtomicIntegerFieldUpdaterDemo {
    private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public static void main(String[] args) {
        User user = new User("a", 1);
        int oldValue = updater.getAndAdd(user, 5);

        System.out.println(oldValue);
        System.out.println(updater.get(user));

        User user2 = new User("b", 2);
        System.out.println(updater.get(user2));
        System.out.println(user);
    }


    static class User {
        private String userName;
        public volatile int age;

        public User(String userName, int age) {
            this.userName = userName;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
