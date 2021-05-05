import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './address.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAddressDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AddressDetail = (props: IAddressDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { addressEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="addressDetailsHeading">
          <Translate contentKey="changsoftSisApp.address.detail.title">Address</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{addressEntity.id}</dd>
          <dt>
            <span id="houseNumber">
              <Translate contentKey="changsoftSisApp.address.houseNumber">House Number</Translate>
            </span>
          </dt>
          <dd>{addressEntity.houseNumber}</dd>
          <dt>
            <span id="streetAddress">
              <Translate contentKey="changsoftSisApp.address.streetAddress">Street Address</Translate>
            </span>
          </dt>
          <dd>{addressEntity.streetAddress}</dd>
          <dt>
            <span id="county">
              <Translate contentKey="changsoftSisApp.address.county">County</Translate>
            </span>
          </dt>
          <dd>{addressEntity.county}</dd>
          <dt>
            <span id="district">
              <Translate contentKey="changsoftSisApp.address.district">District</Translate>
            </span>
          </dt>
          <dd>{addressEntity.district}</dd>
          <dt>
            <span id="cityTown">
              <Translate contentKey="changsoftSisApp.address.cityTown">City Town</Translate>
            </span>
          </dt>
          <dd>{addressEntity.cityTown}</dd>
          <dt>
            <span id="postalCode">
              <Translate contentKey="changsoftSisApp.address.postalCode">Postal Code</Translate>
            </span>
          </dt>
          <dd>{addressEntity.postalCode}</dd>
          <dt>
            <span id="deleted">
              <Translate contentKey="changsoftSisApp.address.deleted">Deleted</Translate>
            </span>
          </dt>
          <dd>{addressEntity.deleted ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="changsoftSisApp.address.student">Student</Translate>
          </dt>
          <dd>{addressEntity.student ? addressEntity.student.studentRegNumber : ''}</dd>
          <dt>
            <Translate contentKey="changsoftSisApp.address.employee">Employee</Translate>
          </dt>
          <dd>{addressEntity.employee ? addressEntity.employee.staffSysNo : ''}</dd>
          <dt>
            <Translate contentKey="changsoftSisApp.address.nextOfKin">Next Of Kin</Translate>
          </dt>
          <dd>{addressEntity.nextOfKin ? addressEntity.nextOfKin.firstName : ''}</dd>
        </dl>
        <Button tag={Link} to="/address" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/address/${addressEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ address }: IRootState) => ({
  addressEntity: address.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AddressDetail);
