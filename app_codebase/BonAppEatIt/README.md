# BonAppEatIt

## Prerequisites

Install Docker desktop.
Install VS Code / IntelliJ IDEA
Install Dev Container VSCode extension / IntelliJ IDEA Ultimate plugin.

The codebase avoids compromising production secrets by the use of a dev container and dev credentials.

## Installation

Clone the repo locally.

Open with your favored IDE. Re-open in dev container.

You may need to set the connection to the Data Source for IDE DB GUI.

```
Host     : localhost
Port     : 5432
User     : postgres
Password : postgres
URL      : jdbc:postgresql://localhost:5432/bonappeatit_dev
```