package fa.dfa;

import java.util.Set;

import fa.State;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

/** DFA class that implements DFA Interfaces */
public class DFA implements DFAInterface {

    // 5-Tuple Variables
    private Set<DFAState> states;
    private Set<Character> sigma;
    private Map<DFAState, Map<Character, DFAState>> transitionTable;
    private String startingState;
    private Set<String> finalStates;

    /** 
     * 
     * DFA Constructor
     *  
     * */
    public DFA() {
        // Instance variables
        states = new HashSet<>();
        sigma = new HashSet<>();
        transitionTable = new HashMap<>();
        finalStates = new HashSet<>();
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

        Iterator<DFAState> stateIterator = states.iterator();
        Iterator<Character> alphabetIterator = sigma.iterator();
        Iterator<String> finalStateIterator = finalStates.iterator();
        String returnString;

        // Print States
        returnString = "Q = { ";
        while (stateIterator.hasNext()) {
            returnString += stateIterator.next() + " ";
        }
        returnString += "}\n";

        // Print alphabet
        returnString += "Sigma = { ";
        while (alphabetIterator.hasNext()) {
            returnString += alphabetIterator.next() + " ";
        }
        returnString += "}\n";

        // Print transition table
        returnString += "delta =\n\t";
        while (alphabetIterator.hasNext()) {
            returnString += alphabetIterator.next() + "\t"; // print alphabet column header
        }
        returnString += "\n";

        for (DFAState state : states) { // for each state
            returnString += state.getName() + "\t";
            Map<Character, DFAState> transitions = transitionTable.get(state);

            for (char symb : sigma) { // for each character in the alphabet
                if (transitions != null && transitions.containsKey(symb)) {
                    returnString += transitions.get(symb) + "\t";
                } 
                // else {
                //     sb.append("-\t"); // Placeholder for missing transitions
                // }
            }
            returnString += "\n";
        }

        // Print starting state
        returnString += "q0 = " + startingState + "\n";

        // Print final state(s)
        returnString += "F = { ";
        while (finalStateIterator.hasNext()) {
            returnString += finalStateIterator.next() + " ";
        }
        returnString += "}\n";


        return returnString;
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

        if (from == null || to == null || !sigma.contains(onSymb)) {
            return false;
        }

        // Add fromState if missing
        transitionTable.putIfAbsent(from, new HashMap<>());

        // Add transition to table
        transitionTable.get(from).put(onSymb, to);
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
        // TODO
        return new DFA();
    }

    /**
	 * Adds a a state to the FA instance
	 * @param name is the label of the state 
	 * @return true if a new state created successfully and false if there is already state with such name
	 */
	public boolean addState(String name) {
        DFAState state = new DFAState(name);
        return states.add(state);
    }

	/**
	 * Marks an existing state as an accepting state
	 * @param name is the label of the state
	 * @return true if successful and false if no state with such name exists
	 */
	public boolean setFinal(String name) {
        DFAState state = getStateByName(name);
        if (state != null) {
            finalStates.add(state.getName());
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
            startingState = state.getName();
            return true;
        }
        return false;
    }
	
	/**
	 * Adds a symbol to Sigma
	 * @param symbol to add to the alphabet set
	 */
	public void addSigma(char symbol) {
        sigma.add(symbol);
    }


	/**
	 * Simulates a DFA on input s to determine
	 * whether the DFA accepts s.
	 * @param s - the input string
	 * @return true if s in the language of the DFA and false otherwise
	 */
	public boolean accepts(String s) {
        DFAState currentState = getStateByName(startingState);
        if (currentState == null) return false;

        for (char c : s.toCharArray()) {
            if (!sigma.contains(c)) return false;
            currentState = transitionTable.getOrDefault(currentState, new HashMap<>()).get(c);
            if (currentState == null) return false;
        }
        return finalStates.contains(currentState.getName());
    }
	
	
	/**
	 * Getter for Sigma
	 * @return the alphabet of FA
	 */
	public Set<Character> getSigma() {
        return new HashSet<>(sigma);
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
        return finalStates.contains(name);
    }
	
	/**
	 * Determines if a state with name is the start state
	 * @param name the name of the state
	 * @return true if a state with that name exists and it is the start state
	 */
	public boolean isStart(String name) {
        return name.equals(startingState);
    }

    /** Helper method to return state object by name */
    private DFAState getStateByName(String name) {
        for (DFAState state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }
    
}
