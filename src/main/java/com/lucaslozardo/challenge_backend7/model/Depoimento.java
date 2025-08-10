package com.lucaslozardo.challenge_backend7.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Entity
@Table(name = "depoimentos")
public class Depoimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String depoimento;
    private String urlFoto;

    @Autowired
    public Depoimento(){
    }

    public Depoimento(String name, String depoimento, String urlFoto){
        this.name = name;
        this.depoimento = depoimento;
        this .urlFoto = urlFoto;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDepoimento(){
        return depoimento;
    }

    public void setDepoimento(String depoimento){
        this.depoimento = depoimento;
    }

    public String getUrlFoto(){
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto){
        this.urlFoto = urlFoto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Depoimento that = (Depoimento) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(depoimento, that.depoimento) && Objects.equals(urlFoto, that.urlFoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, depoimento, urlFoto);
    }

    @Override
    public String toString() {
        return "Depoimento{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", depoimento='" + depoimento + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                '}';
    }
}
