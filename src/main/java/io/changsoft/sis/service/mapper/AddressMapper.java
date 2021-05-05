package io.changsoft.sis.service.mapper;

import io.changsoft.sis.domain.*;
import io.changsoft.sis.service.dto.AddressDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = { StudentMapper.class, EmployeeMapper.class, NextOfKinMapper.class })
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {
    @Mapping(target = "student", source = "student", qualifiedByName = "studentRegNumber")
    @Mapping(target = "employee", source = "employee", qualifiedByName = "staffSysNo")
    @Mapping(target = "nextOfKin", source = "nextOfKin", qualifiedByName = "firstName")
    AddressDTO toDto(Address s);
}
