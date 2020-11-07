package com.netcracker.contractsProject.repositories.sort;

import com.netcracker.contractsProject.сontracts.BaseContract;
import com.netcracker.contractsProject.сontracts.CellularContract;
import com.netcracker.contractsProject.сontracts.InternetContract;

import java.util.Comparator;

public class ComparsionCriteria {

    public static <T extends BaseContract> Comparator<T> sortById() {
        return (b1, b2) -> b1.getId() - b2.getId();
    }

    public static <T extends BaseContract> Comparator<T> sortByStartDate() {
        return (b1, b2) -> b1.getStartDate().compareTo(b2.getStartDate());

    }

    public static <T extends BaseContract> Comparator<T> sortByExpirationDate() {
        return (b1, b2) -> b1.getExpirationDate().compareTo(b2.getExpirationDate());
    }

    public static Comparator<InternetContract> sortBySpeed() {
        return (c1, c2) -> Double.compare(c1.getMaxSpeed(), c2.getMaxSpeed());
    }

}
