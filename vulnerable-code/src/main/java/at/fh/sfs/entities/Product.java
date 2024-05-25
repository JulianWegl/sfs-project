package at.fh.sfs.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    private UUID id;

    private String information;
    private String basicInformation;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;
}
