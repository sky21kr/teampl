import React from 'react';
import { Redirect, Route } from 'react-router-dom';

function PrivateRoute({ component: Component, ...rest }) {
  return (
    <Route
      {...rest}
      render={props =>
        window.sessionStorage.getItem('token') ? (
          <Component {...props} />
        ) : (
          <Redirect to="/log-in" />
        )
      }
    />
  );
}

export default PrivateRoute;