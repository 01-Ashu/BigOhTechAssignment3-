package ProductService.Repository;



import ProductService.Entity.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Arrays;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
}