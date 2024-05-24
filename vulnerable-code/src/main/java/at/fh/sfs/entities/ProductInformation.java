package at.fh.sfs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ProductInformation")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ProductInformation {
    @Id
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    private String information;
}
