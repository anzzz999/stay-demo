package poxy.cglib;

public class Target {
    public String execute() {
        System.out.println("cglibProxy...");
        return "execute";
    }
}
