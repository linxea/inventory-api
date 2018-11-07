package herman.test.service;

import herman.test.entity.Product;
import herman.test.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public List<Product> getByProductIds(List<Long> ids) {
        return productRepository.findAllByIdIn(ids);
    }
}
