# unipar-2021-trabalho-plano

## Descrição

O presente projeto implementa uma API para a gestão operational de de uma operadora de planos de saúde.

## Glossário

- **Operadora:** Empresa que gerencia o Plano de Saúde.
- **Agente:** Funcionário da Operadora responsável pelos cadastros operacionais do sistema.
- **Prestador:** Médico/Clínica/Hospital credenciado para prestar serviços a Operadora.
- **Contrato:** Descreve os usuários do plano e detalhes como Tipo de Acomodação e Área de Abrangência.
- **Usuário:** Pessoa Física atrelada a um contrato.
- **Titular:** Contratante responsável pelo contrato.
- **Dependente:** Qualquer usuário do plano atrelado ao contrato (não titular).
- **Especialidade:** Especialidade de atuação dos Médicos de acordo com https://sistemas.cfm.org.br/normas/visualizar/resolucoes/BR/2018/2221
- **Serviços:** Serviços prestados pelas Clínicas/Hospitais de acordo com as Areas de Atuação médicas descritas em https://sistemas.cfm.org.br/normas/visualizar/resolucoes/BR/2018/2221, acrescidos: Pronto Atendimento, Cirurgia Geral e Exame Laboratorial.

## Estórias

### E1.1 - Credenciamento de Prestador

Como um **Agente**, preciso ***credenciar novos prestadores*** para disponibilizar os seus serviços para os nossos usuários.

#### Critérios de Aceite

- O prestador do tipo **Médico** deve ter um CRM válido (somente o formato seguindo o seguinte padrão **99999/UF**.
- O sistema deve válidar CRMs duplicados e retornar uma mensagem **"Prestador com CRM já cadastrado"**.
- O prestador do tipo **Clínica/Hospital** deve ter um responsável técnico.
- Prestadores do tipo **Médico** são atrelados a pelo menos uma especialidades.
- Prestadores do tipo **Clínica/Hospital** devem estar atrelados a pelo menos um serviço.


### E1.2 - Descredenciamento de Prestador

Como um **Agente**, preciso ***descrecendiar prestadores*** para que seus serviços não sejam mais disponibilizados aos nossos usuários.

#### Critérios de Aceite

- O prestador deve estar **Ativo**.


### E2.1 - Cadastro de Contrato

Como um **Agente**, preciso ***cadastrar contratos*** para registrar nossos usuários.

#### Critérios de Aceite

- O contrato deve ter pelo menos um Titular com CPF válido.
- O Titular não deve ter outro contrato em Aberto com o seu CPF.
- Os Tipo de Acomodação permitidos são (Compartilhado e Individual) e Áreas de Abrangência (Regional, Estadual e Nacional), somente upgrades de planos são permitidos em caso de alteração contratual.
- Os tipos de dependentes permitidos são: CONJUGE, FILHO, PAIS.


### E2.2 - Cancelamento de Contrato

Como um **Agente**, preciso ***cancelar contratos*** para bloquear o acesso aos nossos serviços.

#### Critérios de Aceite

- O contrato deve estar Ativo a pelo menos 90 dias.
- O contrato não deve ter pagamentos em aberto.


### E3.1 - Lançamento de Cobrança

Como um **Agente**, preciso ***lançar cobranças*** para cobrar os serviços de um contrato.

#### Critérios de Aceite

- É cobrado para cada usuário do contrato "100 reais + idade_do_usuario/100".
- É cobrado um adicional de "10 reais por consulta/procedimento realizado no mês + 1000 rais por cirurgia realizada no mês".
- O vencimento da cobrança é para o Décimo dia subsequente a data de lançamento.
- Não podem ser geradas duas cobranças para o mesmo mês (permitido caso as outras cobranças do mesmo mês estejam Canceladas).
- Podem ser lançadas cobranças para qualquer mês anterior ao da data de lançamento.


### E3.2 - Cancelamento de Cobrança

Como um **Agente**, preciso ***cancelar cobranças*** para desfazer possíveis erros de lançamento.

#### Critérios de Aceite

- Somente é permitido cancelar cobranças em Aberto.


### E4.1 - Emissão de Carteirinha

Como um **Agente**, preciso ***solicitar a emissão de carteirinhas*** para que os usuário possam acessar os nossos serviços.

#### Critérios de Aceite

- O número da carteirinha segue o seguinte padrão "UF9999999-99"
- É permitido a emissão de uma carteirinha Válida para cada usuário do contrato.
- O prazo de validade da carteirinha é de 3 anos a partir da data de emissão.


### E4.2 - Registrar Entrega de Carteirinha

Como um **Agente**, preciso ***registrar a entrega de carteirinhas*** para registrar quando a mesma foi entregue ao usuário e liberar a sua utilização.

#### Critérios de Aceite

- A data de liberação deve ser maior que a de emissão.


### E4.3 - Registra Extravio de Carteirinha

Como um **Agente**, preciso ***registrar o extravio de carteirinhas*** para registrar quando a mesma foi extraviada pelo usuário.

#### Critérios de Aceite

- A data de extravio deve ser maior que a de liberação.
- Deve ser registrado o motivo do registro.


### E5.1 - Registrar Procedimento

Como um **Prestador**, preciso ***registrar a realização de um procedimento*** para registrar informar quanto algum serviço foi efetivamente prestado.

#### Critérios de Aceite

- Consultas do tipo Clinica Médica podem ser lançadas a qualquer momento.
- Demais tipos de procedimento devem ter uma liberação atrelada ao mesmo.
- O procedimento deve estar atrelado a uma Carteirinha Válida. 
- O procedimento deve realizado somente de acordo com a Área de Abrangência do Contrato, do contrário uma exceção deve ser retornada.


### E5.2 - Cancelar Procedimento

Como um **Prestador**, preciso ***cancelar a realização de um procedimento*** para corrigir lançamentos indevidos.

#### Critérios de Aceite

- O procedimento prestado não pode ter sido previamente cancelado.


### E5.3 - Extrato de Prestação de Serviços

Como um **Prestador**, preciso ***emitir o Extrato de Serviços Prestados*** para controlar os meus rendimentos com o plano.

#### Critérios de Aceite

- O extrato deve conter a data de emissão.
- O extrato deve ser emitido por Ano/Mês.
- O extrato deve relacionar todos os serviços efetivamente prestados.
- O extrato deve ter a sumarização do valor total dos serviços prestados.


### E6.1 - Solicitar Liberação de Procedimento

Como um **Usuário**, preciso ***solicitar a liberação de um procedimento*** para ter acesso aos serviços restritos.

#### Critérios de Aceite

- A liberação deve ser solicitada para uma carteirinha válida.
- Deve ser solicitada uma liberação por procedimento restrito (todos os procedimentos exceto Consulta Clinica Médica).
- A solicitação deve conter o Médico solicitante.


### E6.2 - Liberar Procedimento

Como um **Agente**, preciso ***liberar procedimentos*** para ter os usuário tenham acesso aos serviços restritos.

#### Critérios de Aceite

- A liberação deve ser solicitada ter sido solicidata previamente pelo Usuário.
- É permitido no máximo duas solicitações para o mesmo procedimento no mês.


### E6.3 - Rejeitar Procedimento

Como um **Agente**, preciso ***rejeitar procedimentos*** caso eu perceba excessos.

#### Critérios de Aceite

- Somente podem ser rejeitadas liberações em Aberto.
- A rejeição deve ter uma descrição com o motivo.
