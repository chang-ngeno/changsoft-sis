package io.changsoft.sis.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link io.changsoft.sis.domain.Address} entity.
 */
public class AddressDTO implements Serializable {

    private Long id;

    private String houseNumber;

    private String streetAddress;

    @NotNull
    private String county;

    @NotNull
    private String district;

    @NotNull
    private String cityTown;

    private Integer postalCode;

    private Boolean deleted;

    private StudentDTO student;

    private EmployeeDTO employee;

    private NextOfKinDTO nextOfKin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCityTown() {
        return cityTown;
    }

    public void setCityTown(String cityTown) {
        this.cityTown = cityTown;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressDTO)) {
            return false;
        }

        AddressDTO addressDTO = (AddressDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, addressDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressDTO{" +
            "id=" + getId() +
            ", houseNumber='" + getHouseNumber() + "'" +
            ", streetAddress='" + getStreetAddress() + "'" +
            ", county='" + getCounty() + "'" +
            ", district='" + getDistrict() + "'" +
            ", cityTown='" + getCityTown() + "'" +
            ", postalCode=" + getPostalCode() +
            ", deleted='" + getDeleted() + "'" +
            ", student=" + getStudent() +
            ", employee=" + getEmployee() +
            ", nextOfKin=" + getNextOfKin() +
            "}";
    }
}
