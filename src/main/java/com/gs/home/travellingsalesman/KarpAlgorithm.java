package com.gs.home.travellingsalesman;

import com.gs.home.FindSubsets;

import java.util.*;
import java.util.stream.Collectors;

import static com.gs.home.log.Logger.*;

/**
 * Dynammic programming using Karp algorithm. Time complexity = 2 pow N * N square
 * Given a graph of 5 cities below with assocaited costs between cities, the algorithm
 * finds the shortest tour path. City 0 is the start and end
 */
public class KarpAlgorithm {
    private static Integer [] cities = {1, 2, 3, 4};
    private static final int start = 0;
    private static final Map<Index, Integer> costMap = new HashMap<>(); //Map to track the costs.
    private static final Map<Index, Integer> prevMap = new HashMap<>(); //Map to keep track of the visited PRIOR cities
    int [][] distances = {
            {0, 11, 10, 9, 17},
            {11, 0, 9, 14, 18},
            {10, 9, 0, 15, 19},
            {9, 13, 15, 0, 20},
            {17, 18, 19, 21, 0}
    };

    public List<List<Integer>> getCombinations() {
        FindSubsets findSubsets = new FindSubsets();
        List<List<Integer>> subsets = findSubsets.findSubsets(Arrays.asList(cities));
        Collections.sort(subsets, Comparator.comparingInt(List::size));
        return subsets;
    }

    /**
     * Tests for the combinations of the subsets of the cites and the CITY itself.
     * {1}, {2}, {1,2}...are subsets. Each of these are combined like so {1, [2]},
     * {1, [2,3]} using Index class. This Index is used the compute the min cost.
     */
    public void processTour() {
        List<List<Integer>> paths = getCombinations();
        for (List<Integer> subset: paths) {
            for (Integer city : cities) {
                if (subset.contains(city)) continue;

                int cost = 0;
                Index index = new Index(city, subset);
                if (index.getSet().isEmpty()) {
                    //distance from city to start needs to be populated
                    cost = distances[start][city];
                } else {
                    cost = getMinCost(index);
                }

                costMap.put(index, cost);
            }
        }

        /** To calculate the min distance from the source to other cities, thus completing the loop **/
        List<Integer> allCitySubset = Arrays.asList(cities);
        Index index = new Index(start, allCitySubset);
        int cost = getMinCost(index);
        costMap.put(new Index(start, allCitySubset), cost);
        stdout("Min cost of tour " + cost);
    }

    /**
     * Calculates the cost using previously updated map, thus leveraging dynamic programmming.
     * Ex. For this combination, say, {start = 1, end = [2, 3]}. Cost for 1==>2==>3.. AND 1==>3==>2 both are
     * computed and the minimum is taken. cost for 2->3 and 3->2 are taken from cost map updated in a
     * prior step
     * @param index
     * @return
     */
    private int getMinCost(Index index) {
        int minCost = Integer.MAX_VALUE;
        int cost = 0;
        for (Integer vertex : index.getSet()) {
            List<Integer> prevSet = getRemainingset(vertex, index.getSet());
            int current = index.getBegin();
            cost = distances[current][vertex] + costMap.get(new Index(vertex, prevSet));
            if (cost < minCost) {
                minCost = cost;
                prevMap.put(index, vertex);
            }
        }

        return minCost;
    }

    /**
     * Prints the tour path using the prevMap that was updated in the getMinCost method.
     * Logic: Last Index with 0 and [1,2,3,4] will have say, 2 as prev index. Then 2 is removed
     * from the above list to get [1,3, 4]. Using index 2 and [1,3,4], we get the next
     * prior index. This is repeated until the map is empty.
     */
    private void printTourPath() {
        List<Integer> allCities = Arrays.stream(cities).collect(Collectors.toList());
        StringBuilder tour = new StringBuilder(start + "==>");
        int begin = start;

        while (!allCities.isEmpty()) {
            Integer prevCity = prevMap.get(new Index(begin, allCities));
            tour.append(prevCity).append("==>");
            allCities.remove(prevCity);
            begin = prevCity;
        }

        tour.append(start);
        stdout(tour.toString());
    }

    private List<Integer> getRemainingset(int vertex, List<Integer> set) {
        return set.stream().filter(value -> value != vertex).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        KarpAlgorithm karpAlgorithm = new KarpAlgorithm();
        karpAlgorithm.processTour();
        karpAlgorithm.printTourPath();
    }
}
