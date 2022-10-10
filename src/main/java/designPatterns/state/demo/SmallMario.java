package designPatterns.state.demo;

/**
 * @Author: zhanmingwei
 */
public class SmallMario implements IMario {

    private static SmallMario instance = new SmallMario();

    private SmallMario(){

    }

    public static SmallMario getInstance() {
        return SmallMario.instance;
    }

    @Override
    public State getName() {
        return State.SMALL;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine stateMachine) {
        stateMachine.setScore(stateMachine.getScore() + 100);
        stateMachine.setCurrentState(SuperMario.getInstance());

    }

    @Override
    public void obtainCape(MarioStateMachine stateMachine) {

    }

    @Override
    public void obtainFireFlower(MarioStateMachine stateMachine) {

    }

    @Override
    public void meetMonster(MarioStateMachine stateMachine) {

    }
}
