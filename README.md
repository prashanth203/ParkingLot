# ParkingLot
implementing a simple parking lot system


it is a simpple parking lot with functionalites of 
1. create parkingLot (initilization)
2. check for free & occupied parking spots
3. park a vehicle (with a given vehicle no & type of vehicle)
4. unpark a vehicle and calculate the cost of parking



# APIs for interacting with application
1. Creating a parking Lot:

       http://localhost:8080/parking/initialize?name={parkingLotName}&rows={rows}&cols={cols}

2. checking for free & ocupied spots
   
       http://localhost:8080/parking/open-spots?parkingLotName={parkingLotName}&occupiedSpot={true/false}

3.Parking a vehicle
  
       http://localhost:8080/parking/park?parkingLotName={parkingLotName}
   
   Body of JSON("vehicleID":int, "type":"BUS"/"CAR"/"MOTORCYCLE"}

4. unparking a vehicle
   
        http://localhost:8080/parking/unPark?vehicleId={vehicleID}&parkingLotName={parkingLotName}
   you will get a cost based on no of seconds the vehicle parked
