package com.github.lexilift.common;

/**
 * this class is a common class for all the response
 * @param status
 * @param message
 * @param data
 */
public record Response(int status, String message, Object data) {

    public static Response ok() {
        return ok("");
    }

    public static Response ok(Object data) {
        return new Response(200, "操作成功!", data);
    }

    public static Response error(int status, String message) {
        return new Response(status, message, "");
    }


}
