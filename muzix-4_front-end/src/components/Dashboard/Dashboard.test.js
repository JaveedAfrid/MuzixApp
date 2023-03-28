import React from 'react';
import { render, screen } from '@testing-library/react';
import { render as renderer, unmountComponentAtNode } from 'react-dom';
import Dashboard from './Dashboard';


describe('Testing Dashboard component', () => {
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

    test('should have class carousel', () => {
        render(<Dashboard/>);
        expect(screen.getByTestId('carouselExampleFade')).toHaveClass('carousel');
    });

    test('should have three image tag', () => {
        renderer(<Dashboard/>, element);
        const count = document.getElementsByTagName('img').length;
        expect(count).toBe(3);
    });

    test('should have content previous', () => {
        render(<Dashboard/>);
        expect(screen.getByTestId('pre')).toHaveTextContent('Previous');
    });

    test('should have content next', () => {
        render(<Dashboard/>);
        expect(screen.getByTestId('next')).toHaveTextContent('Next');
    });

})