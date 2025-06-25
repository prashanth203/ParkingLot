package com.prashanth.ParkingLot.Service;


import com.prashanth.ParkingLot.Model.ParkingLot;
import com.prashanth.ParkingLot.Model.ParkingSpot;
import com.prashanth.ParkingLot.Model.Vehicle;
import com.prashanth.ParkingLot.Model.VehicleType;
import com.prashanth.ParkingLot.Repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService{

    private final ParkingLotRepository parkingLotRepository;

    public ParkingServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public void initializeParkingLot(String name, int r, int c) {
        ParkingLot parkingLot = new ParkingLot(name,r,c);
        parkingLotRepository.save(parkingLot);
    }

    @Override
    public void parkVehicle(Vehicle vehicle, String parkingLotName){
        ParkingLot parkingLot = parkingLotRepository.findByName(parkingLotName);
        if (parkingLot != null) {
            ParkingSpot spot = findAvailableSpot(parkingLot, vehicle.getType());
            if (spot != null) {
                spot.setParkedVehicle(vehicle);
                parkingLotRepository.save(parkingLot);
                System.out.println("Vehicle with ID "+vehicle.getId()+" parked at spot "+spot.getSpotId());
            } else {
                System.out.println("No spot available for Vehicle with ID "+vehicle.getId());
            }
        } else {
            System.out.println("Parking Lot with name "+parkingLotName+" not found");
        }
    }

    private ParkingSpot findAvailableSpot(ParkingLot parkingLot, VehicleType type) {
        List<ParkingSpot> parkingSpots = parkingLot.getSpots();
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getParkedVehicle() == null && isSpotTypeMatch(spot, type)) {
                return spot;
            }
        }
        return null;
    }

    private boolean isSpotTypeMatch(ParkingSpot spot, VehicleType type) {
        return spot.getSpotType().name().equals(type.name());
    }

    @Override
    public void removeVehicle(String vehicleId, String parkingLotName){
        ParkingLot parkingLot = parkingLotRepository.findByName(parkingLotName);
        if (parkingLot != null) {
            ParkingSpot parkingSpot = findSpotByVahicleId(vehicleId,parkingLot);
            if (parkingSpot != null) {
                parkingSpot.setParkedVehicle(null);
                parkingLotRepository.save(parkingLot);
            }
        }
    }

    private ParkingSpot findSpotByVahicleId(String vehicleId, ParkingLot parkingLot) {
        List<ParkingSpot> parkingSpots = parkingLot.getSpots();
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getParkedVehicle() != null && parkingSpot.getParkedVehicle().getVehicleId().equals(vehicleId)) {
                return parkingSpot;
            }
        }
        return null;
    }

    @Override
    public ParkingSpot findSpotById(String spotId, String parkingLotName){
        ParkingLot parkingLot = parkingLotRepository.findByName(parkingLotName);
        if (parkingLot != null) {
            return parkingLot.getSpots()
                    .stream()
                    .filter(spot->spot.getSpotId().equals(spotId))
                    .findFirst()
                    .orElse(null);
        } else {
            System.out.println("Parking Lot with name "+parkingLotName+" not found");
            return null;
        }
    }

    @Override
    public List<ParkingSpot> getOpenSpots(String parkingLotName, boolean occupiedSpot){
        ParkingLot parkingLot = parkingLotRepository.findByName(parkingLotName);
        if (parkingLot != null) {
            List<ParkingSpot> openSpots = new ArrayList<>();
            List<ParkingSpot> occupiedSpots = new ArrayList<>();
            for (ParkingSpot spot: parkingLot.getSpots()) {
                if (spot.getParkedVehicle() == null) {
                    openSpots.add(spot);
                } else {
                    occupiedSpots.add(spot);
                }
            }
            if (occupiedSpot) {
                return occupiedSpots;
            }
            return openSpots;
        } else {
            System.out.println("Parking Lot with name "+parkingLotName+" not found");
            return new ArrayList<>();
        }
    }
}