# Recipe Management API Routes

This document outlines all API endpoints for the recipe management system based on the provided entity relationship diagram.

## Authentication Routes

<table>
<thead>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Description</th>
<th>Request Body</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>POST</td>
<td>/auth/register</td>
<td>Register a new user</td>
<td>{ "email": "string", "password": "string", "isAdmin": false }</td>
<td>{ "user_id": "string", "token": "string" }</td>
</tr>
<tr>
<td>POST</td>
<td>/auth/login</td>
<td>User login</td>
<td>{ "email": "string", "password": "string" }</td>
<td>{ "user_id": "string", "token": "string" }</td>
</tr>
<tr>
<td>POST</td>
<td>/auth/logout</td>
<td>User logout</td>
<td>-</td>
<td>{ "message": "Logged out successfully" }</td>
</tr>
</tbody>
</table>

## User Routes

<table>
<thead>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Description</th>
<th>Request Body</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>GET</td>
<td>/users</td>
<td>Get all users (admin only)</td>
<td>-</td>
<td>Array of user objects</td>
</tr>
<tr>
<td>GET</td>
<td>/users/:user_id</td>
<td>Get user by ID</td>
<td>-</td>
<td>{ "user_id": "string", "email": "string", "isAdmin": boolean }</td>
</tr>
<tr>
<td>PUT</td>
<td>/users/:user_id</td>
<td>Update user</td>
<td>{ "email": "string", "password": "string", "isAdmin": boolean }</td>
<td>Updated user object</td>
</tr>
<tr>
<td>DELETE</td>
<td>/users/:user_id</td>
<td>Delete user (admin only)</td>
<td>-</td>
<td>{ "message": "User deleted successfully" }</td>
</tr>
</tbody>
</table>

## Recipe Routes

<table>
<thead>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Description</th>
<th>Request Body</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>GET</td>
<td>/recipes</td>
<td>Get all recipes</td>
<td>-</td>
<td>Array of recipe objects</td>
</tr>
<tr>
<td>GET</td>
<td>/recipes/:recipe_id</td>
<td>Get recipe by ID</td>
<td>-</td>
<td>{ "recipe_id": "string", "name": "string", "prep_time": int, "servings": "string" }</td>
</tr>
<tr>
<td>POST</td>
<td>/recipes</td>
<td>Create new recipe</td>
<td>{ "name": "string", "prep_time": int, "servings": "string" }</td>
<td>Created recipe object</td>
</tr>
<tr>
<td>PUT</td>
<td>/recipes/:recipe_id</td>
<td>Update recipe</td>
<td>{ "name": "string", "prep_time": int, "servings": "string" }</td>
<td>Updated recipe object</td>
</tr>
<tr>
<td>DELETE</td>
<td>/recipes/:recipe_id</td>
<td>Delete recipe</td>
<td>-</td>
<td>{ "message": "Recipe deleted successfully" }</td>
</tr>
</tbody>
</table>

## Ingredient Routes

<table>
<thead>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Description</th>
<th>Request Body</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>GET</td>
<td>/ingredients</td>
<td>Get all ingredients</td>
<td>-</td>
<td>Array of ingredient objects</td>
</tr>
<tr>
<td>GET</td>
<td>/ingredients/:ingredient_id</td>
<td>Get ingredient by ID</td>
<td>-</td>
<td>Ingredient object with availability data</td>
</tr>
<tr>
<td>POST</td>
<td>/ingredients</td>
<td>Create new ingredient</td>
<td>{ "ingredient_singular": "string", "ingredient_plural": "string", "available_in_*": boolean }</td>
<td>Created ingredient object</td>
</tr>
<tr>
<td>PUT</td>
<td>/ingredients/:ingredient_id</td>
<td>Update ingredient</td>
<td>{ "ingredient_singular": "string", "ingredient_plural": "string", "available_in_*": boolean }</td>
<td>Updated ingredient object</td>
</tr>
<tr>
<td>DELETE</td>
<td>/ingredients/:ingredient_id</td>
<td>Delete ingredient</td>
<td>-</td>
<td>{ "message": "Ingredient deleted successfully" }</td>
</tr>
<tr>
<td>GET</td>
<td>/ingredients/seasonal/:month</td>
<td>Get ingredients available in specific month</td>
<td>-</td>
<td>Array of available ingredients</td>
</tr>
</tbody>
</table>

## Recipe Ingredients Routes

<table>
<thead>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Description</th>
<th>Request Body</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>GET</td>
<td>/recipes/:recipe_id/ingredients</td>
<td>Get all ingredients for a recipe</td>
<td>-</td>
<td>Array of recipe ingredient objects</td>
</tr>
<tr>
<td>POST</td>
<td>/recipes/:recipe_id/ingredients</td>
<td>Add ingredient to recipe</td>
<td>{ "ingredient_id": "string", "unit_id": "string", "quantity": float }</td>
<td>Created recipe ingredient object</td>
</tr>
<tr>
<td>PUT</td>
<td>/recipes/:recipe_id/ingredients/:ingredient_id</td>
<td>Update recipe ingredient</td>
<td>{ "unit_id": "string", "quantity": float }</td>
<td>Updated recipe ingredient object</td>
</tr>
<tr>
<td>DELETE</td>
<td>/recipes/:recipe_id/ingredients/:ingredient_id</td>
<td>Remove ingredient from recipe</td>
<td>-</td>
<td>{ "message": "Ingredient removed from recipe" }</td>
</tr>
</tbody>
</table>

## Instructions Routes

