package edu.eci.arep.app.maquina2;

import edu.eci.arep.app.URLReader;

import static spark.Spark.*;
public class HelloWorld {
    public static void main(String[] args) {
        port(getPort());
        secure(getKeStore(), getPasswordKeyStore(), null,null);
        get("/local", (req, res) -> "Hello Maquina 2");
        get("/remoto", (req, res) -> URLReader.search("https://ec2-35-173-242-201.compute-1.amazonaws.com:5000/local", "certificados/llave1.p12"));
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

    static String getKeStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "certificados/llave2.p12"; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

    static String getPasswordKeyStore() {
        if (System.getenv("PASSWORDKEYSTORE") != null) {
            return System.getenv("PASSWORDKEYSTORE");
        }
        return "123456789"; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}