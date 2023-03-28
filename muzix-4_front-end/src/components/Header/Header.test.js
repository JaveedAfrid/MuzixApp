import { render, screen } from '@testing-library/react';
import { render as renderer, unmountComponentAtNode } from 'react-dom';
import Header from './Header'
import AppContext from '../../AppContext';
import React from 'react'

describe('Testing Header Component', () => {
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

    test('Should have text musix next to logo symbol', () => {

        render(
            <AppContext.Provider value={false}>
                <Header />
            </AppContext.Provider>
        );
        expect(screen.getByText('MUZIX')).toBeInTheDocument();
    });

    test('Should have 7 links in header component', () => {

        renderer(<AppContext.Provider value={false}>
            <Header />
        </AppContext.Provider>, element);
        const count = element.getElementsByTagName('a').length;
        expect(count).toBe(7);
    });

    test('should have navbar class', () => {
       
        render( <AppContext.Provider value={false}>
            <Header />
        </AppContext.Provider>);
        expect(screen.getByTestId('header')).toHaveClass('navbar');
    });

    test('Hyperlinks should have nav-link class', () => {
        renderer(<AppContext.Provider value={false}>
            <Header />
        </AppContext.Provider>, element);
        const links = element.getElementsByTagName('a');
        for (let i = 1; i < links.length; i++) {
            expect(links[i]).toHaveClass('nav-link');
        }
    });

    test('should have toggle-button', () => {
        render(
            <AppContext.Provider value={false}>
                <Header />
            </AppContext.Provider>
        );
       
        expect(screen.getByTestId('toggleBtn')).toHaveClass('navbar-toggler');
    });

})