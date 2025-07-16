class RecipeService {
    static async getRecipes(filters = {}, pagination = {}) {
        try {
            const params = new URLSearchParams();

            // Add pagination params
            if (pagination.page !== undefined) params.append('page', pagination.page);
            if (pagination.size !== undefined) params.append('size', pagination.size);

            // Add filter params
            Object.entries(filters).forEach(([key, value]) => {
                if (value !== null && value !== undefined) {
                    if (Array.isArray(value)) {
                        // For arrays, add each item as separate parameter
                        value.forEach(item => params.append(key, item));
                    } else {
                        params.append(key, value);
                    }
                }
            });

            const response = await fetch(`/api/v1/recipes?${params}`, {
                credentials: 'include'
            });

            if (response.ok) {
                const data = await response.json();
                return { success: true, data };
            } else {
                return { success: false, error: `HTTP ${response.status}` };
            }
        } catch (error) {
            return { success: false, error: error.message };
        }
    }

    static async createRecipe(recipeData) {
        try {
            const response = await fetch('/api/v1/recipes', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                credentials: 'include',
                body: JSON.stringify(recipeData)
            });

            const result = await response.json();

            if (response.ok) {
                return { success: true, data: result };
            } else {
                return { success: false, error: result.errors?.[0]?.message || 'Unknown error' };
            }
        } catch (error) {
            return { success: false, error: error.message };
        }
    }
}

window.RecipeService = RecipeService;