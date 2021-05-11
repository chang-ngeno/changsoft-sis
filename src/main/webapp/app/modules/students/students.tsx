import './students.scss';

import React from 'react';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col } from 'reactstrap';

export type IStudentsProp = StateProps;

export const Students = (props: IStudentsProp) => {
  const { message } = props;

  return (
    <Row>
      <Col md="1" />
      <Col md="10">
        <h6 className="text-right">{message}</h6>
        <h1 className="text-capitalize text-center">
          <Translate contentKey="students.title">Title</Translate>
        </h1>
        <h5 className="text-center">
          <Translate contentKey="students.subtitle">Subtitle</Translate>
        </h5>
        <hr />
        <Row>
          <Col sm="5">
            <span className="text-center">Add Student</span>
          </Col>
          <Col sm="5">
            <span className="text-center">Edit Student</span>
          </Col>
          <Col sm="5">
            <span className="text-center">Allocate Student</span>
          </Col>
          <Col sm="5">
            <span className="text-center">De-Register Student</span>
          </Col>
        </Row>
      </Col>
      <Col md="1" />
    </Row>
  );
};

const mapStateToProps = storeState => ({
  message: storeState.message,
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(Students);
