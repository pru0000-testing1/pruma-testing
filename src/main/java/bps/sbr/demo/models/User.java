package bps.sbr.demo.models;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar")
    private String nip;
    @Column(columnDefinition = "nvarchar")
    private String name;
    @Column(columnDefinition = "nvarchar")
    private String username;
    @Column(columnDefinition = "nvarchar")
    private String email;
    @Column(columnDefinition = "nvarchar")
    private String password;
    @Column(columnDefinition = "nvarchar")
    private String photo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    public User() {
    }
    public User(String username,String email, String name, String nip, String photo,String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nip = nip;
        this.photo = photo;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void updateRoles(Set<Role> roles){
        this.roles = roles;
    }
}