<table>
<thead>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Description</th>
<th>Request Body</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>GET</td>
<td>/recipes/:recipe_id/instructions</td>
<td>Get all instructions for a recipe</td>
<td>-</td>
<td>Array of instruction objects ordered by step_number</td>
</tr>
<tr>
<td>POST</td>
<td>/recipes/:recipe_id/instructions</td>
<td>Add instruction to recipe</td>
<td>{ "text": "string", "step_number": int }</td>
<td>Created instruction object</td>
</tr>
<tr>
<td>PUT</td>
<td>/instructions/:instruction_id</td>
<td>Update instruction</td>
<td>{ "text": "string", "step_number": int }</td>
<td>Updated instruction object</td>
</tr>
<tr>
<td>DELETE</td>
<td>/instructions/:instruction_id</td>
<td>Delete instruction</td>
<td>-</td>
<td>{ "message": "Instruction deleted successfully" }</td>
</tr>
</tbody>
</table>

## Units Routes

<table>
<thead>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Description</th>
<th>Request Body</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>GET</td>
<td>/units</td>
<td>Get all measurement units</td>
<td>-</td>
<td>Array of unit objects</td>
</tr>
<tr>
<td>GET</td>
<td>/units/:unit_id</td>
<td>Get unit by ID</td>
<td>-</td>
<td>{ "unit_id": "string", "unit_name": "string" }</td>
</tr>
<tr>
<td>POST</td>
<td>/units</td>
<td>Create new unit</td>
<td>{ "unit_name": "string" }</td>
<td>Created unit object</td>
</tr>
<tr>
<td>PUT</td>
<td>/units/:unit_id</td>
<td>Update unit</td>
<td>{ "unit_name": "string" }</td>
<td>Updated unit object</td>
</tr>
<tr>
<td>DELETE</td>
<td>/units/:unit_id</td>
<td>Delete unit</td>
<td>-</td>
<td>{ "message": "Unit deleted successfully" }</td>
</tr>
</tbody>
</table>

## Collections Routes

<table>
<thead>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Description</th>
<th>Request Body</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>GET</td>
<td>/users/:user_id/collections</td>
<td>Get all collections for a user</td>
<td>-</td>
<td>Array of collection objects</td>
</tr>
<tr>
<td>GET</td>
<td>/collections/:collection_id</td>
<td>Get collection by ID</td>
<td>-</td>
<td>{ "collection_id": "string", "name": "string" }</td>
</tr>
<tr>
<td>POST</td>
<td>/users/:user_id/collections</td>
<td>Create new collection</td>
<td>{ "name": "string" }</td>
<td>Created collection object</td>
</tr>
<tr>
<td>PUT</td>
<td>/collections/:collection_id</td>
<td>Update collection</td>
<td>{ "name": "string" }</td>
<td>Updated collection object</td>
</tr>
<tr>
<td>DELETE</td>
<td>/collections/:collection_id</td>
<td>Delete collection</td>
<td>-</td>
<td>{ "message": "Collection deleted successfully" }</td>
</tr>
</tbody>
</table>

## Tags Routes

<table>
<thead>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Description</th>
<th>Request Body</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>GET</td>
<td>/tags</td>
<td>Get all tags</td>
<td>-</td>
<td>Array of tag objects</td>
</tr>
<tr>
<td>GET</td>
<td>/recipes/:recipe_id/tags</td>
<td>Get all tags for a recipe</td>
<td>-</td>
<td>Array of tag objects</td>
</tr>
<tr>
<td>POST</td>
<td>/tags</td>
<td>Create new tag</td>
<td>{ "tag_name": "string" }</td>
<td>Created tag object</td>
</tr>
<tr>
<td>POST</td>
<td>/recipes/:recipe_id/tags/:tag_id</td>
<td>Add tag to recipe</td>
<td>-</td>
<td>{ "message": "Tag added to recipe" }</td>
</tr>
<tr>
<td>DELETE</td>
<td>/recipes/:recipe_id/tags/:tag_id</td>
<td>Remove tag from recipe</td>
<td>-</td>
<td>{ "message": "Tag removed from recipe" }</td>
</tr>
<tr>
<td>PUT</td>
<td>/tags/:tag_id</td>
<td>Update tag</td>
<td>{ "tag_name": "string" }</td>
<td>Updated tag object</td>
</tr>
<tr>
<td>DELETE</td>
<td>/tags/:tag_id</td>
<td>Delete tag</td>
<td>-</td>
<td>{ "message": "Tag deleted successfully" }</td>
</tr>
</tbody>
</table>

## Search & Filter Routes

<table>
<thead>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Description</th>
<th>Query Parameters</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>GET</td>
<td>/recipes/search</td>
<td>Search recipes by name</td>
<td>?q=search_term</td>
<td>Array of matching recipes</td>
</tr>
<tr>
<td>GET</td>
<td>/recipes/filter</td>
<td>Filter recipes by criteria</td>
<td>?prep_time=max_minutes&tags=tag1,tag2&ingredients=ing1,ing2</td>
<td>Array of filtered recipes</td>
</tr>
<tr>
<td>GET</td>
<td>/recipes/seasonal</td>
<td>Get recipes with seasonal ingredients</td>
<td>?month=month_name</td>
<td>Array of seasonal recipes</td>
</tr>
</tbody>
</table>

## Error Responses

All endpoints may return the following error responses:

- **400 Bad Request**: Invalid request body or parameters
- **401 Unauthorized**: Authentication required or invalid token
- **403 Forbidden**: Insufficient permissions
- **404 Not Found**: Resource not found
- **409 Conflict**: Resource already exists (for POST operations)
- **500 Internal Server Error**: Server error

Error response format:
```json
{
  "error": "Error message",
  "code": "ERROR_CODE",
  "details": "Detailed error description"
}
```
