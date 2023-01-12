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
    private String word;

    @JsonProperty("powtorzenia")
    private Integer NumberOfOccurrences;

    @JsonProperty("pozycje")
    private List<Integer> positions;
}
