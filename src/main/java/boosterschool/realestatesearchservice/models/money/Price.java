package boosterschool.realestatesearchservice.models.money;

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
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "total_price")
    double totalPrice;

    @Column(name = "active")
    boolean active;

    @ManyToOne
    @JoinColumn(name="currency_id")
    Currency currency;

    @ManyToOne
    @JoinColumn(name = "price_type_id")
    PriceType priceType;

}
