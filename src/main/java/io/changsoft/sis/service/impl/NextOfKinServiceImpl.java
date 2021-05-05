package io.changsoft.sis.service.impl;

import io.changsoft.sis.domain.NextOfKin;
import io.changsoft.sis.repository.NextOfKinRepository;
import io.changsoft.sis.service.NextOfKinService;
import io.changsoft.sis.service.dto.NextOfKinDTO;
import io.changsoft.sis.service.mapper.NextOfKinMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NextOfKin}.
 */
@Service
@Transactional
public class NextOfKinServiceImpl implements NextOfKinService {

    private final Logger log = LoggerFactory.getLogger(NextOfKinServiceImpl.class);

    private final NextOfKinRepository nextOfKinRepository;

    private final NextOfKinMapper nextOfKinMapper;

    public NextOfKinServiceImpl(NextOfKinRepository nextOfKinRepository, NextOfKinMapper nextOfKinMapper) {
        this.nextOfKinRepository = nextOfKinRepository;
        this.nextOfKinMapper = nextOfKinMapper;
    }

    @Override
    public NextOfKinDTO save(NextOfKinDTO nextOfKinDTO) {
        log.debug("Request to save NextOfKin : {}", nextOfKinDTO);
        NextOfKin nextOfKin = nextOfKinMapper.toEntity(nextOfKinDTO);
        nextOfKin = nextOfKinRepository.save(nextOfKin);
        return nextOfKinMapper.toDto(nextOfKin);
    }

    @Override
    public Optional<NextOfKinDTO> partialUpdate(NextOfKinDTO nextOfKinDTO) {
        log.debug("Request to partially update NextOfKin : {}", nextOfKinDTO);

        return nextOfKinRepository
            .findById(nextOfKinDTO.getId())
            .map(
                existingNextOfKin -> {
                    nextOfKinMapper.partialUpdate(existingNextOfKin, nextOfKinDTO);
                    return existingNextOfKin;
                }
            )
            .map(nextOfKinRepository::save)
            .map(nextOfKinMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NextOfKinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NextOfKins");
        return nextOfKinRepository.findAll(pageable).map(nextOfKinMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NextOfKinDTO> findOne(Long id) {
        log.debug("Request to get NextOfKin : {}", id);
        return nextOfKinRepository.findById(id).map(nextOfKinMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NextOfKin : {}", id);
        nextOfKinRepository.deleteById(id);
    }
}
