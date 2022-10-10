package test;

public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.change(test.str, test.ch);
        System.out.print(test.str);
        System.out.print(test.ch);
    }


}
class Test {
    String str = new String("good");
    char[] ch = {'a'};
    public void change(String str, char[] ch) {
        str = "bad";
        ch[0] = 'b';
        ch = new char[]{'c'};
    }




}
