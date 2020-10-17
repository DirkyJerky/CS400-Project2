## Data Wrangler Files 

### Responsibilities
- The primary responsibility of the Data Wrangler role on this project is to write a Twitter API Client to allow the user
to post rules (filters), stream tweets from their rules set, look up users, etc. 

- Data Wrangler is also responsible for code that runs on app initiation and populates the initial RB Tree with tweets 
based on active filters

- All .jar dependencies in /dependencies/ need to be added to the classpath for the API client to work properly.

### Endpoints and References

Examples of Twitter API Requests and Responses
https://documenter.getpostman.com/view/9956214/T1LMiT5U#64280f46-f43b-40b7-b5ad-a670c5505f58

Information on HTTP Framework: okhttp
https://square.github.io/okhttp/

Information on JSON Serialization / Deserialization: Gson
https://dzone.com/articles/deserializing-json-java-object
https://www.tutorialspoint.com/json/json_java_example.htm

Source of Authorization Generation File
https://stackoverflow.com/questions/3756257/absolute-minimum-code-to-get-a-valid-oauth-signature-populated-in-java-or-groovy

