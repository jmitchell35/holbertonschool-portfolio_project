class Recipe {
    constructor(data) {
        this.id = data.id;
        this.name = data.name;
        this.prepTime = data.prepTime;
        this.servings = data.servings;
        this.tags = data.tags || [];
        this.recipeIngredients = data.recipeIngredients || [];
        this.createdAt = new Date(data.createdAt);
        this.thumbnailUrl = data.thumbnailUrl;
        this.bannerUrl = data.bannerUrl
    }

    getFormattedPrepTime() {
        return `${this.prepTime} min`;
    }

    getTagNames() {
        return this.tags.map(tag => tag.name);
    }

    getIngredientCount() {
        return this.recipeIngredients.length;
    }
}