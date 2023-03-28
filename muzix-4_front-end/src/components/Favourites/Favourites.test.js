import React from 'react';
import { render, screen } from '@testing-library/react';
import { render as renderer, unmountComponentAtNode } from 'react-dom';
import DisplayFavourites from './DisplayFavourites';
import Favourites from './Favourites';

describe('Testing Favoutite component', () => {
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

    test('should have title FAVOURITES', () => {
        render(<Favourites/>);
        expect(screen.getByTestId('headerId')).toHaveTextContent('FAVOURITES');
    });

    test('should have class card inside display favourites', () => {
       
        render(<DisplayFavourites/>);
        expect(screen.getByTestId('cardTag')).toHaveClass('card');
    });

    test('should have atleast one image tag in display favourites', () => {
        renderer(<DisplayFavourites/>, element);
        const count = document.getElementsByTagName('img').length;
        expect(count).toBe(1);
    });

    test('should have class audio in display favourites', () => {
        render(<DisplayFavourites/>);
        expect(screen.getByTestId('audioId')).toHaveClass('audio');
    });
})