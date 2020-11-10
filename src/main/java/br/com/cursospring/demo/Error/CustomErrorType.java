package br.com.cursospring.demo.Error;

public class CustomErrorType {
    private String errormessage;

    public CustomErrorType(String errormessage) {
        this.errormessage = errormessage;
    }

    public String getErrormessage() {
        return errormessage;
    }
}
