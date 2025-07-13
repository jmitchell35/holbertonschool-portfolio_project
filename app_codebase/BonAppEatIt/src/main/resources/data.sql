-- Base roles for users
INSERT INTO roles (role_id, role, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'ROLE_USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'ROLE_ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    ON CONFLICT (role) DO NOTHING;

-- Create Admin user
INSERT INTO users (user_id, email, password, password_updated_at, is_verified, is_active, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'th75plu4a@mozmail.com', '$2a$12$gcnoUWbp2JkvkcuUSTv8R.EZIyZZBtsWhnTE5NPZs42CiYRX26yWu', CURRENT_TIMESTAMP, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (email) DO NOTHING;

-- Link admin user to admin role using subquery
INSERT INTO user_roles (user_id, role_id)
SELECT
    u.user_id,
    r.role_id
FROM users u, roles r
WHERE u.email = 'th75plu4a@mozmail.com'
  AND r.role = 'ROLE_ADMIN'
ON CONFLICT DO NOTHING;

-- Also link to user role
INSERT INTO user_roles (user_id, role_id)
SELECT
    u.user_id,
    r.role_id
FROM users u, roles r
WHERE u.email = 'th75plu4a@mozmail.com'
  AND r.role = 'ROLE_USER'
ON CONFLICT DO NOTHING;

-- Create regular user
INSERT INTO users (user_id, email, password, password_updated_at, is_verified, is_active, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'test@example.com', '$2a$12$WuOOclBlqkaBpFZg5gp/pejTElxK.W42iZxXY7eXGQqY4sA296nne', CURRENT_TIMESTAMP, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (email) DO NOTHING;

-- Link to user role
INSERT INTO user_roles (user_id, role_id)
SELECT
    u.user_id,
    r.role_id
FROM users u, roles r
WHERE u.email = 'test@example.com'
  AND r.role = 'ROLE_USER'
ON CONFLICT DO NOTHING;

-- Insert base units
INSERT INTO units (unit_id, name, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'ml', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cl', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'l', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cuillère(s) à café', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cuillère(s) à soupe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'verre(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'tasse(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'louche(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'g', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'kg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'livre(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'once(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'gallon(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'pièce(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'tranche(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'gousse(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'brin(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'pincée(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'poignée(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'filet(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'steak(s)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (name) DO NOTHING;

-- Insert base ingredients
-- Fruits
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'pomme', 'pommes', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'poire', 'poires', true, true, false, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'orange', 'oranges', true, true, true, false, false, false, false, false, false, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'citron', 'citrons', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'fraise', 'fraises', false, false, false, true, true, true, false, false, false, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'framboise', 'framboises', false, false, false, false, false, true, true, true, false, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'myrtille', 'myrtilles', false, false, false, false, false, true, true, true, false, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'pêche', 'pêches', false, false, false, false, false, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'abricot', 'abricots', false, false, false, false, false, true, true, false, false, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'raisin', 'raisins', false, false, false, false, false, false, false, false, true, true, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'banane', 'bananes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'kiwi', 'kiwis', true, true, true, true, false, false, false, false, false, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Leaf and stem vegetables
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'épinard', 'épinards', true, true, true, true, true, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'salade verte', 'salades vertes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'roquette', 'roquettes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'mâche', 'mâches', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'chou', 'choux', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'chou-fleur', 'choux-fleurs', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'brocoli', 'brocolis', true, true, true, true, true, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'poireau', 'poireaux', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'céleri', 'céleris', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'endive', 'endives', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Fruit vegetables
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'tomate', 'tomates', false, false, false, false, true, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'courgette', 'courgettes', false, false, false, false, true, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'aubergine', 'aubergines', false, false, false, false, false, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'poivron', 'poivrons', false, false, false, false, false, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'concombre', 'concombres', false, false, false, false, true, true, true, true, false, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cornichon', 'cornichons', false, false, false, false, false, true, true, true, false, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'avocat', 'avocats', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Squashes and tubers
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'pomme de terre', 'pommes de terre', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'patate douce', 'patates douces', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'carotte', 'carottes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'navet', 'navets', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'radis', 'radis', true, true, true, true, true, true, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'betterave', 'betteraves', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'potiron', 'potirons', false, false, false, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'citrouille', 'citrouilles', false, false, false, false, false, false, false, false, true, true, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'butternut', 'butternuts', false, false, false, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'pâtisson', 'pâtissons', false, false, false, false, false, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Legumes and onions
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'oignon', 'oignons', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'échalote', 'échalotes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'ail', 'ails', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'oignon rouge', 'oignons rouges', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'petit pois', 'petits pois', false, false, false, true, true, true, false, false, false, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'haricot vert', 'haricots verts', false, false, false, false, true, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'fève', 'fèves', false, false, false, true, true, true, false, false, false, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Aromatics, fresh herbs
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'persil', 'persils', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'basilic', 'basilics', false, false, false, true, true, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'ciboulette', 'ciboulettes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'thym', 'thyms', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'romarin', 'romarins', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'origan', 'origans', false, false, false, true, true, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'menthe', 'menthes', false, false, true, true, true, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'coriandre', 'coriandres', false, false, false, true, true, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'aneth', 'aneths', false, false, false, true, true, true, true, true, false, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'estragon', 'estragons', false, false, false, true, true, true, true, true, false, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'sauge', 'sauges', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'laurier', 'lauriers', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Spices
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'poivre noir', 'poivres noirs', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'sel', 'sels', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'paprika', 'paprikas', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cumin', 'cumins', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cannelle', 'cannelles', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'muscade', 'muscades', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'gingembre', 'gingembres', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'curcuma', 'curcumas', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'piment', 'piments', false, false, false, false, true, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'safran', 'safrans', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'vanille', 'vanilles', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'clou de girofle', 'clous de girofle', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Meats
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'jambon', 'jambons', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'lard', 'lards', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'sanglier', 'sangliers', true, true, false, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'chevreuil', 'chevreuils', true, true, false, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Beef cuts
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'steak de bœuf', 'steaks de bœuf', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'bœuf haché', 'bœufs hachés', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'côte de bœuf', 'côtes de bœuf', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'filet de bœuf', 'filets de bœuf', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'rôti de bœuf', 'rôtis de bœuf', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Chicken cuts
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'blanc de poulet', 'blancs de poulet', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cuisse de poulet', 'cuisses de poulet', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'pilon de poulet', 'pilons de poulet', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'aile de poulet', 'ailes de poulet', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'poulet entier', 'poulets entiers', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Turkey cuts
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'blanc de dinde', 'blancs de dinde', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cuisse de dinde', 'cuisses de dinde', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'escalope de dinde', 'escalopes de dinde', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'aile de dinde', 'ailes de dinde', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'dinde entière', 'dindes entières', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'rôti de dinde', 'rôtis de dinde', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Pork cuts
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'côte de porc', 'côtes de porc', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'filet de porc', 'filets de porc', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'rôti de porc', 'rôtis de porc', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'porc haché', 'porcs hachés', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'échine de porc', 'échines de porc', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'palette de porc', 'palettes de porc', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'jarret de porc', 'jarrets de porc', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'travers de porc', 'travers de porc', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Duck cuts
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'magret de canard', 'magrets de canard', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cuisse de canard', 'cuisses de canard', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'confit de canard', 'confits de canard', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'aiguillette de canard', 'aiguillettes de canard', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'canard entier', 'canards entiers', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'foie gras de canard', 'foies gras de canard', true, true, false, false, false, false, false, false, false, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Rabbit cuts
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'lapin entier', 'lapins entiers', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'râble de lapin', 'râbles de lapin', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cuisse de lapin', 'cuisses de lapin', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'épaule de lapin', 'épaules de lapin', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'civet de lapin', 'civets de lapin', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Sausages and charcuterie
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'merguez', 'merguez', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'chipolata', 'chipolatas', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'saucisse de Francfort', 'saucisses de Francfort', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'saucisse de Toulouse', 'saucisses de Toulouse', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'saucisse végétarienne', 'saucisses végétariennes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'saucisse végétale', 'saucisses végétales', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'knack d''Alsace', 'knacks d''Alsace', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'saucisse de canard', 'saucisses de canard', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'chorizo', 'chorizos', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'boudin noir', 'boudins noirs', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'boudin blanc', 'boudins blancs', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'andouillette', 'andouillettes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cervelas', 'cervelas', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Lamb cuts
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'gigot d''agneau', 'gigots d''agneau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'côte d''agneau', 'côtes d''agneau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'épaule d''agneau', 'épaules d''agneau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'carré d''agneau', 'carrés d''agneau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'selle d''agneau', 'selles d''agneau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'agneau haché', 'agneaux hachés', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'navarin d''agneau', 'navarins d''agneau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'souris d''agneau', 'souris d''agneau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Veal cuts
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'escalope de veau', 'escalopes de veau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'côte de veau', 'côtes de veau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'rôti de veau', 'rôtis de veau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'blanquette de veau', 'blanquettes de veau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'osso buco de veau', 'osso bucos de veau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'jarret de veau', 'jarrets de veau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'foie de veau', 'foies de veau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'ris de veau', 'ris de veau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'noix de veau', 'noix de veau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'quasi de veau', 'quasi de veau', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Christmas poultry
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'chapon', 'chapons', true, false, false, false, false, false, false, false, false, false, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'poularde', 'poulardes', true, false, false, false, false, false, false, false, false, false, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'oie', 'oies', true, false, false, false, false, false, false, false, false, false, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'pintade', 'pintades', true, false, false, false, false, false, false, false, false, false, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'caille', 'cailles', true, false, false, false, false, false, false, false, false, false, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Fish and seafood
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'saumon', 'saumons', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'thon', 'thons', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cabillaud', 'cabillauds', true, true, true, true, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'sole', 'soles', true, true, true, true, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'truite', 'truites', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'sardine', 'sardines', false, false, false, false, true, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'maquereau', 'maquereaux', false, false, false, true, true, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'dorade', 'dorades', false, false, false, false, true, true, true, true, true, false, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'crevette', 'crevettes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'moule', 'moules', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'huître', 'huîtres', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'coquille Saint-Jacques', 'coquilles Saint-Jacques', true, true, true, false, false, false, false, false, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'crabe', 'crabes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Dairy products
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'lait', 'laits', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'beurre', 'beurres', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'crème fraîche', 'crèmes fraîches', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'yaourt nature', 'yaourts natures', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'fromage blanc', 'fromages blancs', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'gruyère', 'gruyères', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'camembert', 'camemberts', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'roquefort', 'roqueforts', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'chèvre', 'chèvres', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'parmesan', 'parmesans', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'mozzarella', 'mozzarellas', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'œuf', 'œufs', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- French regional cheeses
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'emmental', 'emmentals', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'emmental râpé', 'emmental râpé', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'comté', 'comtés', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'beaufort', 'beauforts', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'brie de Meaux', 'bries de Meaux', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'brie de Melun', 'bries de Melun', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'reblochon', 'reblochons', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'munster', 'munsters', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'pont-l''évêque', 'pont-l''évêques', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'maroilles', 'maroilles', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Additional popular cheeses
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'cantal', 'cantals', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'saint-nectaire', 'saint-nectaires', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'crottin de chavignol', 'crottins de chavignol', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'boursin', 'boursins', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'saint-marcellin', 'saint-marcellins', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'époisses', 'époisses', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'fourme d''Ambert', 'fourmes d''Ambert', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'bleu d''Auvergne', 'bleus d''Auvergne', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'mimolette', 'mimolettes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'livarot', 'livarots', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Starches and grains
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'riz', 'riz', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'pâte', 'pâtes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'spaghetti', 'spaghetti', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'maccheroni', 'maccheroni', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'penne', 'penne', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'fusilli', 'fusilli', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'tagliatelle', 'tagliatelle', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'nouilles', 'nouilles', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'coquillettes', 'coquillettes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'linguine', 'linguine', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'farfalle', 'farfalle', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'farfaline', 'farfaline', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'fettuccine', 'fettuccine', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'cannelloni', 'cannelloni', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'trofie', 'trofie', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'fusilli bucati', 'fusilli bucati', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'fusilloni', 'fusilloni', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'quinoa', 'quinoa', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'boulgour', 'boulgour', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'semoule', 'semoules', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'avoine', 'avoines', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'orge', 'orges', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'lentilles', 'lentilles', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'lentilles corail', 'lentilles corail', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'lentilles vertes', 'lentilles vertes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'haricots blanc', 'haricots blancs', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'haricots rouge', 'haricots rouges', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'pois chiches', 'pois chiches', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'pois cassés', 'pois cassés', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'flageollets', 'flageollets', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Oils and condiments
INSERT INTO ingredients (ingredient_id, ingredient_singular, ingredient_plural, available_in_january, available_in_february, available_in_march, available_in_april, available_in_may, available_in_june, available_in_july, available_in_august, available_in_september, available_in_october, available_in_november, available_in_december, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'huile d''olive', 'huiles d''olive', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'huile de tournesol', 'huiles de tournesol', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'vinaigre', 'vinaigres', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'vinaigre balsamique', 'vinaigres balsamiques', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'moutarde', 'moutardes', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'mayonnaise', 'mayonnaises', true, true, true, true, true, true, true, true, true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (ingredient_singular) DO NOTHING;

-- Base tags
INSERT INTO tags (tag_id, name, background_color_hex, font_color_hex, created_at, updated_at)
VALUES
    (gen_random_uuid(),'Cuisine italienne','#009246','#FFFFFF',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(),'Cuisine française','#002654','#FFFFFF',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(),'Cuisine indienne','#FF9933','#128807',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(),'Cuisine japonaise','#FFFFFF','#BC002D',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(),'Cuisine chinoise','#DE2812','#FFDC00',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(),'Cuisine végétarienne','#00D668','#00572A',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(),'Cuisine étatsunienne','#113765','#B52047',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(),'Cuisine canadienne','#D63125','#FFFFFF',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(),'Cuisine québécoise','#0843A7','#FFFFFF',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (name) DO NOTHING;
