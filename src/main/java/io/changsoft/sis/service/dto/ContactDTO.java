package io.changsoft.sis.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link io.changsoft.sis.domain.Contact} entity.
 */
public class ContactDTO implements Serializable {

    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String mobileNumber;

    private Boolean deleted;

    private EmployeeDTO employee;

    private NextOfKinDTO nextOfKin;

    private StudentDTO student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public NextOfKinDTO getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(NextOfKinDTO nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactDTO)) {
            return false;
        }

        ContactDTO contactDTO = (ContactDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contactDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContactDTO{" +
            "id=" + getId() +
            ", email='" + getEmail() + "'" +
            ", mobileNumber='" + getMobileNumber() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", employee=" + getEmployee() +
            ", nextOfKin=" + getNextOfKin() +
            ", student=" + getStudent() +
            "}";
    }
}
