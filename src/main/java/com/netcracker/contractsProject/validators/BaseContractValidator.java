package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.BaseContract;

import java.util.List;

/**
 * Base contract validator class
 */
public class BaseContractValidator {

    /**
     * Checks the contract according to all criteria
     * @param contract contract for verification
     * @return list with validation results
     */
    public static List<ValidationResult> checkContract(BaseContract contract) {
        List<ValidationResult> result = ClientValidator.checkClient(contract.getClient());
        result.add(checkDate(contract));
        return result;
    }

    /**
     * Сhecks start date and end date against criteria
     * @param contract contract for verification
     * @return  object with the result of validation
     */
    public static ValidationResult checkDate(BaseContract contract) {
        ValidationResult result = new ValidationResult();
        if (contract.getExpirationDate().isBefore(contract.getStartDate())) {
            result.setStatus(CheckStatus.ERROR);
            result.setMessage("Дата окончания действия контракта не может быть раньше даты начала");
            result.setFieldName("ExpirationDate");
        } else {
            result.setStatus(CheckStatus.OK);
            result.setMessage("корректные данные");
            result.setFieldName("ExpirationDate");
        }
        return result;
    }

}
