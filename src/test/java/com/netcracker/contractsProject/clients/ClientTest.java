package com.netcracker.contractsProject.clients;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void getAge() {
        Client client = new Client("24.04.2000");
        assertEquals(client.getAge(), 20);
    }
}