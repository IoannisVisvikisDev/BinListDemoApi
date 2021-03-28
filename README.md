# BinListDemoApi


DESCRIPTION


Hello all,

The whole mini project was created and tested using Springboot Framework,Java 11 and Intellij IDEA Community Edition. So I would appreciate it if you could download, unzip, import it (as a Maven project) preferably in the same IDE and run it locally.

JPA and H2 open-source lightweight Java database were used as an embedded storage for the purposes of this simple demo. It is accessed for storing values in two tables.

"card_issuing_country" (US and GR entries as provided example in the assignment pdf document, are already stored by default). Countries can be viewed/added/deleted/updated with the appropriate request body and request headers for authentiation (JWT token), using the corresponding GET/POST/DELETE/PUT Http request methods described in the mini documentation.
"registered_user". An already signed-up authorized user (hashed password stored in the database) who can transact with the app is already provided for testing purposes (credentials are provided in the documentation file). No adding/updating/deleting for this table, as it only exists to demostrate security implementation.
JUnit testing classes are also available.

Best Regards Ioannis





DOCUMENTATION


This is a REST API demo application using the https://binlist.net/ free api to send HTTP requests and receive responses containing information about the issuing counrty of a given credit card number in JSON format.

The user must first log in by making a POST request for an already signed up user. Please keep in mind that any credentials OTHER than these provided in the example below will be REJECTED.

LOGIN
Request to : POST https://localhost:8080/login

Headers : Content-Type : application/json

Payload : {"email":"some_email@gmail.com", "password":"SomePassword12#$"}

The response will contain a JWT token, valid for 15 MINUTES for the user to use.

Response : 200 OK Response Body : {"jwtToken":"eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTY5NDEzMDMsImV4cCI6MTYxNjk0MjIwMywidXNlcl9lbWFpbCI6InNvbWVfZW1haWxAZ21haWwuY29tIiwidXNlcl9uYW1lIjoiU29tZVVzZXJOYW1lIn0.QrLqUtnHMF1zJLyyDYpxA01u2gFOkPAa5zI_f39YPZ8"}

From now on, the user MUST provide the value of jwtToken for EVERY successive request made to the REST api with an "Authorization" header and a value in the format of "Bearer jwtTokenValue" as displayed below

Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTY5NDEzMDMsImV4cCI6MTYxNjk0MjIwMywidXNlcl9lbWFpbCI6InNvbWVfZW1haWxAZ21haWwuY29tIiwidXNlcl9uYW1lIjoiU29tZVVzZXJOYW1lIn0.QrLqUtnHMF1zJLyyDYpxA01u2gFOkPAa5zI_f39YPZ8

or else get a 401 unauthorized response

GET ALL REGISTERED COUNTRIES AND CLEARING COSTS
Request to : GET https://localhost:8080/countries

Headers : Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTY5NDEzMDMsImV4cCI6MTYxNjk0MjIwMywidXNlcl9lbWFpbCI6InNvbWVfZW1haWxAZ21haWwuY29tIiwidXNlcl9uYW1lIjoiU29tZVVzZXJOYW1lIn0.QrLqUtnHMF1zJLyyDYpxA01u2gFOkPAa5zI_f39YPZ8

Response : 200 OK Payload : [ { "isoCode": "US", "clearingCostUSD": "5.00" }, { "isoCode": "GR", "clearingCostUSD": "15.00" } ]

GET A SPECIFIC REGISTERED COUNTRY AND IT'S CLEARING COST
Request to : (url format : localhost:8080/countries/countryIso2CodeInUpperCase) GET https://localhost:8080/countries/US

Headers : Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTY5NDEzMDMsImV4cCI6MTYxNjk0MjIwMywidXNlcl9lbWFpbCI6InNvbWVfZW1haWxAZ21haWwuY29tIiwidXNlcl9uYW1lIjoiU29tZVVzZXJOYW1lIn0.QrLqUtnHMF1zJLyyDYpxA01u2gFOkPAa5zI_f39YPZ8

Response : 200 OK Payload : { "isoCode": "US", "clearingCostUSD": "5.00" }

ADD A SPECIFIC REGISTERED COUNTRY AND IT'S CLEARING COST
Request to : POST https://localhost:8080/countries

Headers : Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTY5NDEzMDMsImV4cCI6MTYxNjk0MjIwMywidXNlcl9lbWFpbCI6InNvbWVfZW1haWxAZ21haWwuY29tIiwidXNlcl9uYW1lIjoiU29tZVVzZXJOYW1lIn0.QrLqUtnHMF1zJLyyDYpxA01u2gFOkPAa5zI_f39YPZ8 Content-Type : application/json

Payload : {"isoCode": "CA","clearingCostUSD": "2.5"}

If the request body is providing any less information, a 400 Bad request will be returned
Response 200 OK

UPDATE A SPECIFIC REGISTERED COUNTRY AND IT'S CLEARING COST
Request to : PUT https://localhost:8080/countries

Headers : Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTY5NDEzMDMsImV4cCI6MTYxNjk0MjIwMywidXNlcl9lbWFpbCI6InNvbWVfZW1haWxAZ21haWwuY29tIiwidXNlcl9uYW1lIjoiU29tZVVzZXJOYW1lIn0.QrLqUtnHMF1zJLyyDYpxA01u2gFOkPAa5zI_f39YPZ8 Content-Type : application/json

Payload : {"isoCode": "GR","clearingCostUSD": "7.5"}

If the request body is providing any less information, a 400 Bad request will be returned
Response 200 OK

DELETE A SPECIFIC REGISTERED COUNTRY AND IT'S CLEARING COST
Request DELETE https://localhost:8080/countries

Headers : Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTY5NDEzMDMsImV4cCI6MTYxNjk0MjIwMywidXNlcl9lbWFpbCI6InNvbWVfZW1haWxAZ21haWwuY29tIiwidXNlcl9uYW1lIjoiU29tZVVzZXJOYW1lIn0.QrLqUtnHMF1zJLyyDYpxA01u2gFOkPAa5zI_f39YPZ8 Content-Type : application/json

Payload : {"isoCode": "GR"}

If the request body is providing any less information, a 400 Bad request will be returned
Response 200 OK

GET A SPECIFIC CARD NUMBER'S ISSUING COUNTRY'S CLEARING COST
Request to : POST https://localhost:8080/payment-cards-cost

Headers : Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTY5NDEzMDMsImV4cCI6MTYxNjk0MjIwMywidXNlcl9lbWFpbCI6InNvbWVfZW1haWxAZ21haWwuY29tIiwidXNlcl9uYW1lIjoiU29tZVVzZXJOYW1lIn0.QrLqUtnHMF1zJLyyDYpxA01u2gFOkPAa5zI_f39YPZ8 Content-Type : application/json

Payload : {"card_number":"XXXXXXXXXXXXXXX"}

No empty spaces between the card number's digits, or else a 404 not found will be returned
**The REST api will send a GET request to https://lookup.binlist.net/XXXXXX using the first 6 digits of the credit card's number.
If BinList has the issuing country information, then a lookup in the H2 database will be made for that country's ISO code. If the entry exists, then the stored issuing country's clearing cost (USD) will be returned. If the entry does not exist in H2, the default 10 value will be returned.
If BinList does not have the information about the issuing country, a 404 not found will be returned
Successful responses from BinList will be printed on the console as logs in the format of 'BinListResponseModel{number=Number{}, scheme=....}'
Response : 200 OK

Payload : { "country": "US", "cost": 5 }
