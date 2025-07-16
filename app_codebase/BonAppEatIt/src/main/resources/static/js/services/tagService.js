class TagService {
    static async getAll() {
        const response = await fetch('/api/v1/tags', {
            credentials: 'include'
        });
        if (response.ok) {
            const data = await response.json();
            return { success: true, data: data.data };
        }
        return { success: false, error: `HTTP ${response.status}` };
    }
}

window.TagService = TagService;