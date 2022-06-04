# Gestion des rôles utilisateurs

L’objectif de ce projet et d’implémenter une stratégie rôles-utilisateurs qui permet la gestion des utilisateurs selon leurs rôles.
## Conditions préalables
- [JAVA](https://www.oracle.com/java/technologies/downloads/)

## Structure de projet
- entities/
    - Role.java
    - User.java
- repositories/
    - RoleRepository.java
    - UserRepository.java
- service/
    - IUserService.java
    - UserService.java
- UserRolesApplication.java

## Entités
Les entités dans ce projet sont les suivantes :

```java
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, length = 25)
  private String name;
  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER)
  private List<User> users = new ArrayList<>();
}
```
```java
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 50)
    private String username;
    private String password;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
```

## Services

le service est le suivante :
```java
@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService{

  private UserRepository userRepository;
  private RoleRepository roleRepository;

  @Override
  public User addUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public Role addRole(Role role) {
    return roleRepository.save(role);
  }

  @Override
  public User findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public Role findRoleByName(String name) {
    return roleRepository.findByName(name);
  }

  @Override
  public void addUserRole(String username, String roleName) {
    User user = this.findUserByUsername(username);
    Role role = this.findRoleByName(roleName);
    user.getRoles().add(role);
    role.getUsers().add(user);
  }

  @Override
  public User authenticate(String username, String password) {
    User user = findUserByUsername(username);
    // user not found or incorrect password
    if(user == null || !user.getPassword().equals(password))
      throw new RuntimeException("Username or password is incorrect");
    // user authenticated successfully
    return user;
  }
}
```