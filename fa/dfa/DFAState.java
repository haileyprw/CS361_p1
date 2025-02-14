package fa.dfa;
import java.util.HashMap;
/* Extends fa.State */
public class DFAState extends fa.State {

    private boolean fin; //Accepting State
    private HashMap<Character, DFAState> delta;

    public DFAState(String name) {
        super(name);
        this.delta = new HashMap<>();
        this.fin = false;
    }

    /**
     * Add transition from this state
     * @param c
     * @param state
     */
    public void addNextState(Character c, DFAState state) {
        this.delta.put(c, state);
    }

    /**
     * Remove a transition
     */
    public void removeNextState(Character c) {
        this.delta.remove(c);
    }

    /**
     * Return next state based on input
     * @param c
     * @return
     */
    public DFAState getNextState(Character c) {
        return this.delta.get(c);
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
        return this.fin;
    }

    /**
     * Get transition table for this state
     * @return
     */
    public HashMap<Character, DFAState> getNextState() {
        return new HashMap<>(this.delta);
    }






}
