package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.InternetContract;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InternetContractValidatorTest {
    private InternetContract contract;

    @BeforeAll
    private void init() {
        contract = new InternetContract();
    }

    @Test
    void checkMaxSpeed() {
        contract.setMaxSpeed(23.0);
        assertEquals(InternetContractValidator.checkMaxSpeed(contract).getStatus(),CheckStatus.OK);
        contract.setMaxSpeed(-23.0);
        assertEquals(InternetContractValidator.checkMaxSpeed(contract).getStatus(),CheckStatus.WARNING);
    }
}