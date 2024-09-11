# Record Shop API: Your Gateway to Musical Bliss 🎵

Welcome to the Record Shop API, where the rhythm of your favorite albums, artists, and genres come alive through a sleek and efficient RESTful service. Designed with the latest technologies and best practices in mind, this API is your digital record store, built to scale and secure your musical treasures.

> [Live Demo - Swagger UI](http://record-shop-api-env.eba-k7juhuv2.eu-west-2.elasticbeanstalk.com/api/v1/swagger-ui/index.html)

## 🚀 Getting Started

### Prerequisites

Before diving into the project, ensure you have the following installed:

- **Java 21**: [Download Java](https://www.oracle.com/uk/java/technologies/downloads/#java21)
- **Maven**: [Get Maven](https://maven.apache.org/install.html)
- **IntelliJ IDEA**: [Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

### Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/chatonode/jv-record-shop.git
   ```

2. **Navigate to the Project Directory:**

   ```bash
   cd recordshopapi
   ```

3. **Build the Project:**

   ```bash
   mvn clean install
   ```

4. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

   Your Record Shop API will be up and running locally at `http://localhost:8080/api/v1`. This is the base URL for all your API interactions, whether you're managing albums, artists, or genres.

### Configuration

Customize your application settings using the properties files in `src/main/resources`:

- **`application.properties`**: Holds active profile (as `prod`) and **Swagger UI** url path info 
- **`application-test.properties`**: Testing configurations with **H2** database, select this one as an active profile in the application
- **`application-dev.properties`**: Development environment settings with **PostgreSQL** connection
- **`application-prod.properties`**: Production settings for **AWS RDS**

### Testing, Packaging & Deploying
#### 1. Testing
1. Switch profile to `test` in `application.properties`
2. Run `mvn test` to see all tests (including `repository` tests) **PASS**

#### 2. Packaging
1. Switch profile back to `prod` in `application.properties`
2. `mvn package -D skipTests`
   - The Maven build process will generate a JAR file inside the `target/` directory

#### 2.1. Local Dockerisation
1. `docker build -t recordshopapi:1.0 .`
   - Prepend `sudo` to this `docker` command if it does not work
2. Verify that the image was created by listing all available Docker images with `docker images`

### 3. Deploying to AWS Elastic Beanstalk
1. Open the project directory in your file explorer (do not compress the folder, just the contents).
2. Select all the files and directories inside the project root (where your `Dockerfile`, `pom.xml`, etc., are located).
3. Right-click and select **Compress** (on Mac) or **Send to > Compressed (zipped)** folder (on Windows).
4. This will create a `.zip` file containing the project contents.
   - **Important**: Do not compress the parent folder itself; AWS Elastic Beanstalk expects the zipped contents, not a single directory.
5. Click **Choose file** on your AWS Elastic Beanstalk application installation and upload the `.zip` file you created in the previous step.
6. Create your AWS Elastic Beanstalk application on your AWS dashboard.
   - AWS Elastic Beanstalk will take a few minutes to launch your environment. You can track progress in the Elastic Beanstalk dashboard.
   - Once the deployment is successful, you will see a message: **Environment successfully launched.**

### 4. Interact with Your Application
#### 4.1. Access Swagger UI
1- To access the Swagger UI of your deployed application:
   - Go to: `http://<your-app-domain>/api/v1/swagger-ui/index.html`

This will allow you to interact with your API documentation directly in the browser.


### 5. Congrats!

You have successfully deployed your Spring Boot application in Docker on AWS Elastic Beanstalk. You can now access and interact with the API through Postman or the Swagger UI using the domain provided by AWS.

## 📚 Project Structure

The Record Shop API is organized to keep your code clean and maintainable:

- **Controllers** handle incoming HTTP requests and map them to services
- **DTOs** manage data transfer between client and server, and validate request bodies before controller
- **Services** encapsulate business logic
- **Repositories** interface with the database using Spring Data JPA
- **Exception Handling** provides a global mechanism to handle errors gracefully
- **Mappers** convert between DTOs and entities

## 🌟 Future Enhancements

We’re just getting started! Here are some exciting features on the roadmap:

### Spring Security Integration

To protect your API endpoints and secure user data, we plan to integrate Spring Security. This will provide:

- Authentication
  - Oauth2/GitHub
  - JWT
- Authorization
- Secure Password Storage
- Role-Based Access Control

## 🔄 Continuous Integration (CI)

We use GitHub Actions for continuous integration to ensure code quality and reliability.

This configuration ensures that every pull request is tested, helping maintain a high standard of code quality.

## 📜 Contributing

We welcome contributions to enhance the Record Shop API. Feel free to open issues or submit pull requests.

## 📧 Contact

For any questions or feedback, reach out to us at [chatonode@gmail.com](mailto:chatonode@gmail.com).

---

Thank you for exploring the Record Shop API. Stay tuned for more updates, and keep the music playing! 🎶