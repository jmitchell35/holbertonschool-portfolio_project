class AppHeader extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
            <header class="main-header">
                <div class="hero-section"></div>
                    <div class="header-content">
                       <div class="main-bar">
                           <nav class="main-nav">
                               <a href="/">Nos recettes</a>
                               <a href="/postrecipe.html">Publier une recette</a>
                           </nav>
                           <div class="auth-bar">
                               <a href="/register.html">S'enregistrer</a>
                               <a href="/login.html">Se connecter</a>
                           </div>
                       </div>
            </header>
        `;
    }
}

class AppFavicon extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
        <link rel="icon" href="/assets/images/favicon.png" type="image/png" >`
    }
}

class AppFooter extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
            <footer class="main-footer">
                <span>© 2025  BonAppEatIt - all rights reserved</span>
            </footer>
        `;
    }
}


customElements.define('app-favicon', AppFavicon);
customElements.define('app-header', AppHeader);
customElements.define('app-footer', AppFooter);