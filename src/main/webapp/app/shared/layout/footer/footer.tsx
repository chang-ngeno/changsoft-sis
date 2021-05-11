import './footer.scss';

import React from 'react';
import { Translate } from 'react-jhipster';
import { Col, Row } from 'reactstrap';
import { currentYear } from 'app/shared/util/date-utils';

const Footer = props => (
  <div className="footer page-content">
    <Row>
      <Col sm="12" md="3">
        <p>
          <Translate contentKey="footer-col1">Your footer</Translate>
        </p>
      </Col>
      <Col sm="12" md="3">
        <p>
          <Translate contentKey="footer-col2">Your footer</Translate>
        </p>
      </Col>
      <Col sm="12" md="3">
        <p>
          <Translate contentKey="footer-col3">Your footer</Translate>
        </p>
      </Col>
      <Col sm="12" md="3">
        <p>
          <Translate contentKey="footer-col4">Your footer</Translate>
        </p>
      </Col>
    </Row>
    <Row>
      <Col sm="12" className="d-flex justify-content-center">
        <p>The site was created and is maintained by Macoz Technologies &copy; {currentYear()}</p>
      </Col>
    </Row>
  </div>
);

export default Footer;
