
# 📖 Blog-app

The **Blog-app** is a web application designed to facilitate the creation, management, and interaction with blog content. Built using **Java Spring Boot** and **MySQL**, this application provides a robust platform for users to create and manage blog posts, comments, and user profiles. The application is structured to follow RESTful principles, allowing for easy integration and interaction with front-end applications or other services.

## 🚀 Key Features

### 👤 User Management
- Users can register, log in, and manage their profiles.
- Secure authentication is implemented using **JWT (JSON Web Tokens)**.

### 📝 Post Management
- Users can create, read, update, and delete blog posts.
- Posts can include titles, content, categories, and images.

### 💬 Comment System
- Users can add comments to blog posts.
- Comments can also be deleted, allowing for interaction and feedback on posts.

### 🗂️ Category Support
- Posts can be organized into categories, making it easier for users to navigate and find content.

### 📷 Image Upload
- Users can upload images associated with their blog posts, enhancing the visual appeal of the content.

### 🌐 RESTful API
- The application exposes a **RESTful API**, allowing for easy integration with front-end frameworks or mobile applications.

### 📜 Swagger Documentation
- Interactive API documentation is provided via **Swagger**, enabling developers to test and explore the API endpoints easily.
- Swagger is accessible at: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

## 🛠️ Technologies Used

### 💻 Backend
- **Java**: The primary programming language used for developing the application.
- **Spring Boot**: A framework that simplifies the development of Java applications by providing built-in features and configurations. It allows for rapid application development with minimal setup.

### 📦 Database
- **MySQL**: A relational database management system used to store user data, blog posts, comments, and other application-related information.

### 🛠️ Libraries & Tools
- **ModelMapper**: A library used for object mapping between Data Transfer Objects (DTOs) and entity classes, simplifying the conversion of data between layers.
- **JWT (JSON Web Tokens)**: Used for secure user authentication.
- **Maven**: A build automation tool used for managing project dependencies and building the application.
- **Swagger**: A tool that helps document and test RESTful APIs, providing an interactive interface for developers to explore the API endpoints.

## 📦 Installation

### Prerequisites

- Java 21 or higher
- MySQL Server
- Maven

### Steps

1. **Clone the repository**:
   ```bash
   git clonehttps://github.com/rtanwar572/Blog-app-backend.git
   cd Blog-app

2. **Set up the database:**

- reate a new database in MySQL
- Update the application.properties file with your database credentials.

3. **Build the project:**

```bash
    mvn clean install
```

4. **Run the application**
```bash
    mvn spring-boot:run
```
5. **Access the application:**

- Open your browser and go to http://localhost:8081.

6. **Swagger Documentation:**

- Access the Swagger UI at http://localhost:8081/swagger-ui/index.html to explore and test the API endpoints interactively.


## 📚 API Endpoints

### User Endpoints

#### Create a new user
- **Endpoint**: `POST /api/users/`
- **Request Body**:
  ```json
  {
    "name": "john_doe",
    "password": "securePassword123",
    "email": "john.doe@example.com"
  }
  ```
- **Sample Response**:
  ```json
  {
    "userId": 1,
    "name": "john_doe",
    "email": "john.doe@example.com"
  }
  ```

#### Get user details
- **Endpoint**: `GET /api/users/{id}`
- **Sample Response**:
  ```json
  {
    "userId": 1,
    "name": "john_doe",
    "email": "john.doe@example.com"
  }
  ```

### Post Endpoints

#### Create a new post
- **Endpoint**: `POST /api/user/{userId}/posts`
- **Request Body**:
  ```json
  {
    "title": "My First Blog Post",
    "content": "This is the content of my first blog post.",
    "category": "TEXT",
    "image": "image_url.jpg"
  }
  ```
- **Sample Response**:
  ```json
  {
    "postId": 1,
    "title": "My First Blog Post",
    "content": "This is the content of my first blog post.",
    "image": "image_url.jpg",
    "creationDate": "2024-12-25T02:14:05.653+00:00"
  }
  ```

#### Get all posts
- **Endpoint**: `GET /api/posts`
- **Sample Response**:
  ```json
  {
    "content": [
      {
        "postId": 1,
        "title": "My First Blog Post",
        "content": "This is the content of my first blog post.",
        "image": "image_url.jpg"
      },
      {
        "postId": 2,
        "title": "Another Blog Post",
        "content": "Content of another blog post.",
        "image": "another_image_url.jpg"
      }
    ],
    "pageNumber": 0,
    "pageSize": 10,
    "totalRecords": 2,
    "totalPages": 1
  }
  ```
  ### Comment Endpoints

#### Add a comment to a post
- **Endpoint**: `POST /api/post/{postId}/comments`
- **Request Body**:
  ```json
  {
    "content": "Great post! Thanks for sharing."
  }
  ```
- **Sample Response**:
  ```json
  {
    "id": 1,
    "content": "Great post! Thanks for sharing."
  }
  ```

#### Delete a comment
- **Endpoint**: `DELETE /api/comments/{commentId}`
- **Sample Response**:
  ```json
  {
    "message": "Comment Deleted Successfully!",
    "success": true,
    "details": "Resource with ID 1 has been removed permanently.",
    "errorCode": null
  }
  ```

## 🧪 Testing

To run the tests, use the following command:

```bash
mvn test
```

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue. 
```bash
 Big Thanks To "InnoByte Solution Community" for Helping me out 
```

## 📞 Contact

For any inquiries, please reach out to:

Rohit Tanwar - rtanwar7303@gmail.com
