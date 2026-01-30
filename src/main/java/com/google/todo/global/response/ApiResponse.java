package com.google.todo.global.response;

import lombok.Getter;

@Getter
public class ApiResponse<T>{

    private final boolean success;
    private final T data;
    private final ApiError error;

    private ApiResponse(boolean success, T data,ApiError error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true,data,null);
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(true,null,null);
    }

    public static ApiResponse<Void> error(ApiError error){
        return new ApiResponse<>(false,null,null);
    }
    public static ApiResponse<Void> fail(ApiError error){
        return new ApiResponse<>(false,null,error);

    }
}
