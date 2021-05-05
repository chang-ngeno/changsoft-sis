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
import { getEntity, updateEntity, createEntity, reset } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmployeeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EmployeeUpdate = (props: IEmployeeUpdateProps) => {
  const [idsrelative, setIdsrelative] = useState([]);
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { employeeEntity, nextOfKins, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/employee' + props.location.search);
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
        ...employeeEntity,
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
          <h2 id="changsoftSisApp.employee.home.createOrEditLabel" data-cy="EmployeeCreateUpdateHeading">
            <Translate contentKey="changsoftSisApp.employee.home.createOrEditLabel">Create or edit a Employee</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : employeeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="employee-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="employee-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="firstNameLabel" for="employee-firstName">
                  <Translate contentKey="changsoftSisApp.employee.firstName">First Name</Translate>
                </Label>
                <AvField
                  id="employee-firstName"
                  data-cy="firstName"
                  type="text"
                  name="firstName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="middleNameLabel" for="employee-middleName">
                  <Translate contentKey="changsoftSisApp.employee.middleName">Middle Name</Translate>
                </Label>
                <AvField
                  id="employee-middleName"
                  data-cy="middleName"
                  type="text"
                  name="middleName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameLabel" for="employee-lastName">
                  <Translate contentKey="changsoftSisApp.employee.lastName">Last Name</Translate>
                </Label>
                <AvField
                  id="employee-lastName"
                  data-cy="lastName"
                  type="text"
                  name="lastName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="studentRegNumberLabel" for="employee-studentRegNumber">
                  <Translate contentKey="changsoftSisApp.employee.studentRegNumber">Student Reg Number</Translate>
                </Label>
                <AvField
                  id="employee-studentRegNumber"
                  data-cy="studentRegNumber"
                  type="text"
                  name="studentRegNumber"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateOfBirthLabel" for="employee-dateOfBirth">
                  <Translate contentKey="changsoftSisApp.employee.dateOfBirth">Date Of Birth</Translate>
                </Label>
                <AvInput
                  id="employee-dateOfBirth"
                  data-cy="dateOfBirth"
                  type="datetime-local"
                  className="form-control"
                  name="dateOfBirth"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.employeeEntity.dateOfBirth)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="staffSysNoLabel" for="employee-staffSysNo">
                  <Translate contentKey="changsoftSisApp.employee.staffSysNo">Staff Sys No</Translate>
                </Label>
                <AvField
                  id="employee-staffSysNo"
                  data-cy="staffSysNo"
                  type="text"
                  name="staffSysNo"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="regDocTypeLabel" for="employee-regDocType">
                  <Translate contentKey="changsoftSisApp.employee.regDocType">Reg Doc Type</Translate>
                </Label>
                <AvInput
                  id="employee-regDocType"
                  data-cy="regDocType"
                  type="select"
                  className="form-control"
                  name="regDocType"
                  value={(!isNew && employeeEntity.regDocType) || 'PASSPORT'}
                >
                  <option value="PASSPORT">{translate('changsoftSisApp.RegistrationDocumentType.PASSPORT')}</option>
                  <option value="NATIONAL_ID">{translate('changsoftSisApp.RegistrationDocumentType.NATIONAL_ID')}</option>
                  <option value="HUDUMA">{translate('changsoftSisApp.RegistrationDocumentType.HUDUMA')}</option>
                  <option value="BIRTH_CERTIFICATE">{translate('changsoftSisApp.RegistrationDocumentType.BIRTH_CERTIFICATE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="registrationDocumentNumberLabel" for="employee-registrationDocumentNumber">
                  <Translate contentKey="changsoftSisApp.employee.registrationDocumentNumber">Registration Document Number</Translate>
                </Label>
                <AvField
                  id="employee-registrationDocumentNumber"
                  data-cy="registrationDocumentNumber"
                  type="text"
                  name="registrationDocumentNumber"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="genderLabel" for="employee-gender">
                  <Translate contentKey="changsoftSisApp.employee.gender">Gender</Translate>
                </Label>
                <AvInput
                  id="employee-gender"
                  data-cy="gender"
                  type="select"
                  className="form-control"
                  name="gender"
                  value={(!isNew && employeeEntity.gender) || 'MALE'}
                >
                  <option value="MALE">{translate('changsoftSisApp.Gender.MALE')}</option>
                  <option value="FEMALE">{translate('changsoftSisApp.Gender.FEMALE')}</option>
                  <option value="OTHER">{translate('changsoftSisApp.Gender.OTHER')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="nationalityLabel" for="employee-nationality">
                  <Translate contentKey="changsoftSisApp.employee.nationality">Nationality</Translate>
                </Label>
                <AvField id="employee-nationality" data-cy="nationality" type="text" name="nationality" />
              </AvGroup>
              <AvGroup>
                <Label id="dateJoinedLabel" for="employee-dateJoined">
                  <Translate contentKey="changsoftSisApp.employee.dateJoined">Date Joined</Translate>
                </Label>
                <AvInput
                  id="employee-dateJoined"
                  data-cy="dateJoined"
                  type="datetime-local"
                  className="form-control"
                  name="dateJoined"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.employeeEntity.dateJoined)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup check>
                <Label id="deletedLabel">
                  <AvInput id="employee-deleted" data-cy="deleted" type="checkbox" className="form-check-input" name="deleted" />
                  <Translate contentKey="changsoftSisApp.employee.deleted">Deleted</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="wxtJwtPq55wdLabel" for="employee-wxtJwtPq55wd">
                  <Translate contentKey="changsoftSisApp.employee.wxtJwtPq55wd">Wxt Jwt Pq 55 Wd</Translate>
                </Label>
                <AvField id="employee-wxtJwtPq55wd" data-cy="wxtJwtPq55wd" type="text" name="wxtJwtPq55wd" />
              </AvGroup>
              <AvGroup>
                <Label for="employee-relative">
                  <Translate contentKey="changsoftSisApp.employee.relative">Relative</Translate>
                </Label>
                <AvInput
                  id="employee-relative"
                  data-cy="relative"
                  type="select"
                  multiple
                  className="form-control"
                  name="relatives"
                  value={!isNew && employeeEntity.relatives && employeeEntity.relatives.map(e => e.id)}
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
              <Button tag={Link} id="cancel-save" to="/employee" replace color="info">
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
  employeeEntity: storeState.employee.entity,
  loading: storeState.employee.loading,
  updating: storeState.employee.updating,
  updateSuccess: storeState.employee.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(EmployeeUpdate);
