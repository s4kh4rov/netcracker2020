package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.InternetContract;

/**
 * Speed validation class
 */
public class SpeedValidator implements IValidator<InternetContract> {
    /**
     * Сhecks maxSpeed  against criteria
     *
     * @param contract contract for verification
     * @return object with the result of validation
     */
    @Override
    public ValidationResult validate(InternetContract contract) {
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
