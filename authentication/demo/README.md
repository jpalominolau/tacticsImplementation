# Authentication and Authorization
These tactics focus on ensuring system security by first establishing the identity of users (Authentication) and then controlling access rights to system resources based on that identity (Authorization)

Authentication verifies who the user is, typically through credentials like usernames and passwords. Authorization determines what authenticated users are allowed to do enforcing policies via roles and permissions

In the project implemented with Spring Security authentication is achieved by verifying user credentials against an in memory user stor. Authorization is enforced by restricting access to URLs based on user roles like ADMIN or USER, controlling who can reach admin or user areas. This clear separation of verifying identity and granting permissions improves security by limiting access while maintaining modularity

## Expected behavior
1. The system required users to authenticate before accessing protected resources
2. Only users with the correct role can access role specific sections
3. After login users are redirected to pages that correspond to their roles
4. Unauthorized access attempts trigger an access denied message
5. The entire system consistently enforces authentication and authorization to protect sensitive resources

## Utilities
- Programming language: Java 23
- Framework: Spring boot (https://spring.io/projects/spring-boot)
- Sprint Web dependencies
- Sprint Security dependencies

## How to run the project
Navigate to cd authentication/demo and then use the command ```mvn spring-boot:run```. This will run the project in the browser ```localhost:8080```.

## Route Table
| **Route**              | **HTTP Method** | **Description**                              | **Access**    |
| ---------------------- | --------------- | -------------------------------------------- | ------------- |
| `/login`               | `GET`           | Displays the login page                      | Public        |
| `/login`               | `POST`          | Authenticates user credentials               | Public        |
| `/admin/dashboard`     | `GET`           | Admin dashboard page                         | ADMIN only    |
| `/user/home`           | `GET`           | User homepage                                | USER or ADMIN |
| `/logout`              | `POST`          | Logs out the user, invalidates session       | Authenticated |
| `/access-denied` (403) | `GET`           | Displays access denied page (custom handler) | All users     |

## Activity diagram
The activity diagram can be divided into three main parts: Login, Authentication and Authorization & Logout, each illustrating a specific responsability within the Spring Boot and Spring Security workflow. Together, they depict how the system enforces secure access control through a combination of authentication and authorization tactics as described in "Software Architecture in Practice 4th Edition" under the Security quality attribute.

### Login
In the first section, the user access the ```/login``` page and submits credentials. This interaction initializes the security filter chain in Spring Security. Which interceps and processes the request before it reaches any controller. From and architectural perspective, this part implements the "Identify Actors" phase of the authentication tactic desccribed by Bass. The system captures identity related information (username and password) and prepares it for verification. Spring Boot configuration automatically register this filter chain through the ```SecurityFilterChain```, as defined in the ```SecurityConfig``` class. This design ensures that all incoming request pass through the same entry point, enforcing a consistent authentication policy across the system.

![Activity Diagram Login](activity_login.png)

### Authentication
The second part illustrate the core of the authentication tactic. Here ```SecurityCongig``` uses the ```InMemoryUserDetailsManager``` object representing the verified user identity and associated roles. Then a ```successHandler()``` determines the correct redirect URL based on the user role, either ```/admin/dashboard``` or ```/user/home```. This process aligns directly with the "Authentication Actor" tactic from the book, where the system verifies claimed identities through credentials and establishes a session or token representing trust. Spring Security abstracts this mechanism, making authentication reusable, configurable and testable, which enhances modifiability another architectural quality attribute emphasized in the book.

![Activity Diagram Authentication](activity_authentication.png)

### Authorization & Logout
The final section corresponds to the authorization tactic and the session termination process. Once authenticated, the user attempts to access protected resources like ```/admin/**``` or ```/user/**```. Authorization checks are performed based on role definitions configured in ```HttpSecurity```. If access is granted the ```SampleController``` returns the corresponding HTML page. Otherwise the ```CustomAccessDeniedHandler``` responds with an HTTP 403 error. This embodies the "Authorize Actors" tactic, ensuring that authenticated users can only perform actions permitted by their assigned roles or priviliges.

![Activity Diagram Authorization](authorization_logout.png)

## Sequence diagram
### Application startup
![Sequence Diagram Application Startup](application_startup.png)

### Authentication
![Sequence Diagram Authentication](authentication.png)

### Authorization
![Sequence Diagram Authorization](authorization.png)

### Logout
![Sequence Diagram Logout](logout.png)