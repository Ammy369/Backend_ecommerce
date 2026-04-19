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
//here we are iterating through all the entities and adding their Java types (classes) to the list of entityClasses. This is done so that we can later convert this list into an array of classes, which will be used to expose the IDs for these entities in the API responses.
// The getJavaType() method retrieves the Java class that represents the entity type, and we add it to the entityClasses list. This way, we can dynamically determine which entities are present in the application and expose their IDs without hardcoding each entity class.
        // Iterate through each entity type and add its Java type (class) to the list
        // The getMetamodel() method retrieves the metamodel of the persistence unit, which contains metadata about the entities. The getEntities() method returns a set of EntityType objects, each representing an entity in the application. We then iterate through this set and add the Java type (class) of each entity to the entityClasses list.
        // The getJavaType() method retrieves the Java class that represents the entity type, and we add it to the entityClasses list. This way, we can dynamically determine which entities are present in the application and expose their IDs without hardcoding each entity class.
        // The getMetamodel() method retrieves the metamodel of the persistence unit, which contains metadata about the entities. The getEntities() method returns a set of EntityType objects, each representing an entity in the application. We then iterate through this set and add the Java type (class) of each entity to the entityClasses list.
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
