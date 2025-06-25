package com.prashanth.ParkingLot.Model;

import java.util.List;
import java.util.Random;

public enum SpotType {
    MOTORCYCLE, CAR, BUS;

    private static final List<SpotType> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static SpotType randomSpotType() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

