package com.openclassrooms.SafetyNetAlerts.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * The type Medical record request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecordRequest {
    private String lastName;
    private String firstName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecordRequest that = (MedicalRecordRequest) o;
        return Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(birthdate, that.birthdate) && Objects.equals(medications, that.medications) && Objects.equals(allergies, that.allergies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, birthdate, medications, allergies);
    }

    @Override
    public String toString() {
        return "MedicalRecordRequest{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }
}
