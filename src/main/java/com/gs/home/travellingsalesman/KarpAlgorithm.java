package com.gs.home.travellingsalesman;

import com.gs.home.FindSubsets;

import java.util.*;
import java.util.stream.Collectors;

import static com.gs.home.log.Logger.*;

/**
 * Dynammic programming using Karp algorithm. Time complexity = 2 pow N * N square
 */
public class KarpAlgorithm {
    private static Integer [] cities = {1, 2, 3};
    private static final int start = 0;
    private static final Map<Index, Integer> costMap = new HashMap<>();
    int [][] distances = {
            {0, 11, 17, 9},
            {11, 0, 8, 16},
            {17, 8, 0, 15},
            {9, 16, 15, 0}
    };

    public List<List<Integer>> getCombinations() {
        FindSubsets findSubsets = new FindSubsets();
        List<List<Integer>> subsets = findSubsets.findSubsets(Arrays.asList(cities));
        Collections.sort(subsets, Comparator.comparingInt(List::size));
        return subsets;
    }

    public void processTour() {
        List<List<Integer>> paths = getCombinations();
//        stdout(paths);
        for (List<Integer> subset: paths) {
            boolean processed = false;
            for (Integer city : cities) {
                if (subset.contains(city)) continue;

                int cost = 0;
                Index index = new Index(city, subset);
//                stdout(i);
                if (index.getSet().isEmpty()) {
                    //distance from city to source needs to be populated
                    cost = distances[start][city];
                } else {
                    cost = getMinCost(index);
                }

                processed = true;
                costMap.put(index, cost);
//                stdout(index + " " + subset);
            }

            if (!processed) {
                Index i = new Index(start, subset);
                int cost = getMinCost(i);
                costMap.put(i, cost);
            }
        }
    }

    private int getMinCost(Index i) {
        int minCost = Integer.MAX_VALUE;
        int cost = 0, prev = 0;
        Index temp = null;
        for (Integer vertex : i.getSet()) {
            List<Integer> prevSet = getRemainingset(vertex, i.getSet());
            int current = i.getBegin();
            cost = distances[current][vertex] + costMap.get(new Index(vertex, prevSet));
            if (cost < minCost) {
                minCost = cost;
                prev = vertex;
            }
        }

        List<Integer> remainingset = getRemainingset(prev, i.getSet());
        if (prev != 0 && !remainingset.isEmpty()) {
            stdout(i.getBegin() + "," + prev + ", via: " + remainingset);
        }
        return minCost;
    }

    private List<Integer> getRemainingset(int vertex, List<Integer> set) {
        return set.stream().filter(value -> value != vertex).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        KarpAlgorithm karpAlgorithm = new KarpAlgorithm();
        karpAlgorithm.processTour();
        costMap.entrySet().forEach((entry -> stdout(entry.getKey() + "," + entry.getValue())));
    }
}
