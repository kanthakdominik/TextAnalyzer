package pl.dominik.textanalyzer.services;

import pl.dominik.textanalyzer.payload.TextResponse;

import java.util.List;

public interface TextService {

    List<TextResponse> getAnalyzedWords(String text);
}
