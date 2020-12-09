package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.Product;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.service.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


@Service
@Transactional(noRollbackFor = NullPointerException.class)
public class HibernateProductService implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    static Boolean thrown = false;

    @Override
    public void addProduct(List<Product> products) {
        int i = 0;
        for (Product product : products) {
            entityManager.persist(product);
            i++;

            if (i == 3 && thrown == false) {
                thrown = true;
                throw new IndexOutOfBoundsException();

            }
            if (i == 3) {
                thrown = true;
                throw new NullPointerException();
            }
        }
    }

    @Override
    public void deleteProduct(Integer number) {
        entityManager.remove(new Product(number, null, null, null));
    }

    @Override
    public List<Product> getProducts() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> cr = cb.createQuery(Product.class);

        return entityManager.createQuery(cr.select(cr.from(Product.class))).getResultList();

    }


}
