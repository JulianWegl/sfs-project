package at.fh.sfs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    private UUID id;

    /** this is the field used for SQL injection */
    private String information;

    /** this is where the flag will be returned */
    private String basicInformation;

    /** one of these missing inside {@link ProductInformation}
     * will trigger a query to {@link ProductInformationBackup}, where SQL injection is possible */
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
}
