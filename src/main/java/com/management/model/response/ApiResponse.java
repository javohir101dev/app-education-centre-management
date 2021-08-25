package com.management.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private T data;

    private String errorMassage = "";

    private boolean success;

    public ApiResponse(T data){
        this.data = data;
        this.errorMassage ="";
        this.success = true;
    }

    public ApiResponse(String errorMassage){
        this.errorMassage = errorMassage;
        this.data = null;
        this.success = false;
    }

}
