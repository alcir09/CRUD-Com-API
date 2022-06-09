package com.api.parkingcontrol.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.api.parkingcontrol.DTOs.ParkingSPotDTO;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {

        this.parkingSpotService = parkingSpotService;
    }

    //USANDO O VALID PARA QUE OS CAMPOS OBRIGATÓRIOS SEMPRE SEREM PREENCHIDOS
    @PostMapping
    public ResponseEntity < Object > saveParkingSpot(@RequestBody @Valid ParkingSPotDTO parkingSPotDTO) {

        // if (parkingSpotService.existsByLicensePlateCar(parkingSPotDTO.getLicensePlateCar())) {  

        //      return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflit: License Plate Car is already in use");

        //  }

        // if (parkingSpotService.existsByParkingSpotNumber(parkingSPotDTO.getParkingSpotNumber())) {
        //     return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflit: Parking Spot is already in use");

        // }

        // if (parkingSpotService.existsByApartmentAndBlock(parkingSPotDTO.getApartment(), parkingSPotDTO.getBlock())) {

        //     return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflit: Parking Spot alredy redgistred fot this apartmente/block");

        // }

        var parkingSpotModel = new ParkingSpotModel();

        //COPIANDO OS ARQUIVOS DO DTO PARA O MODEL
        BeanUtils.copyProperties(parkingSPotDTO, parkingSpotModel);
        parkingSpotModel.setLicensePLateCar(parkingSPotDTO.getLicensePlateCar());
        //CONVERTENDO O CAMPO DATA
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        //SALVANDO OS DADOS
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));

    }

    @GetMapping
    public ResponseEntity < List < ParkingSpotModel >> getALLParkingSpots() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findALL());
    }

    @GetMapping("/{id}")
    public ResponseEntity < Object > getOneParkingSpots(@PathVariable(value = "id") UUID id) {


        Optional < ParkingSpotModel > parkingSpotModelOptional = parkingSpotService.findById(id);


        if (!parkingSpotModelOptional.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");

        }

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity < Object > deleteParkingSpot(@PathVariable(value = "id") UUID id) {

        Optional < ParkingSpotModel > parkingSpotModelOptional = parkingSpotService.findById(id);

        if (!parkingSpotModelOptional.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");

        }

        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("A vaga foi deletada!");

    }

    @PutMapping("/{id}")
    public ResponseEntity < Object > updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSPotDTO parkingSPotDTO) {

        Optional < ParkingSpotModel > parkingSpotModelOptional = parkingSpotService.findById(id);

        if (!parkingSpotModelOptional.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada!");

        }

        // var parkingSpotModel =  parkingSpotModelOptional.get();
        // parkingSpotModel.setParkingSpotNumber(parkingSPotDTO.getParkingSpotNumber());
        // parkingSpotModel.setLicensePLateCar(parkingSPotDTO.getLicensePlateCar());
        // parkingSpotModel.setModelCar(parkingSPotDTO.getModelCar());
        // parkingSpotModel.setBrandCar(parkingSPotDTO.getBrandCar());
        // parkingSpotModel.setColorCar(parkingSPotDTO.getColorCar());
        // parkingSpotModel.setResponsibleName(parkingSPotDTO.getResponsibleName());
        // parkingSpotModel.setApartment(parkingSPotDTO.getApartment());
        // parkingSpotModel.setBlock(parkingSPotDTO.getBlock());

        var parkingSpotModel = new ParkingSpotModel();
        parkingSpotModel.setLicensePLateCar(parkingSPotDTO.getLicensePlateCar());
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        BeanUtils.copyProperties(parkingSPotDTO, parkingSpotModel);

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));

    }

}