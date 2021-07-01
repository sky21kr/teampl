import React from 'react';
import { Redirect, Route } from 'react-router-dom';
import Cookies from 'js-cookie';

function PrivateRoute({ component: Component, ...rest }) {
  return (
    <Route
      {...rest}
      render={props =>
        window.sessionStorage.getItem('token') ? (
          <Component {...props} />
        ) : (
          <Redirect to="/" />
        )
      }
    />
  );
}

export default PrivateRoute;