package com.technologyos.functional.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class BusinessException extends RuntimeException {
   private Long id;
   private String code;
   private String detail;
   private HttpStatus httpStatus;

   public BusinessException(String code, String message, String detail, HttpStatus httpStatus) {
      super(message);
      this.code = code;
      this.detail = detail;
      this.httpStatus = httpStatus;
   }

   public BusinessException(String message, Throwable cause) {
      super(message, cause);
   }
}
