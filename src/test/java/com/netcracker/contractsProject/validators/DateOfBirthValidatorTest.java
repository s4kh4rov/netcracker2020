package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.сontracts.BaseContract;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateOfBirthValidatorTest {

    @Test
    void validate() {
        DateOfBirthValidator validator = new DateOfBirthValidator();
        BaseContract contract = new BaseContract();
        Client client = new Client();
        contract.setClient(client);
        client.setDateOfBirth("09.10.1978");
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.OK);

        client.setDateOfBirth("10.12.1830");
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.WARNING);
    }
}