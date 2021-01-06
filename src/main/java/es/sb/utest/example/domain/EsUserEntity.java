package es.sb.utest.example.domain;

import com.google.common.base.Objects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class EsUserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
    @Size(max = 64)
    @Column(name = "id", nullable = false, updatable = false)
    private int userId;

    @NotNull
    @Size(max = 64)
    @Column(name = "password", nullable = false)
    private String userPassword;

    public EsUserEntity() {
    }

    public EsUserEntity(int userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("99", userId)
                .add("password99", userPassword)
                .toString();
    }
}
