import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { getEntity, updateEntity, createEntity, reset } from './next-of-kin.reducer';
import { INextOfKin } from 'app/shared/model/next-of-kin.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INextOfKinUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NextOfKinUpdate = (props: INextOfKinUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { nextOfKinEntity, employees, students, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/next-of-kin' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getEmployees();
    props.getStudents();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateOfBirth = convertDateTimeToServer(values.dateOfBirth);

    if (errors.length === 0) {
      const entity = {
        ...nextOfKinEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="changsoftSisApp.nextOfKin.home.createOrEditLabel" data-cy="NextOfKinCreateUpdateHeading">
            <Translate contentKey="changsoftSisApp.nextOfKin.home.createOrEditLabel">Create or edit a NextOfKin</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : nextOfKinEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="next-of-kin-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="next-of-kin-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="firstNameLabel" for="next-of-kin-firstName">
                  <Translate contentKey="changsoftSisApp.nextOfKin.firstName">First Name</Translate>
                </Label>
                <AvField
                  id="next-of-kin-firstName"
                  data-cy="firstName"
                  type="text"
                  name="firstName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="middleNameLabel" for="next-of-kin-middleName">
                  <Translate contentKey="changsoftSisApp.nextOfKin.middleName">Middle Name</Translate>
                </Label>
                <AvField
                  id="next-of-kin-middleName"
                  data-cy="middleName"
                  type="text"
                  name="middleName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameLabel" for="next-of-kin-lastName">
                  <Translate contentKey="changsoftSisApp.nextOfKin.lastName">Last Name</Translate>
                </Label>
                <AvField
                  id="next-of-kin-lastName"
                  data-cy="lastName"
                  type="text"
                  name="lastName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateOfBirthLabel" for="next-of-kin-dateOfBirth">
                  <Translate contentKey="changsoftSisApp.nextOfKin.dateOfBirth">Date Of Birth</Translate>
                </Label>
                <AvInput
                  id="next-of-kin-dateOfBirth"
                  data-cy="dateOfBirth"
                  type="datetime-local"
                  className="form-control"
                  name="dateOfBirth"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.nextOfKinEntity.dateOfBirth)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="regDocTypeLabel" for="next-of-kin-regDocType">
                  <Translate contentKey="changsoftSisApp.nextOfKin.regDocType">Reg Doc Type</Translate>
                </Label>
                <AvInput
                  id="next-of-kin-regDocType"
                  data-cy="regDocType"
                  type="select"
                  className="form-control"
                  name="regDocType"
                  value={(!isNew && nextOfKinEntity.regDocType) || 'PASSPORT'}
                >
                  <option value="PASSPORT">{translate('changsoftSisApp.RegistrationDocumentType.PASSPORT')}</option>
                  <option value="NATIONAL_ID">{translate('changsoftSisApp.RegistrationDocumentType.NATIONAL_ID')}</option>
                  <option value="HUDUMA">{translate('changsoftSisApp.RegistrationDocumentType.HUDUMA')}</option>
                  <option value="BIRTH_CERTIFICATE">{translate('changsoftSisApp.RegistrationDocumentType.BIRTH_CERTIFICATE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="registrationDocumentNumberLabel" for="next-of-kin-registrationDocumentNumber">
                  <Translate contentKey="changsoftSisApp.nextOfKin.registrationDocumentNumber">Registration Document Number</Translate>
                </Label>
                <AvField
                  id="next-of-kin-registrationDocumentNumber"
                  data-cy="registrationDocumentNumber"
                  type="text"
                  name="registrationDocumentNumber"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="genderLabel" for="next-of-kin-gender">
                  <Translate contentKey="changsoftSisApp.nextOfKin.gender">Gender</Translate>
                </Label>
                <AvInput
                  id="next-of-kin-gender"
                  data-cy="gender"
                  type="select"
                  className="form-control"
                  name="gender"
                  value={(!isNew && nextOfKinEntity.gender) || 'MALE'}
                >
                  <option value="MALE">{translate('changsoftSisApp.Gender.MALE')}</option>
                  <option value="FEMALE">{translate('changsoftSisApp.Gender.FEMALE')}</option>
                  <option value="OTHER">{translate('changsoftSisApp.Gender.OTHER')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="nationalityLabel" for="next-of-kin-nationality">
                  <Translate contentKey="changsoftSisApp.nextOfKin.nationality">Nationality</Translate>
                </Label>
                <AvField id="next-of-kin-nationality" data-cy="nationality" type="text" name="nationality" />
              </AvGroup>
              <AvGroup check>
                <Label id="deletedLabel">
                  <AvInput id="next-of-kin-deleted" data-cy="deleted" type="checkbox" className="form-check-input" name="deleted" />
                  <Translate contentKey="changsoftSisApp.nextOfKin.deleted">Deleted</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="wxtJwtPq55wdLabel" for="next-of-kin-wxtJwtPq55wd">
                  <Translate contentKey="changsoftSisApp.nextOfKin.wxtJwtPq55wd">Wxt Jwt Pq 55 Wd</Translate>
                </Label>
                <AvField id="next-of-kin-wxtJwtPq55wd" data-cy="wxtJwtPq55wd" type="text" name="wxtJwtPq55wd" />
              </AvGroup>
              <AvGroup>
                <Label id="relationLabel" for="next-of-kin-relation">
                  <Translate contentKey="changsoftSisApp.nextOfKin.relation">Relation</Translate>
                </Label>
                <AvField
                  id="next-of-kin-relation"
                  data-cy="relation"
                  type="text"
                  name="relation"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/next-of-kin" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  employees: storeState.employee.entities,
  students: storeState.student.entities,
  nextOfKinEntity: storeState.nextOfKin.entity,
  loading: storeState.nextOfKin.loading,
  updating: storeState.nextOfKin.updating,
  updateSuccess: storeState.nextOfKin.updateSuccess,
});

const mapDispatchToProps = {
  getEmployees,
  getStudents,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NextOfKinUpdate);
