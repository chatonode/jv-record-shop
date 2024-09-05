```properties
# Local - PostgreSQL

spring.jpa.database=postgresql
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://<OUR-DOMAIN-NAME>:<OUR-DOMAIN-PORT>/<OUR-DB-NAME>
spring.datasource.username=<OUR-USERNAME>
spring.datasource.password=<OUR-PASSWORD>
spring.jpa.database-platform=org.hibernate.dialect.PostgresPlusDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```