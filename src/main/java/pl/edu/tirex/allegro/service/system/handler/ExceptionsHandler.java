package pl.edu.tirex.allegro.service.system.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.edu.tirex.github.exception.GithubRepositoryNotFoundException;
import pl.edu.tirex.github.exception.GithubUserNotFoundException;
import pl.edu.tirex.http.exception.HttpResponseCodeException;

@ControllerAdvice
public class ExceptionsHandler
{
    @ExceptionHandler(GithubUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void userNotFoundException()
    {
    }

    @ExceptionHandler(GithubRepositoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void repositoryNotFoundException()
    {
    }

    @ExceptionHandler(HttpResponseCodeException.class)
    public ResponseEntity responseCodeException(HttpResponseCodeException e)
    {
        return ResponseEntity.status(e.getResponseCode()).build();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void otherException(Throwable throwable)
    {
        throwable.printStackTrace();
    }
}
