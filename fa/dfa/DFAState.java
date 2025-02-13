package fa.dfa;
import java.util.HashMap;
/* Extends fa.State */
public class DFAState extends fa.State {

    private boolean fin; //Accepting State
    private HashMap<Character, DFAState> delta = new HashMap<>();

    public DFAState(String name) {
        super(name);
        this.fin = false;
    }

    /**
     * Add transition from this state
     * @param c
     * @param state
     */
    public void addTransition(Character c, DFAState state) {
        delta.put(c, state);
    }

    /**
     * Return next state based on input
     * @param c
     * @return
     */
    public DFAState getNextState(Character c) {
        return delta.get(c);
    }

    /**
     * Set accepting state
     */
    public void setFinalState() {
        this.fin = true;
    }

    /**
     * True is this state is an accepting state
     * @return
     */
    public boolean isFinalState() {
        return fin;
    }

    /**
     * Get transition table for this state
     * @return
     */
    public HashMap<Character, DFAState> getTransitions() {
        return delta;
    }






}
