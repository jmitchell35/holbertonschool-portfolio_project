// Keep count of ingredients and instructions
let ingredientCounter = 1;
let instructionCounter = 1;
// Performance : load once on page load, then filter from the caches' array
let ingredientsCache = [];
let unitsCache = [];
let tagsCache = [];
let selectedTagIds = new Set();

// Load initial data
document.addEventListener('DOMContentLoaded', async () => {
    await loadIngredients();
    await loadUnits();
    await loadUserInfo();
    await loadTags();
    setupFormSubmission();
    renderTags();
});

async function loadUserInfo() {
    try {
        if (await AuthService.isAuthenticated()) {
            const data = await AuthService.getCurrentUser();
            document.getElementById('publisher').value = data.data.username;
        } else {
            alert('Vous devez être connecté pour publier une recette.');
            window.location.href = '/login.html';
        }
    } catch (error) {
        console.error('Failed to load user info:', error);
        alert('Erreur lors de la vérification de l\'authentification.');
        window.location.href = '/login.html';
    }
}

async function loadIngredients() {
    try {
        const result = await IngredientService.getAll();
        if (result.success) {
            ingredientsCache = result.data;
        }
    } catch (error) {
        console.error('Failed to load ingredients:', error);
    }
}

async function loadUnits() {
    try {
        const result = await UnitService.getAll();
        if (result.success) {
            unitsCache = result.data;
        }
    } catch (error) {
        console.error('Failed to load units:', error);
    }
}

async function loadTags() {
    try {
        const result = await TagService.getAll();

        if (result.success) {
            tagsCache = result.data;
        }
    } catch (error) {
        console.error('Failed to load ingredients:', error);
    }
}

function renderTags() {
    const container = document.getElementById('tags-container');

    if (tagsCache.length === 0) {
        container.innerHTML = '<p style="color: #999;">Aucun tag disponible</p>';
        return;
    }

    container.innerHTML = tagsCache.map(tag => `
        <div class="tag-item" 
             data-tag-id="${tag.id}"
             data-tag-name="${tag.name}"
             data-bg-color="${tag.backgroundColorHex}"
             data-font-color="${tag.fontColorHex}"
             style="background-color: ${tag.backgroundColorHex}; color: ${tag.fontColorHex};">
            ${tag.name}
        </div>
    `).join('');
}

document.getElementById('tags-container').addEventListener('click', function(event) {
    const tagElement = event.target.closest('.tag-item');
    if (tagElement) {
        const tagId = tagElement.dataset.tagId;
        const tagName = tagElement.dataset.tagName;
        const bgColor = tagElement.dataset.bgColor;
        const fontColor = tagElement.dataset.fontColor;

        console.log('Clicked tag:', tagName, 'ID:', tagId);
        toggleTag(tagId, tagName, bgColor, fontColor);
    }
});

function toggleTag(tagId) {
    console.log('Before toggle:', Array.from(selectedTagIds));
    console.log('Toggling tag:', tagId);
    if (selectedTagIds.has(tagId)) {
        // Toggle off
        selectedTagIds.delete(tagId);
        updateTagDisplay(tagId, false);
        console.log('Removed tag');
    } else {
        // Toggle on
        selectedTagIds.add(tagId);
        updateTagDisplay(tagId, true);
        console.log('Added tag');
    }
    console.log('After toggle:', Array.from(selectedTagIds));
}

function updateTagDisplay(tagId, isSelected) {
    const tagElement = document.querySelector(`[data-tag-id="${tagId}"]`);
    if (tagElement) {
        if (isSelected) {
            tagElement.classList.add('selected');
        } else {
            tagElement.classList.remove('selected');
        }
    }
}

