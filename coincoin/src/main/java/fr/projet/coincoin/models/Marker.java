package fr.projet.coincoin.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

// Annotations de la librairie "Lombok" pour ne pas avoir à écrire les getter / setter / constructeurs
@Getter
@Setter
@NoArgsConstructor
// Annotations de la libraire "java.persistence.*" pour définir la classe comme entité et comme table en base de données
@Entity
@Table(name = "markers")

/**
 * Marker => Classe de définition d'un coin insolite,
 * Attributes => {
 *     id integer : id en base de donnée,
 *     title string : titre du marqueur,
 *     resume string : description du marqueur,
 *     img string : chemin d'acces à l'image du marqueur,
 *     lat double : latitude du marqueur,
 *     lng double : longitude du marqueur
 * }
 */
public class Marker {

    @Id // @Annotation : Definit comme "PRIMARY KEY" en base de données
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @Annotation : Renseigner la stratégie a utilisé lors de l'autocrémentaion de l'ID
    @Column(name = "marker_id") // @Annotation : Nom de la colonne en base de données
    private Integer id;

    @Column(name = "marker_title") // @Annotation : @Annotation : @Annotation : Nom de la colonne en base de données
    @Length(max = 40) // @Annotation : @Annotation : Longueur maximum du String
    @NotNull // @Annotation : @Annotation : Ne peut pas être null en base de données
    private String title;

    @Column(name= "marker_description") // @Annotation : @Annotation : Nom de la colonne en base de données
    @Length(max = 255) // @Annotation : @Annotation : Longueur maximum du String
    @NotNull // @Annotation : @Annotation : Ne peut pas être null en base de données
    private String resume;

    @Column(name= "marker_src_image") // @Annotation : Nom de la colonne en base de données
    @Length(max = 255) // @Annotation : Longueur maximum du String
    @NotNull // @Annotation : Ne peut pas être null en base de données
    private String img;

    @Column(name = "marker_latitude") // @Annotation : Nom de la colonne en base de données
    @NotNull // @Annotation : Ne peut pas être null en base de données
    private double lat;

    @Column(name = "marker_longitude") // @Annotation : Nom de la colonne en base de données
    @NotNull // @Annotation : Ne peut pas être null en base de données
    private double lng;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marker marker = (Marker) o;
        return Double.compare(marker.lat, lat) == 0 &&
                Double.compare(marker.lng, lng) == 0 &&
                id.equals(marker.id) &&
                title.equals(marker.title) &&
                resume.equals(marker.resume) &&
                img.equals(marker.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, resume, img, lat, lng);
    }
//    -------------                SI DÉCOMMENTÉ                -------------
//    -------------  Problème lors de la réception des données  -------------
//    -------------            Lier avec la classe User       -------------
//
//    @ManyToOne(fetch = FetchType.LAZY) // Cardinalités "n,1"
//    @JoinColumn(name = "fk_user_id")
//    private User user;
}
