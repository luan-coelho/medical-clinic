package br.cliniconect.medical.rest.dto.address;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateAddressDTO {

    @NotBlank(message = "inform the street")
    private String street;
    @NotBlank(message = "inform the number")
    private String number;
    @NotBlank(message = "inform the city")
    private String city;
    @NotBlank(message = "inform the state")
    private String state;
    private String complement;
    @NotBlank(message = "inform the Zipcode")
    private String zipCode;
}
