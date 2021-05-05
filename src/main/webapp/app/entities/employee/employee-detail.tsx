import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './employee.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmployeeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EmployeeDetail = (props: IEmployeeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { employeeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="employeeDetailsHeading">
          <Translate contentKey="changsoftSisApp.employee.detail.title">Employee</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.id}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="changsoftSisApp.employee.firstName">First Name</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.firstName}</dd>
          <dt>
            <span id="middleName">
              <Translate contentKey="changsoftSisApp.employee.middleName">Middle Name</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.middleName}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="changsoftSisApp.employee.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.lastName}</dd>
          <dt>
            <span id="studentRegNumber">
              <Translate contentKey="changsoftSisApp.employee.studentRegNumber">Student Reg Number</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.studentRegNumber}</dd>
          <dt>
            <span id="dateOfBirth">
              <Translate contentKey="changsoftSisApp.employee.dateOfBirth">Date Of Birth</Translate>
            </span>
          </dt>
          <dd>
            {employeeEntity.dateOfBirth ? <TextFormat value={employeeEntity.dateOfBirth} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="staffSysNo">
              <Translate contentKey="changsoftSisApp.employee.staffSysNo">Staff Sys No</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.staffSysNo}</dd>
          <dt>
            <span id="regDocType">
              <Translate contentKey="changsoftSisApp.employee.regDocType">Reg Doc Type</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.regDocType}</dd>
          <dt>
            <span id="registrationDocumentNumber">
              <Translate contentKey="changsoftSisApp.employee.registrationDocumentNumber">Registration Document Number</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.registrationDocumentNumber}</dd>
          <dt>
            <span id="gender">
              <Translate contentKey="changsoftSisApp.employee.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.gender}</dd>
          <dt>
            <span id="nationality">
              <Translate contentKey="changsoftSisApp.employee.nationality">Nationality</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.nationality}</dd>
          <dt>
            <span id="dateJoined">
              <Translate contentKey="changsoftSisApp.employee.dateJoined">Date Joined</Translate>
            </span>
          </dt>
          <dd>
            {employeeEntity.dateJoined ? <TextFormat value={employeeEntity.dateJoined} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="deleted">
              <Translate contentKey="changsoftSisApp.employee.deleted">Deleted</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.deleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="wxtJwtPq55wd">
              <Translate contentKey="changsoftSisApp.employee.wxtJwtPq55wd">Wxt Jwt Pq 55 Wd</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.wxtJwtPq55wd}</dd>
          <dt>
            <Translate contentKey="changsoftSisApp.employee.relative">Relative</Translate>
          </dt>
          <dd>
            {employeeEntity.relatives
              ? employeeEntity.relatives.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {employeeEntity.relatives && i === employeeEntity.relatives.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/employee" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/employee/${employeeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ employee }: IRootState) => ({
  employeeEntity: employee.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EmployeeDetail);
