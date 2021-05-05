import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { INextOfKin } from 'app/shared/model/next-of-kin.model';
import { getEntities as getNextOfKins } from 'app/entities/next-of-kin/next-of-kin.reducer';
import { getEntity, updateEntity, createEntity, reset } from './address.reducer';
import { IAddress } from 'app/shared/model/address.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAddressUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AddressUpdate = (props: IAddressUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { addressEntity, students, employees, nextOfKins, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/address' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getStudents();
    props.getEmployees();
    props.getNextOfKins();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...addressEntity,
        ...values,
        student: students.find(it => it.id.toString() === values.studentId.toString()),
        employee: employees.find(it => it.id.toString() === values.employeeId.toString()),
        nextOfKin: nextOfKins.find(it => it.id.toString() === values.nextOfKinId.toString()),
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
          <h2 id="changsoftSisApp.address.home.createOrEditLabel" data-cy="AddressCreateUpdateHeading">
            <Translate contentKey="changsoftSisApp.address.home.createOrEditLabel">Create or edit a Address</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : addressEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="address-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="address-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="houseNumberLabel" for="address-houseNumber">
                  <Translate contentKey="changsoftSisApp.address.houseNumber">House Number</Translate>
                </Label>
                <AvField id="address-houseNumber" data-cy="houseNumber" type="text" name="houseNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="streetAddressLabel" for="address-streetAddress">
                  <Translate contentKey="changsoftSisApp.address.streetAddress">Street Address</Translate>
                </Label>
                <AvField id="address-streetAddress" data-cy="streetAddress" type="text" name="streetAddress" />
              </AvGroup>
              <AvGroup>
                <Label id="countyLabel" for="address-county">
                  <Translate contentKey="changsoftSisApp.address.county">County</Translate>
                </Label>
                <AvField
                  id="address-county"
                  data-cy="county"
                  type="text"
                  name="county"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="districtLabel" for="address-district">
                  <Translate contentKey="changsoftSisApp.address.district">District</Translate>
                </Label>
                <AvField
                  id="address-district"
                  data-cy="district"
                  type="text"
                  name="district"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="cityTownLabel" for="address-cityTown">
                  <Translate contentKey="changsoftSisApp.address.cityTown">City Town</Translate>
                </Label>
                <AvField
                  id="address-cityTown"
                  data-cy="cityTown"
                  type="text"
                  name="cityTown"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="postalCodeLabel" for="address-postalCode">
                  <Translate contentKey="changsoftSisApp.address.postalCode">Postal Code</Translate>
                </Label>
                <AvField id="address-postalCode" data-cy="postalCode" type="string" className="form-control" name="postalCode" />
              </AvGroup>
              <AvGroup check>
                <Label id="deletedLabel">
                  <AvInput id="address-deleted" data-cy="deleted" type="checkbox" className="form-check-input" name="deleted" />
                  <Translate contentKey="changsoftSisApp.address.deleted">Deleted</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="address-student">
                  <Translate contentKey="changsoftSisApp.address.student">Student</Translate>
                </Label>
                <AvInput id="address-student" data-cy="student" type="select" className="form-control" name="studentId">
                  <option value="" key="0" />
                  {students
                    ? students.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.studentRegNumber}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="address-employee">
                  <Translate contentKey="changsoftSisApp.address.employee">Employee</Translate>
                </Label>
                <AvInput id="address-employee" data-cy="employee" type="select" className="form-control" name="employeeId">
                  <option value="" key="0" />
                  {employees
                    ? employees.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.staffSysNo}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="address-nextOfKin">
                  <Translate contentKey="changsoftSisApp.address.nextOfKin">Next Of Kin</Translate>
                </Label>
                <AvInput id="address-nextOfKin" data-cy="nextOfKin" type="select" className="form-control" name="nextOfKinId">
                  <option value="" key="0" />
                  {nextOfKins
                    ? nextOfKins.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.firstName}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/address" replace color="info">
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
  students: storeState.student.entities,
  employees: storeState.employee.entities,
  nextOfKins: storeState.nextOfKin.entities,
  addressEntity: storeState.address.entity,
  loading: storeState.address.loading,
  updating: storeState.address.updating,
  updateSuccess: storeState.address.updateSuccess,
});

const mapDispatchToProps = {
  getStudents,
  getEmployees,
  getNextOfKins,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AddressUpdate);
