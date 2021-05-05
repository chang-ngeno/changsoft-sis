package io.changsoft.sis.service.mapper;

import io.changsoft.sis.domain.*;
import io.changsoft.sis.service.dto.ItemDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Item} and its DTO {@link ItemDTO}.
 */
@Mapper(componentModel = "spring", uses = { CategoryMapper.class })
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {
    @Mapping(target = "category", source = "category", qualifiedByName = "name")
    ItemDTO toDto(Item s);
}
