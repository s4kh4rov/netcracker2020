package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.TVContract;

import java.util.List;
/**
 * TV contract validator class
 */
public class TVContractValidator {
    /**
     * Checks the contract according to all criteria
     * @param contract contract for verification
     * @return list with validation results
     */
    public static List<ValidationResult> checkTVContract(TVContract contract) {
        return BaseContractValidator.checkContract(contract);
    }
}
