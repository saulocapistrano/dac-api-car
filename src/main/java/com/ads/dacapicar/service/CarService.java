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

    public CarResponseDTO updateCar(Long id, CarRequestDTO carRequestDTO){
        Optional<Car> carOptional = carRepository.findById(id);
        if(carOptional.isPresent()){
            Car car = carOptional.get();
            car.setName(carRequestDTO.getName());
            car.setPrice(carRequestDTO.getPrice());
            car.setYear(carRequestDTO.getYear());
            car.setCity(carRequestDTO.getCity());
            car.setCompany(carRequestDTO.getCompany());
            car.setPlaca(carRequestDTO.getPlaca());

            car = carRepository.save(car);
            return CarMapper.toDto(car);
        }

        return null;
    }


    public void deletCar(Long id){
        carRepository.deleteById(id);
    }



}
