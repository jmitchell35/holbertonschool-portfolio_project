class AppHeader extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
            <header class="main-header">
                <div class="hero-section">
                </div>
                <div class="header-content">
                    <div class="main-bar">
                        <nav class="main-nav">
                            <a href="/">Nos recettes</a>
                        </nav>
                        <div class="auth-bar">
                            <a href="/register.html">S'enregistrer</a>
                            <a href="/login.html">Se connecter</a>
                        
                        </div>
                    </div>
            </header>
            
            <style>
                .main-header {
                    .main-header {
                    position: relative;
                }
                
                .hero-section {
                    background-image: url('/assets/images/background_cropped.png');
                    
                    /* Sizing */
                    background-size: cover;        /* Scale to fill entire container */
                    background-position: center;   /* Center the image */
                    background-repeat: no-repeat;  /* Don't tile the image */
                    
                    background-color: rgba(0, 0, 0, 0.4);
                    
                    min-height: 200px;              /* Minimum height for content */
                    padding: 2rem 0;                /* Space for navigation */
                    
                    display: flex;
                    align-items: center;
                    justify-content: center;
                }
                
                .header-content {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    padding: 0 2rem;
                    color: white;
                }
                
                .main-nav {
                    display: flex;
                    gap: 2rem;  /* Space between nav links */
                }
                
                .main-nav a {
                    color: #001A78;
                    text-decoration: none;
                    font-size: 1.2rem;
                    font-weight: 500;
                    padding: 0.5rem 1rem;
                    border-radius: 4px;
                    transition: background-color 0.3s ease;
                }
                
                .main-nav a:hover {
                    background-color: rgba(255, 255, 255, 0.2);
                }
            </style>
        `;
    }
}

class AppFavicon extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
        <link rel="icon" href="/assets/images/favicon.png" type="image/png" >`
    }
}


customElements.define('app-favicon', AppFavicon);
customElements.define('app-header', AppHeader);