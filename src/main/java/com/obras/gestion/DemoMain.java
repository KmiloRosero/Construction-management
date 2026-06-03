package com.obras.gestion;

public class DemoMain {
    public static void main(String[] args) {
        System.out.println("DemoMain: aplicación de demostración");
        String mongo = System.getenv("SPRING_DATA_MONGODB_URI");
        String port = System.getenv("SERVER_PORT");
        if (mongo == null || mongo.isBlank()) {
            mongo = "(no configurado)";
        }
        if (port == null || port.isBlank()) {
            port = "8080 (por defecto)";
        }
        System.out.println("SPRING_DATA_MONGODB_URI=" + mongo);
        System.out.println("SERVER_PORT=" + port);
        System.out.println("Fin de DemoMain.");
    }
}
