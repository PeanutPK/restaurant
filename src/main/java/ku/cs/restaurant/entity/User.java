package ku.cs.restaurant.entity;

import jakarta.persistence.*;
import ku.cs.restaurant.security.AttributeEncryptor;
import lombok.Data;
import org.bouncycastle.crypto.PasswordConverter;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "USER_INFO") // Change table name due to conflict with h2 table
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @Convert(converter = AttributeEncryptor.class)
    private String name;
    private String role;
    private Instant createdAt;
}
