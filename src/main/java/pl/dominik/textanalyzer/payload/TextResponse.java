package pl.dominik.textanalyzer.payload;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TextResponse {

    @JsonProperty("slowo")
    String word;

    @JsonProperty("powtorzenia")
    Integer NumberOfOccurrences;

    @JsonProperty("pozycje")
    List<Integer> positions;
}
