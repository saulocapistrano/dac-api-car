package com.ads.dacapicar.entities.dto.response;

public class CarResponseDTO {
    private Long id;
    private String name;
    private double price;
    private int year;
    private String city;
    private String placa;
    private String company;

    public CarResponseDTO(Long id, String name, double price, int year, String city, String placa, String company) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.year = year;
        this.city = city;
        this.placa = placa;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
