package fa.dfa;
import java.util.HashMap;

/**
 * This class represent an individual state in a Deterministic Finite Automata (DFA).
 * DFAState holds names for states, a Hash Map representing next state transitions, 
 * methods for adding/removing next states, and other utilizing extending State functionaility.
 * 
 * @author Cameron Quitugua, Hailey Whitaker
 */
public class DFAState extends fa.State {

    private HashMap<Character, DFAState> delta;

    /**
     * Construct new DFA State with a name
     * Initialize transition table
     * @param name of state
     */
    public DFAState(String name) {
        super(name);
        this.delta = new HashMap<>();
    }

    /**
     * Add transition from this state
     * @param c corresponding transition input
     * @param state to connect to from current state
     */
    public void addNextState(Character c, DFAState state) {
        this.delta.put(c, state);
    }

    /**
     * Remove a transition
     * @param c corresponding transition state
     */
    public void removeNextState(Character c) {
        this.delta.remove(c);
    }

    /**
     * Return next state based on input
     * @param c input
     * @return next state transition given input symbol
     */
    public DFAState getNextState(Character c) {
        return this.delta.get(c);
    }

    /**
     * Get transition table for this state
     * @return copy of next state transitions from current state
     */
    public HashMap<Character, DFAState> getNextState() {
        return new HashMap<>(this.delta);
    }






}
