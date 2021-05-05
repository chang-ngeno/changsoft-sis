import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INextOfKin } from 'app/shared/model/next-of-kin.model';
import { getEntities as getNextOfKins } from 'app/entities/next-of-kin/next-of-kin.reducer';
import { getEntity, updateEntity, createEntity, reset } from './student.reducer';
import { IStudent } from 'app/shared/model/student.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStudentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const StudentUpdate = (props: IStudentUpdateProps) => {
  const [idsrelative, setIdsrelative] = useState([]);
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { studentEntity, nextOfKins, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/student' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getNextOfKins();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateOfBirth = convertDateTimeToServer(values.dateOfBirth);
    values.dateJoined = convertDateTimeToServer(values.dateJoined);

    if (errors.length === 0) {
      const entity = {
        ...studentEntity,
        ...values,
        relatives: mapIdList(values.relatives),
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
          <h2 id="changsoftSisApp.student.home.createOrEditLabel" data-cy="StudentCreateUpdateHeading">
            <Translate contentKey="changsoftSisApp.student.home.createOrEditLabel">Create or edit a Student</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : studentEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="student-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="student-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="firstNameLabel" for="student-firstName">
                  <Translate contentKey="changsoftSisApp.student.firstName">First Name</Translate>
                </Label>
                <AvField
                  id="student-firstName"
                  data-cy="firstName"
                  type="text"
                  name="firstName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="middleNameLabel" for="student-middleName">
                  <Translate contentKey="changsoftSisApp.student.middleName">Middle Name</Translate>
                </Label>
                <AvField
                  id="student-middleName"
                  data-cy="middleName"
                  type="text"
                  name="middleName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameLabel" for="student-lastName">
                  <Translate contentKey="changsoftSisApp.student.lastName">Last Name</Translate>
                </Label>
                <AvField
                  id="student-lastName"
                  data-cy="lastName"
                  type="text"
                  name="lastName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="studentRegNumberLabel" for="student-studentRegNumber">
                  <Translate contentKey="changsoftSisApp.student.studentRegNumber">Student Reg Number</Translate>
                </Label>
                <AvField
                  id="student-studentRegNumber"
                  data-cy="studentRegNumber"
                  type="text"
                  name="studentRegNumber"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateOfBirthLabel" for="student-dateOfBirth">
                  <Translate contentKey="changsoftSisApp.student.dateOfBirth">Date Of Birth</Translate>
                </Label>
                <AvInput
                  id="student-dateOfBirth"
                  data-cy="dateOfBirth"
                  type="datetime-local"
                  className="form-control"
                  name="dateOfBirth"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.studentEntity.dateOfBirth)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="regDocTypeLabel" for="student-regDocType">
                  <Translate contentKey="changsoftSisApp.student.regDocType">Reg Doc Type</Translate>
                </Label>
                <AvInput
                  id="student-regDocType"
                  data-cy="regDocType"
                  type="select"
                  className="form-control"
                  name="regDocType"
                  value={(!isNew && studentEntity.regDocType) || 'PASSPORT'}
                >
                  <option value="PASSPORT">{translate('changsoftSisApp.RegistrationDocumentType.PASSPORT')}</option>
                  <option value="NATIONAL_ID">{translate('changsoftSisApp.RegistrationDocumentType.NATIONAL_ID')}</option>
                  <option value="HUDUMA">{translate('changsoftSisApp.RegistrationDocumentType.HUDUMA')}</option>
                  <option value="BIRTH_CERTIFICATE">{translate('changsoftSisApp.RegistrationDocumentType.BIRTH_CERTIFICATE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="registrationDocumentNumberLabel" for="student-registrationDocumentNumber">
                  <Translate contentKey="changsoftSisApp.student.registrationDocumentNumber">Registration Document Number</Translate>
                </Label>
                <AvField
                  id="student-registrationDocumentNumber"
                  data-cy="registrationDocumentNumber"
                  type="text"
                  name="registrationDocumentNumber"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="genderLabel" for="student-gender">
                  <Translate contentKey="changsoftSisApp.student.gender">Gender</Translate>
                </Label>
                <AvInput
                  id="student-gender"
                  data-cy="gender"
                  type="select"
                  className="form-control"
                  name="gender"
                  value={(!isNew && studentEntity.gender) || 'MALE'}
                >
                  <option value="MALE">{translate('changsoftSisApp.Gender.MALE')}</option>
                  <option value="FEMALE">{translate('changsoftSisApp.Gender.FEMALE')}</option>
                  <option value="OTHER">{translate('changsoftSisApp.Gender.OTHER')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="nationalityLabel" for="student-nationality">
                  <Translate contentKey="changsoftSisApp.student.nationality">Nationality</Translate>
                </Label>
                <AvField id="student-nationality" data-cy="nationality" type="text" name="nationality" />
              </AvGroup>
              <AvGroup>
                <Label id="dateJoinedLabel" for="student-dateJoined">
                  <Translate contentKey="changsoftSisApp.student.dateJoined">Date Joined</Translate>
                </Label>
                <AvInput
                  id="student-dateJoined"
                  data-cy="dateJoined"
                  type="datetime-local"
                  className="form-control"
                  name="dateJoined"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.studentEntity.dateJoined)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup check>
                <Label id="deletedLabel">
                  <AvInput id="student-deleted" data-cy="deleted" type="checkbox" className="form-check-input" name="deleted" />
                  <Translate contentKey="changsoftSisApp.student.deleted">Deleted</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="wxtJwtPq55wdLabel" for="student-wxtJwtPq55wd">
                  <Translate contentKey="changsoftSisApp.student.wxtJwtPq55wd">Wxt Jwt Pq 55 Wd</Translate>
                </Label>
                <AvField id="student-wxtJwtPq55wd" data-cy="wxtJwtPq55wd" type="text" name="wxtJwtPq55wd" />
              </AvGroup>
              <AvGroup>
                <Label for="student-relative">
                  <Translate contentKey="changsoftSisApp.student.relative">Relative</Translate>
                </Label>
                <AvInput
                  id="student-relative"
                  data-cy="relative"
                  type="select"
                  multiple
                  className="form-control"
                  name="relatives"
                  value={!isNew && studentEntity.relatives && studentEntity.relatives.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {nextOfKins
                    ? nextOfKins.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/student" replace color="info">
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
  nextOfKins: storeState.nextOfKin.entities,
  studentEntity: storeState.student.entity,
  loading: storeState.student.loading,
  updating: storeState.student.updating,
  updateSuccess: storeState.student.updateSuccess,
});

const mapDispatchToProps = {
  getNextOfKins,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StudentUpdate);
