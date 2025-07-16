class RecipeList extends HTMLElement {
    constructor() {
        super();
        this.recipes = [];
        this.pagination = null;
        this.loading = false;
        this.currentFilters = {}; // Keep track of current filters between pages
    }

    connectedCallback() {
        this.render();
    }

    async loadRecipes(filters = {}, pagination = {}) {
        this.currentFilters = filters;
        this.loading = true;
        this.renderLoading();

        try {
            const result = await fetchRecipes(filters, pagination);

            if (result.success) {
                // result.data is your Page<RecipeOutputDTO> from Spring
                this.recipes = result.data.data.content; // The actual recipes array
                this.pagination = {
                    totalPages: result.data.data.totalPages,
                    totalElements: result.data.data.totalElements,
                    number: result.data.data.number,
                    size: result.data.data.size,
                    first: result.data.data.first,
                    last: result.data.data.last
                };
                this.renderRecipes();
            } else {
                this.renderError(result.error);
            }
        } catch (error) {
            this.renderError(error.message);
        } finally {
            this.loading = false;
        }
    }

    render() {
        this.innerHTML = `
            <div class="recipe-list-container">
                <div class="recipe-grid" id="recipe-grid">
                    <!-- Recipe cards will be inserted here -->
                </div>
                
                <div class="pagination-container" id="pagination-container">
                    <!-- Pagination will be inserted here -->
                </div>
            </div>
        `;
    }

    renderLoading() {
        const grid = this.querySelector('#recipe-grid');
        grid.innerHTML = `
            <div class="loading-state">
                <div class="spinner"></div>
                <p>Chargement des recettes...</p>
            </div>
        `;
    }

    renderRecipes() {
        const grid = this.querySelector('#recipe-grid');

        grid.innerHTML = '';

        this.recipes.forEach(recipeData => {
            const recipeCard = document.createElement('recipe-card-small');
            recipeCard.setRecipe(recipeData);
            grid.appendChild(recipeCard);
        });

        this.renderPagination();
    }

    renderPagination() {
        const container = this.querySelector('#pagination-container');

        if (this.pagination.totalPages <= 1) {
            container.innerHTML = '';
            return;
        }

        let paginationHTML = '<div class="pagination">';

        if (!this.pagination.first) {
            paginationHTML += `
                <button class="page-btn" onclick="this.closest('recipe-list').goToPage(${this.pagination.number - 1})">
                    ← Précédent
                </button>
            `;
        }

        const currentPage = this.pagination.number;
        const totalPages = this.pagination.totalPages;

        for (let i = Math.max(0, currentPage - 2); i <= Math.min(totalPages - 1, currentPage + 2); i++) {
            const isActive = i === currentPage;
            paginationHTML += `
                <button class="page-btn ${isActive ? 'active' : ''}" 
                        onclick="this.closest('recipe-list').goToPage(${i})">
                    ${i + 1}
                </button>
            `;
        }

        if (!this.pagination.last) {
            paginationHTML += `
                <button class="page-btn" onclick="this.closest('recipe-list').goToPage(${this.pagination.number + 1})">
                    Suivant →
                </button>
            `;
        }

        paginationHTML += '</div>';
        container.innerHTML = paginationHTML;
    }

    renderError(errorMessage) {
        const grid = this.querySelector('#recipe-grid');
        grid.innerHTML = `
            <div class="error-state">
                <p>Erreur lors du chargement des recettes</p>
                <p class="error-details">${errorMessage}</p>
                <button onclick="this.closest('recipe-list').retryLoad()" class="retry-btn">
                    Réessayer
                </button>
            </div>
        `;
    }

    retryLoad() {
        void this.loadRecipes(this.currentFilters, { page: 0 });
    }

    goToPage(pageNumber) {
        void this.loadRecipes(this.currentFilters, { page: pageNumber });
    }
}

customElements.define('recipe-list', RecipeList);