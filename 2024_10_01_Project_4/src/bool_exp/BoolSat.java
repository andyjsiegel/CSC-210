package bool_exp;
// package com.gradescope.bool_exp;

/*
This program uses the methods from the ASTNode and BoolSatParser classes
to take in a file name and extract the boolean expression from it, then
evaluate it. If a second argument is provided ("DEBUG"), all variable assignments
will be printed and their result. Otherwise, only variable assignments that
evaluate to true will be printed, without their result. 
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BoolSat {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1 || args.length > 2) {
            System.err.println("USAGE: java BoolSat <inputFile.txt> <DEBUG*>");
            System.exit(1);
        }

        // Get the expression from the file
        String expression;
        Scanner s = new Scanner(new File(args[0]));
        expression = s.nextLine();
        System.out.println("input: " + expression);
        s.close();

        // Call the parser to generate the AST for the expression
        ASTNode root = BoolSatParser.parse(expression);

        // Get string output to print out
        String output;
        
        // turn on/off debug mode
        if (args.length == 2 && args[1].equals("DEBUG")) {
            output = getOuput(root, true);
        } else {
            output = getOuput(root, false);
        }

        // finally print output string
        System.out.println(output);
    }

    // Method to check satisfiability and generate output
    public static String getOuput(ASTNode root, boolean debug) {
        Set<String> variables = new HashSet<>();
        collectVariables(root, variables); // Collect all variables from the AST

        List<String> configurations = new ArrayList<>();
        boolean satisfiable = evaluateSatisfiability(root, variables, new HashMap<>(), 0, configurations, debug);

        // Prepare the output
        StringBuilder result = new StringBuilder();
        result.append(satisfiable ? "SAT" : "UNSAT").append("\n");
        Collections.sort(configurations); // Sort configurations alphabetically
        for (String config : configurations) {
            result.append(config).append("\n");
        }

        return result.toString().trim(); // Remove trailing newline
    }

    // loop through the tree and get all the variables to test their assignments
    private static void collectVariables(ASTNode node, Set<String> variables) {
        // if node has id, it has no children and is a variable
        if (node.isId()) {
            variables.add(node.getId());
            /* otherwise iterate through children, but do not use else just in case
            null node exists. */
        } else if (node.isNand()) {
            collectVariables(node.child1, variables);
            collectVariables(node.child2, variables);
        }
    }

    // Recursive method to evaluate satisfiability
    public static boolean evaluateSatisfiability(ASTNode root, Set<String> variables, Map<String, Boolean> assignment, int index, List<String> configurations, boolean debug) {
        // base case: if all variables are assigned, evaluate the expression.
        if (index == variables.size()) {
            boolean result = evaluateExpression(root, assignment);
            if (result == true || debug == true) { 
                /* conditionally add configurations that either evaluate to 
                true or add all of them if debug mode is enabled. */
                
                // use StringBuilder for memory/efficiency purposes
                StringBuilder config = new StringBuilder();
                List<String> sortedKeys = new ArrayList<>(assignment.keySet());
                Collections.sort(sortedKeys); // Sort keys for consistent output
                for (String key : sortedKeys) {
                    config.append(key).append(": ").append(assignment.get(key)).append(", ");
                }
                if (debug) {
                    config.append(result); /* only add the evaluation of the 
                    expression if debug mode is enabled. */
                }
                // clean up the string
                configurations.add(config.toString().replaceAll(", $", ""));
            }
            return result;
        }

        // Get the current variable
        String[] varArray = variables.toArray(new String[0]);
        String currentVar = varArray[index];

        // Try assigning true to the current variable
        assignment.put(currentVar, true);
        boolean foundSatisfiable = evaluateSatisfiability(root, variables, assignment, index + 1, configurations, debug);

        // Try assigning false to the current variable
        assignment.put(currentVar, false);
        // use bitwise OR operator to not overwrite the variable if it is true
        foundSatisfiable |= evaluateSatisfiability(root, variables, assignment, index + 1, configurations, debug);

        // backtracking
        assignment.remove(currentVar);
        return foundSatisfiable; // Return if any assignment was satisfiable
        // ArrayList configurations keeps track of of all configurations
    }

    // Evaluate the boolean expression based on the current assignment
    public static boolean evaluateExpression(ASTNode node, Map<String, Boolean> assignment) {
        if (node.isId()) {
            return assignment.getOrDefault(node.getId(), false); /* Default to 
            false if not assigned */
        } else if (node.isNand()) {
            boolean leftValue = evaluateExpression(node.child1, assignment);
            boolean rightValue = evaluateExpression(node.child2, assignment);
            return !(leftValue && rightValue); // NAND operation -- NAND = not AND
        }
        throw new UnsupportedOperationException("Unknown node type");
    }
}
