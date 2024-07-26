# ECommerceCart

## Development

Lombok must be supported by your IDE. For IntelliJ install the Lombok plugin and enable annotation processing -
[learn more](https://bootify.io/next-steps/spring-boot-with-lombok.html).

After starting the application it is accessible under `localhost:8080`.

An in memory H2 database is used, which can be accessed at `http://localhost:8080/h2-console`

    JDBC URL: "jdbc:h2:mem:e-commerce-cart"
    User Name: "sa"
    Password: ""

API documentation can be found at `http://localhost:8080/swagger-ui/index.html`

## Build

The application can be built using the following command:

```
mvnw clean package
```

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/e-commerce-cart-0.0.1-SNAPSHOT.jar
```


