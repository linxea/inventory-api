package herman.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import herman.test.entity.Event;
import herman.test.entity.Product;
import herman.test.model.EventData;
import herman.test.model.EventType;
import herman.test.model.ProductData;
import herman.test.service.IEventService;
import herman.test.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {
    @Autowired
    private IEventService eventService;

    @Autowired
    private IProductService productService;

    private ObjectMapper mapper = new ObjectMapper();

    @GetMapping(path="/retrieveProducts")
    public EventData retrieveProducts(@Valid @RequestBody EventData eventData) {
        // save event data for history report
        eventService.saveEvent(convertToEvent(eventData, EventType.RETRIEVE));

        // retrieve products data by ids
        List<Long> productIds = new ArrayList<>();
        for (ProductData productData : eventData.getProducts()) {
            productIds.add(productData.getId());
        }
        List<Product> products = productService.getByProductIds(productIds);

        // response data
        List<ProductData> productDatas = products.stream().map(pd -> convertToProductData(pd)).collect(Collectors.toList());
        eventData.setProducts(productDatas);
        return eventData;
    }

    @PostMapping(path="/storeProducts")
    public EventData storeProducts(@Valid @RequestBody EventData eventData) {
        // save event data for history report
        eventService.saveEvent(convertToEvent(eventData, EventType.STORE));

        // save products data
        List<Product> products = new ArrayList<>();
        for (ProductData productData : eventData.getProducts()) {
            products.add(convertToProduct(productData));
        }
        List<Product> productsSaved = productService.saveProducts(products);

        // response data
        List<ProductData> productDatas = productsSaved.stream().map(pd -> convertToProductData(pd)).collect(Collectors.toList());
        eventData.setProducts(productDatas);
        return eventData;
    }

    private Event convertToEvent(EventData eventData, EventType type) {
        Event event = new Event();
        event.setEventId(eventData.getId());
        event.setTimestamp(eventData.getTimestamp());
        event.setType(type.getId());

        //TODO convert to products json data for PostgreSQL
        try {
            event.setProducts(mapper.writeValueAsString(eventData.getProducts()));
        } catch (Exception err) {
            err.printStackTrace();
        }
        return event;
    }

    private Product convertToProduct(ProductData productData) {
        Product product = new Product();
        product.setId(productData.getId());
        product.setName(productData.getName());
        product.setQuantity(productData.getQuantity());
        product.setSaleAmount(productData.getSale_amount());
        return product;
    }

    private ProductData convertToProductData(Product product) {
        ProductData productData = new ProductData();
        productData.setId(product.getId());
        productData.setName(product.getName());
        productData.setQuantity(product.getQuantity());
        productData.setSale_amount(product.getSaleAmount());
        return productData;
    }
}
