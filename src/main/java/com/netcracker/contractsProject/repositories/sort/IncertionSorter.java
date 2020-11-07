package com.netcracker.contractsProject.repositories.sort;

import com.netcracker.contractsProject.сontracts.BaseContract;

import java.util.Comparator;

public class IncertionSorter<T extends BaseContract> implements ISorter<T> {
    /**
     * sorts an array of contracts based on incertion sort
     *
     * @param contracts  an array of contracts to be sorted
     * @param comparator comparator with sorting logic
     */
    @Override
    public void sort(BaseContract[] contracts, Comparator<T> comparator) {
        for (int i = 1; i < contracts.length; i++) {
            for (int j = i; j > 0 && contracts[i] != null && contracts[i + 1] != null && comparator.compare((T) contracts[j - 1], (T) contracts[j]) > 0; j--) {
                BaseContract tmp = contracts[j - 1];
                contracts[j - 1] = contracts[j];
                contracts[j] = tmp;
            }
        }
    }
}
