package com.stti.user.error;

public class ApiError {

    private String fieldName;
    private Object rejectedValue;
    private String errorMsg;

    public ApiError(String fieldName, Object rejectedValue, String errorMsg) {
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
        this.errorMsg = errorMsg;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
    
}
