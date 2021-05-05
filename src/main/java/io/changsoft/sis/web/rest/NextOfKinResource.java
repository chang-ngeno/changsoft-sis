package io.changsoft.sis.web.rest;

import io.changsoft.sis.repository.NextOfKinRepository;
import io.changsoft.sis.service.NextOfKinService;
import io.changsoft.sis.service.dto.NextOfKinDTO;
import io.changsoft.sis.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link io.changsoft.sis.domain.NextOfKin}.
 */
@RestController
@RequestMapping("/api")
public class NextOfKinResource {

    private final Logger log = LoggerFactory.getLogger(NextOfKinResource.class);

    private static final String ENTITY_NAME = "nextOfKin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NextOfKinService nextOfKinService;

    private final NextOfKinRepository nextOfKinRepository;

    public NextOfKinResource(NextOfKinService nextOfKinService, NextOfKinRepository nextOfKinRepository) {
        this.nextOfKinService = nextOfKinService;
        this.nextOfKinRepository = nextOfKinRepository;
    }

    /**
     * {@code POST  /next-of-kins} : Create a new nextOfKin.
     *
     * @param nextOfKinDTO the nextOfKinDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nextOfKinDTO, or with status {@code 400 (Bad Request)} if the nextOfKin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/next-of-kins")
    public ResponseEntity<NextOfKinDTO> createNextOfKin(@Valid @RequestBody NextOfKinDTO nextOfKinDTO) throws URISyntaxException {
        log.debug("REST request to save NextOfKin : {}", nextOfKinDTO);
        if (nextOfKinDTO.getId() != null) {
            throw new BadRequestAlertException("A new nextOfKin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NextOfKinDTO result = nextOfKinService.save(nextOfKinDTO);
        return ResponseEntity
            .created(new URI("/api/next-of-kins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /next-of-kins/:id} : Updates an existing nextOfKin.
     *
     * @param id the id of the nextOfKinDTO to save.
     * @param nextOfKinDTO the nextOfKinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nextOfKinDTO,
     * or with status {@code 400 (Bad Request)} if the nextOfKinDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nextOfKinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/next-of-kins/{id}")
    public ResponseEntity<NextOfKinDTO> updateNextOfKin(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody NextOfKinDTO nextOfKinDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NextOfKin : {}, {}", id, nextOfKinDTO);
        if (nextOfKinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nextOfKinDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nextOfKinRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NextOfKinDTO result = nextOfKinService.save(nextOfKinDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nextOfKinDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /next-of-kins/:id} : Partial updates given fields of an existing nextOfKin, field will ignore if it is null
     *
     * @param id the id of the nextOfKinDTO to save.
     * @param nextOfKinDTO the nextOfKinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nextOfKinDTO,
     * or with status {@code 400 (Bad Request)} if the nextOfKinDTO is not valid,
     * or with status {@code 404 (Not Found)} if the nextOfKinDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the nextOfKinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/next-of-kins/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<NextOfKinDTO> partialUpdateNextOfKin(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody NextOfKinDTO nextOfKinDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NextOfKin partially : {}, {}", id, nextOfKinDTO);
        if (nextOfKinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nextOfKinDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nextOfKinRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NextOfKinDTO> result = nextOfKinService.partialUpdate(nextOfKinDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nextOfKinDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /next-of-kins} : get all the nextOfKins.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nextOfKins in body.
     */
    @GetMapping("/next-of-kins")
    public ResponseEntity<List<NextOfKinDTO>> getAllNextOfKins(Pageable pageable) {
        log.debug("REST request to get a page of NextOfKins");
        Page<NextOfKinDTO> page = nextOfKinService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /next-of-kins/:id} : get the "id" nextOfKin.
     *
     * @param id the id of the nextOfKinDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nextOfKinDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/next-of-kins/{id}")
    public ResponseEntity<NextOfKinDTO> getNextOfKin(@PathVariable Long id) {
        log.debug("REST request to get NextOfKin : {}", id);
        Optional<NextOfKinDTO> nextOfKinDTO = nextOfKinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nextOfKinDTO);
    }

    /**
     * {@code DELETE  /next-of-kins/:id} : delete the "id" nextOfKin.
     *
     * @param id the id of the nextOfKinDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/next-of-kins/{id}")
    public ResponseEntity<Void> deleteNextOfKin(@PathVariable Long id) {
        log.debug("REST request to delete NextOfKin : {}", id);
        nextOfKinService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
