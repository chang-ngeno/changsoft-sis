package io.changsoft.sis.service;

import io.changsoft.sis.service.dto.NextOfKinDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.changsoft.sis.domain.NextOfKin}.
 */
public interface NextOfKinService {
    /**
     * Save a nextOfKin.
     *
     * @param nextOfKinDTO the entity to save.
     * @return the persisted entity.
     */
    NextOfKinDTO save(NextOfKinDTO nextOfKinDTO);

    /**
     * Partially updates a nextOfKin.
     *
     * @param nextOfKinDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NextOfKinDTO> partialUpdate(NextOfKinDTO nextOfKinDTO);

    /**
     * Get all the nextOfKins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NextOfKinDTO> findAll(Pageable pageable);

    /**
     * Get the "id" nextOfKin.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NextOfKinDTO> findOne(Long id);

    /**
     * Delete the "id" nextOfKin.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
