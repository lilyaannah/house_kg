package boosterschool.realestatesearchservice.models.money;

import boosterschool.realestatesearchservice.enums.CheckBox;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
@Table(name = "mortgages")
public class Mortgage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "mark")
    @Enumerated(EnumType.STRING)
    CheckBox mark;

    @Column(name = "active")
    boolean active;
}
