package io.changsoft.sis.repository;

import io.changsoft.sis.domain.NextOfKin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NextOfKin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NextOfKinRepository extends JpaRepository<NextOfKin, Long> {}
