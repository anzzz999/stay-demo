package error.sof;

/**
 * 虚拟机栈和本地方法栈内存溢出测试，抛出stackoverflow exception
 * VM ARGS: -Xss128k 减少栈内存容量
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try {
            sof.stackLeak();
        }catch (Exception e){
            System.out.println("stack length = " + sof.stackLength);
            throw e;
        }

    }
}
