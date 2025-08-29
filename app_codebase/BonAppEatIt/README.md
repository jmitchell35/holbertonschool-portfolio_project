# BonAppEatIt

## Prerequisites

* Install **Docker Desktop**
* Install **VS Code / IntelliJ IDEA**
* Install **Dev Container** (Microsoft opensource project) VSCode extension / IntelliJ IDEA Ultimate plugin.

The codebase avoids compromising production secrets by the use of a dev container and dev credentials.

## Installation

Clone the repo locally.

Open with your favored IDE. Re-open in dev container.

## Troubleshooting

Known issues and workarounds  
*  Failing to pull docker image : check local network DNS config, set up docker DNS config
```bash
Running the postCreateCommand from devcontainer.json...
[20042 ms] Start: Run in container: /bin/sh -c bash -c 'sudo chown -R vscode:vscode /home/vscode/.m2 && mvn -e -X -f app_codebase/BonAppEatIt/pom.xml dependency:resolve'
OCI runtime exec failed: exec failed: unable to start container process: chdir to cwd ("/workspaces/holbertonschool-portfolio_project") set in config.json failed: no such file or directory: unknown
[20105 ms] postCreateCommand from devcontainer.json failed with exit code 126. Skipping any further user-provided commands.
```
* Failing to launch docker : forgot to install Docker Desktop ?
```bash
[5743 ms] Error: Exectuable 'docker' not found on PATH
```
You may need to set the connection to the Data Source for IDE DB GUI.

```
Host     : localhost
Port     : 5432
User     : postgres
Password : postgres
URL      : jdbc:postgresql://localhost:5432/bonappeatit_dev
```