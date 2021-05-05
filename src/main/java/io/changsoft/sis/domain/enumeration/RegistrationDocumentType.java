package io.changsoft.sis.domain.enumeration;

/**
 * The RegistrationDocumentType enumeration.
 */
public enum RegistrationDocumentType {
    PASSPORT("Passport"),
    NATIONAL_ID("NationalId"),
    HUDUMA("Huduma"),
    BIRTH_CERTIFICATE("BirthCertificate");

    private final String value;

    RegistrationDocumentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
