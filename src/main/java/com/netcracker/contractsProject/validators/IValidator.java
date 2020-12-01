package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.BaseContract;

/**
 * The interface represents a generic validator
 */
public interface IValidator<T extends BaseContract> {
    public ValidationResult validate(T contract);
}
