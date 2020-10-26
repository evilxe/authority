package com.fzw.authority.exception;


public class AuthorityException extends RuntimeException {

    private Integer code;

    private String message;

    public AuthorityException(Integer code,  String message){
        this.message = message;
    }

    public AuthorityException(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
