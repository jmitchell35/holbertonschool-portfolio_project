class RecipeCardSmall extends HTMLElement {
    constructor() {
        super();
        this.recipe = null;
    }

    // Called from the RecipeList
    setRecipe(recipeData) {
        this.recipe = new Recipe(recipeData);
        this.render();
    }

    render() {
        if (!this.recipe) return;
        console.log('Recipe data:', this.recipes);

        const thumbnailHtml = this.recipe.thumbnailUrl ?
            `<img src="${this.recipe.thumbnailUrl}" alt="${this.recipe.name}" class="recipe-thumbnail" onerror="this.style.display='none'">`
            : '';

        this.innerHTML = `
            <article class="recipe-card" data-recipe-id="${this.recipe.id}">
             ${thumbnailHtml}
                <div class="recipe-header">
                    <h3 class="recipe-title">${this.recipe.name}</h3>
                    <div class="recipe-meta">
                        <span class="prep-time">⏱️ ${this.recipe.getFormattedPrepTime()}</span>
                        <span class="servings">👥 ${this.recipe.servings} portions</span>
                        <span class="ingredients">🥗 ${this.recipe.getIngredientCount()} ingrédients</span>
                    </div>
                </div>
                
                <div class="recipe-tags">
                    ${this.renderTags()}
                </div>
                
                <div class="recipe-ingredients-preview">
                    ${this.renderIngredientsPreview()}
                </div>
                
                <div class="recipe-actions">
                    <button class="btn-view" onclick="this.closest('recipe-card-small').viewRecipe()">
                        Voir la recette
                    </button>
                </div>
            </article>
        `;
    }

    renderTags() {
        return this.recipe.tags.map(tag => `
            <span class="tag" 
                  style="background-color: ${tag.backgroundColorHex}; color: ${tag.fontColorHex};">
                ${tag.name}
            </span>
        `).join('');
    }

    renderIngredientsPreview() {
        const preview = this.recipe.recipeIngredients.slice(0, 3);
        const remaining = this.recipe.recipeIngredients.length - 3;

        let html = preview.map(ri => `
            <span class="ingredient-preview">
                ${ri.quantity} ${ri.unit.name} ${ri.ingredient.ingredientSingular}
            </span>
        `).join('');

        if (remaining > 0) {
            html += `<span class="ingredient-more">+${remaining} autres...</span>`;
        }

        return html;
    }

    viewRecipe() {
        window.location.href = `/recipe.html?id=${this.recipe.id}`;
    }
}

customElements.define('recipe-card-small', RecipeCardSmall);