package com.sribalajivelan.queryexpansionwithelasticsearch.controllers;

import com.sribalajivelan.queryexpansionwithelasticsearch.services.SearchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SearchController {

    @Autowired
    private SearchServices searchServices;

    @PostMapping("/query/expansion")
    public String getQueryExpansion(@RequestBody String input) {
        return searchServices.expansionQuery(input);
    }
}
