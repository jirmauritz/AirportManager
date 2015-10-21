package cz.muni.fi.pa165.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */

//lombok
//vytvareni
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
//gettery settery
@Getter
@Setter
//dalsi
@EqualsAndHashCode(of = {"firstName", "lastName"})
//@EqualsAndHashCode(exclude = {"id", "flights"}) // druha moznost


@Entity
public class Steward {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @lombok.NonNull // builder a konstruktory konrtoluji nullabilitu
    private String firstName;

    @NotNull
    @lombok.NonNull // builder a konstruktory konrtoluji nullabilitu
    private String lastName;

    @ManyToMany
    @lombok.Singular() // builder obsahuje metodu na pridani do kolekce
    private Set<Flight> flights = new HashSet<>();

    // tohle bohuzel lombok neumi
    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    public void removeFlight(Flight flight) {
        this.flights.remove(flight);
    }

}

class StewardUsage {

    public static void main(String[] args) {

        // vyuziti builderu
        Steward steward = Steward.builder()
                .id(0L)
                .firstName("Jan")
                .lastName("Novak")
                .flight(new Flight())
                .build();

        Steward anotherSteward = new Steward(1L, "Jan", "Novak", Collections.<Flight>emptySet());

        if (steward.equals(anotherSteward)) {
            System.out.println("Objekty jsou si businesslogicky rovny");
        }

        if (steward.hashCode() == anotherSteward.hashCode()) {
            System.out.println("a produkuji stejny hashcode");
        }

    }

}

