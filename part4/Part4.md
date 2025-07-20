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
    <td>S1 - IBM Certifications</td>
    <td>June 2nd</td>
    <td>June 13th</td>
    <td>2 mandatory certifications + Java optional certification</td>
  </tr>
  <tr>
    <td>S2 - back-end</td>
    <td>June 16th</td>
    <td>June 29th</td>
    <td>Finish the back-end part of the project : API routes, Service Layer, Repository Layer</td>
  </tr>
  <tr>
    <td>S3 - Build the client</td>
    <td>June 30th</td>
    <td>July 14th</td>
    <td>Build up the client : html pages, CSS styles, js scripts</td>
  </tr>
</table>

### SPRINT 1 - IBM certifications

<table width="100%">
  <tr>
    <th align="left" bgcolor="#E8E8E8">USER STORY</th>
    <th align="left" bgcolor="#E8E8E8">TASK</th>
    <th align="left" bgcolor="#E8E8E8">DELIVERABLE</th>
    <th align="left" bgcolor="#E8E8E8">PRIORITY</th>
    <th align="left" bgcolor="#E8E8E8">STATUS</th>
  </tr>
  <tr>
    <td rowspan="3">Not applicable</td>
    <td>IBM Cloud essentials</td>
    <td>Certificate</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td rowspan="3">Not applicable</td>
    <td>IBM Cybersecurity fundamentals</td>
    <td>Certificate</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td rowspan="3">Not applicable</td>
    <td>Coding in Java</td>
    <td>Certificate</td>
    <td>Optional - direct project connection</td>
    <td>Done</td>
  </tr>
</table>

### SPRINT 2 - Finishing the Back-End - API first approach

<table width="100%">
  <tr>
    <th align="left" bgcolor="#E8E8E8">USER STORY</th>
    <th align="left" bgcolor="#E8E8E8">TASK</th>
    <th align="left" bgcolor="#E8E8E8">DELIVERABLE</th>
    <th align="left" bgcolor="#E8E8E8">PRIORITY</th>
    <th align="left" bgcolor="#E8E8E8">STATUS</th>
  </tr>
  <tr>
    <td rowspan="3">Foundation Setup</td>
    <td>Configure PostgreSQL database connection</td>
    <td>application.properties with DB config</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Create database schema/tables</td>
    <td>SQL migration scripts or JPA auto-generation</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Add Spring Data JPA repositories</td>
    <td>Repository interfaces for all entities</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td rowspan="2">User Story 1: Recipe List</td>
    <td>Create RecipeService with findAll method</td>
    <td>RecipeService.java with basic CRUD operations</td>
    <td>Must Have</td>
    <td>Partially done - reste of CRUDs delayed to admin dashboard completion</td>
  </tr>
  <tr>
    <td>Create RecipeController with GET /api/v1/recipes endpoint</td>
    <td>REST endpoint returning JSON recipe list</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td rowspan="3">User Story 2: Recipe Filtering</td>
    <td>Add filtering methods to RecipeService</td>
    <td>Methods for ingredient, tag, and ingredient count filters</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Create query parameters in RecipeController</td>
    <td>GET /api/v1/recipes with query params</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Add TagService and IngredientService for filter options</td>
    <td>Services to provide available tags and ingredients</td>
    <td>Should Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td rowspan="4">User Story 3: Authentication</td>
    <td>Configure Spring Security</td>
    <td>SecurityConfig.java with basic auth setup</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Create UserService with registration logic</td>
    <td>UserService.java with password hashing</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Create AuthController with login/register endpoints</td>
    <td>POST /api/v1/auth/login and /register</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Add JWT token generation (optional for MVP)</td>
    <td>JWT-based authentication</td>
    <td>Could Have</td>
    <td>To Do - Will be part of preparation for mobile app - irrelevant too early in the project</td>
  </tr>
  <tr>
    <td rowspan="2">User Story 4: Recipe Details</td>
    <td>Create GET /api/v1/recipes/{id} endpoint</td>
    <td>Endpoint returning full recipe details with ingredients</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Optimize recipe queries to include ingredients and instructions</td>
    <td>Efficient JPA queries with joins</td>
    <td>Should Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td rowspan="2">User Story 5: Comments (Basic)</td>
    <td>Create Comment entity and repository</td>
    <td>Comment.java model and CommentRepository</td>
    <td>Should Have</td>
    <td>To Do - Delayed - not a core feature</td>
  </tr>
  <tr>
    <td>Add POST /api/v1/recipes/{id}/comments endpoint</td>
    <td>Authenticated endpoint for adding comments</td>
    <td>Should Have</td>
    <td>To Do - Delayed - not a core feature</td>
  </tr>
  <tr>
    <td rowspan="2">User Story 7: Admin Features</td>
    <td>Create TagController for admin tag creation</td>
    <td>POST /api/v1/admin/tags endpoint</td>
    <td>Won't Have (MVP)</td>
    <td>To Do - delayed to admin dashboard completion</td>
  </tr>
  <tr>
    <td>Create IngredientController for admin ingredient creation</td>
    <td>POST /api/v1/admin/ingredients endpoint</td>
    <td>Won't Have (MVP)</td>
    <td>To Do - delayed to admin dashboard completion</td>
  </tr>
  <tr>
    <td rowspan="2">Documentation & Testing</td>
    <td>Add Swagger/OpenAPI documentation</td>
    <td>Auto-generated API documentation</td>
    <td>Should Have</td>
    <td>To Do - delayed for time management</td>
  </tr>
  <tr>
    <td>Write basic integration tests</td>
    <td>Test classes for main endpoints</td>
    <td>Could Have</td>
    <td>To Do - delayed - back-end tested manually with Bruno, Curl - no time to go through JUnit, Mockito...</td>
  </tr>
