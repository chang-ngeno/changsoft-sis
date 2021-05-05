package io.changsoft.sis.service.mapper;

import io.changsoft.sis.domain.*;
import io.changsoft.sis.service.dto.EmployeeDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring", uses = { NextOfKinMapper.class })
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {
    @Mapping(target = "relatives", source = "relatives", qualifiedByName = "idSet")
    EmployeeDTO toDto(Employee s);

    @Mapping(target = "removeRelative", ignore = true)
    Employee toEntity(EmployeeDTO employeeDTO);

    @Named("staffSysNo")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "staffSysNo", source = "staffSysNo")
    EmployeeDTO toDtoStaffSysNo(Employee employee);
}
