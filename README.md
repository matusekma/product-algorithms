# product-algorithms
Small Spring Boot application for solving an algorithmic problem

## Task Description:
Write a backend application in Node.js, Java, or .NET Core to solve an algorithmic problem
and retrieve earlier solutions via an API.  
The algorithm's input is an n-long integer array, and the output is also an n-long integer
array, where the element with index i is equal to the product of the input array except the
element i.  

For example, with input of [1, 2, 3, 4] the output should be [24, 12, 8, 6], due to:
24=2*3*4, 12=1*3*4, 8=1*2*4, 6=1*2*3.  

a) Solve the problem any way you like  
b) Solve the problem without using division operation  
c) Solve the problem without using division operation, and the solutions algorithmic
complexity must be O(n)  

The application should listen on port 3000, and the different calculations must be available
on the following POST endpoints: /api/calculate/a, /api/calculate/b, and /api/calculate/c.  

Apart from the algorithm's input, one must send a comment via the API calls. You cannot
use any external dependencies to write the algorithm part, and endpoints must use JSON
format for communication.  

Also, every request and response must be persisted in a PostgreSQL database, with the
timestamp of the call.  

Create an /api/history GET endpoint to fetch all previous calculations. Add a filtering
mechanism to this endpoint, where the caller can specify a search term, which filters
calculations by their comment field.  

Make a README.md file, which describes the configuration and other steps required to run
the application.