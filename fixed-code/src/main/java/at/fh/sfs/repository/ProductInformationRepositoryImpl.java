package at.fh.sfs.repository;

import at.fh.sfs.entities.Product;
import at.fh.sfs.entities.ProductCategory;
import at.fh.sfs.entities.ProductInformation;
import at.fh.sfs.entities.ProductInformationBackup;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class ProductInformationRepositoryImpl implements ProductInformationRepository {
    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public Product saveProduct(Product newProduct, boolean isAdmin) {
        if (newProduct.getId() != null) throw new IllegalArgumentException("ID of a nem Product must be null");
        if (newProduct.getCategory() == null) throw new IllegalArgumentException("Category cannot be null");
        if (newProduct.getBasicInformation() != null) throw new IllegalArgumentException("Basic Information cannot be filled");

        UUID id = UUID.randomUUID();
        newProduct.setId(id);

        Optional<ProductInformation> info = getInformation(newProduct.getCategory());

        if (info.isEmpty() && isAdmin) {
            info = getBackup(newProduct).map(p -> backupToPrimary(p, newProduct.getCategory())); // FIXED
        }

        if (info.isEmpty()) throw new IllegalArgumentException(
                "Cannot retrieve information for product category '" + newProduct.getCategory().name() + "'" // FIXED
        );

        ProductInformation information = info.get();
        // this is where the flag is handed over (if the user is an admin)
        newProduct.setBasicInformation(information.getInformation());

        this.entityManager.persist(newProduct);
        return this.entityManager.find(Product.class, id);
    }

    private Optional<ProductInformation> getInformation(ProductCategory category) {
        ProductInformation info = this.entityManager.find(ProductInformation.class, category);

        if (info == null) return Optional.empty();

        return Optional.of(info);
    }

    private ProductInformation backupToPrimary(ProductInformationBackup backup, ProductCategory category) {
        return new ProductInformation(
                category,
                backup.getInformation()
        );
    }

    // FIXED entire method
    private Optional<ProductInformationBackup> getBackup(Product product) {
        String SQL = String.format("SELECT target from %s target WHERE target.information = :info",
                ProductInformationBackup.class);

        List<ProductInformationBackup> matches = this.entityManager
                .createQuery(SQL, ProductInformationBackup.class)
                .setParameter("info", product.getInformation())
                .getResultList();

        if (matches.isEmpty()) return Optional.empty();

        return Optional.of(matches.getFirst());
    }
}
