#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
# language: pt
@GerenciarGrupos
Funcionalidade: Gerenciar grupos
  Como administrador,
  eu quero gerenciar os grupos para que possa categorizar o carros.
	
  @CadastrarGrupos
  Cenario: Cadastrar Grupo
    Dado que eu estou logado como administrador
    E entro na página de cadastro de grupos
    Quando eu preencho o nome do grupo "<nome_grupo>"
    E aperto o botão
    Entao verei "<msg>"
		
		Exemplos: 
      | nome_grupo 	| msg |
      | A						| O(a)(s) grupo foi inserido(a)(s) com sucesso	|
      | 						| O(a) nome não pode está em branco |
		
  @AtualizarGrupos
  Cenario: Atualizar Grupo
  	Dado que eu estou logado como administrador
  	E na pagina de atualizar
  	Quando eu preencho o nome do grupo "<nome_grupo>"
  	E aperto o botão
  	Entao verei "<msg>"
  	
  	Exemplos:
  		| nome_grupo	| msg	|
  		| ABC					| O(a)(s) grupo foi atualizado(a)(s) com sucesso	|
  		| 						| O(a) nome não pode está em branco |
  
  
  