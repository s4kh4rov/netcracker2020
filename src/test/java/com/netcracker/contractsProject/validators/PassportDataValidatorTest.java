package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.сontracts.BaseContract;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassportDataValidatorTest {

    @Test
    void validate() {
        PassportDataValidator validator = new PassportDataValidator();
        BaseContract contract = new BaseContract();
        Client client = new Client();
        contract.setClient(client);
        client.setPassportID(10);
        client.setPassportSeries(2020);
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.ERROR);
        client.setPassportID(10);
        client.setPassportSeries(202076);
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.ERROR);
        client.setPassportID(1034);
        client.setPassportSeries(20207);
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.ERROR);
        client.setPassportID(-2020);
        client.setPassportSeries(-202076);
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.ERROR);
        client.setPassportID(2012);
        client.setPassportSeries(832401);
        assertEquals(validator.validate(contract).getStatus(), CheckStatus.OK);
    }
}