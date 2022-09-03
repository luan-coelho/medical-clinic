package br.cliniconect.medical.domain.model.patient;

import br.cliniconect.medical.domain.model.DefaultEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address extends DefaultEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "inform the street")
    private String street;
    @NotBlank(message = "inform the district")
    private String district;
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
