import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './next-of-kin.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INextOfKinDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NextOfKinDetail = (props: INextOfKinDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { nextOfKinEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="nextOfKinDetailsHeading">
          <Translate contentKey="changsoftSisApp.nextOfKin.detail.title">NextOfKin</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{nextOfKinEntity.id}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="changsoftSisApp.nextOfKin.firstName">First Name</Translate>
            </span>
          </dt>
          <dd>{nextOfKinEntity.firstName}</dd>
          <dt>
            <span id="middleName">
              <Translate contentKey="changsoftSisApp.nextOfKin.middleName">Middle Name</Translate>
            </span>
          </dt>
          <dd>{nextOfKinEntity.middleName}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="changsoftSisApp.nextOfKin.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{nextOfKinEntity.lastName}</dd>
          <dt>
            <span id="dateOfBirth">
              <Translate contentKey="changsoftSisApp.nextOfKin.dateOfBirth">Date Of Birth</Translate>
            </span>
          </dt>
          <dd>
            {nextOfKinEntity.dateOfBirth ? <TextFormat value={nextOfKinEntity.dateOfBirth} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="regDocType">
              <Translate contentKey="changsoftSisApp.nextOfKin.regDocType">Reg Doc Type</Translate>
            </span>
          </dt>
          <dd>{nextOfKinEntity.regDocType}</dd>
          <dt>
            <span id="registrationDocumentNumber">
              <Translate contentKey="changsoftSisApp.nextOfKin.registrationDocumentNumber">Registration Document Number</Translate>
            </span>
          </dt>
          <dd>{nextOfKinEntity.registrationDocumentNumber}</dd>
          <dt>
            <span id="gender">
              <Translate contentKey="changsoftSisApp.nextOfKin.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{nextOfKinEntity.gender}</dd>
          <dt>
            <span id="nationality">
              <Translate contentKey="changsoftSisApp.nextOfKin.nationality">Nationality</Translate>
            </span>
          </dt>
          <dd>{nextOfKinEntity.nationality}</dd>
          <dt>
            <span id="deleted">
              <Translate contentKey="changsoftSisApp.nextOfKin.deleted">Deleted</Translate>
            </span>
          </dt>
          <dd>{nextOfKinEntity.deleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="wxtJwtPq55wd">
              <Translate contentKey="changsoftSisApp.nextOfKin.wxtJwtPq55wd">Wxt Jwt Pq 55 Wd</Translate>
            </span>
          </dt>
          <dd>{nextOfKinEntity.wxtJwtPq55wd}</dd>
          <dt>
            <span id="relation">
              <Translate contentKey="changsoftSisApp.nextOfKin.relation">Relation</Translate>
            </span>
          </dt>
          <dd>{nextOfKinEntity.relation}</dd>
        </dl>
        <Button tag={Link} to="/next-of-kin" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/next-of-kin/${nextOfKinEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ nextOfKin }: IRootState) => ({
  nextOfKinEntity: nextOfKin.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NextOfKinDetail);
