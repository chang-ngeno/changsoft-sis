package io.changsoft.sis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.changsoft.sis.domain.enumeration.Gender;
import io.changsoft.sis.domain.enumeration.RegistrationDocumentType;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "student_reg_number", nullable = false)
    private String studentRegNumber;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private Instant dateOfBirth;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "reg_doc_type", nullable = false)
    private RegistrationDocumentType regDocType;

    @NotNull
    @Column(name = "registration_document_number", nullable = false)
    private String registrationDocumentNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "nationality")
    private String nationality;

    @NotNull
    @Column(name = "date_joined", nullable = false)
    private Instant dateJoined;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "wxt_jwt_pq_55_wd")
    private String wxtJwtPq55wd;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @NotNull
    @JoinTable(
        name = "rel_student__relative",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "relative_id")
    )
    @JsonIgnoreProperties(value = { "contacts", "employees", "students" }, allowSetters = true)
    private Set<NextOfKin> relatives = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "employee", "nextOfKin", "student" }, allowSetters = true)
    private Set<Contact> contacts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student id(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Student firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public Student middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Student lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentRegNumber() {
        return this.studentRegNumber;
    }

    public Student studentRegNumber(String studentRegNumber) {
        this.studentRegNumber = studentRegNumber;
        return this;
    }

    public void setStudentRegNumber(String studentRegNumber) {
        this.studentRegNumber = studentRegNumber;
    }

    public Instant getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Student dateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public RegistrationDocumentType getRegDocType() {
        return this.regDocType;
    }

    public Student regDocType(RegistrationDocumentType regDocType) {
        this.regDocType = regDocType;
        return this;
    }

    public void setRegDocType(RegistrationDocumentType regDocType) {
        this.regDocType = regDocType;
    }

    public String getRegistrationDocumentNumber() {
        return this.registrationDocumentNumber;
    }

    public Student registrationDocumentNumber(String registrationDocumentNumber) {
        this.registrationDocumentNumber = registrationDocumentNumber;
        return this;
    }

    public void setRegistrationDocumentNumber(String registrationDocumentNumber) {
        this.registrationDocumentNumber = registrationDocumentNumber;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Student gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return this.nationality;
    }

    public Student nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Instant getDateJoined() {
        return this.dateJoined;
    }

    public Student dateJoined(Instant dateJoined) {
        this.dateJoined = dateJoined;
        return this;
    }

    public void setDateJoined(Instant dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public Student deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getWxtJwtPq55wd() {
        return this.wxtJwtPq55wd;
    }

    public Student wxtJwtPq55wd(String wxtJwtPq55wd) {
        this.wxtJwtPq55wd = wxtJwtPq55wd;
        return this;
    }

    public void setWxtJwtPq55wd(String wxtJwtPq55wd) {
        this.wxtJwtPq55wd = wxtJwtPq55wd;
    }

    public Set<NextOfKin> getRelatives() {
        return this.relatives;
    }

    public Student relatives(Set<NextOfKin> nextOfKins) {
        this.setRelatives(nextOfKins);
        return this;
    }

    public Student addRelative(NextOfKin nextOfKin) {
        this.relatives.add(nextOfKin);
        nextOfKin.getStudents().add(this);
        return this;
    }

    public Student removeRelative(NextOfKin nextOfKin) {
        this.relatives.remove(nextOfKin);
        nextOfKin.getStudents().remove(this);
        return this;
    }

    public void setRelatives(Set<NextOfKin> nextOfKins) {
        this.relatives = nextOfKins;
    }

    public Set<Contact> getContacts() {
        return this.contacts;
    }

    public Student contacts(Set<Contact> contacts) {
        this.setContacts(contacts);
        return this;
    }

    public Student addContact(Contact contact) {
        this.contacts.add(contact);
        contact.setStudent(this);
        return this;
    }

    public Student removeContact(Contact contact) {
        this.contacts.remove(contact);
        contact.setStudent(null);
        return this;
    }

    public void setContacts(Set<Contact> contacts) {
        if (this.contacts != null) {
            this.contacts.forEach(i -> i.setStudent(null));
        }
        if (contacts != null) {
            contacts.forEach(i -> i.setStudent(this));
        }
        this.contacts = contacts;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        return id != null && id.equals(((Student) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Student{" +
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
            "}";
    }
}
