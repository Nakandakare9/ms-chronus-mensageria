package br.com.chronus.mensageria.exception;

public class EmailException extends BaseException {

    public EmailException(String errorMsg) {
        super(errorMsg);
    }

    public EmailException(String errorMsg, Throwable throwable) {
        super(errorMsg, throwable);
    }

}
