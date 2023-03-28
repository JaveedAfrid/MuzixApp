import React from 'react';
import { render, screen } from '@testing-library/react';
import { render as renderer, unmountComponentAtNode } from 'react-dom';
import Playlist from './Playlist';
import DisplayPlaylist from './DisplayPlaylist';
import DisplayPtracks from './DisplayPtracks';


describe('Testing Playlist component', () => {
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

    test('should have text PLAYLIST', () => {
        render(<Playlist/>);
        expect(screen.getByTestId('headerId')).toHaveTextContent('PLAYLIST');
    });

    test('should have class card inside display playlist', () => {
        render(<DisplayPlaylist/>);
        expect(screen.getByTestId('cardTag')).toHaveClass('card');
    });

    test('should have one link tag in display playlist', () => {
        
        renderer(<DisplayPlaylist/>, element);
        const count = document.getElementsByTagName('a').length;
        expect(count).toBe(1);
    });


    test('should have class card inside display playlist track', () => {
        render(<DisplayPtracks/>);
        expect(screen.getByTestId('cardTag')).toHaveClass('card');
    });

    test('should have class audio in display playlist track', () => {
        render(<DisplayPtracks/>);
        expect(screen.getByTestId('audioId')).toHaveClass('audio');
    });

})