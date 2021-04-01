package com.netcracker.contractsProject.clients;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void getAge() {
        Client client = new Client("2000-04-24");
        assertEquals(client.getAge(), 20);
    }
}