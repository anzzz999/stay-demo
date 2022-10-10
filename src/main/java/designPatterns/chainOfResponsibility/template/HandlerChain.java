package designPatterns.chainOfResponsibility.template;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanmingwei
 */
public class HandlerChain {
    private List<IHandler> list = new ArrayList<>();

    public boolean addHandler(IHandler handler){
        return list.add(handler);
    }

    public void handle(){
        for (IHandler handler : list) {
            if (handler.handle()){
                break;
            }
        }
    }
}
