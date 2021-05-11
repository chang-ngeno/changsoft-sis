import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react';
import { Translate } from 'react-jhipster';
import { Alert } from 'reactstrap';

class PageNotFound extends React.Component {
  render() {
    return (
      <div>
        <Alert color="danger">
          <Translate contentKey="error.http.404">
            The page does not exist.
            <span>
              <FontAwesomeIcon icon="tools" />
            </span>
          </Translate>
        </Alert>
      </div>
    );
  }
}

export default PageNotFound;
