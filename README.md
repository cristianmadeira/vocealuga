# Você-Aluga: Locadora de Veículos
* [Pré-requisitos](#pré-requisitos)
* [Resumo](#resumo)
* [Tecnologias](#tecnologias)
* [Subir os Serviços](#configuração)
* [Acessar o banco de dados externamente](#acessando-o-banco-de-dados)

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
* Verifique através do comando ```docker --version ``` e ```docker-compose --version ``` se o docker e o docker-compose estão instalados corretamente 
* Navegue até a raiz do projeto
* Execute ```docker-compose up --build ```
* Quando terminar a subida dos serviços, entre no navegador e digite localhost:8081

# Acessando o Banco de Dados
> Podemos utilizar alguns programas de acesso à banco de dados como, por exemplo: Dbeaver, Mysql Workbench. Este utlizaremos para fazer o acesso. As credenciais estão no arquivo docker-compose.yml. Expor-as-ei algumas, como ```vocealuga``` a qual é o nome do banco, do usuário, da senha. A porta cujo número é 3305 e o HOST o qual é localhost. Basta preenche com essas informações que verás o banco com as respectivas tabelas.