function addIngredientLine() {
    const container = document.getElementById('ingredients-fields');
    const lineDiv = document.createElement('div');
    lineDiv.className = 'ingredient-line';
    lineDiv.id = `ingredient-line-${ingredientCounter}`;

    lineDiv.innerHTML = `
        <button type="button" class="line-delete-button" onclick="removeIngredientLine(${ingredientCounter})">×</button>
        
        <input type="hidden" name="recipeIngredients[${ingredientCounter}].ingredientId" id="ingredientId-${ingredientCounter}">
        <input type="hidden" name="recipeIngredients[${ingredientCounter}].unitId" id="unitId-${ingredientCounter}">
        
        <div class="ingredient-block">
            <label for="ingredient-search-${ingredientCounter}">Ingrédient</label>
            <input type="search" id="ingredient-search-${ingredientCounter}" placeholder="Rechercher un ingrédient..." 
                   oninput="searchIngredients(${ingredientCounter}, this.value)" autocomplete="off">
            <div class="suggestions" id="ingredient-suggestions-${ingredientCounter}"></div>
        </div>
        
        <div class="quantity-block">
            <label for="quantity-${ingredientCounter}">Quantité</label>
            <input type="number" name="recipeIngredients[${ingredientCounter}].quantity" id="quantity-${ingredientCounter}" min="1" required>
        </div>
        
        <div class="unit-block">
            <label for="unit-search-${ingredientCounter}">Unité</label>
            <input type="search" id="unit-search-${ingredientCounter}" placeholder="Rechercher une unité..." 
                   oninput="searchUnits(${ingredientCounter}, this.value)" autocomplete="off">
            <div class="suggestions" id="unit-suggestions-${ingredientCounter}"></div>
        </div>
    `;

    container.appendChild(lineDiv);
    ingredientCounter++;
}

function removeIngredientLine(index) {
    const line = document.getElementById(`ingredient-line-${index}`);
    if (line) {
        line.remove();
        reorderIngredientLines();
    }
}

// Necessary when a line has been deleted : ensure the count and order are right
function reorderIngredientLines() {
    const lines = document.querySelectorAll('.ingredient-line');
    // New set of indices to re-enforce continuous indices
    lines.forEach((line, index) => {
        // Get the line's name attribute for ingredient, unit, quantity (using pattern matching)
        const hiddenIngredient = line.querySelector('input[name*="ingredientId"]');
        const hiddenUnit = line.querySelector('input[name*="unitId"]');
        const quantity = line.querySelector('input[name*="quantity"]');

        //
        if (hiddenIngredient) hiddenIngredient.name = `recipeIngredients[${index}].ingredientId`;
        if (hiddenUnit) hiddenUnit.name = `recipeIngredients[${index}].unitId`;
        if (quantity) quantity.name = `recipeIngredients[${index}].quantity`;
    });
}

function searchIngredients(lineIndex, query) {
    const suggestionsDiv = document.getElementById(`ingredient-suggestions-${lineIndex}`);

    if (query.length < 2) {
        suggestionsDiv.style.display = 'none';
        return;
    }

    const filteredIngredients = ingredientsCache.filter(ingredient =>
        ingredient.ingredientSingular.toLowerCase().includes(query.toLowerCase())
    );

    if (filteredIngredients.length === 0) {
        suggestionsDiv.style.display = 'none';
        return;
    }

    suggestionsDiv.innerHTML = filteredIngredients
        .slice(0, 10) // Limit to 10 results
        .map(ingredient =>
            `<div class="suggestion-item" onclick="selectIngredient(${lineIndex}, '${ingredient.id}', '${ingredient.ingredientSingular}')">${ingredient.ingredientSingular}</div>`
        ).join('');

    suggestionsDiv.style.display = 'block';
}

// Select = set value to selected suggestion, set id to selected ingredient's id, switch off suggestion display.
function selectIngredient(lineIndex, ingredientId, ingredientName) {
    document.getElementById(`ingredient-search-${lineIndex}`).value = ingredientName;
    document.getElementById(`ingredientId-${lineIndex}`).value = ingredientId;
    document.getElementById(`ingredient-suggestions-${lineIndex}`).style.display = 'none';
}

