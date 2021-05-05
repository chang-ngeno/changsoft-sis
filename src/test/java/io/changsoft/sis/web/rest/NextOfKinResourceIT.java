package io.changsoft.sis.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.changsoft.sis.IntegrationTest;
import io.changsoft.sis.domain.NextOfKin;
import io.changsoft.sis.domain.enumeration.Gender;
import io.changsoft.sis.domain.enumeration.RegistrationDocumentType;
import io.changsoft.sis.repository.NextOfKinRepository;
import io.changsoft.sis.service.dto.NextOfKinDTO;
import io.changsoft.sis.service.mapper.NextOfKinMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NextOfKinResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NextOfKinResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_OF_BIRTH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OF_BIRTH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final RegistrationDocumentType DEFAULT_REG_DOC_TYPE = RegistrationDocumentType.PASSPORT;
    private static final RegistrationDocumentType UPDATED_REG_DOC_TYPE = RegistrationDocumentType.NATIONAL_ID;

    private static final String DEFAULT_REGISTRATION_DOCUMENT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRATION_DOCUMENT_NUMBER = "BBBBBBBBBB";

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    private static final String DEFAULT_WXT_JWT_PQ_55_WD = "AAAAAAAAAA";
    private static final String UPDATED_WXT_JWT_PQ_55_WD = "BBBBBBBBBB";

    private static final String DEFAULT_RELATION = "AAAAAAAAAA";
    private static final String UPDATED_RELATION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/next-of-kins";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NextOfKinRepository nextOfKinRepository;

    @Autowired
    private NextOfKinMapper nextOfKinMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNextOfKinMockMvc;

    private NextOfKin nextOfKin;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NextOfKin createEntity(EntityManager em) {
        NextOfKin nextOfKin = new NextOfKin()
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .regDocType(DEFAULT_REG_DOC_TYPE)
            .registrationDocumentNumber(DEFAULT_REGISTRATION_DOCUMENT_NUMBER)
            .gender(DEFAULT_GENDER)
            .nationality(DEFAULT_NATIONALITY)
            .deleted(DEFAULT_DELETED)
            .wxtJwtPq55wd(DEFAULT_WXT_JWT_PQ_55_WD)
            .relation(DEFAULT_RELATION);
        return nextOfKin;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NextOfKin createUpdatedEntity(EntityManager em) {
        NextOfKin nextOfKin = new NextOfKin()
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .regDocType(UPDATED_REG_DOC_TYPE)
            .registrationDocumentNumber(UPDATED_REGISTRATION_DOCUMENT_NUMBER)
            .gender(UPDATED_GENDER)
            .nationality(UPDATED_NATIONALITY)
            .deleted(UPDATED_DELETED)
            .wxtJwtPq55wd(UPDATED_WXT_JWT_PQ_55_WD)
            .relation(UPDATED_RELATION);
        return nextOfKin;
    }

    @BeforeEach
    public void initTest() {
        nextOfKin = createEntity(em);
    }

    @Test
    @Transactional
    void createNextOfKin() throws Exception {
        int databaseSizeBeforeCreate = nextOfKinRepository.findAll().size();
        // Create the NextOfKin
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);
        restNextOfKinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO)))
            .andExpect(status().isCreated());

        // Validate the NextOfKin in the database
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeCreate + 1);
        NextOfKin testNextOfKin = nextOfKinList.get(nextOfKinList.size() - 1);
        assertThat(testNextOfKin.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testNextOfKin.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testNextOfKin.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testNextOfKin.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testNextOfKin.getRegDocType()).isEqualTo(DEFAULT_REG_DOC_TYPE);
        assertThat(testNextOfKin.getRegistrationDocumentNumber()).isEqualTo(DEFAULT_REGISTRATION_DOCUMENT_NUMBER);
        assertThat(testNextOfKin.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testNextOfKin.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testNextOfKin.getDeleted()).isEqualTo(DEFAULT_DELETED);
        assertThat(testNextOfKin.getWxtJwtPq55wd()).isEqualTo(DEFAULT_WXT_JWT_PQ_55_WD);
        assertThat(testNextOfKin.getRelation()).isEqualTo(DEFAULT_RELATION);
    }

    @Test
    @Transactional
    void createNextOfKinWithExistingId() throws Exception {
        // Create the NextOfKin with an existing ID
        nextOfKin.setId(1L);
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        int databaseSizeBeforeCreate = nextOfKinRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNextOfKinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NextOfKin in the database
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nextOfKinRepository.findAll().size();
        // set the field null
        nextOfKin.setFirstName(null);

        // Create the NextOfKin, which fails.
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        restNextOfKinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO)))
            .andExpect(status().isBadRequest());

        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMiddleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nextOfKinRepository.findAll().size();
        // set the field null
        nextOfKin.setMiddleName(null);

        // Create the NextOfKin, which fails.
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        restNextOfKinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO)))
            .andExpect(status().isBadRequest());

        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nextOfKinRepository.findAll().size();
        // set the field null
        nextOfKin.setLastName(null);

        // Create the NextOfKin, which fails.
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        restNextOfKinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO)))
            .andExpect(status().isBadRequest());

        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateOfBirthIsRequired() throws Exception {
        int databaseSizeBeforeTest = nextOfKinRepository.findAll().size();
        // set the field null
        nextOfKin.setDateOfBirth(null);

        // Create the NextOfKin, which fails.
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        restNextOfKinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO)))
            .andExpect(status().isBadRequest());

        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRegDocTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = nextOfKinRepository.findAll().size();
        // set the field null
        nextOfKin.setRegDocType(null);

        // Create the NextOfKin, which fails.
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        restNextOfKinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO)))
            .andExpect(status().isBadRequest());

        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRegistrationDocumentNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = nextOfKinRepository.findAll().size();
        // set the field null
        nextOfKin.setRegistrationDocumentNumber(null);

        // Create the NextOfKin, which fails.
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        restNextOfKinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO)))
            .andExpect(status().isBadRequest());

        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = nextOfKinRepository.findAll().size();
        // set the field null
        nextOfKin.setGender(null);

        // Create the NextOfKin, which fails.
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        restNextOfKinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO)))
            .andExpect(status().isBadRequest());

        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRelationIsRequired() throws Exception {
        int databaseSizeBeforeTest = nextOfKinRepository.findAll().size();
        // set the field null
        nextOfKin.setRelation(null);

        // Create the NextOfKin, which fails.
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        restNextOfKinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO)))
            .andExpect(status().isBadRequest());

        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllNextOfKins() throws Exception {
        // Initialize the database
        nextOfKinRepository.saveAndFlush(nextOfKin);

        // Get all the nextOfKinList
        restNextOfKinMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nextOfKin.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].regDocType").value(hasItem(DEFAULT_REG_DOC_TYPE.toString())))
            .andExpect(jsonPath("$.[*].registrationDocumentNumber").value(hasItem(DEFAULT_REGISTRATION_DOCUMENT_NUMBER)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].wxtJwtPq55wd").value(hasItem(DEFAULT_WXT_JWT_PQ_55_WD)))
            .andExpect(jsonPath("$.[*].relation").value(hasItem(DEFAULT_RELATION)));
    }

    @Test
    @Transactional
    void getNextOfKin() throws Exception {
        // Initialize the database
        nextOfKinRepository.saveAndFlush(nextOfKin);

        // Get the nextOfKin
        restNextOfKinMockMvc
            .perform(get(ENTITY_API_URL_ID, nextOfKin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nextOfKin.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.regDocType").value(DEFAULT_REG_DOC_TYPE.toString()))
            .andExpect(jsonPath("$.registrationDocumentNumber").value(DEFAULT_REGISTRATION_DOCUMENT_NUMBER))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
            .andExpect(jsonPath("$.wxtJwtPq55wd").value(DEFAULT_WXT_JWT_PQ_55_WD))
            .andExpect(jsonPath("$.relation").value(DEFAULT_RELATION));
    }

    @Test
    @Transactional
    void getNonExistingNextOfKin() throws Exception {
        // Get the nextOfKin
        restNextOfKinMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNextOfKin() throws Exception {
        // Initialize the database
        nextOfKinRepository.saveAndFlush(nextOfKin);

        int databaseSizeBeforeUpdate = nextOfKinRepository.findAll().size();

        // Update the nextOfKin
        NextOfKin updatedNextOfKin = nextOfKinRepository.findById(nextOfKin.getId()).get();
        // Disconnect from session so that the updates on updatedNextOfKin are not directly saved in db
        em.detach(updatedNextOfKin);
        updatedNextOfKin
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .regDocType(UPDATED_REG_DOC_TYPE)
            .registrationDocumentNumber(UPDATED_REGISTRATION_DOCUMENT_NUMBER)
            .gender(UPDATED_GENDER)
            .nationality(UPDATED_NATIONALITY)
            .deleted(UPDATED_DELETED)
            .wxtJwtPq55wd(UPDATED_WXT_JWT_PQ_55_WD)
            .relation(UPDATED_RELATION);
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(updatedNextOfKin);

        restNextOfKinMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nextOfKinDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO))
            )
            .andExpect(status().isOk());

        // Validate the NextOfKin in the database
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeUpdate);
        NextOfKin testNextOfKin = nextOfKinList.get(nextOfKinList.size() - 1);
        assertThat(testNextOfKin.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testNextOfKin.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testNextOfKin.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testNextOfKin.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testNextOfKin.getRegDocType()).isEqualTo(UPDATED_REG_DOC_TYPE);
        assertThat(testNextOfKin.getRegistrationDocumentNumber()).isEqualTo(UPDATED_REGISTRATION_DOCUMENT_NUMBER);
        assertThat(testNextOfKin.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testNextOfKin.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testNextOfKin.getDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testNextOfKin.getWxtJwtPq55wd()).isEqualTo(UPDATED_WXT_JWT_PQ_55_WD);
        assertThat(testNextOfKin.getRelation()).isEqualTo(UPDATED_RELATION);
    }

    @Test
    @Transactional
    void putNonExistingNextOfKin() throws Exception {
        int databaseSizeBeforeUpdate = nextOfKinRepository.findAll().size();
        nextOfKin.setId(count.incrementAndGet());

        // Create the NextOfKin
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNextOfKinMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nextOfKinDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NextOfKin in the database
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNextOfKin() throws Exception {
        int databaseSizeBeforeUpdate = nextOfKinRepository.findAll().size();
        nextOfKin.setId(count.incrementAndGet());

        // Create the NextOfKin
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNextOfKinMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NextOfKin in the database
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNextOfKin() throws Exception {
        int databaseSizeBeforeUpdate = nextOfKinRepository.findAll().size();
        nextOfKin.setId(count.incrementAndGet());

        // Create the NextOfKin
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNextOfKinMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NextOfKin in the database
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNextOfKinWithPatch() throws Exception {
        // Initialize the database
        nextOfKinRepository.saveAndFlush(nextOfKin);

        int databaseSizeBeforeUpdate = nextOfKinRepository.findAll().size();

        // Update the nextOfKin using partial update
        NextOfKin partialUpdatedNextOfKin = new NextOfKin();
        partialUpdatedNextOfKin.setId(nextOfKin.getId());

        partialUpdatedNextOfKin
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .regDocType(UPDATED_REG_DOC_TYPE)
            .registrationDocumentNumber(UPDATED_REGISTRATION_DOCUMENT_NUMBER)
            .gender(UPDATED_GENDER)
            .deleted(UPDATED_DELETED);

        restNextOfKinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNextOfKin.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNextOfKin))
            )
            .andExpect(status().isOk());

        // Validate the NextOfKin in the database
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeUpdate);
        NextOfKin testNextOfKin = nextOfKinList.get(nextOfKinList.size() - 1);
        assertThat(testNextOfKin.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testNextOfKin.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testNextOfKin.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testNextOfKin.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testNextOfKin.getRegDocType()).isEqualTo(UPDATED_REG_DOC_TYPE);
        assertThat(testNextOfKin.getRegistrationDocumentNumber()).isEqualTo(UPDATED_REGISTRATION_DOCUMENT_NUMBER);
        assertThat(testNextOfKin.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testNextOfKin.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testNextOfKin.getDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testNextOfKin.getWxtJwtPq55wd()).isEqualTo(DEFAULT_WXT_JWT_PQ_55_WD);
        assertThat(testNextOfKin.getRelation()).isEqualTo(DEFAULT_RELATION);
    }

    @Test
    @Transactional
    void fullUpdateNextOfKinWithPatch() throws Exception {
        // Initialize the database
        nextOfKinRepository.saveAndFlush(nextOfKin);

        int databaseSizeBeforeUpdate = nextOfKinRepository.findAll().size();

        // Update the nextOfKin using partial update
        NextOfKin partialUpdatedNextOfKin = new NextOfKin();
        partialUpdatedNextOfKin.setId(nextOfKin.getId());

        partialUpdatedNextOfKin
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .regDocType(UPDATED_REG_DOC_TYPE)
            .registrationDocumentNumber(UPDATED_REGISTRATION_DOCUMENT_NUMBER)
            .gender(UPDATED_GENDER)
            .nationality(UPDATED_NATIONALITY)
            .deleted(UPDATED_DELETED)
            .wxtJwtPq55wd(UPDATED_WXT_JWT_PQ_55_WD)
            .relation(UPDATED_RELATION);

        restNextOfKinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNextOfKin.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNextOfKin))
            )
            .andExpect(status().isOk());

        // Validate the NextOfKin in the database
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeUpdate);
        NextOfKin testNextOfKin = nextOfKinList.get(nextOfKinList.size() - 1);
        assertThat(testNextOfKin.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testNextOfKin.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testNextOfKin.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testNextOfKin.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testNextOfKin.getRegDocType()).isEqualTo(UPDATED_REG_DOC_TYPE);
        assertThat(testNextOfKin.getRegistrationDocumentNumber()).isEqualTo(UPDATED_REGISTRATION_DOCUMENT_NUMBER);
        assertThat(testNextOfKin.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testNextOfKin.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testNextOfKin.getDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testNextOfKin.getWxtJwtPq55wd()).isEqualTo(UPDATED_WXT_JWT_PQ_55_WD);
        assertThat(testNextOfKin.getRelation()).isEqualTo(UPDATED_RELATION);
    }

    @Test
    @Transactional
    void patchNonExistingNextOfKin() throws Exception {
        int databaseSizeBeforeUpdate = nextOfKinRepository.findAll().size();
        nextOfKin.setId(count.incrementAndGet());

        // Create the NextOfKin
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNextOfKinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, nextOfKinDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NextOfKin in the database
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNextOfKin() throws Exception {
        int databaseSizeBeforeUpdate = nextOfKinRepository.findAll().size();
        nextOfKin.setId(count.incrementAndGet());

        // Create the NextOfKin
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNextOfKinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NextOfKin in the database
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNextOfKin() throws Exception {
        int databaseSizeBeforeUpdate = nextOfKinRepository.findAll().size();
        nextOfKin.setId(count.incrementAndGet());

        // Create the NextOfKin
        NextOfKinDTO nextOfKinDTO = nextOfKinMapper.toDto(nextOfKin);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNextOfKinMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(nextOfKinDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NextOfKin in the database
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNextOfKin() throws Exception {
        // Initialize the database
        nextOfKinRepository.saveAndFlush(nextOfKin);

        int databaseSizeBeforeDelete = nextOfKinRepository.findAll().size();

        // Delete the nextOfKin
        restNextOfKinMockMvc
            .perform(delete(ENTITY_API_URL_ID, nextOfKin.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NextOfKin> nextOfKinList = nextOfKinRepository.findAll();
        assertThat(nextOfKinList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
