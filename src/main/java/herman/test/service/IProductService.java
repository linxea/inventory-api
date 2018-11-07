package herman.test.service;

import herman.test.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> saveProducts(List<Product> products);

    List<Product> getByProductIds(List<Long> ids);
}
