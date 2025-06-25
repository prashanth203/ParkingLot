package com.prashanth.ParkingLot.Model;

import jakarta.persistence.*;

@Entity
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String spotId;

    private SpotType spotType;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id",nullable = false)
    private ParkingLot parkingLot;

    @ManyToOne
    private Vehicle parkedVehicle;

    public void setSpotId(String s)
    {
        this.spotId = s;
    }

    public Vehicle getParkedVehicle() {
        return this.parkedVehicle;
    }

    public SpotType getSpotType() {
        return this.spotType;
    }

    public void setParkedVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
    }

    public String getSpotId() {
        return this.spotId;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }
}
