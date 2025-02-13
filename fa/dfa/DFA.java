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
    private Set<String> states;
    private Set<Character> sigma;
    private Map<String, Map<Character, String>> transitionTable;
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

        Iterator<String> stateIterator = states.iterator();
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

        while (stateIterator.hasNext()) { // for each state
            // TODO**************************
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
        if (!states.contains(fromState) || !states.contains(toState) || !sigma.contains(onSymb)) {
            return false;
        }
        // if the fromState is not already in the transition table, add it
        if (!transitionTable.containsKey(fromState)) {
            transitionTable.put(fromState, new HashMap<>());
        }

        // add the transition to the table
        transitionTable.get(fromState).put(onSymb, toState);

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
        return states.add(name);
    }

	/**
	 * Marks an existing state as an accepting state
	 * @param name is the label of the state
	 * @return true if successful and false if no state with such name exists
	 */
	public boolean setFinal(String name) {
        if (states.contains(name)) {
            finalStates.add(name);
            return true;
        } else {
            return false;
        }
    }
	
	/**
	 * Adds the initial state to the DFA instance
	 * @param name is the label of the start state
	 * @return true if successful and false if no state with such name exists
	 */
	public boolean setStart(String name) {
        if (states.contains(name)) {
            startingState = name;
            return true;
        } else {
            return false;
        } 
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
        // TODO
        return true;
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
        // TODO
        return null;
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
    
}
