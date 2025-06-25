
package com.prashanth.ParkingLot.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ParkingSpot> spots;

    public ParkingLot(){
    }
    public ParkingLot(String name, int row, int col){
        this.name = name;
        this.spots = initializeParkingSpots(row, col);
    }

    private List<ParkingSpot> initializeParkingSpots(int r, int c) {
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        for (int i=1; i<=r; i++) {
            for (int j=1; j<=c; j++) {
                ParkingSpot spot = new ParkingSpot();
                spot.setSpotId(this.name+"Spot"+i+"-"+j);
                spot.setSpotType(SpotType.randomSpotType());
                spot.setParkingLot(this);
                parkingSpots.add(spot);
            }
        }
        return parkingSpots;
    }

    public List<ParkingSpot> getSpots()
    {
        return this.spots;
    }
}