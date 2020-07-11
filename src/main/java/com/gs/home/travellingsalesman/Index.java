package com.gs.home.travellingsalesman;

import java.util.List;
import java.util.Objects;

public class Index {
    int begin;
    List<Integer> set;

    public Index(int city, List<Integer> subset) {
        this.begin = city;
        this.set = subset;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public List<Integer> getSet() {
        return set;
    }

    public void setSet(List<Integer> set) {
        this.set = set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return begin == index.begin && set.equals(index.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, set);
    }

    @Override
    public String toString() {
        return "{" + "start=" + begin + ", end=" + set + '}';
    }
}
