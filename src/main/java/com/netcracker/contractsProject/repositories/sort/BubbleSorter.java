package com.netcracker.contractsProject.repositories.sort;

import com.netcracker.contractsProject.сontracts.BaseContract;

import java.util.Comparator;

public class BubbleSorter<T extends BaseContract> implements ISorter<T> {
    /**
     * sorts an array of contracts based on bubble sort
     *
     * @param contracts  an array of contracts to be sorted
     * @param comparator comparator with sorting logic
     */
    @Override
    public void sort(BaseContract[] contracts, Comparator<T> comparator) {
        boolean isSorted = false;
        BaseContract buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < contracts.length - 1; i++) {
                if (contracts[i] != null && contracts[i + 1] != null && comparator.compare((T) contracts[i], (T) contracts[i + 1]) > 0) {
                    isSorted = false;
                    buf = contracts[i];
                    contracts[i] = contracts[i + 1];
                    contracts[i + 1] = buf;
                }
            }
        }
    }
}
