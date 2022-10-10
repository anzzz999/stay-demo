package designPatterns.interpreter;

import java.util.Map;

/**
 * @Author: zhanmingwei
 */
public interface Expression {
    boolean interpret(Map<String, Long> stats);
}
