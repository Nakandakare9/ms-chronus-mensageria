package br.com.chronus.mensageria.exception;

public abstract class BaseException extends Exception {

    public BaseException(String errorMsg) {
        super(errorMsg);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String errorMsg, Throwable throwable) {
        super(errorMsg, throwable);
    }
}