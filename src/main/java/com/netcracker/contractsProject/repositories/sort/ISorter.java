package com.netcracker.contractsProject.repositories.sort;

import com.netcracker.contractsProject.сontracts.BaseContract;

import java.util.Comparator;

public interface ISorter<T> {
    public void sort(BaseContract[] contracts, Comparator<T> comparator);
}
