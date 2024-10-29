package services.webplus.polling.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StandardError {
    private String messageError;
    private String path;
    private long timestamp;
    private int status;
}
