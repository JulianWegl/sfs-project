package at.fh.sfs.rest;

import at.fh.sfs.entities.Product;
import at.fh.sfs.repository.ProductInformationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.Logger;

@ApplicationScoped
@Path("/products")
@Transactional
public class ProductResource {


    @Inject
    protected ProductInformationRepository productRepo;

    @Inject
    protected Logger logger;

    @PUT
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Product save(Product product,
                     @QueryParam("userName") String userName,
                     @QueryParam("passwd") String passwd) {
        if(product == null) {
            throw new WebApplicationException("Given product was null!", Response.Status.BAD_REQUEST);
        }
        logger.info("ProductResource: received call for product {} with username={}, password={}",
                product, userName, passwd);
        logger.info("testmsg");
        return productRepo.saveProduct(product, MainResource.checkAdminRights(userName, passwd));
    }
}
