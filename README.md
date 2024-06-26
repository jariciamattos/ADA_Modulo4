Implementações:

   > Regras de negócio [Conta Corrente]
      * package tech.ada.banco.service.operacoes.SaquePJ

   > Regras de negócio [Conta Investimento]
       *  package tech.ada.banco.service.operacoes.DepositoInvertimento, com a implementação do getPercentualRendimento() nas classes concretas de Cliente

   > Endpoint para realização de investimento
       * package tech.ada.banco.controller.InvestimentoController


Princípios SOLID:
   - Agregar mais 2 princípios no código, além dos existentes
         
     Princípio da Segregação de Interfaces:      
      * package tech.ada.banco.service.operacoes.Rentabilidade
      * package tech.ada.banco.service.operacoes.DepositoInvertimento
 
    Princípio da Inversão de Dependências:
      * package tech.ada.banco.service.ContaService linhas 78 e 79 -  utilizei uma operacoesBancaria(nível mais alto) cuja implementação estava na classe Saque ou SaquePJ
       

   - Listar e explicar 5 ocorrências dos princípios
   
   Princípio da Responsabilidade Única: Cada classe só deve ter uma única responsabilidade, por exemplo: uma classe criada para armazenar características de um cliente pessoa física não deve ser responsável por controlar o saldo que ela tem em sua conta corrente.
     * package tech.ada.banco.model.ClientePF   

   Princípio Aberto/Fechado - Uma classe deve estar aberta para extensões porém fecha para alterações, por exemplo: Se tenho uma classe cliente com características básicas, não deve sofrer alteração para criar variações entre Clientes PF e PJ, devendo ser estendida para novas classes específicas para implementação das novas características:
    * package tech.ada.banco.model.Cliente -  Abstrata fechada para alterações
    * package tech.ada.banco.model.ClientePF - Extensão da classe Cliente para novas alterações

   Princípio da Substituição de Liskov - Objetos abstratos podem ser substituídos por qualquer uma de suas concretizações.
       *  package tech.ada.banco.service.operacoes.DepositoInvertimento linha 13 -> A ContaInvestimento possui um objeto da abstrato Cliente, que nessa implementação está sendo substituída por uma de suas concretizações (ClientePF ou ClientePJ) para recuperação do método getPercentualRendimento();

   
   Princípio da Segregação de Interfaces - Uma classe não deve ser obrigada a implementar métodos que não serão utilizados, por exemplo: apenas as contas do tipo investimento possuem rentabilidade nos depósito, por isso, a única operação bancária que implementa rentabilidade é a classe depósito em Investimento.
      * package tech.ada.banco.service.operacoes.DepositoInvertimento linhas 8 e  16 a 19
         
  Princípio da Inversão de Dependências - A dependência é sempre do nível mais alto, por exemplo: tenho a classe abstrata Conta(alto nível), que é implementada em uma classe ContaCorrente ou ContaInvestimento.  E utilizo todos os métodos disponível nesse nível mais alto.
      * package tech.ada.banco.service.ContaService linha 77 (Conta conta = contaRepository.findByUuid(contaDTO.getUuid()))


Padrões de projeto (Design Patterns):
   - Aplique mais 2 padrões ao projeto, além dos existentes

      - Padrão Singleton (Lazy) : Criação da classe package tech.ada.banco.factory.OperacaoBancariaFactory 

      - Padrão Prototype: Criação da interface package tech.ada.banco.model.Clonar e implementação nas classes filhas de conta 


   - Listar e explicar 5 ocorrências de padrões
      
      Builder: É um padrão criacional para construção de objetos de forma incremental, permitindo definir apenas as características necessárias para o escopo a ser tratado.
      *package tech.ada.banco.service.ContaService - Linha 63 - return RetornoDto.builder().mensagem("Conta Corrente Criada.").conta(contaNova).build();
   - 
   -  *package tech.ada.banco.controller.ClientePFController - linha 44 - return ResponseEntity.noContent().build();

     Singleton: É um padrão criacional para que uma classe tenha uma única instância.
        * package tech.ada.banco.factory.OperacaoBancariaFactory -> ao utilizar a classe no package tech.ada.banco.service.ContaService linha 34, recupero apenas a instância que já existe, no lugar de criar um novo objeto. 
           
     Prototype: É um padrão criacional para duplicar um objeto. 
         * package tech.ada.banco.model.ContaCorrente linhas 17 a 26 -> utilizei uma interface e apliquei na classe abstrata Conta, implementando em suas classes filhas.





      


        #   A D A _ M o d u l o 4  
 