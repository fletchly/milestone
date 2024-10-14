package org.gcu.milestone.business;

import org.gcu.milestone.data.entity.DataEntity;
import org.gcu.milestone.data.entity.product.ProductEntity;
import org.gcu.milestone.data.repository.product.*;
import org.gcu.milestone.data.service.product.*;
import org.gcu.milestone.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class ProductsBusinessService
{
    @Autowired
    private final ProductDataService productDataService;

    @Autowired
    private final BrandDataService brandDataService;

    @Autowired
    private final CategoryDataService categoryDataService;

    @Autowired
    private final ColorDataService colorDataService;

    @Autowired
    private final SizeDataService sizeDataService;

    public ProductsBusinessService(ProductDataService productDataService, BrandDataService brandDataService, CategoryDataService categoryDataService, ColorDataService colorDataService, SizeDataService sizeDataService)
    {
        this.productDataService = productDataService;
        this.brandDataService = brandDataService;
        this.categoryDataService = categoryDataService;
        this.colorDataService = colorDataService;
        this.sizeDataService = sizeDataService;
    }

    public List<ProductModel> getAllProducts()
    {
        var products = productDataService.findAllProducts();
        var productsDomain = new ArrayList<ProductEntity>();
        var resultSet = new ArrayList<ProductModel>();

        for(var product : products)
        {
            productsDomain.add((ProductEntity) product);
        }

        for (var product : productsDomain)
        {
            resultSet.add(new ProductModel(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getBrands().getName(),
                    product.getSizes().getName(),
                    product.getColors().getName(),
                    product.getCategories().getName()
            ));
        }

        return resultSet;
    }
}