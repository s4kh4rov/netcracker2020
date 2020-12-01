package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.BaseContract;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {

    @Test
    void validate() {
        DateValidator validator = new DateValidator();
        BaseContract contract = new BaseContract();
        contract.setExpirationDate("02.03.2020");
        contract.setStartDate("02.04.2030");
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.ERROR);

        contract.setStartDate("03.08.2008");
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.OK);
    }
}