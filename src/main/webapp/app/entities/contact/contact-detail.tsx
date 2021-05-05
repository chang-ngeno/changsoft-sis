import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './contact.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IContactDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ContactDetail = (props: IContactDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { contactEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contactDetailsHeading">
          <Translate contentKey="changsoftSisApp.contact.detail.title">Contact</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{contactEntity.id}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="changsoftSisApp.contact.email">Email</Translate>
            </span>
          </dt>
          <dd>{contactEntity.email}</dd>
          <dt>
            <span id="mobileNumber">
              <Translate contentKey="changsoftSisApp.contact.mobileNumber">Mobile Number</Translate>
            </span>
          </dt>
          <dd>{contactEntity.mobileNumber}</dd>
          <dt>
            <span id="deleted">
              <Translate contentKey="changsoftSisApp.contact.deleted">Deleted</Translate>
            </span>
          </dt>
          <dd>{contactEntity.deleted ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="changsoftSisApp.contact.employee">Employee</Translate>
          </dt>
          <dd>{contactEntity.employee ? contactEntity.employee.staffSysNo : ''}</dd>
          <dt>
            <Translate contentKey="changsoftSisApp.contact.nextOfKin">Next Of Kin</Translate>
          </dt>
          <dd>{contactEntity.nextOfKin ? contactEntity.nextOfKin.registrationDocumentNumber : ''}</dd>
          <dt>
            <Translate contentKey="changsoftSisApp.contact.student">Student</Translate>
          </dt>
          <dd>{contactEntity.student ? contactEntity.student.studentRegNumber : ''}</dd>
        </dl>
        <Button tag={Link} to="/contact" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contact/${contactEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ contact }: IRootState) => ({
  contactEntity: contact.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ContactDetail);
