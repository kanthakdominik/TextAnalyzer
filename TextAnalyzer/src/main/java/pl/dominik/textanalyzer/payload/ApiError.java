package pl.dominik.textanalyzer.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiError {

    private final HttpStatus status;
    private final String error;

    public ApiError(HttpStatus status, String error) {
        super();
        this.status = status;
        this.error = error;
    }
}
