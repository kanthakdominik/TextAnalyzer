package pl.dominik.textanalyzer.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dominik.textanalyzer.payload.TextRequest;
import pl.dominik.textanalyzer.payload.TextResponse;
import pl.dominik.textanalyzer.services.TextService;

import java.util.List;

@RestController
@RequestMapping("api/texts")
public class TextController {

    @Autowired
    private TextService textService;

    @PostMapping("/analyze")
    public ResponseEntity<List<TextResponse>> analyzeText(@Valid @RequestBody TextRequest textRequest) {
        List<TextResponse> analyzedWords = textService.getAnalyzedWords(textRequest.getText());
        return new ResponseEntity<>(analyzedWords, HttpStatus.OK);
    }
}
