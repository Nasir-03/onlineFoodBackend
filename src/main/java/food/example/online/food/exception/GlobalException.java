package food.example.online.food.exception;

import food.example.online.food.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    private final UserRepository userRepository;

    GlobalException(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
     @ExceptionHandler(UsernameAlreadyFoundException.class)
	public ResponseEntity<ErrorResponse> usernameAlreadyFound(UsernameAlreadyFoundException ex){
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.CONFLICT.value());
		error.setErrorMessage(ex.getMessage());
		
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.CONFLICT);
	}
     
     @ExceptionHandler(UsernameNotFoundException.class)
     public ResponseEntity<?> handleUsernameNotFound(UsernameNotFoundException ex) {
         Map<String, String> error = new HashMap<>();
         error.put("message", "Invalid email");
         return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
     }

     @ExceptionHandler(BadCredentialsException.class)
     public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
         Map<String, String> error = new HashMap<>();
         error.put("message", "Invalid password");
         return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
     }
     
     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<Map<String, String>> handleValidationExceptions(
             MethodArgumentNotValidException ex) {

         Map<String, String> errors = new HashMap<>();
         ex.getBindingResult().getFieldErrors().forEach(error ->
             errors.put(error.getField(), error.getDefaultMessage())
         );

         return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
     }
     
}
