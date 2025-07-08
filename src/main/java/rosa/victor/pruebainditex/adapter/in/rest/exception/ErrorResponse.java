package rosa.victor.pruebainditex.adapter.in.rest.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Getter @Setter @NoArgsConstructor
public class ErrorResponse {
    String message;
    int status;
    Instant timestamp;
    Map<String, String> errors = new java.util.HashMap<>();
    String path;
}
