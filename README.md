# Primes finder
This application is a service that determines the next prime number based on a given input.

#### Application owner
**Name**: George Ciachir Arhire
**Contact**: george.ciachir@yahoo.com , george.ciachir@gmail.com

#### Implementation details
* Application structure
Due to the simplicity of the functionality, a technical separation seemed more suitable, and a layered 
structure has been chosen for the implementation. 

* Chosen algorithm
The selected algorithm for the primality test is Miller-Rabin.
Motivation:
    1. **No absolute pseudoprimes (Carmichael numbers)**
    2. **Complexity**: The current implementation has **O(k log3n)** -> where k is the number of iterations (witnesses).
    This could be improved using advanced calculus techniques: FFT(Fast Fourier transform) to approximately **O(k log2n)**.
    3. **High precision**, depending on the number of iterations: 4^(-k). For the current implementation a number of 50 
    iterations has been chosen.
    The number has been chosen by comparing to the Java implementation for BigInteger and taking into consideration that 
    in the Java implementation an additional primality test is performed under certain conditions.
    4. **Very fast** even without heavy optimisation > in the **MillerRabinStrategyTest** provided in the test package there
    is a number comprised of 357 digits and the duration of the primality test revolves around 200 ms. By comparison, 
    the BigInteger implementation takes around 100 ms.

* Notes on possible improvements
    1. The generation of the witnesses **BigIntUtil.generateWitnessInInterval** could be done using coprimes (basically, known primes)
    2. Consider first checking if the tested number is a candidate for primality, by first checking if it can be expressed
    a form of a primorial number. 
    Additional implementation is required to store the coprimes of the primorials and also heavy testing is needed for this. 

* Other algorithms taken into consideration:
    ###### Simple methods
    * **The "school implementation"**
    Iteration to **sqrt(n)** with a **5K+1** step
    * **Custom ForkJoin**
    Splits the workload, but with the "compute()" method still based on iterations. 

    ###### Probabilistic tests
    *  **Fermat**:
    Although performant, it has the flaw of not detecting absolute pseudoprimes. It is true that
    the probability of stumbling upon a Carmichael number is extremely low, there is no point in taking the chance,
    especially when Miller-Rabin test takes it out of the equation.
    * **Euler**:
    Advantage over Fermat > there are only about half as many Euler pseudoprimes than Fermat pseudoprimes.


* Documentation:
    * [https://en.wikipedia.org/wiki/Primality_test](https://en.wikipedia.org/wiki/Primality_test)
    * [https://en.wikipedia.org/wiki/Fermat%27s_little_theorem](https://en.wikipedia.org/wiki/Fermat%27s_little_theorem)
    * [https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test](https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test)
    * [http://home.sandiego.edu/~dhoffoss/teaching/cryptography/10-Rabin-Miller.pdf](http://home.sandiego.edu/~dhoffoss/teaching/cryptography/10-Rabin-Miller.pdf)
    * [https://en.wikipedia.org/wiki/Fast_Fourier_transform](https://en.wikipedia.org/wiki/Fast_Fourier_transform)
    * Document present at project level: Rabin-Miller.pdf > also present in the above links

#### Quickstart
1. Pre-requisites 
- This project uses Java 11, so make sure you have this version installed on your local machine.
- This project uses Lombok for some code generation, so make sure you install Lombok plugin in your IntelliJ IDEA.
- For Windows OS, it is possible that depending on the project location, in order to be able to run the tests 
(unit or integration) from IntelliJ, additional steps need to be followed:
    * In the 'Run' menu, select 'Edit Configurations'
    * In the 'Templates' section select 'JUnit'
    * In the right panel select the used JRE and then edit the 'Shorten command line' to '@argfile(Java9+)'
 
2. The application can be started in several ways: 
    * Using IntelliJ IDEA
    As a standalone SpringBoot app:
     ```
        run 'PrimesFinderApplication' IntelliJ IDEA configuration
     ``` 
    Inside a Docker container -> The configuration is in place, and the exposed port of the container is 8080.
    First, build the project and after that:
    ```
       run 'Run as container' IntelliJ IDEA configuration
    ```
    
    * Manually, in a Docker container
     First, build the project and after that use the Dockerfile provided at project level
     
          
#### Authentication
For security, this application is integrated with the Auth0 platform: [https://auth0.com/](https://auth0.com/)
In order to access the application, the client must be authenticated.
A client has been configured in the Auth0 platform and can be used to perform calls.
In order to obtain the client credentials, please send an email to the owner of the application.

In order to access the application (via SwaggerUi or otherwise), an access token needs to be provided. 
It has been assumed that, under normal circumstances, a configuration server is provided to the client or that
the client itself it s configured in such a way that it has access its own credentials.
   
Follow the next steps to manually obtain the token:

1. Obtaining the client credentials (id and secret) from the application owner
2. Make a POST request on the following endpoint: https://primes-finder.eu.auth0.com/oauth with the request body:  
    ```
        {
            "client_id":"the client id obtained via email",
            "client_secret":"the client secret obtained via email",
            "audience":"primes-finder-webapp",
            "grant_type":"client_credentials"
        }    
    ```
3. Extract the **access_token** from the response and use it in the **Authorization** header. The format of the 
authorization token is:
   ```
        "Authorization": "Bearer access_token"
   ```

#### Swagger
The application exposes a swagger-ui endpoint, on  **/swagger-ui/** and it is not secured. However, in order to call
the application from SwaggerUi, the bearer token containing the authentication info needs to be provided by the user. 

