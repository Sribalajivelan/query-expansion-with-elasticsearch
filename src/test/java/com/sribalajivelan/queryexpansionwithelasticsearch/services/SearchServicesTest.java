package com.sribalajivelan.queryexpansionwithelasticsearch.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SearchServicesTest {

    @Autowired
    SearchServices searchServices;

    @Test
    void testSearchQueryExpansion() {
        String expansion = searchServices.expansionQuery("are crab cake oreos real");
        assertNotNull(expansion);
        assertTrue(expansion.contains("actual"));
        assertTrue(expansion.contains("cake"));
    }
}
