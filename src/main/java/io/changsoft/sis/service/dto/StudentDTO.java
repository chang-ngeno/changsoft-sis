package io.changsoft.sis.service.dto;

import io.changsoft.sis.domain.enumeration.Gender;
import io.changsoft.sis.domain.enumeration.RegistrationDocumentType;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link io.changsoft.sis.domain.Student} entity.
 */
public class StudentDTO implements Serializable {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private String studentRegNumber;

    @NotNull
    private Instant dateOfBirth;

    @NotNull
    private RegistrationDocumentType regDocType;

    @NotNull
    private String registrationDocumentNumber;

    @NotNull
    private Gender gender;

    private String nationality;

    @NotNull
    private Instant dateJoined;

    private Boolean deleted;

    private String wxtJwtPq55wd;

    private Set<NextOfKinDTO> relatives = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentRegNumber() {
        return studentRegNumber;
    }

    public void setStudentRegNumber(String studentRegNumber) {
        this.studentRegNumber = studentRegNumber;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public RegistrationDocumentType getRegDocType() {
        return regDocType;
    }

    public void setRegDocType(RegistrationDocumentType regDocType) {
        this.regDocType = regDocType;
    }

    public String getRegistrationDocumentNumber() {
        return registrationDocumentNumber;
    }

    public void setRegistrationDocumentNumber(String registrationDocumentNumber) {
        this.registrationDocumentNumber = registrationDocumentNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Instant getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Instant dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getWxtJwtPq55wd() {
        return wxtJwtPq55wd;
    }

    public void setWxtJwtPq55wd(String wxtJwtPq55wd) {
        this.wxtJwtPq55wd = wxtJwtPq55wd;
    }

    public Set<NextOfKinDTO> getRelatives() {
        return relatives;
    }

    public void setRelatives(Set<NextOfKinDTO> relatives) {
        this.relatives = relatives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentDTO)) {
            return false;
        }

        StudentDTO studentDTO = (StudentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, studentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StudentDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", studentRegNumber='" + getStudentRegNumber() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", regDocType='" + getRegDocType() + "'" +
            ", registrationDocumentNumber='" + getRegistrationDocumentNumber() + "'" +
            ", gender='" + getGender() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", dateJoined='" + getDateJoined() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", wxtJwtPq55wd='" + getWxtJwtPq55wd() + "'" +
            ", relatives=" + getRelatives() +
            "}";
    }
}
