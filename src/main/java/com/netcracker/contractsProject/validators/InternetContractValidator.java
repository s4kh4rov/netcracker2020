package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.InternetContract;

import java.util.List;
/**
 * Internet contract validator class
 */
public class InternetContractValidator {
    /**
     * Checks the contract according to all criteria
     * @param contract contract for verification
     * @return list with validation results
     */
    public static List<ValidationResult> checkContract(InternetContract contract) {
        List<ValidationResult> results = BaseContractValidator.checkContract(contract);
        results.add(checkMaxSpeed(contract));
        return results;
    }
    /**
     * Сhecks maxSpeed  against criteria
     * @param contract contract for verification
     * @return  object with the result of validation
     */
    public static ValidationResult checkMaxSpeed(InternetContract contract) {
        ValidationResult result = new ValidationResult();
        if (contract.getMaxSpeed() < 0) {
            result.setStatus(CheckStatus.WARNING);
            result.setMessage("Скорость не может быть отрицательной");
            result.setFieldName("maxSpeed");
        } else {
            result.setStatus(CheckStatus.OK);
            result.setMessage("корректные данные");
            result.setFieldName("maxSpeed");
        }
        return result;
    }
}
