# PROJECT EXECUTION

## SPRINTS

<table width="100%">
  <tr>
    <th align="left" bgcolor="#E8E8E8">SPRINT</th>
    <th align="left" bgcolor="#E8E8E8">START</th>
    <th align="left" bgcolor="#E8E8E8">FINISH</th>
    <th align="left" bgcolor="#E8E8E8">OBJECTIVE</th>
  </tr>
  <tr>
    <td>S1 - Finish the back-end</td>
    <td>June 23rd</td>
    <td>June 29th</td>
    <td>Finish the back-end part of the project : API routes, Service Layer, Repository Layer</td>
  </tr>
  <tr>
    <td>S2 - Build the client</td>
    <td>May 5th 2025</td>
    <td>June 6th 2025</td>
    <td>Build up the client : html pages, CSS styles, js scripts</td>
  </tr>
</table>

### SPRINT 1 - Finishing the Back-End - API first approach

<table width="100%">
  <tr>
    <th align="left" bgcolor="#E8E8E8">USER STORY</th>
    <th align="left" bgcolor="#E8E8E8">TASK</th>
    <th align="left" bgcolor="#E8E8E8">DELIVERABLE</th>
    <th align="left" bgcolor="#E8E8E8">STATUS</th>
  </tr>
  <tr>
    <td rowspan="3">Setup & Configuration</td>
    <td>Configure PostgreSQL connection and JPA settings</td>
    <td>application.properties with DB config</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Create Repository interfaces for all entities</td>
    <td>Repository package with 7 repository interfaces</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Setup Swagger/OpenAPI documentation</td>
    <td>API documentation accessible at /swagger-ui</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td rowspan="4">Story 1 & 2: Recipe Listing & Filtering</td>
    <td>Create RecipeService with findAll and filtering methods</td>
    <td>RecipeService.java with CRUD + filter operations</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Build RecipeController with GET /api/v1/recipes endpoint</td>
    <td>REST endpoint returning paginated recipe list</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Implement filtering by ingredients, tags, prep time</td>
    <td>Query parameters: ?ingredients=x&tags=y&maxPrepTime=z</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Create RecipeDTO for API responses</td>
    <td>DTO package with Recipe response objects</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td rowspan="3">Story 3: Authentication</td>
    <td>Configure Spring Security with JWT</td>
    <td>SecurityConfig.java with JWT authentication</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Create AuthController with login/register endpoints</td>
    <td>POST /api/v1/auth/login and /register endpoints</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Implement UserService with registration/authentication logic</td>
    <td>UserService.java with password hashing and JWT generation</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td rowspan="2">Story 4: Recipe Details</td>
    <td>Enhance RecipeController with GET /api/v1/recipes/{id}</td>
    <td>Single recipe endpoint with full details</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Create detailed RecipeDTO with ingredients and instructions</td>
    <td>Comprehensive DTO showing all recipe relationships</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td rowspan="2">Story 7: Admin Features</td>
    <td>Create TagService and TagController for CRUD operations</td>
    <td>REST endpoints for tag management</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Create IngredientService and IngredientController</td>
    <td>REST endpoints for ingredient management</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td rowspan="2">Testing & Data</td>
    <td>Create database migration scripts or data.sql</td>
    <td>Sample data for testing API endpoints</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Write integration tests for main endpoints</td>
    <td>Test classes covering authentication and recipe endpoints</td>
    <td>Not Started</td>
  </tr>
</table>

### SPRINT 2 - Building up the client

<table width="100%">
  <tr>
    <th align="left" bgcolor="#E8E8E8">USER STORY</th>
    <th align="left" bgcolor="#E8E8E8">TASK</th>
    <th align="left" bgcolor="#E8E8E8">DELIVERABLE</th>
    <th align="left" bgcolor="#E8E8E8">STATUS</th>
  </tr>
  <tr>
    <td rowspan="4">Frontend Setup</td>
    <td>Create basic HTML structure with semantic elements</td>
    <td>index.html with header, nav, main, footer structure</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Setup CSS with CSS Grid/Flexbox for responsive layout</td>
    <td>styles.css with mobile-first responsive design</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Create JavaScript modules for API communication</td>
    <td>api.js with fetch-based HTTP client functions</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Implement simple client-side routing with hash navigation</td>
    <td>router.js for SPA-like navigation without page refresh</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td rowspan="5">Story 1 & 2: Recipe List & Filtering</td>
    <td>Create recipe list HTML template and CSS styling</td>
    <td>Recipe cards with CSS Grid layout, responsive design</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Build DOM manipulation functions for recipe rendering</td>
    <td>recipes.js with functions to create/update recipe elements</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Implement filter form with event listeners</td>
    <td>Filter sidebar with ingredient, tag, and time inputs</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Connect filters to API with debounced search</td>
    <td>Real-time filtering with 300ms debounce on input changes</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Add pagination with Previous/Next buttons</td>
    <td>Pagination controls updating recipe list via API calls</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td rowspan="5">Story 3 & 6: Authentication UI</td>
    <td>Create login/register modal with CSS animations</td>
    <td>Modal overlay with smooth transitions and form validation</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Implement form validation with vanilla JavaScript</td>
    <td>Client-side validation with error message display</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Build authentication state management in localStorage</td>
    <td>auth.js managing JWT token and user state persistence</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Create dynamic navigation bar showing user status</td>
    <td>Nav bar toggling between login/logout states</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Implement page access control based on auth state</td>
    <td>JavaScript functions hiding/showing content by user role</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td rowspan="3">Story 4: Recipe Details</td>
    <td>Create recipe detail page template</td>
    <td>HTML template with sections for ingredients, instructions, etc.</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Build recipe detail rendering functions</td>
    <td>JavaScript functions populating detail template with API data</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Add click event handlers for recipe card navigation</td>
    <td>Recipe cards linking to detail view via hash routing</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td rowspan="3">Story 5: Comments (Optional)</td>
    <td>Create comment section HTML and CSS</td>
    <td>Comment list and form styling for recipe details</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Implement comment form submission</td>
    <td>Form handling comment creation via API</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Add comment display with author and timestamp</td>
    <td>Dynamic comment rendering with user info</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td rowspan="3">Story 7: Admin Dashboard</td>
    <td>Create admin dashboard HTML layout</td>
    <td>Admin-only page with ingredient and tag management sections</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Build CRUD forms for ingredients and tags</td>
    <td>Create/Edit forms with validation and API integration</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Implement admin role-based access control</td>
    <td>JavaScript checking admin status and showing/hiding admin features</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td rowspan="4">Polish & Deploy</td>
    <td>Create custom CSS styling with modern design patterns</td>
    <td>CSS with variables, smooth animations, and professional appearance</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Add loading spinners and error handling UI</td>
    <td>Loading states and user-friendly error messages for API calls</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Implement responsive design for mobile/tablet/desktop</td>
    <td>Media queries ensuring usability across all device sizes</td>
    <td>Not Started</td>
  </tr>
  <tr>
    <td>Deploy static frontend and integrate with deployed backend</td>
    <td>Live application hosted on GitHub Pages or similar static host</td>
    <td>Not Started</td>
  </tr>
</table>
