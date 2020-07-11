package com.gs.home;

import com.gs.home.log.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created by chandrashekar on 7/12/2018.
 * Time complexity(O(2 ^n - 1). 2 ^ n -1 ops for all the elements
 */
public class FindSubsets {
    public  List<List<Integer>> findSubsets(List<Integer> inputList) {
        if (inputList == null || inputList.isEmpty()) return Collections.emptyList();

        return getSubsets(inputList);
    }

    private List<List<Integer>> getSubsets(List<Integer> inputList) {
        if (inputList == null || inputList.isEmpty()) {
            List<List<Integer>> list = new ArrayList<>();
            list.add(Collections.EMPTY_LIST);
            return list;
        }

        Integer first = inputList.get(0);
        List<Integer> subList = inputList.subList(1, inputList.size());
        List<List<Integer>> subsets = getSubsets(subList);
        return combine(first, subsets);
    }

    /**
     * 1) Create a result list
     * 2) Loop through the subsets, get each set and add to the result list
     * 3) Now add first to the list of each subsets and append them to the result list
     * @param first
     * @param subsets
     */
    private List<List<Integer>> combine(Integer first, List<List<Integer>> subsets) {
        List<List<Integer>> resultList = new ArrayList<>();

        subsets.stream().forEach(resultList::add);
        return concatenate(first, subsets, resultList);
    }

    private List<List<Integer>> concatenate(Integer first, List<List<Integer>> subsets, List<List<Integer>> resultList) {
        subsets.stream().forEach(subset -> {
            List<Integer> list = new ArrayList<>();
            list.add(first);
            list.addAll(subset);
            resultList.add(list);
        });

        return resultList;
    }

    private static boolean isSum16(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum() == 16;
    }

    public static void main(String[] args) {
        List<Integer> inputList = Arrays.asList(2, 4, 6, 10);
        List<List<Integer>> subsets = new FindSubsets().findSubsets(inputList);
        subsets.stream().filter(FindSubsets::isSum16).forEach(Logger::stdout);
    }
}
