
**Project Overview**

GitHub Repos API - an application written in Java 21 and based on Quarkus 3.19 that retrieves and returns a list of user
repositories from GitHub, excluding repositories that are forks.

For each repository returned are:
- Repository Name
- Owner Login
- For each branch it’s name and last commit sha

**Tech/framework used** 

- Java 21 
- Quarkus 3.19
- Mutiny 
- Gradle
- JUnit 5

**Installation and Running**

1. Clone the repository:

```bash
git clone https://github.com/krzysztofKolodziej/github-repos-api.git
cd github-repos-api 
```

2. Build the project

```bash
./gradlew build
```

3. Run in development mode:

```bash
./gradlew quarkusDev
```

**API Endpoints**

- GET /github/{username}

Retrieves the list of repositories for a given GitHub username (excluding forks).

- Example:

```bash
curl http://localhost:8080/github/krzysztofKolodziej
```

**Error Handling**

If the provided GitHub username does not exist, the API will return a 404 error with a response similar to:

```JSON
{
"status": 404,
"message": "GitHub user or repository not found."
}
```

**Testing**

Run the tests using Gradle:

```bash
./gradlew test
```
A sample integration test is included to verify the "happy path" scenario.





