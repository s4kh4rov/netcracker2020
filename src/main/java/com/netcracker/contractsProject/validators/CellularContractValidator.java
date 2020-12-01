package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.CellularContract;

import java.util.List;

/**
 * Cellular contract validator class
 */
public class CellularContractValidator {

    /**
     * Checks the contract according to all criteria
     * @param contract contract for verification
     * @return list with validation results
     */
    public static List<ValidationResult> checkCellularContract(CellularContract contract){
        return BaseContractValidator.checkContract(contract);

    }
}
