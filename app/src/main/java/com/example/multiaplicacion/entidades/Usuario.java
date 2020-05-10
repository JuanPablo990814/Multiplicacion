package com.example.multiaplicacion.entidades;

public class Usuario {
    private int cedula;
    private String nombre;
    private String apellido;
    private String tipo_contacto;
    private String email;

    public Usuario(int cedula, String nombre, String apellido, String tipo_contacto, String email) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_contacto = tipo_contacto;
        this.email = email;
    }
}
