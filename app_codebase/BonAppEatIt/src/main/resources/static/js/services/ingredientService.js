class IngredientService {
    static async getAll() {
        const response = await fetch('/api/v1/ingredients', {
            credentials: 'include'
        });
        if (response.ok) {
            const data = await response.json();
            return { success: true, data: data.data };
        }
        return { success: false, error: `HTTP ${response.status}` };
    }

    static async search(query) {
        const params = new URLSearchParams();
        if (query) params.append('query', query);

        const response = await fetch(`/api/v1/ingredients/search?${params}`, {
            credentials: 'include'
        });
        if (response.ok) {
            const data = await response.json();
            return { success: true, data: data.data };
        }
        return { success: false, error: `HTTP ${response.status}` };
    }
}

window.IngredientService = IngredientService;  // globally available