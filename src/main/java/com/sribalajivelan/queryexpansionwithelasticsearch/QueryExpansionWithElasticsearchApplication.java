package com.sribalajivelan.queryexpansionwithelasticsearch;

import WordNet.WordNet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QueryExpansionWithElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryExpansionWithElasticsearchApplication.class, args);
    }

    @Bean
    public WordNet getWordNet() {
        return new WordNet("english_wordnet_version_31.xml");
    }

}
