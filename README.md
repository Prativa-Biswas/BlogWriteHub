# 📝 BlogWriteHub

A full-stack web application where users can create accounts, write blogs, search content, and interact through comments. The application includes advanced features like AJAX-based search, session management, and email notifications.

---

## 🚀 Features

- User Registration, Login & Logout (Session Management)
- Forgot Password with Email Notification
- Create, Edit, and Delete Blog Posts
- Cascade Delete (Deleting a blog removes all associated comments)
- View all blogs and individual blog details
- Comment on blog posts

### User Profile Dashboard
- View all blogs
- Add new blog (separate page)
- View all comments

- Rich Text Editor for writing blogs

### AJAX-based Search (jQuery)
- Keyword-based blog search
- User-specific blog filtering

- Dynamic UI using Thymeleaf templates

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot
- **Frontend:** Thymeleaf, HTML, CSS, Bootstrap, jQuery (AJAX)
- **Database:** MySQL
- **ORM:** Spring Data JPA
- **Other:** JavaMail API (for email functionality)

---

## 📂 Project Structure

```
Controller Layer – Handles HTTP requests  
Service Layer – Business logic  
Repository Layer – Database operations  
Model Layer – Entity classes  
View Layer – Thymeleaf templates  
Utils Package – Handles reusable functionalities like email sending  
```

---

## ⚙️ Setup Instructions

1. Clone the repository
2. Configure MySQL in `application.properties`
3. Set email credentials for mail functionality
4. Run the Spring Boot application
5. Open: http://localhost:8080

---

## 📌 Key Highlights

- Implemented AJAX-based real-time search for better user experience
- Designed session-based authentication system
- Integrated email service for account creation and password recovery
- Ensured data integrity using cascade delete
- Built modular architecture using MVC pattern

---

## 📌 Future Enhancements

- Like & share functionality
- Pagination and sorting
- Role-based access (Admin/User)

---

## 🙋‍♀️ Author

**Prativa Biswas**