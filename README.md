# LAB 3 - PAD

## Deployement diagram
![deployement diagram](https://i.imgur.com/2Y8KWkE.png)

## Implemented features
- Create a data-warehouse node with two resources

```java 
Company.java
Customer.java
```
- Define at least 3 **HTTP methods**

```java
@RequestMapping(method = RequestMethod.GET)
@RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
@RequestMapping(method = RequestMethod.POST)
```

- Data validation for POST

Example for **Company.java** validation

```java
    @NotNull
    private String name;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    private Double rating;

    @NotNull
    private String country;
``` 

- Content negotiation & HATEOAS
For **HATEOAS** was used a separate Service that adds necessary information

```java
public interface HATEOASService {
    <T extends ResourceSupport> List<T> getLinksForList(List<T> list);

    <T extends ResourceSupport> T getLinksForEntity(T element, long id);
}
```
**Result**

```json
{
    "list": [
        {
            "name": "Andys",
            "rating": 4,
            "country": "Moldova",
            "links": [
                {
                    "rel": "self",
                    "href": "http://localhost:8081/api/companies"
                }
            ]
        },
        {
            "name": "Andys",
            "rating": 4,
            "country": "Moldova",
            "links": [
                {
                    "rel": "self",
                    "href": "http://localhost:8081/api/companies"
                }
            ]
        }
    ]
}
```
**Content negociation**

Just indicate the query field in the url ``mediaType=xml`` default is JSON
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<companies xmlns:atom="http://www.w3.org/2005/Atom">
    <company>
        <atom:link rel="self" href="http://localhost:8081/api/companies"/>
        <country>Moldova</country>
        <name>Andys</name>
        <rating>4.0</rating>
    </company>
    <company>
        <atom:link rel="self" href="http://localhost:8081/api/companies"/>
        <country>Moldova</country>
        <name>Andys</name>
        <rating>4.0</rating>
    </company>
</companies>
```


- Syncronization between two or more nodes
For this task was implemented a separate controller **SyncController**. When a **POST** request is done on a node,
via discovery it get's all the names of registered nodes and calles the POST method from **SyncController**

Example

```java
private void syncNodes(CompanyView view) {
        String property = environment.getProperty("spring.application.name");
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add((new MappingJackson2HttpMessageConverter()));
        HttpEntity<CompanyView> request = new HttpEntity<>(view, headers);

        discoveryClient.getServices().forEach(element -> {
            if(!property.equals(element)) {
                restTemplate.postForObject("http://" + element + "/api/sync/companies", request, CompanyView.class);
            }
        });
    }
```
