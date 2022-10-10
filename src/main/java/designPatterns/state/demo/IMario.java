package designPatterns.state.demo;

/**
 * @Author: zhanmingwei
 */
public interface IMario {

    State getName();

    void obtainMushRoom(MarioStateMachine stateMachine);

    void obtainCape(MarioStateMachine stateMachine);

    void obtainFireFlower(MarioStateMachine stateMachine);

    void meetMonster(MarioStateMachine stateMachine);

}
