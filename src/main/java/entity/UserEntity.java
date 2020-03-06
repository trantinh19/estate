package entity;


import annotation.Column;
import annotation.Entity;
import annotation.Table;

@Entity
@Table(name = "user")
public class UserEntity extends AbstractBaseEntity{

    @Column(name= "id")
    private Long id;

    @Column(name= "fullname")
    private String userName;

    @Column(name= "password")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
