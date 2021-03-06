import React from 'react';
import { Translate } from 'react-jhipster';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import appConfig from 'app/config/constants';

export const BrandIcon = props => (
  <div {...props} className="brand-icon">
    <img src="content/images/logo-jhipster.png" alt="Logo" />
  </div>
);

export const Brand = props => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <BrandIcon />
    <span className="brand-title">
      <Translate contentKey="global.title">ChangsoftSIS</Translate>
    </span>
    <span className="navbar-version">{appConfig.VERSION}</span>
  </NavbarBrand>
);

export const Students = props => (
  <NavItem>
    <NavLink tag={Link} to="/students" className="d-flex align-items-center">
      <FontAwesomeIcon icon="hand-spock" />
      <span>
        <Translate contentKey="global.menu.students">Students</Translate>
      </span>
    </NavLink>
  </NavItem>
);
export const AboutUs = props => (
  <NavItem>
    <NavLink tag={Link} to="/about-us" className="d-flex align-items-center">
      <FontAwesomeIcon icon="hand-spock" />
      <span>
        <Translate contentKey="global.menu.aboutUs">About Us</Translate>
      </span>
    </NavLink>
  </NavItem>
);
export const Home = props => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span>
        <Translate contentKey="global.menu.home">Home</Translate>
      </span>
    </NavLink>
  </NavItem>
);
