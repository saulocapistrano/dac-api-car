package com.ads.dacapicar.service;

import com.ads.dacapicar.entities.Car;
import com.ads.dacapicar.entities.dto.request.CarRequestDTO;
import com.ads.dacapicar.entities.dto.response.CarResponseDTO;
import com.ads.dacapicar.entities.map.CarMapper;
import com.ads.dacapicar.exception.CarNotFoundException;
import com.ads.dacapicar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;


    public List<CarResponseDTO> findAll(){
        return  carRepository.findAll()
                .stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList());
    }

    public CarResponseDTO findById(Long id){
        Optional<Car> car = carRepository.findById(id);
        return car.map(CarMapper::toDto).orElseThrow(()->new CarNotFoundException("Carro não entcontrado para o id:" +id));
    }

    public CarResponseDTO createCar(CarRequestDTO carRequestDTO){
        Car car = CarMapper.toEntity(carRequestDTO);
        car = carRepository.save(car);
        return CarMapper.toDto(car);
    }

    public List<CarResponseDTO> createMultipleCars(List<CarRequestDTO> carRequestDTOList) {
        List<Car> cars = carRequestDTOList.stream()
                .map(CarMapper::toEntity)
                .collect(Collectors.toList());

        List<Car> savedCars = carRepository.saveAll(cars);

        return savedCars.stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList());
    }


    public CarResponseDTO updateCar(Long id, CarRequestDTO carRequestDTO) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Carro não encontrado com o ID: " + id));

        car.setName(carRequestDTO.getName());
        car.setPrice(carRequestDTO.getPrice());
        car.setYear(carRequestDTO.getYear());
        car.setCity(carRequestDTO.getCity());
        car.setPlaca(carRequestDTO.getPlaca());
        car.setCompany(carRequestDTO.getCompany());

        car = carRepository.save(car);

        return CarMapper.toDto(car);
    }



    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }



}
