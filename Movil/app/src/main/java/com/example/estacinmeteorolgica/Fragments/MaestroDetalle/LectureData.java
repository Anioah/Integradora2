package com.example.estacinmeteorolgica.Fragments.MaestroDetalle;

import java.io.Serializable;

public class LectureData implements Serializable {

    private String id;
    private String temperatura;
    private String humedad;
    private String latitud;
    private String longitud;
    private String tiempo;

    public LectureData(String id, String temperatura, String humedad,String latitud, String longitud){
        this.id = id;
        this.temperatura= temperatura;
        this.humedad = humedad;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public LectureData(){}


    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHumedad() {
        return humedad;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }
}
