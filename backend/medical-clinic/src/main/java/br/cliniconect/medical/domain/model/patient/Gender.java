package br.cliniconect.medical.domain.model.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Gender {

    MALE(1, "Male"), FEMALE(2, "Female");

    private int id;
    private String label;

    public static Gender valueOf(int id) {
        for (Gender gender : Gender.values()) {
            if (gender.getId() == id)
                return gender;
        }
        return null;
    }
}