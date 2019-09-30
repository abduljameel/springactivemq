# springactivemq
Spring Boot with ActiveMQ and H2 Database

Import this as a Maven project and it runs using inbuilt ActiveMQ and inbuilt H2 Database.

1. The application creates the Table by itself

2. To post Shopping cart order info, goto: http://localhost:8080/product/send and POST order in JSON format.
e.g:
{
  "id":"2",
  "productName":"laptop",
  "prdCount":"2",
  "price":6000
}

3. JMS Listener has been implemented to read the message and save it to the DB.

4. Junit Test cases have been created using Mockito.
