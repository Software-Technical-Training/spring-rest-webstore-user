package com.stti.user.error;

import java.util.ArrayList;
import java.util.List;

public class ApiErrorResponse {
    
    List<ApiError> errorList = new ArrayList<>();

    public ApiErrorResponse(List<ApiError> errorList) {
        this.errorList = errorList;
    }

    public List<ApiError> getErrorList() {
        return errorList;
    }

}
