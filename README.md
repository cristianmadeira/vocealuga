# Você-Aluga: Locadora de Veículos
* [Pré-requisitos](#pré-requisitos)
* [Resumo](#resumo)
* [Tecnologias](#tecnologias)
* [Subir os Serviços](#configuração)

# Pré-requisitos
* Ter a porta 8081 e 3305 disponíveis, pois o servidor web java e o MariaDB rodará nessas respectivamente.
* [Docker](https://docker.com)
  * [Windows 10 64-bit: Pro, Enterprise, or Education ](https://hub.docker.com/editions/community/docker-ce-desktop-windows/)
  * [Windows 10 64-bit: Home](https://docs.docker.com/docker-for-windows/install-windows-home/)

# Resumo
> Consiste em realizar um protótipo de um sistema de locadora utilizando ferramentas, padrões e tecnlogiais usuais de mercado. 
# Tecnologias
* [Spring](https://spring.io/)
* [Thymeleaf](https://www.thymeleaf.org/)
* [Docker](https://docker.com)
* [Docker-compose](https://docs.docker.com/compose/)

# Configuração
* Entre no power shell
* Verique através do comando ```docker --version ``` e ```docker-compose --version ``` se o docker e o docker-compose estão instalados corretamente 
* Navegue até a raiz do projeto
* Execute ```docker-compose up --build ```
* Quando terminar a subida dos serviços, entre no navegador e digite localhost:8081
