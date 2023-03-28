import React from 'react';
import { render, screen } from '@testing-library/react';
import { render as renderer, unmountComponentAtNode } from 'react-dom';
import Register from './Register'
import Login from './Login'
import Profile from './Profile'
import EditProfile from './EditProfile'
import ChangePassword from './ChangePassword'

describe('Testing Track component', () => {
    let element;

    beforeEach(() => {
        element = document.createElement('div');
        document.body.appendChild(element);
    });

    afterEach(() => {
        unmountComponentAtNode(element);
        element.remove();
        element = null;
    });

    test('should have text Sign Up', () => {
        render(<Register/>);
        expect(screen.getByTestId('headerId')).toHaveTextContent('Sign Up');
    });

    test('should have button Sign Up in register component', () => {
        render(<Register/>);
        expect(screen.getByTestId('btnLogin')).toHaveTextContent('Sign Up');
    });

    test('should have text Login Here in login component', () => {
        render(<Login/>);
        expect(screen.getByTestId('headerId')).toHaveTextContent('Login Here');
    });

    test('should have one link tag in login component', () => {
        
        renderer(<Login/>, element);
        const count = document.getElementsByTagName('a').length;
        expect(count).toBe(1);
    });
    
    test('should have one img tag in profile component', () => {
        
        renderer(<Profile/>, element);
        const count = document.getElementsByTagName('img').length;
        expect(count).toBe(1);
    });

    test('should have text Edit Profile in Profile component', () => {
        render(<Profile />);
        expect(screen.getByTestId('edit')).toHaveTextContent('Edit Profile');
    });

    test('should have one img tag in EditProfile component', () => {
        
        renderer(<EditProfile/>, element);
        const count = document.getElementsByTagName('img').length;
        expect(count).toBe(1);
    });

    test('should have text Change Password in ChangePassword component', () => {
        render(<ChangePassword />);
        expect(screen.getByTestId('headerId')).toHaveTextContent('Change Password');
    });


})