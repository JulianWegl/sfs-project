package at.fh.sfs.repository;

import at.fh.sfs.entities.Product;

public interface ProductInformationRepository {
    Product saveProduct(Product newProduct, boolean isAdmin);
}
