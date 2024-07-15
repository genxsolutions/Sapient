# Project Descriptions

This project aims to demonstrate a feature-based modularization by managing the inter-feature dependencies through a dedicated navigation module and draws inspiration from Hexagonal Architecture and Clean Architecture to isolate the core of the application (domain logic or business logic) from other factors, allowing for a more flexible and decoupled system design.   
![1](https://github.com/genxsolutions/Sapient/blob/main/docs/architecture.png)

# Architecture Opinion

The domain layer has a sole dependency on domain-impl. Within the data layer, structures like API and persistence are focused solely on their respective operations. The dependency of the domain on the data layer is mitigated through the use of mappers within the domain-impl. These mappers transform data responses into domain entities, thus decoupling the domain logic from the specifics of the data source implementations. This is a strategic design choice that preserves the purity of the domain layer, allowing it to evolve independently of the data layer changes and maintaining the domain model's integrity.

![1](https://github.com/genxsolutions/Sapient/blob/main/docs/infrastructure.png)

# Module Descriptions

## Feature Module - `home`
![1](https://github.com/genxsolutions/Sapient/blob/main/docs/home-module.png)

Each feature module is divided into data, domain, and presentation layers. The data layer is further divided into API, DomainImpl and Persistence. The main reason for this distinction is the principle of single responsibility and the management of the resource from a single point. For example, while the API module is only responsible for communicating with remote services, with domainimpl we prevent the dependency of the domain layer on the API layer.
This structure shows how an Android application can be developed in a sustainable and scalable way, inspired by architectural principles such as Clean Architecture and Hexagonal Architecture ( Like Adapter is domain-impl and port is domain).

## Navigation Module - `navigation`
![1](https://github.com/genxsolutions/Sapient/blob/main/docs/navigation.png)

This module orchestrates the screen transitions and manages the navigation routes within the app. The Navigator class is equipped with functions that facilitate navigation to different screens, while AppNavigation is responsible for setting up the navigation routes. Crucially, the Navigation module operates independently of other modules, which plays a key role in decoupling feature modules from one another. This means that individual features do not have direct knowledge of each other, and all inter-feature navigation is coordinated through the Navigation module.

## Network Module - `network`

The Network Module is a critical component of the architecture, encompassing all aspects of networking logic. It is crafted to function independently, sourcing its constants from the core module while remaining detached from other modules.

### Dependencies Flow

![1](https://github.com/genxsolutions/Sapient/blob/main/docs/dependencies-flow.png)

## App Screens:

![1](https://github.com/genxsolutions/Sapient/blob/main/docs/home.png)
![1](https://github.com/genxsolutions/Sapient/blob/main/docs/list.png)
![1](https://github.com/genxsolutions/Sapient/blob/main/docs/detail-sheet.png)
 
