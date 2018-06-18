# rest-api-spring-boot

## API List

1. curl -X PUT -H "Content-Type:application/json" -d '{"companyId" : "CM1233", "name" : "ABC Corp", "address" : "2 Saxon Lodge, CR02AL", "country" : "UK", "city" : "Glasgow", "email" : "amit-45@gmail.com", "phone" : "020-34453442", "owners" : ["Anil", "Knushka"]}' http://localhost:8080/company/add

2. curl -v http://localhost:8080/company/detail/CM1234
3. curl -v http://localhost:8080/company/list
4. curl -X POST -H "Content-Type:application/json" -d '{"name":"Viser Corp"}' http://localhost:8080/company/update/CM1234
5. curl -X POST -H "Content-Type:application/json" -d '{"owners" : ["Sam", "Vio"]}' http://localhost:8080/company/CM1234/addowners
6. curl -X POST -H "Content-Type:application/json" -d '{"owners" : ["Sam"]}' http://localhost:8080/company/CM1234/removeowners
