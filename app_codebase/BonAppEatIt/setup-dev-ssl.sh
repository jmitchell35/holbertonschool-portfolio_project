#!/bin/bash
# setup-dev-ssl.sh

# DEBUG: Show what the script sees
echo "DEBUG: Length check: $([ -z "$BONAPPEATIT_DEV_KEYSTORE_PASSWORD" ] && echo "EMPTY" || echo "NOT EMPTY")"

# Check if password is set
if [ -z "$BONAPPEATIT_DEV_KEYSTORE_PASSWORD" ]; then
    echo "Please set BONAPPEATIT_DEV_KEYSTORE_PASSWORD environment variable first:"
    echo "export BONAPPEATIT_DEV_KEYSTORE_PASSWORD=your-dev-password"
    exit 1
fi

# Create SSL directory (-p = if doesn't exist)
mkdir -p ~/.ssl

# Generate certificate using environment variable
keytool -genkeypair -alias bonappeatit -keyalg RSA -keysize 2048 \
    -storetype PKCS12 -keystore ~/.ssl/bonappeatit-dev-keystore.p12 \
    -validity 365 -dname "CN=localhost,OU=Development,O=BonAppEatIt" \
    -storepass "$BONAPPEATIT_DEV_KEYSTORE_PASSWORD"

echo "Development SSL certificate created"