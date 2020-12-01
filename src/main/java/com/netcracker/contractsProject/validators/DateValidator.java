package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.BaseContract;

/**
 * Date validation class
 */
public class DateValidator implements IValidator {
    /**
     * Сhecks start date and end date against criteria
     *
     * @param contract contract for verification
     * @return object with the result of validation
     */
    @Override
    public ValidationResult validate(BaseContract contract) {
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
