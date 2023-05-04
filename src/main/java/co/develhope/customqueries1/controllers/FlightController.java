package co.develhope.customqueries1.controllers;

import co.develhope.customqueries1.entities.Flight;
import co.develhope.customqueries1.entities.FlightStatus;
import co.develhope.customqueries1.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;
    @GetMapping("")
    public List<Flight> provisioning(){
        List<Flight> flightsProvisioning = new ArrayList<>();
        for (int i=1;i<=50;i++){
            flightsProvisioning.add(new Flight(i,
                    randomValueForFlight(),
                    randomValueForFlight(),
                    randomValueForFlight(),
                    FlightStatus.ON_TIME));

        }
        flightRepository.saveAll(flightsProvisioning);

        return flightsProvisioning;
    }
    @GetMapping("/get")
    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public String randomValueForFlight(){
        Random random = new Random();
        int limitA = 20;
        int limitZ = 150;
        int targetStringLength = 11;

        return random.ints(limitA,limitZ + 1)
                .limit(targetStringLength).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    //ES: CustomQueries02 --------------------------------------------------------------------------------------

    @GetMapping("/n")
    public List<Flight> getNFlights(@RequestParam Integer n){
        List<Flight> otherFlight = new ArrayList<>();
        if (n==null || n!=100){
            n=100;
        }
        for (int i = 0; i < n; i++) {
            otherFlight.add(new Flight(i,randomValueForFlight(),randomValueForFlight(),
                    randomValueForFlight(),FlightStatus.randomFlightStatus()));
        }
        flightRepository.saveAll(otherFlight);
        return otherFlight;
    }

    @GetMapping("/page")
    public Page<Flight> flightPage(@RequestParam Integer totalPages, @RequestParam Integer numberOfPages){
        return flightRepository.findAll(PageRequest.of(totalPages,numberOfPages,
                Sort.by("fromAirport").ascending()));
    }

    @GetMapping("/onTime")
    public List<Flight> getOnTimeFlights(@RequestParam String flightStatus){
        return flightRepository.findByFlightStatus(FlightStatus.valueOf(flightStatus));
    }

    /**
     * for retrieving - using a custom query - all the flights that are in p1 or in p2 status
     * consider that the user has to pass p1 and p2 as parameters to the GET request
     */
    @GetMapping("/status/{status}")
    public Page<Flight> getAllFlightsByStatus(@PathVariable FlightStatus status, @RequestParam int page, @RequestParam int size){
        return flightRepository.findAllByStatus(status, (PageRequest.of(page, size)));
    }

}