function searchUnits(lineIndex, query) {
    const suggestionsDiv = document.getElementById(`unit-suggestions-${lineIndex}`);

    if (query.length < 1) {
        suggestionsDiv.style.display = 'none';
        return;
    }

    const filteredUnits = unitsCache.filter(unit =>
        unit.name.toLowerCase().includes(query.toLowerCase())
    );

    if (filteredUnits.length === 0) {
        suggestionsDiv.style.display = 'none';
        return;
    }

    suggestionsDiv.innerHTML = filteredUnits
        .slice(0, 10)
        .map(unit =>
            `<div class="suggestion-item" onclick="selectUnit(${lineIndex}, '${unit.id}', '${unit.name}')">${unit.name}</div>`
        ).join('');

    suggestionsDiv.style.display = 'block';
}

function selectUnit(lineIndex, unitId, unitName) {
    document.getElementById(`unit-search-${lineIndex}`).value = unitName;
    document.getElementById(`unitId-${lineIndex}`).value = unitId;
    document.getElementById(`unit-suggestions-${lineIndex}`).style.display = 'none';
}

function addInstructionLine() {
    const container = document.getElementById('instructions-fields');
    const lineDiv = document.createElement('div');
    lineDiv.className = 'instruction-line';
    lineDiv.id = `instruction-line-${instructionCounter}`;

    const stepNumber = instructionCounter + 1;

    lineDiv.innerHTML = `
        <button type="button" class="line-delete-button" onclick="removeInstructionLine(${instructionCounter})">×</button>
        
        <input type="hidden" name="instructions[${instructionCounter}].stepNumber" value="${stepNumber}">
        
        <div class="instruction-block">
            <label for="instruction-${instructionCounter}">Étape ${stepNumber}</label>
            <textarea name="instructions[${instructionCounter}].text" id="instruction-${instructionCounter}" 
                     placeholder="Décrivez cette étape..." required></textarea>
        </div>
    `;

    container.appendChild(lineDiv);
    instructionCounter++;
}

function removeInstructionLine(index) {
    const line = document.getElementById(`instruction-line-${index}`);
    if (line) {
        line.remove();
        reorderInstructionLines();
    }
}

function reorderInstructionLines() {
    const lines = document.querySelectorAll('.instruction-line');
    lines.forEach((line, index) => {
        const stepNumber = index + 1;

        const hiddenStep = line.querySelector('input[name*="stepNumber"]');
        if (hiddenStep) {
            hiddenStep.name = `instructions[${index}].stepNumber`;
            hiddenStep.value = stepNumber;
        }

        const textarea = line.querySelector('textarea');
        if (textarea) {
            textarea.name = `instructions[${index}].text`;
        }

        const label = line.querySelector('label');
        if (label) {
            label.textContent = `Étape ${stepNumber}`;
            label.setAttribute('for', `instruction-${index}`);
        }

        if (textarea) {
            textarea.id = `instruction-${index}`;
        }
    });
}

function setupFormSubmission() {
    const form = document.getElementById('post-recipe-form');
    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        if (!(await AuthService.isAuthenticated())) {
            alert('Votre session a expiré. Vous allez être redirigé vers la page de connexion.');
            window.location.href = '/login.html';
            return;
        }

        // Validate that all ingredients have IDs selected
        const ingredientIds = document.querySelectorAll('input[name*="ingredientId"]');
        const unitIds = document.querySelectorAll('input[name*="unitId"]');

        for (let input of ingredientIds) {
            if (!input.value) {
                alert('Veuillez sélectionner tous les ingrédients dans la liste de suggestions.');
                return;
            }
        }

        for (let input of unitIds) {
            if (!input.value) {
                alert('Veuillez sélectionner toutes les unités dans la liste de suggestions.');
                return;
            }
        }

        if (!validateForm()) {
            return;
        }

        // Convert form data to JSON
        const formData = new FormData(form);
        const jsonData = convertFormDataToJSON(formData);

        try {
            const result = await RecipeService.createRecipe(jsonData);

            if (result.success) {
                alert('Recette créée avec succès !');
                window.location.href = '/';
            } else {
                alert('Erreur: ' + result.error);
            }
        } catch (error) {
            console.error('Error submitting recipe:', error);
            alert('Erreur lors de la soumission de la recette.');
        }
    });
}

