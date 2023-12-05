package com.bcn.utils;

public class Constantes {
    // Mensajes
    public class Mensaje {
        public static final String MSG_SUCCES = "Operación exitosa";
        public static final String MSG_FAILED = "Operación fallida";
    }

    // Codigos http response
    public static class Codigo {
        public static final int OK = 200;
        public static final int CREATED = 201;
        public static final int BAD_REQUEST = 400;
        public static final int UNAUTHORIZED = 401;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND = 404;
        public static final int INTERNAL_ERROR = 500;
    }
}