# Record Shop API: Your Gateway to Musical Bliss 🎵

Welcome to the Record Shop API, where the rhythm of your favorite albums, artists, and genres come alive through a sleek and efficient RESTful service. Designed with the latest technologies and best practices in mind, this API is your digital record store, built to scale and secure your musical treasures.

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

- **`application-test.properties`**: Testing configurations with H2 database
- **`application-dev.properties`**: Development environment settings with PostgreSQL connection
- **`application-prod.properties`**: Production settings for **AWS RDS**

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

### 1. Spring Security Integration

To protect your API endpoints and secure user data, we plan to integrate Spring Security. This will provide:

- Authentication
  - Oauth2/GitHub
  - JWT
- Authorization
- Secure Password Storage
- Role-Based Access Control

### 2. Dockerising the Application

To simplify deployment and ensure consistency across different environments, we’ll **dockerise** the application. This includes:

- Creating a `Dockerfile` to package the app into a container.

### 3. Deploying to AWS Elastic Beanstalk

Ready to take your API to the cloud? We’ll deploy the application to **AWS Elastic Beanstalk**, which will provide:

- **Auto-Scaling**: Automatically adjust capacity based on traffic.
- **Managed Environment**: AWS handles infrastructure management.

## 🔄 Continuous Integration (CI)

We use GitHub Actions for continuous integration to ensure code quality and reliability.

This configuration ensures that every pull request is tested, helping maintain a high standard of code quality.

## 📜 Contributing

We welcome contributions to enhance the Record Shop API. Feel free to open issues or submit pull requests.

## 📧 Contact

For any questions or feedback, reach out to us at [chatonode@gmail.com](mailto:chatonode@gmail.com).

---

Thank you for exploring the Record Shop API. Stay tuned for more updates, and keep the music playing! 🎶