package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.InternetContract;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeedValidatorTest {

    @Test
    void validate() {
        SpeedValidator validator = new SpeedValidator();
        InternetContract contract = new InternetContract();
        contract.setMaxSpeed(-23.0);
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.WARNING);

        contract.setMaxSpeed(13.0);
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.OK);
    }
}