import React from 'react';
import { render, screen } from '@testing-library/react';
import { render as renderer, unmountComponentAtNode } from 'react-dom';
import Albums from './Album'
import DisplayAlbum from './DisplayAlbum'
import AlbumTracks from './AlbumTracks'
import DisplayAtracks from './DisplayAtracks';

describe('Testing Album component', () => {
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

    test('should have text ALBUMS', () => {
        render(<Albums/>);
        expect(screen.getByTestId('headerId')).toHaveTextContent('ALBUMS');
    });

    test('should have class card inside display Album', () => {
        render(<DisplayAlbum/>);
        expect(screen.getByTestId('cardTag')).toHaveClass('card');
    });

    test('should have one link tag in display album', () => {
        renderer(<DisplayAlbum/>, element);
        const count = document.getElementsByTagName('a').length;
        expect(count).toBe(1);
    });


    test('should have class card inside display search track', () => {
        render(<DisplayAtracks/>);
        expect(screen.getByTestId('cardTag')).toHaveClass('card');
    });

    test('should have class audio in display search track', () => {
        render(<DisplayAtracks/>);
        expect(screen.getByTestId('audioId')).toHaveClass('audio');
    });

})