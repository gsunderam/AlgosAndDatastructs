package com.gs.home.knapsack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.gs.home.log.Logger.stdout;

/**
 * Knapsack solved in Java. Time Complexity = num(items N) * Capacity = NC
 */
public class Knapsack {
    private static final int [] weights = {0, 2, 3, 4, 4, 5, 9}; //items
    private static final int [] values = {0, 3, 4, 3, 5, 8, 10}; //profits for each item
    private static final int CAPACITY = 20;

    /** Populate this table so there is no need to recompute values */
    private static final int [][] knapsackTable = new int[weights.length][CAPACITY + 1];

    /** Map to keep track of the items selected for the best solution. Key will be the profit value like
     *  10. Value will be - delimited rows to keep like so "4-3-2". The last element will have the
     *  best solution */
    private static final Map<Integer, String> map = new HashMap<>();

    private void printTable() {
        for (int i = 0; i < weights.length; i++) {
            stdout(Arrays.toString(knapsackTable[i]));
        }

        stdout(map);
    }

    public void solve() {
        for (int item = 1; item < weights.length; item++) {
            for (int w = 1; w <= CAPACITY ; w++) {
                if (weights[item] > w) { //if item wt > knapsack size, get it from prev row.
                    knapsackTable[item][w] = knapsackTable[item - 1][w];
                } else {
                    int prevValue = knapsackTable[item - 1][w];
                    int residue = knapsackTable[item - 1][w - weights[item]];
                    int currentValue = values[item] + residue;
                    if (prevValue > currentValue) {
                        knapsackTable[item][w] = prevValue;
                    } else {
                        knapsackTable[item][w] = currentValue;
                        /** Get the prev stored rows and append it to current one using - delimiter */
                        String prevRows = map.get(residue) != null ? map.get(residue) : "";
                        if (!map.containsKey(currentValue)) map.put(currentValue, item + "-" + prevRows);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        knapsack.solve();
        knapsack.printTable();
        knapsack.solution();
    }

    /**
     * Max profit will be in the last element of the knapsackTable. Map with this key value
     * will have the - delimited items to select
     **/
    private void solution() {
        int profit = knapsackTable[weights.length - 1][CAPACITY];
        String [] bestRows = map.get(profit).split("-");
        stdout("Items (index) to select are: " + Arrays.toString(bestRows));
        Integer capacity = Arrays.stream(bestRows).map(row -> weights[Integer.valueOf(row)]).reduce(0, Integer::sum);
        stdout(String.format("Max Profit = %d and capacity = %d ", profit,  capacity));
    }
}
