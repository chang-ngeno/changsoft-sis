package io.changsoft.sis.web.rest;

import io.changsoft.sis.repository.ContactRepository;
import io.changsoft.sis.service.ContactService;
import io.changsoft.sis.service.dto.ContactDTO;
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
 * REST controller for managing {@link io.changsoft.sis.domain.Contact}.
 */
@RestController
@RequestMapping("/api")
public class ContactResource {

    private final Logger log = LoggerFactory.getLogger(ContactResource.class);

    private static final String ENTITY_NAME = "contact";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContactService contactService;

    private final ContactRepository contactRepository;

    public ContactResource(ContactService contactService, ContactRepository contactRepository) {
        this.contactService = contactService;
        this.contactRepository = contactRepository;
    }

    /**
     * {@code POST  /contacts} : Create a new contact.
     *
     * @param contactDTO the contactDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contactDTO, or with status {@code 400 (Bad Request)} if the contact has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contacts")
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactDTO contactDTO) throws URISyntaxException {
        log.debug("REST request to save Contact : {}", contactDTO);
        if (contactDTO.getId() != null) {
            throw new BadRequestAlertException("A new contact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactDTO result = contactService.save(contactDTO);
        return ResponseEntity
            .created(new URI("/api/contacts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contacts/:id} : Updates an existing contact.
     *
     * @param id the id of the contactDTO to save.
     * @param contactDTO the contactDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactDTO,
     * or with status {@code 400 (Bad Request)} if the contactDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contactDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contacts/{id}")
    public ResponseEntity<ContactDTO> updateContact(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContactDTO contactDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Contact : {}, {}", id, contactDTO);
        if (contactDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contactDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contactRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContactDTO result = contactService.save(contactDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contactDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /contacts/:id} : Partial updates given fields of an existing contact, field will ignore if it is null
     *
     * @param id the id of the contactDTO to save.
     * @param contactDTO the contactDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactDTO,
     * or with status {@code 400 (Bad Request)} if the contactDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contactDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contactDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/contacts/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ContactDTO> partialUpdateContact(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContactDTO contactDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Contact partially : {}, {}", id, contactDTO);
        if (contactDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contactDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contactRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContactDTO> result = contactService.partialUpdate(contactDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contactDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /contacts} : get all the contacts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contacts in body.
     */
    @GetMapping("/contacts")
    public ResponseEntity<List<ContactDTO>> getAllContacts(Pageable pageable) {
        log.debug("REST request to get a page of Contacts");
        Page<ContactDTO> page = contactService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contacts/:id} : get the "id" contact.
     *
     * @param id the id of the contactDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contacts/{id}")
    public ResponseEntity<ContactDTO> getContact(@PathVariable Long id) {
        log.debug("REST request to get Contact : {}", id);
        Optional<ContactDTO> contactDTO = contactService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contactDTO);
    }

    /**
     * {@code DELETE  /contacts/:id} : delete the "id" contact.
     *
     * @param id the id of the contactDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        log.debug("REST request to delete Contact : {}", id);
        contactService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
