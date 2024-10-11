package com.ads.dacapicar.entities.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CarRequestDTO {

    @NotBlank(message = "O nome do carro é obrigatório")
    private String name;

    @Positive(message = "O preço deve ser positivo")
    private double price;

    @Min(value = 1886, message = "Ano inválido. O primeiro carro foi inventado em 1886")
    private int year;

    @NotBlank(message = "A cidade é obrigatória")
    private String city;

    @NotBlank(message = "A placa é obrigatória")
    private String placa;

    @NotBlank(message = "A empresa (marca) do carro é obrigatória")
    private String company;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
