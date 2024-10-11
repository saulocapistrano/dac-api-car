package com.ads.dacapicar.controller;

import com.ads.dacapicar.entities.dto.request.CarRequestDTO;
import com.ads.dacapicar.entities.dto.response.CarResponseDTO;
import com.ads.dacapicar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> findAll(){
        List<CarResponseDTO> cars = carService.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDTO> findById(@PathVariable Long id){
        CarResponseDTO car = carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<CarResponseDTO> createCar(@RequestBody CarRequestDTO carRequestDTO) {
        CarResponseDTO createdCar = carService.createCar(carRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<CarResponseDTO>> createMultipleCars(@RequestBody List<CarRequestDTO> carRequestDTOList) {
        List<CarResponseDTO> createdCars = carService.createMultipleCars(carRequestDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCars);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Long id, @RequestBody CarRequestDTO carRequestDTO) {
        CarResponseDTO updatedCar = carService.updateCar(id, carRequestDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
