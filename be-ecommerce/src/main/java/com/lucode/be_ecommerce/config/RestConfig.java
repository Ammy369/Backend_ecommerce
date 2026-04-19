package com.lucode.be_ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.lucode.be_ecommerce.entity.Category;
import com.lucode.be_ecommerce.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;;

@Configuration // Indicates that this class contains configuration settings
public class RestConfig implements RepositoryRestConfigurer {

    // EntityManager allows interaction with the persistence context
    private EntityManager entityManager;

    // Constructor to inject the EntityManager dependency
    // @Autowired
    public RestConfig(EntityManager _entityManager){
        entityManager = _entityManager;
    }
//whats the use of this methods
     // Override method to configure repository REST settings
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        // Call the parent method
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        // Define the HTTP methods that are not supported (disabled)
        HttpMethod[] unsupportedAction = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        // Disable for Product API
        config.getExposureConfiguration()
            .forDomainType(Product.class) // Specify the domain type (entity) for which we want to configure exposure settings
            .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedAction))
            .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedAction));

        // Disable for Category API
        config.getExposureConfiguration()
            .forDomainType(Category.class)
            .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedAction))
            .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedAction));


        // Configure CORS settings to allow requests from a specific origin
        // Call method to expose entity IDs in the API responses
        exposeIds(config);
    }

    // Method to expose the IDs of entities in the API responses
    private void exposeIds(RepositoryRestConfiguration config){

        // Get all entities managed by the EntityManager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // Create a list to hold the entity classes
        List<Class> entityClasses = new ArrayList<>();
//get the java type of each entity and add it to the list of entity classes
// The getJavaType() method returns the Java class that represents the entity type

//entity
        // Add each entity's class to the list
        for(EntityType tempEntityType : entities){
            entityClasses.add(tempEntityType.getJavaType());
        }
        
        
        //Array

        // Convert list to an array of classes
        Class[] domainTypes = entityClasses.toArray(new Class[0]);

        // Expose the IDs for the specified domain types
        config.exposeIdsFor(domainTypes);
    }

    

}
