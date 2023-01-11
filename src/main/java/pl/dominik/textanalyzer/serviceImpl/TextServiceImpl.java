package pl.dominik.textanalyzer.serviceImpl;

import org.springframework.stereotype.Service;
import pl.dominik.textanalyzer.payload.TextResponse;
import pl.dominik.textanalyzer.services.TextService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.IntStream;

@Service
public class TextServiceImpl implements TextService {

    @Override
    public List<TextResponse> getAnalyzedWords(String text) {
        List<String> words = splitIntoWords(text);
        Map<String, List<Integer>> wordsWithIndexes = searchWordsIndexes(words);

        return generateTextResponses(wordsWithIndexes);
    }

    private List<String> splitIntoWords(String text) {
        List<String> words = new ArrayList<>();
        String delimiter = " \n\r\t\f\b,.;:--–()„”<>~%!?@$€#…‰ƒ^&*+_[]{}\\/=|'`\"";

        StringTokenizer stringTokenizer = new StringTokenizer(text, delimiter);
        while (stringTokenizer.hasMoreTokens()) {
            words.add(stringTokenizer.nextToken());
        }
        return words;
    }

    private Map<String, List<Integer>> searchWordsIndexes(List<String> words) {
        Map<String, List<Integer>> wordsWithIndexes = new TreeMap<>();

        for (String word : words) {
            List<Integer> indexes = IntStream.range(0, words.size())
                    .filter(i -> Objects.equals(words.get(i), word)).boxed().toList();
            wordsWithIndexes.put(word, indexes);
        }
        return wordsWithIndexes;
    }

    private List<TextResponse> generateTextResponses(Map<String, List<Integer>> wordsWithIndexes) {
        List<TextResponse> analyzedWords = new ArrayList<>();

        for (String word : wordsWithIndexes.keySet()) {
            analyzedWords.add(new TextResponse(word, wordsWithIndexes.get(word).size(), wordsWithIndexes.get(word)));
        }
        return analyzedWords;
    }
}
