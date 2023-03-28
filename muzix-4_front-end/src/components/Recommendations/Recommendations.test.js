import React from 'react';
import { render, screen } from '@testing-library/react';
import { render as renderer, unmountComponentAtNode } from 'react-dom';
import Recommendations from './Recommendations';
import DisplayRecommendations from './DisplayRecommendations';


describe('Testing Recommendation component', () => {
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

    test('should have title TOP 10 RECOMMENDED TRACKS', () => {
        render(<Recommendations/>);
        expect(screen.getByTestId('headerId')).toHaveTextContent('TOP 10 RECOMMENDED TRACKS');
    });

    test('should have class container', () => {
        render(<Recommendations/>);
        expect(screen.getByTestId('containerClass')).toHaveClass('container');
    });

    test('should have class card inside display recommendations', () => {
        render(<DisplayRecommendations/>);
        expect(screen.getByTestId('cardTag')).toHaveClass('card');
    });

    test('should have class audio in display recommendations', () => {
        render(<DisplayRecommendations/>);
        expect(screen.getByTestId('audioId')).toHaveClass('audio');
    });

    test('should have atleast one image tag in display recommendations', () => {
        renderer(<DisplayRecommendations/>, element);
        const count = document.getElementsByTagName('img').length;
        expect(count).toBe(1);
    });

})
