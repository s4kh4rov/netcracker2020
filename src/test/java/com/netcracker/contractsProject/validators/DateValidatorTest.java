package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.BaseContract;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {

    @Test
    void validate() {
        DateValidator validator = new DateValidator();
        BaseContract contract = new BaseContract();
        contract.setExpirationDate("2020-03-02");
        contract.setStartDate("2030-04-02");
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.ERROR);

        contract.setStartDate("2008-08-03");
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.OK);
    }
}