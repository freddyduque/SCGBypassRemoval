# SCGBypassRemoval
SCG Bypass Removal Application, Based on a Client-Server Methodology

I created this application basically because the customer I was working for, was having a concurrent "bypass stage" activation, which means that if the platform reaches a specific amount of error/result codes based on the TPS, as a protection manner, the platform switches to bypass mode automatically, allowing the Telco users to use internet services for free. 
* The problem was not in the platform itself, it was in the legacy systems. as a result, I created a friendly Bypass Removal Application.
* By clicking on one of the buttons in the application, the system changes from "bypass stage"​ to "normal stage".
* It was done using a Java Client-Server Application with straight communication through ssh to the servers, to check its status and then send the "remove bypass" command.
* With this application, the 1st level of response, was able to solve the issue asap, instead of escalating it to a higher level. On call Engineers extra working hours were reduced around 60%.
