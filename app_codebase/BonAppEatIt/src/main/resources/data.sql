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