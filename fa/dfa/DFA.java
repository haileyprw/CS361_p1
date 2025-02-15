package fa.dfa;

import java.util.Set;

import fa.State;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.HashMap;

/**
 * This class represents a Deterministic Finitea Automatin (DFA).
 * Includes method to add to alphabet (sigma), add states (Q),
 * set starting (q0) and accepting/final states (F), and modify a
 * transition table (delta).
 * 
 * @author Cameron Quitugua, Hailey Whitaker
 */
public class DFA implements DFAInterface {

    // 5-Tuple Variables
    private Set<DFAState> states;
    private Set<Character> sigma;
    private Map<DFAState, Map<Character, DFAState>> transitionTable;
    private DFAState startingState;
    private Set<DFAState> finalStates;

    /** 
     * DFA Constructor
     * */
    public DFA() {
        // Instance variables
        this.states = new LinkedHashSet<>();
        this.sigma = new LinkedHashSet<>();
        this.transitionTable = new HashMap<>();
        this.finalStates = new LinkedHashSet<>();
    }


    /**
	 * Construct the textual representation of the DFA, for example
	 * A simple two state DFA
	 * Q = { a b }
	 * Sigma = { 0 1 }
	 * delta =
	 *		0	1	
	 *	a	a	b	
	 *	b	a	b	
	 * q0 = a
	 * F = { b }
	 * 
	 * The order of the states and the alphabet is the order
	 * in which they were instantiated in the DFA.
	 * @return String representation of the DFA
	 */
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Q = { ");
        for (DFAState state : this.states) sb.append(state.getName()).append(" "); //Print existing states
        sb.append("}\n");

        sb.append("Sigma = { ");
        for (char c : this.sigma) sb.append(c).append(" "); //Print alphabet
        sb.append("}\n");

        sb.append("delta =\n\t"); 
        for (char c : this.sigma) sb.append(c).append("\t");
        sb.append("\n");

        for (DFAState state : this.states) { //Print transition table
            sb.append(state.getName()).append("\t");
            Map<Character, DFAState> transitions = this.transitionTable.getOrDefault(state, new HashMap<>());
            for (char c : this.sigma) {
                sb.append(transitions.getOrDefault(c, new DFAState("-")).getName()).append("\t");
            }
            sb.append("\n");
        }

        sb.append("q0 = ").append(startingState.getName()).append("\n"); //Print starting state
        sb.append("F = { ");
        for (DFAState finalState : this.finalStates) sb.append(finalState.getName()).append(" "); //Print accepting states
        sb.append("}\n");
        return sb.toString();
    }
	
	
	/**
	 * Adds the transition to the DFA's delta data structure
	 * @param fromState is the label of the state where the transition starts
	 * @param toState is the label of the state where the transition ends
	 * @param onSymb is the symbol from the DFA's alphabet.
	 * @return true if successful and false if one of the states don't exist or the symbol in not in the alphabet
	 */
	public boolean addTransition(String fromState, String toState, char onSymb) {

        DFAState from = getStateByName(fromState);
        DFAState to = getStateByName(toState);

        if (from == null || to == null || !this.sigma.contains(onSymb)) { //Check if states exist or symbol exist
            return false;
        }

        from.addNextState(onSymb, to);
        this.transitionTable.put(from, from.getNextState());

        return true;
    }
	
	/**
	 * Creates a deep copy of this DFA
	 * which transitions labels are
	 * swapped between symbols symb1
	 * and symb2.
	 * @return a copy of this DFA
	 */
	public DFA swap(char symb1, char symb2) {
        DFA swapCopy = new DFA();

        for (Character s : this.sigma) {
            swapCopy.addSigma(s); //Copy over alphbet (sigma)
        }

        for (DFAState state : this.states) {  
            swapCopy.addState(state.getName()); //Copy over existing states (Q)
        }

        swapCopy.setStart(startingState.getName()); //Copy over starting state (q0)

        for (DFAState finals : this.finalStates) { //Copy over accepting states (F)
            swapCopy.setFinal(finals.getName());
        }

        for (DFAState state : this.states) { //Copy transition table (delta)
            swapCopy.addTransition(state.getName(), state.getNextState(symb1).getName(), symb2); //Swaps next state transitions symb1 -> symb2
            swapCopy.addTransition(state.getName(), state.getNextState(symb2).getName(), symb1); //Swaps next state transitions symb2 -> symb1
        }

        return swapCopy;
    }

    /**
	 * Adds a a state to the FA instance
	 * @param name is the label of the state 
	 * @return true if a new state created successfully and false if there is already state with such name
	 */
	public boolean addState(String name) {
        for (DFAState state : this.states) {
            if (state.getName().equals(name)) { //Check state with 'name' doesn't exist
                return false;
            }
        }

        DFAState state = new DFAState(name);
        return this.states.add(state);
    }

	/**
	 * Marks an existing state as an accepting state
	 * @param name is the label of the state
	 * @return true if successful and false if no state with such name exists
	 */
	public boolean setFinal(String name) {
        DFAState state = getStateByName(name);
        if (state != null) {
            this.finalStates.add(state);
            return true;
        }
        return false;
    }
	
	/**
	 * Adds the initial state to the DFA instance
	 * @param name is the label of the start state
	 * @return true if successful and false if no state with such name exists
	 */
	public boolean setStart(String name) {
        DFAState state = getStateByName(name);
        if (state != null) {
            this.startingState = state;
            return true;
        }
        return false;
    }
	
	/**
	 * Adds a symbol to Sigma
	 * @param symbol to add to the alphabet set
	 */
	public void addSigma(char symbol) {
        this.sigma.add(symbol);
    }


	/**
	 * Simulates a DFA on input s to determine
	 * whether the DFA accepts s.
	 * @param s - the input string
	 * @return true if s in the language of the DFA and false otherwise
	 */
	public boolean accepts(String s) {
        
        if (this.startingState == null) {
            return false;
        }
        DFAState currentState = this.startingState;

        for (char c : s.toCharArray()) {
            if (!sigma.contains(c)) {
                return false;
            }
            currentState = this.transitionTable.getOrDefault(currentState, new HashMap<>()).get(c);
            if (currentState == null) {
                return false;
            }
        }
        return this.finalStates.contains(currentState);
    }
	
	
	/**
	 * Getter for Sigma
	 * @return the alphabet of FA
	 */
	public Set<Character> getSigma() {
        return new LinkedHashSet<>(this.sigma);
    }
	
	
	/**
	 * Returns state with the given name, or null if none exists
	 * @param name of a state
	 * @return state object or null
	 */
	public State getState(String name) {
        return getStateByName(name);
    }
        
	/**
	 * Determines if a state with a given name is final
	 * @param name the name of the state
	 * @return true if a state with that name exists and it is final
	 */
	public boolean isFinal(String name) {
        DFAState state = getStateByName(name);
        if (state != null && this.finalStates.contains(state)) {
            return true;
        }
        return false;
    }
	
	/**
	 * Determines if a state with name is the start state
	 * @param name the name of the state
	 * @return true if a state with that name exists and it is the start state
	 */
	public boolean isStart(String name) {
        if (this.startingState != null && this.startingState.getName().equals(name)) {
            return true;
        }
        return false;
    }

    /** Helper method to return state object by name */
    private DFAState getStateByName(String name) {
        for (DFAState state : this.states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }
    
}
