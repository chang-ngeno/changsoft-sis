package io.changsoft.sis.service.mapper;

import io.changsoft.sis.domain.*;
import io.changsoft.sis.service.dto.NextOfKinDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NextOfKin} and its DTO {@link NextOfKinDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NextOfKinMapper extends EntityMapper<NextOfKinDTO, NextOfKin> {
    @Named("idSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Set<NextOfKinDTO> toDtoIdSet(Set<NextOfKin> nextOfKin);

    @Named("firstName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    NextOfKinDTO toDtoFirstName(NextOfKin nextOfKin);

    @Named("registrationDocumentNumber")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "registrationDocumentNumber", source = "registrationDocumentNumber")
    NextOfKinDTO toDtoRegistrationDocumentNumber(NextOfKin nextOfKin);
}
