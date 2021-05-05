import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { setFileData, byteSize, Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICategory } from 'app/shared/model/category.model';
import { getEntities as getCategories } from 'app/entities/category/category.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './item.reducer';
import { IItem } from 'app/shared/model/item.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IItemUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ItemUpdate = (props: IItemUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { itemEntity, categories, loading, updating } = props;

  const { description } = itemEntity;

  const handleClose = () => {
    props.history.push('/item' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCategories();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateSince = convertDateTimeToServer(values.dateSince);
    values.dateUpto = convertDateTimeToServer(values.dateUpto);

    if (errors.length === 0) {
      const entity = {
        ...itemEntity,
        ...values,
        category: categories.find(it => it.id.toString() === values.categoryId.toString()),
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
          <h2 id="changsoftSisApp.item.home.createOrEditLabel" data-cy="ItemCreateUpdateHeading">
            <Translate contentKey="changsoftSisApp.item.home.createOrEditLabel">Create or edit a Item</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : itemEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="item-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="item-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="item-name">
                  <Translate contentKey="changsoftSisApp.item.name">Name</Translate>
                </Label>
                <AvField
                  id="item-name"
                  data-cy="name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="codeLabel" for="item-code">
                  <Translate contentKey="changsoftSisApp.item.code">Code</Translate>
                </Label>
                <AvField
                  id="item-code"
                  data-cy="code"
                  type="text"
                  name="code"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="item-description">
                  <Translate contentKey="changsoftSisApp.item.description">Description</Translate>
                </Label>
                <AvInput id="item-description" data-cy="description" type="textarea" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="dateSinceLabel" for="item-dateSince">
                  <Translate contentKey="changsoftSisApp.item.dateSince">Date Since</Translate>
                </Label>
                <AvInput
                  id="item-dateSince"
                  data-cy="dateSince"
                  type="datetime-local"
                  className="form-control"
                  name="dateSince"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.itemEntity.dateSince)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateUptoLabel" for="item-dateUpto">
                  <Translate contentKey="changsoftSisApp.item.dateUpto">Date Upto</Translate>
                </Label>
                <AvInput
                  id="item-dateUpto"
                  data-cy="dateUpto"
                  type="datetime-local"
                  className="form-control"
                  name="dateUpto"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.itemEntity.dateUpto)}
                />
              </AvGroup>
              <AvGroup check>
                <Label id="deletedLabel">
                  <AvInput id="item-deleted" data-cy="deleted" type="checkbox" className="form-check-input" name="deleted" />
                  <Translate contentKey="changsoftSisApp.item.deleted">Deleted</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="item-category">
                  <Translate contentKey="changsoftSisApp.item.category">Category</Translate>
                </Label>
                <AvInput id="item-category" data-cy="category" type="select" className="form-control" name="categoryId">
                  <option value="" key="0" />
                  {categories
                    ? categories.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/item" replace color="info">
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
  categories: storeState.category.entities,
  itemEntity: storeState.item.entity,
  loading: storeState.item.loading,
  updating: storeState.item.updating,
  updateSuccess: storeState.item.updateSuccess,
});

const mapDispatchToProps = {
  getCategories,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ItemUpdate);
