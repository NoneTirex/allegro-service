package pl.edu.tirex.allegro.service.system.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.tirex.github.exception.GithubRepositoryNotFoundException;
import pl.edu.tirex.github.exception.GithubUserNotFoundException;

@ControllerAdvice
public class ExceptionsHandler
{
    @ExceptionHandler(GithubUserNotFoundException.class)
    public ResponseEntity userNotFoundException()
    {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(GithubRepositoryNotFoundException.class)
    public ResponseEntity repositoryNotFoundException()
    {
        return ResponseEntity.notFound().build();
    }
}
