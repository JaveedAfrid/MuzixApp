context('Muzix E2E testing', () => {
    beforeEach(() => {
        cy.visit('/dashboard');
    });

    it('Should check for register', () => {
        cy.get("#btnRegister").click();
        cy.get("#fname").type("first");
        cy.get("#lname").type("last");
        cy.get("#email").type("first@gmail.com");
        cy.get("#pass").type("abc123");
        cy.get("#pass1").type("abc123");
        cy.get("#btnRegister").click();
        cy.wait(1000);
        cy.visit('/');
        cy.get("#login").should("have.text", "Login Here");
    });

    it('Should check for login', () => {
        cy.get("#username").type("first@gmail.com");
        cy.get("#password").type("abc123");
        cy.get("#btnLogin").click();
        cy.get("#appName").should("have.text", "MUZIX");
    })

    it('Should check for profile', () => {
        cy.get("#username").type("first@gmail.com");
        cy.get("#password").type("abc123");
        cy.get("#btnLogin").click();
        cy.visit('/profile');
        cy.get("#profile").should("have.text", "My Profile");
    })

    it('Should check for edit profile', () => {
        cy.get("#username").type("first@gmail.com");
        cy.get("#password").type("abc123");
        cy.get("#btnLogin").click();
        cy.visit('/profile');
        cy.get("#editProfile").click();
        cy.get("#fname").clear();
        cy.get("#fname").type("tom");
        cy.get("#lname").clear();
        cy.get("#lname").type("jerry");
        cy.get("#gender").click();
        cy.get("#female").click();
        cy.get("#phone").clear();
        cy.get("#phone").type("5555555");
        cy.get("#dob").clear();
        cy.get("#dob").type("2000-12-12");
        cy.get("#age").clear();
        cy.get("#age").type("22");
        cy.get("#updateBtn").click();
        cy.get("#profile").should("have.text", "My Profile");
        cy.get("#fname").should("have.value", "tom");
    })

    it('Should check for dashboard', () => {
        cy.get("#username").type("first@gmail.com");
        cy.get("#password").type("abc123");
        cy.get("#btnLogin").click();
        cy.get("#album").should("have.text", "ALBUMS");
        cy.get("#playlist").should("have.text", "PLAYLIST");
        cy.get("#tracks").should("have.text", "TRACKS");     
    })

    it('Should check for favourites', () => {
        cy.get("#username").type("first@gmail.com");
        cy.get("#password").type("abc123");
        cy.get("#btnLogin").click();
        cy.visit('/favourites');
        cy.get("#fav").should("have.text", "FAVOURITES");
    })

    it('Should check for recommendations', () => {
        cy.get("#username").type("first@gmail.com");
        cy.get("#password").type("abc123");
        cy.get("#btnLogin").click();
        cy.visit('/recommend');
        cy.get("#recommend").should("have.text", "TOP 10 RECOMMENDED TRACKS");
    })

    it('Should check for search', () => {
        cy.get("#username").type("first@gmail.com");
        cy.get("#password").type("abc123");
        cy.get("#btnLogin").click();
        cy.get("#search").type("24k gold");
        cy.get("#searchbtn").click();
        cy.get("#searchTitle").should("have.text", "ALBUMS");
    })

    it('Should check for logout', () => {
        cy.get("#username").type("first@gmail.com");
        cy.get("#password").type("abc123");
        cy.get("#btnLogin").click();
        cy.get("#logout").click();
        cy.get("#login").should("have.text", "Login Here");
    })

});