package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.clients.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientValidatorTest {
    private Client client;

    @BeforeAll
    private void init() {
        client = new Client();
    }

    @Test
    void checkDateOfBirth() {
        client.setDateOfBirth("09.10.1978");
        assertEquals(ClientValidator.checkDateOfBirth(client).getStatus(), CheckStatus.OK);

        client.setDateOfBirth("10.12.1830");
        assertEquals(ClientValidator.checkDateOfBirth(client).getStatus(), CheckStatus.WARNING);
    }

    @Test
    void checkPassportData() {
        client.setPassportID(10);
        client.setPassportSeries(2020);
        assertEquals(ClientValidator.checkPassportData(client).getStatus(), CheckStatus.ERROR);
        client.setPassportID(10);
        client.setPassportSeries(202076);
        assertEquals(ClientValidator.checkPassportData(client).getStatus(), CheckStatus.ERROR);
        client.setPassportID(1034);
        client.setPassportSeries(20207);
        assertEquals(ClientValidator.checkPassportData(client).getStatus(), CheckStatus.ERROR);
        client.setPassportID(-2020);
        client.setPassportSeries(-202076);
        assertEquals(ClientValidator.checkPassportData(client).getStatus(), CheckStatus.ERROR);
        client.setPassportID(2012);
        client.setPassportSeries(832401);
        assertEquals(ClientValidator.checkPassportData(client).getStatus(), CheckStatus.OK);
    }
}