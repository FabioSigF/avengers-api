package com.avengers.avengers_api.resource.avenger;

import com.avengers.avengers_api.domain.avenger.Avenger;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "avenger")
public class AvengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nick;

    @Column(nullable = false)
    private String person;
    private String description;
    private String history;

    public static Avenger toAvenger(AvengerEntity avenger) {
        return new Avenger(
                avenger.id,
                avenger.nick,
                avenger.person,
                avenger.description,
                avenger.history);
    }

    public static AvengerEntity toAvengerEntity(Avenger avenger) {
        return new AvengerEntity(
                null,
                avenger.nick(),
                avenger.person(),
                avenger.description(),
                avenger.history());
    }
}
