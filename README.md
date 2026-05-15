
# OpenAPI Light-4J Server Pet tutorial


Little tutorial for using light4j. This manages a pet list
and has two end points:<br />
GET  http://localhost:8080/pets/{id}<br />
POST http://localhost:8080/pets<br />
The api is described in src/main/resources/config openapi.yml<br />
You can copy paste the contents of openapi.yml into left side of https://editor.swagger.io/ and see the end points on the right.<br />

The handler chains for the end points are in src/main/resources/config handler.yml<br />
The pet repository is in memory and starts with a dog Bella and a cat Whiskers.<br />

To build: gradle clean build<br />
To run: gradle run<br />

Once you build and run, you can use Bruno to call<br />
GET  http://localhost:8080/pets/1 - you will get back json describing Bella the dog <br />
POST http://localhost:8080/pets<br />
body
```
{    
    "name": "Kesha",
    "type": "Parrot"
}
```
and it will add Kesha the Parrot into the repository. His id will be auto generated to be the next one available.



