let tagsCache = [];
let selectedFilterTags = new Set();
let ingredientsCache = [];
let selectedIngredients = { include: [], exclude: [] };

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
        await loadIngredientsForFiltering();

        void recipeList.loadRecipes();
        setupFilterToggle();
        setupFilters(recipeList);
        setupIngredientAutocomplete();
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

                includeIngredients: selectedIngredients.include.length > 0 ? selectedIngredients.include : null,
                excludeIngredients: selectedIngredients.exclude.length > 0 ? selectedIngredients.exclude : null,

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

async function loadTagsForFiltering() {
    try {
        const tagsResult = await TagService.getAll();
        if (tagsResult.success) {
            tagsCache = tagsResult.data;
            renderFilterTags();
        } else {
            console.error('Failed to load tags for filtering');
        }
    } catch (error) {
        console.error('Error loading tags:', error);
    }
}

async function loadIngredientsForFiltering() {
    try {
        const ingredientsResult = await IngredientService.getAll();
        if (ingredientsResult.success) {
            ingredientsCache = ingredientsResult.data;
        } else {
            console.error('Failed to load ingredients for filtering');
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
             data-tag-name="${tag.name}">
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
        const removeBtn = tagElement.querySelector('.remove-btn');
        if (removeBtn) removeBtn.remove();
    } else {
        // Add tag to selection
        selectedFilterTags.add(tagName);
        tagElement.classList.add('selected');
        addRemoveButtonToTag(tagElement, tagName);
    }

    console.log('Selected filter tags:', Array.from(selectedFilterTags));
}

function addRemoveButtonToTag(tagElement, tagName) {
    // Check if button already exists
    if (tagElement.querySelector('.remove-btn')) return;

    const removeBtn = document.createElement('button');
    removeBtn.className = 'remove-btn';
    removeBtn.type = 'button';
    removeBtn.innerHTML = '×';
    removeBtn.onclick = function(e) {
        e.stopPropagation(); // Prevent triggering the tag click
        removeFilterTag(tagName, tagElement);
    };

    tagElement.appendChild(removeBtn);
}

function removeFilterTag(tagName, tagElement) {
    selectedFilterTags.delete(tagName);
    tagElement.classList.remove('selected');
    const removeBtn = tagElement.querySelector('.remove-btn');
    if (removeBtn) removeBtn.remove();
}

function clearSelectedFilterTags() {
    selectedFilterTags.clear();
    selectedIngredients = { include: [], exclude: [] };
    updateSelectedIngredientsDisplay();

    // Remove visual selection from all tag elements
    const tagElements = document.querySelectorAll('.filter-tag-item');
    tagElements.forEach(element => {
        element.classList.remove('selected');
        const removeBtn = element.querySelector('.remove-btn');
        if (removeBtn) removeBtn.remove();
    });
}

function setupIngredientAutocomplete() {
    setupSingleAutocomplete('include');
    setupSingleAutocomplete('exclude');
}

function setupSingleAutocomplete(type) {
    const searchInput = document.getElementById(`${type}-search`);
    const suggestionsDiv = document.getElementById(`${type}-suggestions`);

    if (!searchInput || !suggestionsDiv) return;

    searchInput.addEventListener('input', (event) => {
        const query = event.target.value.trim();

        if (query.length < 2) {
            hideSuggestions(suggestionsDiv);
            return;
        }

        const results = IngredientService.searchCache(ingredientsCache, query);
        showSuggestions(suggestionsDiv, results, type);
    });

    document.addEventListener('click', (event) => {
        if (!event.target.closest(`#${type}-search`) && !event.target.closest(`#${type}-suggestions`)) {
            hideSuggestions(suggestionsDiv);
        }
    });
}

function showSuggestions(suggestionsDiv, results, type) {
    if (results.length === 0) {
        hideSuggestions(suggestionsDiv);
        return;
    }

    suggestionsDiv.innerHTML = results.map(ingredient => `
        <div class="suggestion-item" onclick="selectIngredient('${ingredient.ingredientSingular}', '${type}')">
            ${ingredient.ingredientSingular}
        </div>
    `).join('');

    suggestionsDiv.style.display = 'block';
}

function hideSuggestions(suggestionsDiv) {
    suggestionsDiv.style.display = 'none';
}

function selectIngredient(ingredientName, type) {
    if (selectedIngredients[type].includes(ingredientName)) return;

    const otherType = type === 'include' ? 'exclude' : 'include';
    selectedIngredients[otherType] = selectedIngredients[otherType].filter(name => name !== ingredientName);
    selectedIngredients[type].push(ingredientName);

    updateSelectedIngredientsDisplay();

    const searchInput = document.getElementById(`${type}-search`);
    const suggestionsDiv = document.getElementById(`${type}-suggestions`);
    searchInput.value = '';
    hideSuggestions(suggestionsDiv);
}

function updateSelectedIngredientsDisplay() {
    updateSingleDisplay('include');
    updateSingleDisplay('exclude');
}

function updateSingleDisplay(type) {
    const container = document.getElementById(`${type}-selected`);
    if (!container) return;

    const ingredients = selectedIngredients[type];
    container.innerHTML = ingredients.map(ingredient => `
        <div class="ingredient-tag ${type === 'exclude' ? 'exclude' : ''}">
            <span>${ingredient}</span>
            <button class="remove-btn" onclick="removeIngredient('${ingredient}', '${type}')" type="button">×</button>
        </div>
    `).join('');
}

function removeIngredient(ingredientName, type) {
    selectedIngredients[type] = selectedIngredients[type].filter(name => name !== ingredientName);
    updateSelectedIngredientsDisplay();
}