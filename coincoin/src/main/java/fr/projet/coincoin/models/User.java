package fr.projet.coincoin.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


// Annotations de la librairie "Lombok" pour ne pas avoir à écrire les getter / setter / constructeurs
@Getter
@Setter
@NoArgsConstructor
// Annotations de la libraire "java.persistence.*" pour définir la classe comme entité et comme table en base de données
@Entity
@Table(name = "users")

/**
 * User => Classe de définition d'un utilisateur,
 * Attributes => {
 *     id integer : id en base de donnée,
 *     name string : nom de l'utilisateur,
 *     login string : identifiant de l'utilisateur,
 *     pwd string : mot de passe de l'utilisateur,
 *     role integer : role de l'utilisateur ( Ne peut être que 0='admin' ou 1='utilisateur' sauf si ajout de nouveau role )
 * }
 */
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

//    -------------                SI DÉCOMMENTÉ                -------------
//    -------------  Problème lors de la réception des données  -------------
//    -------------            Lier avec la classe Marker       -------------
//
//    @OneToMany(mappedBy = "fk_user_id") // Cardinalités "1,n"
//    private List<Marker> markers;
}
