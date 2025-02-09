package designPatterns.state.chabiao;

/**
 * @Author: zhanmingwei
 */
enum State {
    SMALL(0),
    SUPER(1),
    FIRE(2),
    CAPE(3);

    private int value;

    private State(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}


 enum Event {
    GOT_MUSHROOM(0),
    GOT_CAPE(1),
    GOT_FIRE(2),
    MET_MONSTER(3);

    private int value;

    private Event(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}


class MarioStateMachine {
    private int score;
    private State currentState;

    private static final State[][] transitionTable = {
            {State.SUPER, State.CAPE, State.FIRE, State.SMALL},
            {State.SUPER, State.CAPE, State.FIRE, State.SMALL},
            {State.CAPE, State.CAPE, State.CAPE, State.SMALL},
            {State.FIRE, State.FIRE, State.FIRE, State.SMALL}
    };
    private static final int[][] actionTable = {
            {+100, +200, +300, +0},
            {+0, +200, +300, -100},
            {+0, +0, +0, -200},
            {+0, +0, +0, -300}
    };

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    public void obtainMushRoom() {
        executeEvent(Event.GOT_MUSHROOM);
    }


    public void obtainCape() {
        executeEvent(Event.GOT_CAPE);
    }

    public void obtainFireFlower() {
        executeEvent(Event.GOT_FIRE);
    }

    public void meetMonster() {
        executeEvent(Event.MET_MONSTER);
    }

    private void executeEvent(Event event) {
        this.score += actionTable[currentState.getValue()][event.getValue()];
        this.currentState = transitionTable[currentState.getValue()][event.getValue()];
    }


    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }
}

public class Test2 {
    public static void main(String[] args) {
        MarioStateMachine marioStateMachine = new MarioStateMachine();
        marioStateMachine.obtainCape();
        marioStateMachine.meetMonster();
        System.out.println("currentState:" + marioStateMachine.getCurrentState() + " ,currentScore:" + marioStateMachine.getScore());

    }
}
