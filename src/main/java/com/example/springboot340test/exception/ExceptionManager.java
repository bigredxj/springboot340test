package com.example.springboot340test.exception;

import com.example.springboot340test.enums.ResultEnum;
import com.example.springboot340test.object.vo.JsonResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor(onConstructor_ = {@Autowired})
@RestControllerAdvice
@Slf4j

public class ExceptionManager {


    @ExceptionHandler({GeneralException.class})
    public ResponseEntity<JsonResponse> handleAppException(GeneralException ex) {
        log.debug("Caught AppException:", ex);
        String message = ex.getMessage();
        if (StringUtils.isNotEmpty(message)) {
            return new ResponseEntity<>(new JsonResponse(ex.getCode(),ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(new JsonResponse(ResultEnum.OPERATION_FAILED.getCode(),
                                                         ResultEnum.OPERATION_FAILED.getMessage()
                    ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 默认异常
     *
     * @param e 未经上述handler处理的异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonResponse> handleException(Exception e) {
        log.error("Caught unhandled exception:", e);

        return new ResponseEntity<>(
                new JsonResponse(ResultEnum.OPERATION_FAILED.getCode(),
                        ExceptionUtils.getRootCauseMessage(e)),

                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

