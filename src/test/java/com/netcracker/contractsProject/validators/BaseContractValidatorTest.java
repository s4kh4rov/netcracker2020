package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.сontracts.BaseContract;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BaseContractValidatorTest {
    private BaseContract contract;

    @BeforeAll
    private void init() {
        contract = new BaseContract();
    }

    @Test
    void checkDate() {
        contract.setExpirationDate("02.03.2020");
        contract.setStartDate("02.04.2030");
        assertEquals(BaseContractValidator.checkDate(contract).getStatus(), CheckStatus.ERROR);

        contract.setStartDate("03.08.2008");
        assertEquals(BaseContractValidator.checkDate(contract).getStatus(), CheckStatus.OK);
    }
}