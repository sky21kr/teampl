import React from 'react';
import './Footer.scss'
import { useHistory } from "react-router-dom";

const Footer = ({}) => {
    let history = useHistory();

    return (
        <div className="footer">
            <img className="logo" src="/images/logo.svg" onClick={() => history.push('/')}/>
        </div>
    )
}

export default Footer;