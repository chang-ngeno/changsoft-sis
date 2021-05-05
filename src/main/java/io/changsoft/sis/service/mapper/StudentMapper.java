package io.changsoft.sis.service.mapper;

import io.changsoft.sis.domain.*;
import io.changsoft.sis.service.dto.StudentDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring", uses = { NextOfKinMapper.class })
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {
    @Mapping(target = "relatives", source = "relatives", qualifiedByName = "idSet")
    StudentDTO toDto(Student s);

    @Mapping(target = "removeRelative", ignore = true)
    Student toEntity(StudentDTO studentDTO);

    @Named("studentRegNumber")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "studentRegNumber", source = "studentRegNumber")
    StudentDTO toDtoStudentRegNumber(Student student);
}
