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
 * A NextOfKin.
 */
@Entity
@Table(name = "next_of_kin")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NextOfKin implements Serializable {

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

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "wxt_jwt_pq_55_wd")
    private String wxtJwtPq55wd;

    @NotNull
    @Column(name = "relation", nullable = false)
    private String relation;

    @OneToMany(mappedBy = "nextOfKin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "employee", "nextOfKin", "student" }, allowSetters = true)
    private Set<Contact> contacts = new HashSet<>();

    @ManyToMany(mappedBy = "relatives")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "relatives", "contacts" }, allowSetters = true)
    private Set<Employee> employees = new HashSet<>();

    @ManyToMany(mappedBy = "relatives")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "relatives", "contacts" }, allowSetters = true)
    private Set<Student> students = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NextOfKin id(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public NextOfKin firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public NextOfKin middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public NextOfKin lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Instant getDateOfBirth() {
        return this.dateOfBirth;
    }

    public NextOfKin dateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public RegistrationDocumentType getRegDocType() {
        return this.regDocType;
    }

    public NextOfKin regDocType(RegistrationDocumentType regDocType) {
        this.regDocType = regDocType;
        return this;
    }

    public void setRegDocType(RegistrationDocumentType regDocType) {
        this.regDocType = regDocType;
    }

    public String getRegistrationDocumentNumber() {
        return this.registrationDocumentNumber;
    }

    public NextOfKin registrationDocumentNumber(String registrationDocumentNumber) {
        this.registrationDocumentNumber = registrationDocumentNumber;
        return this;
    }

    public void setRegistrationDocumentNumber(String registrationDocumentNumber) {
        this.registrationDocumentNumber = registrationDocumentNumber;
    }

    public Gender getGender() {
        return this.gender;
    }

    public NextOfKin gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return this.nationality;
    }

    public NextOfKin nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public NextOfKin deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getWxtJwtPq55wd() {
        return this.wxtJwtPq55wd;
    }

    public NextOfKin wxtJwtPq55wd(String wxtJwtPq55wd) {
        this.wxtJwtPq55wd = wxtJwtPq55wd;
        return this;
    }

    public void setWxtJwtPq55wd(String wxtJwtPq55wd) {
        this.wxtJwtPq55wd = wxtJwtPq55wd;
    }

    public String getRelation() {
        return this.relation;
    }

    public NextOfKin relation(String relation) {
        this.relation = relation;
        return this;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Set<Contact> getContacts() {
        return this.contacts;
    }

    public NextOfKin contacts(Set<Contact> contacts) {
        this.setContacts(contacts);
        return this;
    }

    public NextOfKin addContact(Contact contact) {
        this.contacts.add(contact);
        contact.setNextOfKin(this);
        return this;
    }

    public NextOfKin removeContact(Contact contact) {
        this.contacts.remove(contact);
        contact.setNextOfKin(null);
        return this;
    }

    public void setContacts(Set<Contact> contacts) {
        if (this.contacts != null) {
            this.contacts.forEach(i -> i.setNextOfKin(null));
        }
        if (contacts != null) {
            contacts.forEach(i -> i.setNextOfKin(this));
        }
        this.contacts = contacts;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public NextOfKin employees(Set<Employee> employees) {
        this.setEmployees(employees);
        return this;
    }

    public NextOfKin addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.getRelatives().add(this);
        return this;
    }

    public NextOfKin removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.getRelatives().remove(this);
        return this;
    }

    public void setEmployees(Set<Employee> employees) {
        if (this.employees != null) {
            this.employees.forEach(i -> i.removeRelative(this));
        }
        if (employees != null) {
            employees.forEach(i -> i.addRelative(this));
        }
        this.employees = employees;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    public NextOfKin students(Set<Student> students) {
        this.setStudents(students);
        return this;
    }

    public NextOfKin addStudent(Student student) {
        this.students.add(student);
        student.getRelatives().add(this);
        return this;
    }

    public NextOfKin removeStudent(Student student) {
        this.students.remove(student);
        student.getRelatives().remove(this);
        return this;
    }

    public void setStudents(Set<Student> students) {
        if (this.students != null) {
            this.students.forEach(i -> i.removeRelative(this));
        }
        if (students != null) {
            students.forEach(i -> i.addRelative(this));
        }
        this.students = students;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NextOfKin)) {
            return false;
        }
        return id != null && id.equals(((NextOfKin) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NextOfKin{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", regDocType='" + getRegDocType() + "'" +
            ", registrationDocumentNumber='" + getRegistrationDocumentNumber() + "'" +
            ", gender='" + getGender() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", wxtJwtPq55wd='" + getWxtJwtPq55wd() + "'" +
            ", relation='" + getRelation() + "'" +
            "}";
    }
}
