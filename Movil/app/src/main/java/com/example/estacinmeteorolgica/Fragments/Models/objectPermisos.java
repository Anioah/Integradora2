package com.example.estacinmeteorolgica.Fragments.Models;

import java.io.Serializable;

public class objectPermisos implements Serializable{

    private String status;
    private String permission;

    public objectPermisos(String permission, String status) {
        this.status = status;
        this.permission = permission;
    }

    public objectPermisos(){

    }

    public String getStatus() {
            return status;
        }

    public void setStatus(String status) {
            this.status = status;
        }

    public String getPermission() {
            return permission;
        }

    public void setPermission(String permission) {
            this.permission = permission;
        }
}