function validateForm() {
    const ingredientLines = document.querySelectorAll('.ingredient-line');
    const errors = [];

    ingredientLines.forEach((line, index) => {
        const ingredientId = line.querySelector('input[name*="ingredientId"]').value;
        const unitId = line.querySelector('input[name*="unitId"]').value;
        const quantity = line.querySelector('input[name*="quantity"]').value;
        const ingredientSearch = line.querySelector('input[id*="ingredient-search"]').value;
        const unitSearch = line.querySelector('input[id*="unit-search"]').value;

        // Check for partial data
        if (ingredientSearch && !ingredientId) {
            errors.push(`Ligne ${index + 1}: Veuillez sélectionner l'ingrédient "${ingredientSearch}" dans la liste de suggestions.`);
        }

        if (unitSearch && !unitId) {
            errors.push(`Ligne ${index + 1}: Veuillez sélectionner l'unité "${unitSearch}" dans la liste de suggestions.`);
        }

        if ((ingredientId || unitId || quantity) && (!ingredientId || !unitId || !quantity)) {
            errors.push(`Ligne ${index + 1}: Tous les champs (ingrédient, quantité, unité) sont requis.`);
        }
    });

    if (errors.length > 0) {
        alert('Erreurs dans le formulaire:\n\n' + errors.join('\n'));
        return false;
    }

    return true;
}

// Convert FormData to the JSON to meet our RecipeCreateDTO
function convertFormDataToJSON(formData) {
    const data = {
        name: formData.get('name'),
        prepTime: parseInt(formData.get('prepTime')),
        servings: parseInt(formData.get('servings')),
        publisher: formData.get('publisher'),
        thumbnailUrl: formData.get('thumbnailUrl') || null,
        bannerUrl: formData.get('bannerUrl') || null,
        recipeIngredients: [],
        instructions: [],
        tagIds: Array.from(selectedTagIds),
        collectionIds: []
    };

    // Process ingredients : find all ingredient entries
    const ingredientEntries = [];
    for (let [key, value] of formData.entries()) {
        const match = key.match(/recipeIngredients\[(\d+)\]\.(\w+)/);
        if (match) {
            const index = parseInt(match[1]);
            const field = match[2];

            if (!ingredientEntries[index]) {
                ingredientEntries[index] = {};
            }

            ingredientEntries[index][field] = field === 'quantity' ? parseInt(value) : value;
        }
    }

    data.recipeIngredients = ingredientEntries.filter(entry => entry && entry.ingredientId);

    // Then same with instructions
    const instructionEntries = [];
    for (let [key, value] of formData.entries()) {
        const match = key.match(/instructions\[(\d+)\]\.(\w+)/);
        if (match) {
            const index = parseInt(match[1]);  // Because match[0] returns the full sequence match
            const field = match[2];

            // Avoids overwriting data if already exists
            if (!instructionEntries[index]) {
                instructionEntries[index] = {};
            }

            instructionEntries[index][field] = field === 'stepNumber' ? parseInt(value) : value;
        }
    }

    data.instructions = instructionEntries.filter(entry => entry && entry.text);

    return data;
}

// Hide suggestions when clicking outside
document.addEventListener('click', (event) => {
    if (!event.target.closest('.ingredient-block') && !event.target.closest('.unit-block')) {
        document.querySelectorAll('.suggestions').forEach(div => {
            div.style.display = 'none';
        });
    }
});