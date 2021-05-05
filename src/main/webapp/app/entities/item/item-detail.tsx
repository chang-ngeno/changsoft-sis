import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './item.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ItemDetail = (props: IItemDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { itemEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="itemDetailsHeading">
          <Translate contentKey="changsoftSisApp.item.detail.title">Item</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{itemEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="changsoftSisApp.item.name">Name</Translate>
            </span>
          </dt>
          <dd>{itemEntity.name}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="changsoftSisApp.item.code">Code</Translate>
            </span>
          </dt>
          <dd>{itemEntity.code}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="changsoftSisApp.item.description">Description</Translate>
            </span>
          </dt>
          <dd>{itemEntity.description}</dd>
          <dt>
            <span id="dateSince">
              <Translate contentKey="changsoftSisApp.item.dateSince">Date Since</Translate>
            </span>
          </dt>
          <dd>{itemEntity.dateSince ? <TextFormat value={itemEntity.dateSince} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="dateUpto">
              <Translate contentKey="changsoftSisApp.item.dateUpto">Date Upto</Translate>
            </span>
          </dt>
          <dd>{itemEntity.dateUpto ? <TextFormat value={itemEntity.dateUpto} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="deleted">
              <Translate contentKey="changsoftSisApp.item.deleted">Deleted</Translate>
            </span>
          </dt>
          <dd>{itemEntity.deleted ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="changsoftSisApp.item.category">Category</Translate>
          </dt>
          <dd>{itemEntity.category ? itemEntity.category.name : ''}</dd>
        </dl>
        <Button tag={Link} to="/item" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/item/${itemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ item }: IRootState) => ({
  itemEntity: item.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ItemDetail);
