package edu.virginia.cs.testingintro;

import java.util.*;

public class MySortedList {
    private ArrayList<Integer> mySortedList;
    private boolean isSorted;

    public MySortedList(ArrayList<Integer> starterList) {
        mySortedList = new ArrayList<>(starterList);
        isSorted = false;
    }

    public MySortedList() {
        this(new ArrayList<>());
    }

    public ArrayList<Integer> getSortedList() {
        if (!isSorted) {
            sortList();
        }
        return mySortedList;
    }

    public boolean isEmpty() {
        return mySortedList.isEmpty();
    }

    public void clear() {
        mySortedList = new ArrayList<>();
        isSorted = true;
    }

    public void add(int toAdd) {
        mySortedList.add(toAdd);
        isSorted = false;
    }

    public void remove(int index) {
        if (!isSorted) {
            sortList();
        }
        mySortedList.remove(index);
    }

    public int get(int index) {
        if (!isSorted) {
            sortList();
        }
        return mySortedList.get(index);
    }

    public int find(int target) {
        if (!isSorted) {
            sortList();
        }
        return mySortedList.indexOf(target);
    }

    public boolean contains(Object target) {
        if (!(target instanceof Integer)) {
            return false;
        }
        return mySortedList.contains(target);
    }

    public int size() {
        return mySortedList.size();
    }

    public String toString() {
        if (!isSorted) {
            sortList();
        }
        return mySortedList.toString();
    }

    private void sortList() {
        Collections.sort(mySortedList);
        isSorted = true;
    }
}
