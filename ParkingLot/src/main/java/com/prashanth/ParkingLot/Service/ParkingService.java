package com.prashanth.ParkingLot.Service;


import com.prashanth.ParkingLot.Model.ParkingSpot;
import com.prashanth.ParkingLot.Model.Vehicle;

import java.util.List;

public interface ParkingService {
    void initializeParkingLot(String name, int r, int c);

    void parkVehicle(Vehicle vehicle, String parkingLotName);

    String unParkVehicle(String vehicleId, String parkingLotName);

    ParkingSpot findSpotById(String spotId, String parkingLotName);

    List<ParkingSpot> getOpenSpots(String parkingLotName, boolean occupiedSpot);
}
