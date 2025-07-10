#!/bin/bash
# setup-dev-db.sh

# Clean up existing DB : running setup-script means you want a fresh start, no migration
PGPASSWORD=$POSTGRESQL_PASSWORD psql -h localhost -U $POSTGRESQL_USER -d postgres -c "DROP DATABASE IF EXISTS bonappeatit_dev;"

echo "Setting up BonAppEatIt development database..."

# Check if environment variables are set
if [ -z "$POSTGRESQL_USER" ] || [ -z "$POSTGRESQL_PASSWORD" ]; then
    echo "Please set POSTGRESQL_USER and POSTGRESQL_PASSWORD environment variables"
    exit 1
fi

# Check if database exists
PGPASSWORD=$POSTGRESQL_PASSWORD psql -h localhost -U $POSTGRESQL_USER -d postgres -tc "SELECT 1 FROM pg_database WHERE datname = 'bonappeatit_dev'" | grep -q 1

if [ $? -ne 0 ]; then
    echo "Database doesn't exist. Creating bonappeatit_dev..."
    PGPASSWORD=$POSTGRESQL_PASSWORD psql -h localhost -U $POSTGRESQL_USER -d postgres -c "CREATE DATABASE bonappeatit_dev;"

    # Check if the CREATE DATABASE command succeeded
    if [ $? -eq 0 ]; then
        echo "Database created successfully!"
    else
        echo "Error: Failed to create database!"
        exit 1
    fi
else
    echo "Database bonappeatit_dev already exists."
fi

echo "Database setup complete!"