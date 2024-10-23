package com.ads.dacapicar.entities.dto.response;

import com.ads.dacapicar.entities.User;
import com.ads.dacapicar.entities.enums.TypeUser;
import java.time.LocalDate;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private String cpf;
    private TypeUser typeUser;

    public UserResponseDTO(User obj) {
        super();
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.phoneNumber = obj.getPhoneNumber();
        this.birthDate = obj.getBirthDate();
        this.cpf = obj.getCpf();
        this.typeUser = obj.getTypeUser();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }
}
