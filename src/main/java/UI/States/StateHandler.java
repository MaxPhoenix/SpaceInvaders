package UI.States;

/**
 * Created by Max on 3/3/2017.
 */
public class StateHandler {
    //Static stuff
    private static State currentState;

    public static State getCurrentState(){
        return currentState;
    }

    public static void setCurrentState(State state){
        currentState = state;
    }
}
