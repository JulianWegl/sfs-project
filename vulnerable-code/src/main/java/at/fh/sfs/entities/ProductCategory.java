package at.fh.sfs.entities;

public enum ProductCategory {
    SODA,
    ICE_CREAM,
    VEGETABLE,
    FRUIT,
    BREAD,
    MILK,
    CHEESE,
    CAKE,
    SAUSAGE,
    BUTTER,
    ROLL,
    FLOWERS,  // this is the vulnerable one (i.e. it is not present within the primary table
    OTHER
}
