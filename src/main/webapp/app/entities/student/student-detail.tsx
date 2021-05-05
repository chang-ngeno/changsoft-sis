import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './student.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const StudentDetail = (props: IStudentDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { studentEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="studentDetailsHeading">
          <Translate contentKey="changsoftSisApp.student.detail.title">Student</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{studentEntity.id}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="changsoftSisApp.student.firstName">First Name</Translate>
            </span>
          </dt>
          <dd>{studentEntity.firstName}</dd>
          <dt>
            <span id="middleName">
              <Translate contentKey="changsoftSisApp.student.middleName">Middle Name</Translate>
            </span>
          </dt>
          <dd>{studentEntity.middleName}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="changsoftSisApp.student.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{studentEntity.lastName}</dd>
          <dt>
            <span id="studentRegNumber">
              <Translate contentKey="changsoftSisApp.student.studentRegNumber">Student Reg Number</Translate>
            </span>
          </dt>
          <dd>{studentEntity.studentRegNumber}</dd>
          <dt>
            <span id="dateOfBirth">
              <Translate contentKey="changsoftSisApp.student.dateOfBirth">Date Of Birth</Translate>
            </span>
          </dt>
          <dd>
            {studentEntity.dateOfBirth ? <TextFormat value={studentEntity.dateOfBirth} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="regDocType">
              <Translate contentKey="changsoftSisApp.student.regDocType">Reg Doc Type</Translate>
            </span>
          </dt>
          <dd>{studentEntity.regDocType}</dd>
          <dt>
            <span id="registrationDocumentNumber">
              <Translate contentKey="changsoftSisApp.student.registrationDocumentNumber">Registration Document Number</Translate>
            </span>
          </dt>
          <dd>{studentEntity.registrationDocumentNumber}</dd>
          <dt>
            <span id="gender">
              <Translate contentKey="changsoftSisApp.student.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{studentEntity.gender}</dd>
          <dt>
            <span id="nationality">
              <Translate contentKey="changsoftSisApp.student.nationality">Nationality</Translate>
            </span>
          </dt>
          <dd>{studentEntity.nationality}</dd>
          <dt>
            <span id="dateJoined">
              <Translate contentKey="changsoftSisApp.student.dateJoined">Date Joined</Translate>
            </span>
          </dt>
          <dd>{studentEntity.dateJoined ? <TextFormat value={studentEntity.dateJoined} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="deleted">
              <Translate contentKey="changsoftSisApp.student.deleted">Deleted</Translate>
            </span>
          </dt>
          <dd>{studentEntity.deleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="wxtJwtPq55wd">
              <Translate contentKey="changsoftSisApp.student.wxtJwtPq55wd">Wxt Jwt Pq 55 Wd</Translate>
            </span>
          </dt>
          <dd>{studentEntity.wxtJwtPq55wd}</dd>
          <dt>
            <Translate contentKey="changsoftSisApp.student.relative">Relative</Translate>
          </dt>
          <dd>
            {studentEntity.relatives
              ? studentEntity.relatives.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {studentEntity.relatives && i === studentEntity.relatives.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/student" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/student/${studentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ student }: IRootState) => ({
  studentEntity: student.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StudentDetail);
