
# Simple Banking 

Hello, I realized the Simple Banking project using Java 17, Spring Boot, Hibernate, Docker technologies. I am sharing the Docker installation command below. I will also share a Collection via Postman.

## Installation
Docker PostgreSQL
```bash
  docker run -d --name simple-banking-postgres -e POSTGRES_DB=simpleBanking -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres:latest
```

At the beginning of the project, the query I forwarded below runs automatically and creates the user.
```bash
  INSERT INTO bank_account (id, account_number, owner, balance, create_date, update_date) VALUES (1, '669-7788', 'Guney Caglayan', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

```

## Feedback

If you have any feedback, please reach out to us at guneycaglayann@gmail.com
