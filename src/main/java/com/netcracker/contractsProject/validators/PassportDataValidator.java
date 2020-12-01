package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.BaseContract;

/**
 * Passport data validation class
 */
public class PassportDataValidator<T extends BaseContract> implements IValidator {
    /**
     * Сhecks passport data against criteria
     *
     * @param contract contract for verification
     * @return object with the result of validation
     */
    @Override
    public ValidationResult validate(BaseContract contract) {
        ValidationResult result = new ValidationResult();
        if (Integer.toString(contract.getClient().getPassportID()).length() != 4 ||
                Integer.toString(contract.getClient().getPassportSeries()).length() != 6 ||
                contract.getClient().getPassportID() < 0 || contract.getClient().getPassportSeries() < 0) {
            result.setStatus(CheckStatus.ERROR);
            result.setMessage("Некорректные паспортные данные");
            result.setFieldName("passportData");
        } else {
            result.setStatus(CheckStatus.OK);
            result.setMessage("корректные данные");
            result.setFieldName("passportData");
        }
        return result;
    }
}
