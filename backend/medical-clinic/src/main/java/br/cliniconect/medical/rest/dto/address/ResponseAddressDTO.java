package br.cliniconect.medical.rest.dto.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAddressDTO {

    private String street;
    private String district;
    private String number;
    private String city;
    private String state;
    private String complement;
    private String zipCode;
}
