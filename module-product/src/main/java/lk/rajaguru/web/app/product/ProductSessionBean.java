package lk.rajaguru.web.app.product;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lk.rajaguru.web.app.core.model.Product;
import lk.rajaguru.web.app.core.model.ProductCategory;
import lk.rajaguru.web.app.core.service.ProductService;

import java.util.List;

@Stateless
public class ProductSessionBean implements ProductService {

    @PersistenceContext(unitName = "APP-UNIT")
    private EntityManager em;

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void saveProduct(Product product) {
        em.persist(product);
    }

    @RolesAllowed({"USER","ADMIN","SUPER_ADMIN"})
    @Override
    public Product getProduct(int id) {
        return em.find(Product.class, id);
    }

    @RolesAllowed({"USER","ADMIN","SUPER_ADMIN"})
    @Override
    public List<Product> getProducts() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    @RolesAllowed({"USER","ADMIN","SUPER_ADMIN"})
    @Override
    public Product getProductByName(String name) {
        try {
            return em.createNamedQuery("Product.findByName", Product.class).setParameter("name", name).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @RolesAllowed({"USER","ADMIN","SUPER_ADMIN"})
    @Override
    public List<Product> getProductsByCategory(ProductCategory category) {
        return em.createNamedQuery("Product.findByCategory", Product.class).setParameter("category", category).getResultList();
    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void updateProduct(Product product) {
        em.merge(product);
    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void deleteProduct(Product product) {
        Product merged = em.merge(product);
        em.remove(merged);
    }
}
