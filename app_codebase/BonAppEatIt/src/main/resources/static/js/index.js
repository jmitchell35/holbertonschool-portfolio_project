let tagsCache = [];
let selectedFilterTags = new Set();

function buildRecipeUrl(baseUrl, filters = {}, pagination = {}) {
    const url = new URL(baseUrl);

    const addParam = (key, value) => {
        if (value !== undefined && value !== null && value !== '') {
            url.searchParams.set(key, value);
        }
    };

    const addArrayParam = (key, array) => {
        if (array && Array.isArray(array) && array.length > 0) {
            array.forEach(item => url.searchParams.append(key, item));
        }
    };

    addParam('page', pagination.page);
    addParam('size', pagination.size);
    addParam('sort', pagination.sort);

    addParam('maxIngredients', filters.maxIngredients);
    addParam('month', filters.month);
    addParam('maxPrepTime', filters.maxPrepTime);
    addArrayParam('includeIngredients', filters.includeIngredients);
    addArrayParam('excludeIngredients', filters.excludeIngredients);
    addArrayParam('tags', filters.tags);

    return url;
}

async function fetchRecipes(filters = {}, pagination = {}) {
    return await RecipeService.getRecipes(filters, pagination);
}

document.addEventListener('DOMContentLoaded', async () => {
    const recipeList = document.querySelector('recipe-list');
    if (recipeList) {
        await loadTagsForFiltering();

        void recipeList.loadRecipes();
        setupFilterToggle();
        setupFilters(recipeList);
    }
});

function setupFilterToggle() {
    const toggleBtn = document.getElementById('filter-toggle');
    const filterPanel = document.getElementById('filter-panel');
    const filterArrow = document.querySelector('.filter-arrow');

    if (toggleBtn && filterPanel) {
        toggleBtn.addEventListener('click', () => {
            const isExpanded = filterPanel.classList.contains('expanded');

            if (isExpanded) {
                filterPanel.classList.remove('expanded');
                filterPanel.classList.add('collapsed');
                filterArrow.classList.remove('expanded');
            } else {
                filterPanel.classList.remove('collapsed');
                filterPanel.classList.add('expanded');
                filterArrow.classList.add('expanded');
            }
        });
    }
}

function setupFilters(recipeList) {
    const filterForm = document.querySelector('#filter-form');

    if (filterForm) {
        filterForm.addEventListener('submit', (e) => {
            e.preventDefault();

            const formData = new FormData(filterForm);

            console.log('Form data entries:');
            for (let [key, value] of formData.entries()) {
                console.log(`${key}: ${value}`);
            }

            console.log('Selected filter tags:', Array.from(selectedFilterTags));

            const filters = {
                maxPrepTime: formData.get('maxPrepTime') || null,
                maxIngredients: formData.get('maxIngredients') || null,
                month: formData.get('month') || null,
                // Convert comma-separated strings to arrays
                includeIngredients: parseCommaSeparated(formData.get('includeIngredients')),
                excludeIngredients: parseCommaSeparated(formData.get('excludeIngredients')),

                tagIds: Array.from(selectedFilterTags).map(tagName => {
                    const tag = tagsCache.find(t => t.name === tagName);
                    return tag ? tag.id : null;
                }).filter(id => id !== null)
            };

            console.log('Applying filters:', filters);

            // Reset to page 0 when applying new filters
            void recipeList.loadRecipes(filters, { page: 0 });
        });

        const clearButton = document.querySelector('#clear-filters');
        if (clearButton) {
            clearButton.addEventListener('click', () => {
                filterForm.reset();
                clearSelectedFilterTags();
                void recipeList.loadRecipes({}, { page: 0 });
            });
        }
    }
}

function parseCommaSeparated(value) {
    if (!value || value.trim() === '') return null;
    return value.split(',').map(item => item.trim()).filter(item => item.length > 0);
}

async function loadTagsForFiltering() {
    try {
        const result = await TagService.getAll();
        if (result.success) {
            tagsCache = result.data;
            renderFilterTags();
        } else {
            console.error('Failed to load tags for filtering');
        }
    } catch (error) {
        console.error('Error loading tags:', error);
    }
}

function renderFilterTags() {
    const container = document.getElementById('filter-tags-container');

    if (!container) return;

    if (tagsCache.length === 0) {
        container.innerHTML = '<div class="tags-loading">No tags available</div>';
        return;
    }

    container.innerHTML = tagsCache.map(tag => `
        <div class="filter-tag-item" 
             data-tag-name="${tag.name}"
             style="background-color: ${tag.backgroundColorHex}; color: ${tag.fontColorHex};">
            ${tag.name}
        </div>
    `).join('');

    setupFilterTagListeners();
}

function setupFilterTagListeners() {
    const container = document.getElementById('filter-tags-container');
    if (!container) return;

    container.addEventListener('click', function(event) {
        const tagElement = event.target.closest('.filter-tag-item');
        if (tagElement) {
            const tagName = tagElement.dataset.tagName;
            toggleFilterTag(tagName, tagElement);
        }
    });
}

function toggleFilterTag(tagName, tagElement) {
    if (selectedFilterTags.has(tagName)) {
        // Remove tag from selection
        selectedFilterTags.delete(tagName);
        tagElement.classList.remove('selected');
    } else {
        // Add tag to selection
        selectedFilterTags.add(tagName);
        tagElement.classList.add('selected');
    }

    console.log('Selected filter tags:', Array.from(selectedFilterTags));
}

function clearSelectedFilterTags() {
    selectedFilterTags.clear();

    // Remove visual selection from all tag elements
    const tagElements = document.querySelectorAll('.filter-tag-item');
    tagElements.forEach(element => {
        element.classList.remove('selected');
    });
}