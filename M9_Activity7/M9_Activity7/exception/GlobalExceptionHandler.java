package M9_Activity7.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handle(RuntimeException ex) {
        String json = """
        {
          "error": "Exception",
          "message": "%s"
        }
        """.formatted(ex.getMessage());

        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }
}