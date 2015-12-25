!!! PLEASE TEST IN ORDER

Rest API for Flight entity can be tested:
mvn clean install
cd AirportManager\ Rest
mvn tomcat7:run

#GET ALL
curl -i -X GET http://localhost:8080/pa165/rest/flights/flights

#GET flight
curl -i -X GET http://localhost:8080/pa165/rest/flights/flightdetail/1

#CREATE new flight
curl -X POST -i -H "Content-Type: application/json" --data '{"international":true,"departure":"1970-01-17 20:14","arrival":"2016-01-02 10:00","airplane":{"name":"Boeing 747 Jumbo","type":"ECONOMY","capacity":600,"id":1},"from":{"name":"SHA","city":"Shanghai","country":"China","id":1},"to":{"name":"KAR","city":"Karachi","country":"Pakistan","id":2}}' http://localhost:8080/pa165/rest/flights/createFlight

#UPDATE flight
curl -X PUT -i -H "Content-Type: application/json" --data '{"international":true,"departure":"1970-01-17 20:14:14.000","arrival":"1970-01-17 20:14:14.001","airplane":{"name":"Boeing 747 Jumbo","type":"ECONOMY","capacity":600,"id":1},"from":{"name":"SHA","city":"Shanghai","country":"China","id":1},"to":{"name":"KAR","city":"Karachi","country":"Pakistan","id":2},"id":2}' http://localhost:8080/pa165/rest/flights/updateFlight

#DELETE flight
curl -i -X DELETE  http://localhost:8080/pa165/rest/flights/deleteFlight/1

#Add steward
curl -i -X PUT  http://localhost:8080/pa165/rest/flights/addSteward/1/1

#Remove steward
curl -i -X PUT  http://localhost:8080/pa165/rest/flights/removeSteward/1/1

or through web browser plugin e.q. https://addons.mozilla.org/sk/firefox/addon/restclient/ --> for mozilla
                                   https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo --> for Chrome