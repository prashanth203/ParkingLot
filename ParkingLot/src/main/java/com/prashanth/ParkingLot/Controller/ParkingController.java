package com.prashanth.ParkingLot.Controller;


import com.prashanth.ParkingLot.Model.ParkingSpot;
import com.prashanth.ParkingLot.Model.Vehicle;
import com.prashanth.ParkingLot.Repository.VehicleRepository;
import com.prashanth.ParkingLot.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public ParkingController(ParkingService parkingService, VehicleRepository vehicleRepository) {
        this.parkingService = parkingService;
        this.vehicleRepository = vehicleRepository;
    }

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeParkingLot(@RequestParam String name,
                                                       @RequestParam int r,
                                                       @RequestParam int c) {
        parkingService.initializeParkingLot(name,r,c);
        return ResponseEntity.ok("Parking Lot initialized successfully");
    }

    @PostMapping("/park")
    public ResponseEntity<String> parkVehicle(@RequestBody Vehicle vehicle,
                                              @RequestParam String parkingLotName) {
        vehicleRepository.save(vehicle);
        parkingService.parkVehicle(vehicle, parkingLotName);
        return ResponseEntity.ok("Vehicle parked successfully");
    }

    @DeleteMapping ("/unPark")
    public ResponseEntity<String> unParkVehicle(@RequestParam String vehicleId,
                                                @RequestParam String parkingLotName) {
        String unpark_message=parkingService.unParkVehicle(vehicleId, parkingLotName);
        //      vehicleRepository.save(vehicle);
        return ResponseEntity.ok(unpark_message);
    }


    @GetMapping("/open-spots")
    public ResponseEntity<List<ParkingSpot>> getOpenSpots(@RequestParam String parkingLotName, @RequestParam boolean occupiedSpot) {
        List<ParkingSpot> openSpots = parkingService.getOpenSpots(parkingLotName, occupiedSpot);
        return ResponseEntity.ok(openSpots);
    }

}
