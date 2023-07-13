package com.sribalajivelan.queryexpansionwithelasticsearch.services;

import WordNet.Literal;
import WordNet.WordNet;
import opennlp.tools.tokenize.SimpleTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchServices {

    @Autowired
    private WordNet wordNet;

    public String searchQueryExpansion(String query) {
        System.out.println("query === " + query);
        StringBuilder query_string = new StringBuilder();
        int num_tokens = 0;
        List<String> tokens = wordTokenize(query);
        System.out.println("tokens === " + tokens);
        int totalToken = tokens.size();
        for (String token : tokens) {
            Set<String> synonyms = new HashSet<>();
            wordNet.getSynSetsWithLiteral(token).forEach(syn -> {

                for (int i = 0; i < syn.getSynonym().literalSize(); i++) {
                    Literal relation = syn.getSynonym().getLiteral(i);
                    synonyms.add(relation.getName());
                }
            });
            query_string.append("(").append(String.join(" OR ", synonyms)).append(")");
            num_tokens++;

            if (num_tokens < totalToken) {
                query_string.append(" AND ");
            }
        }
        System.out.println("query_string === " + query_string);
        return query_string.toString();
    }

    private List<String> wordTokenize(String input) {
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        return Arrays.stream(tokenizer.tokenize(input)).toList();
    }

    private List<String> wordTokenizeBuildIn(String input) {
        // Define the pattern to match words, numbers, and punctuation
        Pattern pattern = Pattern.compile("\\b\\w+\\b|\\d+\\.\\d+|\\p{Punct}");

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(input);

        // Store the tokens in a list
        List<String> tokens = matcher.results()
                .map(MatchResult::group)
                .toList();

        // Print the tokens
        System.out.println(tokens);
        return tokens;
    }


}
