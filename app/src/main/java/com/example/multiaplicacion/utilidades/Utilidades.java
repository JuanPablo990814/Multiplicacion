package com.example.multiaplicacion.utilidades;

public class Utilidades {


    public static final String CAMPO_CEDULA="cedula";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_APELLIDO="apellido";
    public static final String CAMPO_TIPO="tipo_contacto";
    public static final String CAMPO_EMAIL="email";
    public static final String TABLA_USUARIO="usuario";
    public static final String TABLA_USUARIO2="usuario";

   public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO+"("+CAMPO_CEDULA+" INTEGER, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_APELLIDO+" TEXT,"+CAMPO_TIPO+" TEXT,"+CAMPO_EMAIL+" TEXT)";


}
