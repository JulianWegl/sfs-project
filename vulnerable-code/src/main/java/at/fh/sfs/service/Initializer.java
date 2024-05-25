package at.fh.sfs.service;

import at.fh.sfs.entities.ProductCategory;
import at.fh.sfs.entities.ProductInformation;
import at.fh.sfs.entities.ProductInformationBackup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class Initializer {
    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    public void onStart(@Observes StartupEvent event) {
        createPrimary().forEach(p -> entityManager.persist(p));
        createBackup().forEach(p -> entityManager.persist(p));
    }

    private List<ProductInformation> createPrimary() {
        ProductCategory exempt = ProductCategory.FLOWERS;

        return Arrays.stream(ProductCategory.values())
                .filter(cat -> !exempt.equals(cat))
                .map(cat -> new ProductInformation(
                        cat,
                        cat.name() + " Information"
                )).toList();
    }

    private List<ProductInformationBackup> createBackup() {
        List<ProductInformationBackup> backups = new ArrayList<>();
        backups.add(new ProductInformationBackup("CTF{CHALLENGE-COMPLETE-CONGRATULATIONS}"));

        backups.addAll(Arrays.stream(ProductCategory.values())
                .map(cat -> new ProductInformationBackup(
                        cat.name() + " Information"
                )).toList());

        return backups;
    }
}
