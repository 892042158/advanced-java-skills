package top.xmindguoguo.springmvc.demo.validator.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/19 0:13
 * @Version: v1.0
 */
@ControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handle(MethodArgumentNotValidException exception) {
        String errMsg = exception.getBindingResult().getAllErrors().stream().map(err -> err.getDefaultMessage())
                .collect(Collectors.joining(";"));
        return ResponseEntity.ok(errMsg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handle(ConstraintViolationException exception) {
        String errMsg = exception.getConstraintViolations().stream().map(err -> err.getMessage())
                .collect(Collectors.joining(";"));
        return ResponseEntity.ok(errMsg);
    }
}
