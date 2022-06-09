package com.api.parkingcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositores.ParkingSpotRepository;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Transactional
    public Object save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public List<ParkingSpotModel> findALL() {
        return parkingSpotRepository.findAll();
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }

    // public boolean existsByLicensePLateCar(String licensePlateCar) {
    //     return parkingSpotRepository.existsByLicensePLateCar(licensePlateCar);
    // }

    // public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
    //     return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    // }

    // public boolean existsByApartmentAndBlock(String apartment, String block) {
    //     return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    // }
    
}
