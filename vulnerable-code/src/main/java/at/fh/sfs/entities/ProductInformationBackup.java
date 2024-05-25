package at.fh.sfs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ProductInformationBackup")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductInformationBackup {
    @Id
    @GeneratedValue
    private Long id;

    private String information;

    public ProductInformationBackup(String information) {
        this.information = information;
    }
}
