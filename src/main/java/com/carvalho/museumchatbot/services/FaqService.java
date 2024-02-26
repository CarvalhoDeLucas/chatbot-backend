package com.carvalho.museumchatbot.services;

import com.carvalho.museumchatbot.utils.FaqAnswers;
import com.carvalho.museumchatbot.domain.FaqAnswer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FaqService {

    private FaqAnswers faqAnswers = new FaqAnswers();

    public String getAnswer(String question) {
        String[] words = question.toLowerCase().split("\\s+");
        List wordsList = Arrays.asList(words).stream().map(this::replaceForbiddenChar).toList();

        for (FaqAnswer entry : faqAnswers.getAnswers()) {
            for (String keyword : entry.getKeywords()) {
                if (wordsList.contains(keyword.toLowerCase())) {
                    return entry.getAnswer();
                }
            }
        }

        return faqAnswers.getDefaultAnswer();
    }

    private String replaceForbiddenChar(String question) {
        return question.replace("?", "");
    }

}
