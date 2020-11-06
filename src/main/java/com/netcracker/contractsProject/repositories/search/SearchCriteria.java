package com.netcracker.contractsProject.repositories.search;

import com.netcracker.contractsProject.сontracts.BaseContract;
import com.netcracker.contractsProject.сontracts.InternetContract;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Class containing search criteria in the repository
 */
public class SearchCriteria {

    /**
     * Date search criteria
     *
     * @param sd start date for comparison
     * @return a predicate that can be used as a start date search criterion in the contracts repository
     */
    public static <T extends BaseContract> Predicate<T> byStartDate(LocalDate sd) {
        return p -> p.getStartDate().equals(sd);
    }

    /**
     * Date search criteria
     *
     * @param ed expiration date for comparison
     * @return a predicate that can be used as a expiration date search criterion in the contracts repository
     */
    public static <T extends BaseContract> Predicate<T> byExpirationDate(LocalDate ed) {
        return p -> p.getExpirationDate().equals(ed);
    }

    /**
     * Speed search criteria
     *
     * @param speed speed for comparison
     * @return a predicate that can be used as a speed search criterion in the contracts repository
     */
    public static Predicate<InternetContract> bySpeed(double speed) {
        return p -> p.getMaxSpeed() == speed;
    }
}
