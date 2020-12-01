package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.BaseContract;

import java.time.LocalDate;

/**
 * BirthDate validation class
 */
public class DateOfBirthValidator implements IValidator {
    /**
     * Сhecks dateOfBith against criteria
     *
     * @param contract contract for verification
     * @return object with the result of validation
     */
    @Override
    public ValidationResult validate(BaseContract contract) {
        ValidationResult result = new ValidationResult();
        if (contract.getClient().getDateOfBirth().isBefore(LocalDate.now().minusYears(100))) {
            result.setStatus(CheckStatus.WARNING);
            result.setMessage("Возраст клиента превышает 99 лет");
            result.setFieldName("dateOfBirth");
        } else {
            result.setStatus(CheckStatus.OK);
            result.setMessage("корректные данные");
            result.setFieldName("dateOfBirth");
        }
        return result;
    }
}
