import React from 'react';
import { render, screen } from '@testing-library/react';
import { render as renderer, unmountComponentAtNode } from 'react-dom';
import Search from './Search';
import AppContext from '../../AppContext';
import DisplaySearchAlbums from './DisplaySearchAlbums';
import SearchAlbumTracks from './SearchAlbumTracks';
import DisplaySearchedTracks from './DisplaySearchedTracks';


describe('Testing Search component', () => {
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


    test('should have class card inside display search album', () => {
        render(<DisplaySearchAlbums />);
        expect(screen.getByTestId('cardTag')).toHaveClass('card');
    });

    test('should have one link tag in display search album', () => {
        renderer(<DisplaySearchAlbums />, element);
        const count = document.getElementsByTagName('a').length;
        expect(count).toBe(1);
    });

    test('should have text TRACK LIST', () => {
        render(<SearchAlbumTracks />);
        expect(screen.getByTestId('headerId')).toHaveTextContent('TRACK LIST');
    });

    test('should have class card inside display search track', () => {
        render(<DisplaySearchedTracks />);
        expect(screen.getByTestId('cardTag')).toHaveClass('card');
    });

    test('should have class audio in display search track', () => {
        render(<DisplaySearchedTracks />);
        expect(screen.getByTestId('audioId')).toHaveClass('audio');
    });


})