# BonAppEatIt

## Prerequisites

Install postgresql and postgresql-server.

Initialize the postgre's local database (users) : 

```bash
sudo postgresql-setup --initdb
```

Set up default user's password.

Don't forget to change the identification mode to md5 to allow credentials login

```bash
sudo -u postgres psql -c "SHOW hba_file;"

sudo YOUR_TEXT_EDITOR /the/path/you/just/got
```

```bash
# TYPE  DATABASE        USER            ADDRESS                 METHOD

# "local" is for Unix domain socket connections only
local   all             all                                     peer
# IPv4 local connections:
host    all             all             127.0.0.1/32            md5
# IPv6 local connections:
host    all             all             ::1/128                 md5
# Allow replication connections from localhost, by a user with the
# replication privilege.
local   replication     all                                     peer
host    replication     all             127.0.0.1/32            md5
host    replication     all             ::1/128                 md5
```

The codebase avoids hardcoded passwords. Please start by setting required environment variables or set them in a file

```bash
echo 'POSTGRESQL_USER=username_you_defined_in_postgresql' >> YOUR_LOCAL_BASHRC_OR_YOUR_ENV_FILE

echo 'POSTGRESQL_PASSWORD=password_you_defined_in_postgresql' >> YOUR_LOCAL_BASHRC_OR_YOUR_ENV_FILE

echo 'BONAPPEATIT_DEV_KEYSTORE_PASSWORD=password_of_your_choice' >> YOUR_LOCAL_BASHRC_OR_YOUR_ENV_FILE
```

Refresh your env if you're using your .bashrc
```bash
source ~/.bashrc
```

Then run the script below from the project's directory.
It will create a `.ssl` directory in your home directory if it doesn't already exist, and create a keystore with the above `BONAPPEATIT_DEV_KEYSTORE_PASSWORD`. 
```bash
~/BonAppEatIt$ ./setup-dev-ssl.sh
```

In case you can't properly access the env variables when running the app you may have to define them as IDE env variables.

Before running the app, make sure you have your DB service running :
```bash
sudo systemctl start postgresql
```
