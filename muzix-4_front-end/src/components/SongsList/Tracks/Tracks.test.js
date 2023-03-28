import React from 'react';
import { render, screen } from '@testing-library/react';
import { render as renderer, unmountComponentAtNode } from 'react-dom';
import Tracks from './Tracks';
import DisplayTracks from './DisplayTracks';

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

    test('should have text TRACKS', () => {
        render(<Tracks/>);
        expect(screen.getByTestId('headerId')).toHaveTextContent('TRACKS');
    });

    test('should have class card inside display tracks', () => {
        render(<DisplayTracks/>);
        expect(screen.getByTestId('cardTag')).toHaveClass('card');
    });

    test('should have class audio in display track', () => {
        render(<DisplayTracks/>);
        expect(screen.getByTestId('audioId')).toHaveClass('audio');
    });

    test('should have one img tag in display tracks', () => {
        
        renderer(<DisplayTracks/>, element);
        const count = document.getElementsByTagName('img').length;
        expect(count).toBe(1);
    });


})