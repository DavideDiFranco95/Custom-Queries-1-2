package co.develhope.customqueries1.repositories;

import co.develhope.customqueries1.entities.Flight;
import co.develhope.customqueries1.entities.FlightStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFlightStatus(FlightStatus status);
    Page<Flight> findAllByStatus(FlightStatus status, PageRequest of);
}
