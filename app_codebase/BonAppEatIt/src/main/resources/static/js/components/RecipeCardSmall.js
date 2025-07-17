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

        const thumbnailHtml = this.recipe.thumbnailUrl ?
            `<img src="${this.recipe.thumbnailUrl}" alt="${this.recipe.name}" class="recipe-thumbnail">`
            : '';

        console.log(this.recipe.recipeIngredients);

        this.innerHTML = `
            <div class="card">
              <div class="content">
                <div class="back">
                  <div class="back-content">
                    ${thumbnailHtml}
                  </div>
                </div>
                <div class="front">
                  
                  <div class="img">
                    <div class="circle">
                    </div>
                    <div class="circle" id="right">
                    </div>
                    <div class="circle" id="bottom">
                    </div>
                  </div>
            
                  <div class="front-content" style="--bg-image: url('${this.recipe.thumbnailUrl}')">
                    <div class="description">
                        <div class="card-description-header">
                            <div class="prepTime">
                                <i class="fa-solid fa-clock"></i>
                                <span>${this.recipe.getFormattedPrepTime()}</span>
                            </div>
                            <div class="prepTime">
                                <i class="fa-solid fa-user"></i>
                                <span>${this.recipe.servings} portion(s)</span>
                            </div>
                        </div>
                        <div class="card-recipe-ingredients">
                        <h3>${this.recipe.name}</h3>
                            ${this.recipe.recipeIngredients
                                .map(ing => {
                                    const quantity = ing.quantity;
                                    const unit = ing.unit?.name || '';
                                    const name = quantity > 1
                                        ? ing.ingredient.ingredientPlural
                                        : ing.ingredient.ingredientSingular;
                                    return `<li>${name} : ${quantity} ${unit}</li>`;
                                })
                                .join('')}
                       </div>
                    </div>
                  <div class="recipe-card-tags">
                           ${this.renderTags()}
                       </div>  
                  </div>
                </div>
              </div>
            </div>
        `;
    }

    renderTags() {
        return this.recipe.tags.map(tag => `
            <span class="recipe-card-tag">
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