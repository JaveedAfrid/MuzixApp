import React from 'react';
import Error from '../../images/Error.gif'

const NotFound = () => {
    return (
        <div className="text-center">
            <img src={Error} alt="" />
        </div>
    );
};

export default NotFound;