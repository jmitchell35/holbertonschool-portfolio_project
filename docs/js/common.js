class AppHeader extends HTMLElement {
    constructor() {
        super();
        this.isAuthenticated = false;
        this.userInfo = null;
        this.loading = false;
    }

    // Called when an element is added to the DOM
    connectedCallback() {
        this.render();
        void this.validateAuth();
    }

    render() {
        this.innerHTML = `
            <header class="main-header">
                <div class="hero-section"></div>
                    <div class="header-content">
                       <div class="main-bar">
                           <nav class="main-nav">
                               <a href="/">Nos recettes</a>
                               ${this.renderNavigationLinks()}
                           </nav>
                           <div class="auth-bar">
                               ${this.renderAuthSection()}
                           </div>
                       </div>
                    </div>
            </header>
        `;
    }

    renderNavigationLinks() {
        // Only show "Publier une recette" if authenticated
        if (this.isAuthenticated) {
            return `
                <a href="/postrecipe.html">Publier une recette</a>
            `;
        }
        return ''; // No extra nav links for guests
    }

    renderAuthSection() {
        // Show loading state while validating
        if (this.loading) {
            return `
                <div class="auth-loading">
                    <span>Vérification...</span>
                </div>
            `;
        }

        // Show authenticated user UI
        if (this.isAuthenticated && this.userInfo) {
            return `
                <div class="user-info">
                    <span class="welcome-text">Bonjour ${this.userInfo.username || 'Utilisateur'}</span>
                    <button class="logout-btn" onclick="this.closest('app-header').logout()">
                        Se déconnecter
                    </button>
                </div>
            `;
        }

        // Show authenticated but no user info yet (during validation)
        if (this.isAuthenticated && !this.userInfo) {
            return `
                <div class="user-info">
                    <span class="welcome-text">Connecté</span>
                    <button class="logout-btn" onclick="this.closest('app-header').logout()">
                        Se déconnecter
                    </button>
                </div>
            `;
        }

        // Show guest/unauthenticated UI
        return `
            <div class="guest-links">
                <a href="/register.html" class="auth-link register-link">S'enregistrer</a>
                <a href="/login.html" class="auth-link login-link">Connexion</a>
            </div>
        `;
    }

    // Server-side check of valid session
    async validateAuth() {
        this.loading = true;
        this.render();

        try {
            this.isAuthenticated = await AuthService.isAuthenticated();
            if (this.isAuthenticated) {
                const data = await AuthService.getCurrentUser();
                this.userInfo = data.data;
            } else {
                this.userInfo = null;
            }
        } catch (error) {
            console.error('Auth check failed:', error);
            this.isAuthenticated = false;
            this.userInfo = null;
        } finally {
            this.loading = false;
            this.render();
        }
    }

    async logout() {
        try {
            const response = await fetch('/api/v1/auth/logout', {
                method: 'POST',
                credentials: 'include'
            });

            if (response.ok) {
                this.isAuthenticated = false;
                this.userInfo = null;
                this.render();
                // Optionally redirect
                window.location.href = '/';
            }
        } catch (error) {
            console.error('Logout failed:', error);
        }
    }
}

class AppFavicon extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
        <link rel="icon" href="/favicon.ico" type="image/x-icon" >`
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

class AuthService {
    static async getCurrentUser() {
        const response = await fetch('/api/v1/auth/me', { credentials: 'include' });
        if (response.ok) {
            return await response.json();
        }
        throw new Error('Authentication failed');
    }

    static async isAuthenticated() {
        try {
            const data = await this.getCurrentUser();
            return data.data.authenticated;
        } catch {
            return false;
        }
    }
}

window.AuthService = AuthService;

customElements.define('app-favicon', AppFavicon);
customElements.define('app-header', AppHeader);
customElements.define('app-footer', AppFooter);