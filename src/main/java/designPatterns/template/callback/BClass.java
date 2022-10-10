package designPatterns.template.callback;

/**
 * @Author: zhanmingwei
 */
public class BClass {

    public void process(ICallBack callBack){
        //...
        callBack.methodToCallBack();
        //...
    }
}
