package com.sribalajivelan.queryexpansionwithelasticsearch.services;

import WordNet.Literal;
import WordNet.WordNet;
import opennlp.tools.tokenize.SimpleTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class SearchServices {

    private static final Logger LOGGER = Logger.getLogger(SearchServices.class.getName());

    @Autowired
    private WordNet wordNet;

    // Convert Query to Expansion Query
    public String expansionQuery(String query) {
        LOGGER.info(String.format("Query === %s", query));
        StringBuilder query_string = new StringBuilder();
        int num_tokens = 0;
        List<String> tokens = wordTokenize(query);
        LOGGER.info(String.format("tokens from query === %s", tokens));
        int totalToken = tokens.size();
        for (String token : tokens) {
            Set<String> synonyms = getSynonymsForToken(token);
            synonyms.add(token);

            // Convert the synonyms word to OR condition to apply on query
            query_string.append("(").append(String.join(" OR ", synonyms)).append(")");
            num_tokens++;

            // After added synonyms for current token, then add AND for next token check condition
            if (num_tokens < totalToken) {
                query_string.append(" AND ");
            }
        }
        LOGGER.info(String.format("Expansion Query === %s", query_string));
        return query_string.toString();
    }

    // Fetch Synonyms for given token word using WordNet
    private Set<String> getSynonymsForToken(String token) {
        Set<String> synonyms = new HashSet<>();
        wordNet.getSynSetsWithLiteral(token).forEach(syn -> {

            for (int i = 0; i < syn.getSynonym().literalSize(); i++) {
                Literal relation = syn.getSynonym().getLiteral(i);
                synonyms.add(relation.getName());
            }
        });
        return synonyms;
    }

    // Covert statement to tokens
    private List<String> wordTokenize(String input) {
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        return Arrays.stream(tokenizer.tokenize(input)).toList();
    }

}
