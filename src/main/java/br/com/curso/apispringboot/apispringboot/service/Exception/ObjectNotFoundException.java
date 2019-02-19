package br.com.curso.apispringboot.apispringboot.service.Exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    //Esse método recebe a mensagem e uma outra exceção que seria a causa de algo que ocorreu antes.
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
