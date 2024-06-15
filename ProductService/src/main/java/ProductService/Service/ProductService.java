package ProductService.Service;




import ProductService.DTO.ProductDTO;
import ProductService.Entity.Product;
import ProductService.Repository.ProductRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElse(null);
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setId(id);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}