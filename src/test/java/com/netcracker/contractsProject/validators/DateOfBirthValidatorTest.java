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
        client.setDateOfBirth("1978-10-09");
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.OK);

        client.setDateOfBirth("1830-12-10");
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.WARNING);
    }
}