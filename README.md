# Microservicio Manejar Stats Implementando Clean Architecture - Muebles SAS

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**

# Sobre el proyecto

Las implementaciones que aparecen en el repositorio, corresponde a un reto técnico con el siguiente contexto

## Contexto

Muebles SAS está implementando una nueva arquitectura basada en microservicios para mejorar la calidad del servicio al cliente. Uno de estos microservicios debenencargarse de recibir estadísticas de interacción con usuarios, validarlas y procesarlas de forma reactiva, aprovechando tecnologías modernas como DynamoDB y RabbitMQ.

## Requisitos mínimos

Para poder ejecutar el proyecto de manera apropiada, y siguiendo los pasos aquí establecidos, es necesario cumplir con los siguientes requisitos:
- Java v.21
- Docker v.27
- Gradle v.8.13+

## Inténtalo tú mismo
### 1. Clonar repositorio
Crea una nueva carpeta y clona el repositorio
```
git clone https://github.com/alejo190404/muebles-sas-stats
```

### 2. Ejecutar docker-compose
Ingresa hasta la carpeta /deployment y ejecuta docker-compose
```
cd ./muebles-sas-stats/deployment
docker-compose up
```
Espera a que el servicio de Spring esté disponible
### 3. Probar endpoint

Realiza una petición POST al endpoint http://localhost:8080/stats, y asegúrate que el cuerpo tenga los campos mostrados en los ejemplos:

**Ejemplos para el cuerpo de la petición**
Ejemplo de petición no válida:
```
{
  "totalContactoClientes": 250,
  "motivoReclamo": 25,
  "motivoGarantia": 10,
  "motivoDuda": 100,
  "motivoCompra": 100,
  "motivoFelicitaciones": 7,
  "motivoCambio": 8,
  "hash": "02946f262f2eb0d8d5c8e76c50433ed8"
}
```

Ejemplo de petición válida:
```
{
  "totalContactoClientes": 250,
  "motivoReclamo": 25,
  "motivoGarantia": 10,
  "motivoDuda": 100,
  "motivoCompra": 100,
  "motivoFelicitaciones": 7,
  "motivoCambio": 8,
  "hash": "5484062a4be1ce5645eb414663e14f59"
}
```

### 4. Probar cola de mensaje (Opcional)
Para comprobar el funcionamiento de la cola de mensajes, puedes seguir las instrucciones del siguiente [respositorio](https://github.com/alejo190404/muebles-sas-consumer)
Por defecto, está configurado para recibir mensajes de este proyecto


