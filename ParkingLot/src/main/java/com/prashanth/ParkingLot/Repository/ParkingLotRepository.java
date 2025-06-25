package com.prashanth.ParkingLot.Repository;

import com.prashanth.ParkingLot.Model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot,Long> {

    ParkingLot findByName(String name);
}
