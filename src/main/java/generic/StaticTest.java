package generic;

/**
 * 泛型-当泛型内包含静态变量
 * 这段代码编译都无法通过，因为泛型里面不能引用静态变量。
 * 由于经过类型擦除，所有的泛型类实例都关联到同一份字节码上，泛型类的所有静态变量是共享的。
 */
public class StaticTest{
//    public static void main(String[] args) {
//        GT<Integer> gti = new GT<Integer>();
//        gti.var = 1;
//        GT<String> gts = new GT<String>();
//        gts.var = 2;
//        System.out.println(gti.var);
//    }
//
//    class GT<T> {
//        public static int var = 0;
//
//        public void nothing(T x) {
//        }
//    }
}