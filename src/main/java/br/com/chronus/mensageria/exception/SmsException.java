package br.com.chronus.mensageria.exception;

public class SmsException extends BaseException {

  public SmsException(String errorMsg) {
    super(errorMsg);
  }

  public SmsException(String errorMsg, Throwable throwable) {
    super(errorMsg, throwable);
  }

}