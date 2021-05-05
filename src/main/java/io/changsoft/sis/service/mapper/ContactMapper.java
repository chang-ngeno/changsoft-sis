package io.changsoft.sis.service.mapper;

import io.changsoft.sis.domain.*;
import io.changsoft.sis.service.dto.ContactDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contact} and its DTO {@link ContactDTO}.
 */
@Mapper(componentModel = "spring", uses = { EmployeeMapper.class, NextOfKinMapper.class, StudentMapper.class })
public interface ContactMapper extends EntityMapper<ContactDTO, Contact> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "staffSysNo")
    @Mapping(target = "nextOfKin", source = "nextOfKin", qualifiedByName = "registrationDocumentNumber")
    @Mapping(target = "student", source = "student", qualifiedByName = "studentRegNumber")
    ContactDTO toDto(Contact s);
}
