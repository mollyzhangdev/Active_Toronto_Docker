<!-- Author: Dongyue Zhang -->
<a name="readme-top"></a>
<div align="center">
  <h2 align="center">ActiveTO</h2>
  <p align="center">Drop-In Activities at City of Toronto Recreation Facilities</p>
</div>

## About the app
Active is a REST API application that allows users to browse and search drop-in activities at City of Toronto Recreation Facilities.

While an open resource API exists for Toronto's recreation facilities and activities, it is primarily available in JSON and CSV formats, which can be challenging for non-developer users to access and comprehend. To address this issue, my application employs a Python processor to gather the data and transforms it into a more user-friendly format. Utilizing Spring Boot REST API Server deployed on Docker, we publish the restructured information, ensuring accessibility and readability for all users.

## Built With
* Java
* Spring Boot
* Python
* MySQL
* REST API
* Docker
* Beautiful Soup

## Endpoints Info
### API base path: https://www.api.activeto.mollyzhang.dev
### available city: toronto

It provides in total 15 HTTP endpoints:
<img width="1000" height="1073" alt="Screenshot 2026-01-20 at 11 46 54 PM" src="https://github.com/user-attachments/assets/24417a38-f4bd-42b0-8b3a-eef3e19980e2" />


## Usage sample
To explore all available types of activities, simply enter "https://www.api.activeto.mollyzhang.dev/toronto/types" ("/{city}/types") into your browser's address bar. This will provide you with a dataset of activities in JSON format. From there, you can select specific activity types for further information. Simply substitute {id} with your desired type ID in the second endpoint ("/{city}/types/{id}").

Check more details about the endpoints in [Swagger-ui](https://api.activeto.mollyzhang.dev/swagger-ui).

## Contact
Molly Dongyue Zhang - [@dongyuezhang](https://www.linkedin.com/in/dongyue-zhang-507549224/)

Project Link: [https://github.com/dongyue-zhang/Active_Toronto_Docker](https://github.com/dongyue-zhang/Active_Toronto_Docker)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