</table>


### SPRINT 2 - Building up the client


<table width="100%">
  <tr>
    <th align="left" bgcolor="#E8E8E8">USER STORY</th>
    <th align="left" bgcolor="#E8E8E8">TASK</th>
    <th align="left" bgcolor="#E8E8E8">DELIVERABLE</th>
    <th align="left" bgcolor="#E8E8E8">PRIORITY</th>
    <th align="left" bgcolor="#E8E8E8">STATUS</th>
  </tr>
  <tr>
    <td rowspan="3">Foundation Setup</td>
    <td>Create basic HTML structure and navigation</td>
    <td>index.html with header, nav, main sections</td>
    <td>Must Have</td>
    <td>Done late (July 12th)</td>
  </tr>
  <tr>
    <td>Set up CSS framework/base styles</td>
    <td>styles.css with responsive grid and base styling</td>
    <td>Must Have</td>
    <td>DOne</td>
  </tr>
  <tr>
    <td>Create JavaScript API service module</td>
    <td>api.js for making HTTP requests to backend</td>
    <td>Must Have</td>
    <td>Done - Structure adapted - will have to be refactored</td>
  </tr>
  <tr>
    <td rowspan="3">User Story 1: Recipe List Display</td>
    <td>Create recipe list component</td>
    <td>HTML template and JS for displaying recipe cards</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Implement recipe card styling</td>
    <td>CSS for attractive recipe cards with images</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Add loading states and error handling</td>
    <td>Loading spinners and error messages</td>
    <td>Should Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td rowspan="4">User Story 2: Recipe Filtering</td>
    <td>Create filter sidebar/section</td>
    <td>HTML form with ingredient, tag, and count filters</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Implement filter JavaScript logic</td>
    <td>JS functions to apply filters and update recipe list</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Add filter reset functionality</td>
    <td>Clear filters button and logic</td>
    <td>Should Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Style filters with responsive design</td>
    <td>Mobile-friendly filter UI</td>
    <td>Should Have</td>
    <td>Partially done - web responsive, fails some mobile devices</td>
  </tr>
  <tr>
    <td rowspan="4">User Story 3: Authentication UI</td>
    <td>Create login/register modal or page</td>
    <td>HTML forms for login and registration</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Implement authentication JavaScript</td>
    <td>JS for form submission and token handling</td>
    <td>Must Have</td>
    <td>Done - Session handling instead of token handling</td>
  </tr>
  <tr>
    <td>Add form validation</td>
    <td>Client-side validation for email and password</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Update navigation based on auth state</td>
    <td>Show/hide login/logout buttons dynamically</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td rowspan="3">User Story 4: Recipe Details Page</td>
    <td>Create recipe detail page template</td>
    <td>HTML template for full recipe display</td>
    <td>Must Have</td>
    <td>Done (Late - July 17th)</td>
  </tr>
  <tr>
    <td>Implement recipe detail JavaScript</td>
    <td>JS to fetch and display recipe details</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Style recipe detail page with ingredients list and instructions</td>
    <td>CSS for readable recipe layout</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td rowspan="2">User Story 5: Comments Display</td>
    <td>Add comments section to recipe detail page</td>
    <td>HTML template for comments display and form</td>
    <td>Should Have</td>
    <td>To Do - Delayed, not a core feature</td>
  </tr>
  <tr>
    <td>Implement comment submission JavaScript</td>
    <td>JS for posting and displaying comments</td>
    <td>Should Have</td>
    <td>To Do - Delayed, not a core feature</td>
  </tr>
  <tr>
    <td rowspan="2">User Story 6: Logout</td>
    <td>Add logout button functionality</td>
    <td>Logout button that clears session</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td>Handle logout state changes</td>
    <td>Update UI when user logs out</td>
    <td>Must Have</td>
    <td>Done</td>
  </tr>
  <tr>
    <td rowspan="3">Polish & Optimization</td>
    <td>Add responsive design for mobile</td>
    <td>Mobile-optimized layouts and navigation</td>
    <td>Should Have</td>
    <td>To Do - not fully responsive with all mobile devices</td>
  </tr>
  <tr>
    <td>Implement search functionality</td>
    <td>Search bar for finding recipes by name</td>
    <td>Could Have</td>
    <td>To Do - not core feature - in the backlog</td>
  </tr>
  <tr>
    <td>Add animations and micro-interactions</td>
    <td>CSS transitions and hover effects</td>
    <td>Could Have</td>
    <td>Done</td>
  </tr>
</table>

## Takeaways

<table width="100%">
  <tr>
    <th align="left" bgcolor="#E8E8E8">SPRINT</th>
    <th align="left" bgcolor="#E8E8E8">START</th>
    <th align="left" bgcolor="#E8E8E8">FINISH</th>
    <th align="left" bgcolor="#E8E8E8">TAKEAWAY</th>
  </tr>
  <tr>
    <td>S1 - IBM Certifications</td>
    <td>June 2nd</td>
    <td>June 13th => June 16th</td>
    <td>Very long certifications. It takes a lot a time to complete.</td>
  </tr>
  <tr>
    <td>S2 - back-end</td>
    <td>June 16th</td>
    <td>June 29th => July 11th</td>
    <td>Solid foundation is key, but also takes a lot of time. I guess I should have restricted the MVP even more.</td>
  </tr>
  <tr>
    <td>S3 - Build the client</td>
    <td>June 30th => July 11th</td>
    <td>July 14th => July 17th</td>
    <td>Built in hurry due to the delays stacking up. However, the solid back-end foundations made it easier, although a few adjustments were required.</td>
  </tr>
</table>
