package co.develhope.customqueries1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String description;
    @Column
    private String fromAirport;
    @Column
    private String toAirport;
    @Enumerated(EnumType.ORDINAL)
    private FlightStatus status;
}