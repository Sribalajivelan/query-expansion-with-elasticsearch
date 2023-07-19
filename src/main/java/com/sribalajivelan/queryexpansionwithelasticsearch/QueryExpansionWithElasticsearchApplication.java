package com.sribalajivelan.queryexpansionwithelasticsearch;

import WordNet.WordNet;
import com.sribalajivelan.queryexpansionwithelasticsearch.services.SearchServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class QueryExpansionWithElasticsearchApplication {

    private static final Logger LOGGER = Logger.getLogger(QueryExpansionWithElasticsearchApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(QueryExpansionWithElasticsearchApplication.class, args);
    }

    @Bean
    public WordNet getWordNet() {
        LOGGER.info("Creating WordNet Bean, It will take time");
        WordNet wordNet = new WordNet("english_wordnet_version_31.xml");
        LOGGER.info("Created WordNet Bean");
        return wordNet;
    }

}
