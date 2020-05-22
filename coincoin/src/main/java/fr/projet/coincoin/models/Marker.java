package fr.projet.coincoin.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

// Annotations de la librairie "Lombok"
// Pour ne pas avoir à écrire les getter / setter / constructeurs
@Getter
@Setter
@NoArgsConstructor
// Annotations de la libraire "java.persistence.*"
// Pour définir la classe comme entité et comme table en base de données
@Entity
@Table(name = "markers")
public class Marker {

    @Id // Definit comme "PRIMARY KEY" en base de données
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Renseigner la stratégie a utilisé lors de l'autocrémentaion de l'ID
    @Column(name = "marker_id") // Nom de la colonne en base de données
    private Integer id;

    @Column(name = "marker_title") // Nom de la colonne en base de données
    @Length(max = 40) // Longueur maximum du String
    @NotNull // Ne peut pas être null en base de données
    private String title;

    @Column(name= "marker_description") // Nom de la colonne en base de données
    @Length(max = 255) // Longueur maximum du String
    @NotNull // Ne peut pas être null en base de données
    private String resume;

    @Column(name= "marker_src_image") // Nom de la colonne en base de données
    @Length(max = 255) // Longueur maximum du String
    @NotNull // Ne peut pas être null en base de données
    private String img;

    @Column(name = "marker_latitude") // Nom de la colonne en base de données
    @NotNull // Ne peut pas être null en base de données
    private Double lat;

    @Column(name = "marker_longitude") // Nom de la colonne en base de données
    @NotNull // Ne peut pas être null en base de données
    private Double lng;

}
