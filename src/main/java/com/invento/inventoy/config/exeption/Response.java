package com.invento.inventoy.config.exeption;

public class Response {
    public record ApiResponse<T>( boolean success, int status, String message, T data){
        public static<T> ApiResponse<T> success(String msg, T data){
            return new ApiResponse<>(true, 200, msg, data);
        }
        public static<T> ApiResponse<Void> success(String msg){
            return new ApiResponse<>(true, 200, msg, null);
        }
        public static<T> ApiResponse<Void> fail(String msg, int StatusCode){
            return new ApiResponse<>(false, StatusCode, msg, null);
        }
    }
}
