package fr.projet.coincoin.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

// Annotations de la librairie "Lombok"
// Pour ne pas avoir à écrire les getter / setter / constructeurs
@Getter
@Setter
@NoArgsConstructor
// Annotations de la libraire "java.persistence.*"
// Pour définir la classe comme entité et comme table en base de données
@Entity
@Table(name = "users")
public class User {

    @Id // Definit comme "PRIMARY KEY" en base de données
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Renseigner la stratégie a utilisé lors de l'autocrémentaion de l'ID
    @Column(name = "user_id") // Nom de la colonne en base de données
    private Integer id;

    @Column(name = "user_name") // Nom de la colonne en base de données
    @Length(max = 40) // Longueur maximum du String
    @NotNull // Ne peut pas être null en base de données
    private String name;

    @Column(name = "user_login") // Nom de la colonne en base de données
    @Length(max = 40) // Longueur maximum du String
    @NotNull // Ne peut pas être null en base de données
    private String login;

    @Column(name= "user_pwd") // Nom de la colonne en base de données
    @Length(max = 255) // Longueur maximum du String
    @NotNull // Ne peut pas être null en base de données
    private String pwd;

    @Column(name= "user_role") // Nom de la colonne en base de données
    @NotNull // Ne peut pas être null en base de données
    private Integer role; // 0 = administrateur; 1 = utilisateur

    @OneToMany(mappedBy = "id",fetch = FetchType.LAZY)// Cardinalités "1,n"; On peut pas revenir sur l'entité user depuis marker;
    private List<Marker> markers;

}
