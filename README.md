# unipar-2021-trabalho-plano

djonathanb/unipar-2021-trabalho-plano

![badge](https://github.com/djonathanb/unipar-2021-trabalho-plano/actions/workflows/test-CI.yml/badge.svg)

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


### E2.1 - Cadastro de Plano

Como um **Agente**, preciso ***cadastrar novos planos*** para informar os parâmetros de serviço prestados para os usuários.

#### Critérios de Aceite

- Um plano descreve pode ter Abrangência Nacional ou Estadual.
- Os Tipo de Acomodação permitidos são (Compartilhado e Individual).
- O plano pode ter ou não Obstetrícia.
- O plano pode ter ou não Transporte Aéreo.
- Todo plano tem um valor base de mensalidade que não deve ser menor que R$100.00.

### E2.2 - Exclusão de Plano

Como um **Agente**, preciso ***deletar planos*** para que os mesmos não sejam mais utilizados.

#### Critérios de Aceite

- O plano não pode estar sendo utilizado em nenhum contrato.


### E3.1 - Cadastro de Contrato

Como um **Agente**, preciso ***cadastrar contratos*** para registrar nossos usuários.

#### Critérios de Aceite

- O contrato deve ter pelo menos um Titular com CPF válido.
- O Titular não deve ter outro contrato em Aberto com o seu CPF.
- Os tipos de dependentes permitidos são: CONJUGE, FILHO, PAIS.
- Somente upgrades de planos são permitidos em caso de alteração contratual.

### E3.2 - Cancelamento de Contrato

Como um **Agente**, preciso ***cancelar contratos*** para bloquear o acesso aos nossos serviços.

#### Critérios de Aceite

- O contrato deve estar Ativo a pelo menos 90 dias.
- O contrato não deve ter pagamentos em aberto.


### E4.1 - Lançamento de Cobrança

Como um **Agente**, preciso ***lançar cobranças*** para cobrar os serviços de um contrato.

#### Critérios de Aceite

- É cobrado para cada usuário do contrato "valor base do plano + idade_do_usuario".
- É cobrado um adicional de "10 reais por consulta/procedimento realizado no mês + 1000 reais por cirurgia realizada no mês".
- O vencimento da cobrança é para o Décimo dia subsequente a data de lançamento.
- Não podem ser geradas duas cobranças para o mesmo mês (permitido caso as outras cobranças do mesmo mês estejam Canceladas).
- Podem ser lançadas cobranças para qualquer mês anterior ao da data de lançamento.


### E4.2 - Cancelamento de Cobrança

Como um **Agente**, preciso ***cancelar cobranças*** para desfazer possíveis erros de lançamento.

#### Critérios de Aceite

- Somente é permitido cancelar cobranças em Aberto.


### E5.1 - Emissão de Carteirinha

Como um **Agente**, preciso ***solicitar a emissão de carteirinhas*** para que os usuário possam acessar os nossos serviços.

#### Critérios de Aceite

- O número da carteirinha segue o seguinte padrão "UF9999999-99"
- É permitido a emissão de uma carteirinha Válida para cada usuário do contrato.
- O prazo de validade da carteirinha é de 3 anos a partir da data de emissão.


### E5.2 - Registrar Entrega de Carteirinha

Como um **Agente**, preciso ***registrar a entrega de carteirinhas*** para registrar quando a mesma foi entregue ao usuário e liberar a sua utilização.

#### Critérios de Aceite

- A data de liberação deve ser maior que a de emissão.


### E5.3 - Registra Extravio de Carteirinha

Como um **Agente**, preciso ***registrar o extravio de carteirinhas*** para registrar quando a mesma foi extraviada pelo usuário.

#### Critérios de Aceite

- A data de extravio deve ser maior que a de liberação.
- Deve ser registrado o motivo do registro.


### E6.1 - Registrar Procedimento

Como um **Prestador**, preciso ***registrar a realização de um procedimento*** para registrar informar quanto algum serviço foi efetivamente prestado.

#### Critérios de Aceite

- Consultas do tipo Clinica Médica podem ser lançadas a qualquer momento.
- Demais tipos de procedimento devem ter uma liberação atrelada ao mesmo.
- O procedimento deve estar atrelado a uma Carteirinha Válida. 
- O procedimento deve realizado somente de acordo com a Área de Abrangência do Contrato, do contrário uma exceção deve ser retornada.


### E6.2 - Cancelar Procedimento

Como um **Prestador**, preciso ***cancelar a realização de um procedimento*** para corrigir lançamentos indevidos.

#### Critérios de Aceite

- O procedimento prestado não pode ter sido previamente cancelado.


### E6.3 - Extrato de Prestação de Serviços

Como um **Prestador**, preciso ***emitir o Extrato de Serviços Prestados*** para controlar os meus rendimentos com o plano.

#### Critérios de Aceite

- O extrato deve conter a data de emissão.
- O extrato deve ser emitido por Ano/Mês.
- O extrato deve relacionar todos os serviços efetivamente prestados.
- O extrato deve ter a sumarização do valor total dos serviços prestados.


### E7.1 - Solicitar Liberação de Procedimento

Como um **Usuário**, preciso ***solicitar a liberação de um procedimento*** para ter acesso aos serviços restritos.

#### Critérios de Aceite

- A liberação deve ser solicitada para uma carteirinha válida.
- Deve ser solicitada uma liberação por procedimento restrito (todos os procedimentos exceto Consulta Clinica Médica).
- A solicitação deve conter o Médico solicitante.


### E7.2 - Liberar Procedimento

Como um **Agente**, preciso ***liberar procedimentos*** para ter os usuário tenham acesso aos serviços restritos.

#### Critérios de Aceite

- A liberação deve ser solicitada ter sido solicidata previamente pelo Usuário.
- É permitido no máximo duas solicitações para o mesmo procedimento no mês.


### E7.3 - Rejeitar Procedimento

Como um **Agente**, preciso ***rejeitar procedimentos*** caso eu perceba excessos.

#### Critérios de Aceite

- Somente podem ser rejeitadas liberações em Aberto.
- A rejeição deve ter uma descrição com o motivo.


### E8.1 - Solicitar Reembolso

Como um **Usuário**, preciso ***solicitar o reembolso*** caso eu tenha sido atendido em uma emergência fora de minha área de abrangência.

#### Critérios de Aceite

- A solicitação deve ser solicitada para uma carteirinha válida.
- A solicitação deve ser feita para um estado fora da Área de Abrangência do plano.
- É permitido no máximo quatro solicitações de reembolso ano no valor total de R$5000.00.


### E8.2 - Autorizar Reembolso

Como um **Agente**, preciso ***autorizar reembolsos*** para que o usuário possa receber seu dinheiro.

#### Critérios de Aceite

- O reembolso deve ter sido solicitado previamente pelo Usuário.


### E8.3 - Rejeitar Reembolso

Como um **Agente**, preciso ***rejeitar reembolsos*** caso não esteja em conformidade.

#### Critérios de Aceite

- O reembolso deve ter sido solicitado previamente pelo Usuário.
- A rejeição deve ter uma descrição com o motivo.


### E9.1 - Solicitar Pré-Natal

Como um **Usuário**, preciso ***solicitar o pré-natal*** para liberar o pacote de exames para acompanhamento de grávidez.

#### Critérios de Aceite

- A solicitação deve ser solicitada para uma carteirinha válida.
- O plano contratado deve incluir Obstetrícia.
- É permitido no máximo uma solicitação por ano.


### E9.2 - Cancelar solicitação de Pré-Natal

Como um **Usuário**, preciso ***cancelar uma solicitar de pré-natal*** caso tenha sido lançada indevidamente.

#### Critérios de Aceite

- A solicitação não pode ter sido autorizada.


### E9.3 - Autorizar Pré-Natal

Como um **Agente**, preciso ***autorizar o pré-natal*** para que o pacote de exames básico seja criado.

#### Critérios de Aceite

- O pré-natal deve ter sido solicitado previamente pelo Usuário.
- Ao autorizar o pré-natal uma cirurgia será autorizada bem como 9 consultas gineológicas.


### E10.1 - Solicitar Transporte

Como um **Usuário**, preciso ***solicitar transporte*** para encaminhar passageiros entre diferentes cidades.

#### Critérios de Aceite

- A solicitação deve ser solicitada para uma carteirinha válida.
- A soliciação deve conter a cidade de origem e destino.
- Os tipos de transporte são Ambulância, UTI Móvel ou Aereo.
- O transporte aéreo só é permitido se contratado no plano.


### E10.2 - Cancelar solicitação de Transporte

Como um **Usuário**, preciso ***cancelar uma solicitar de transporte*** caso tenha sido lançada indevidamente.

#### Critérios de Aceite

- A solicitação não pode ter sido autorizada.


### E10.3 - Autorizar Transporte

Como um **Agente**, preciso ***cancelar uma solicitação transporte*** caso detecte qualquer irregularidade.

#### Critérios de Aceite

- O transporte deve ter sido solicitado previamente pelo Usuário.

---

## Equipes

<table>
<thead>
  <tr>
    <th>Equipe</th>
    <th>Estórias</th>
    <th>Integrantes</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>1</td>
    <td>
      E1.1 - Credenciamento de Prestador<br>
      E1.2 - Descredenciamento de Prestador
    </td>
    <td>
      William Schanoski (schanoski)<br>
      Stéfano (marianistefanoj)
    </td>
  </tr>
  <tr>
    <td>2</td>
    <td>
      E2.1 - Cadastro de Plano<br>
      E2.2 - Exclusão de Plano
    </td>
    <td>
      Sergio Schulz(sergioschu)<br>
      Lucas Martins (Lucas-Martins-de-Oliveira)
    </td>
  </tr>
  <tr>
    <td>3</td>
    <td>
      E3.1 - Cadastro de Contrato<br>
      E3.2 - Cancelamento de Contrato
    </td>
    <td>
      Matheus Gobo (MatheusGobo)<br>
      Everton Busse (evertonab)
    </td>
  </tr>
  <tr>
    <td>4</td>
    <td>
      E4.1 - Lançamento de Cobrança<br>
      E4.2 - Cancelamento de Cobrança
    </td>
    <td>
      Bruno Henrique Pereira Szczuk (brunoszczuk)<br>
      Jader Hericks Anschau (jaderhe)
    </td>
  </tr>
  <tr>
    <td>5</td>
    <td>
      E5.1 - Emissão de Carteirinha<br>
      E5.2 - Registrar Entrega de Carteirinha<br>
      E5.3 - Registra Extravio de Carteirinha
    </td>
    <td>
      Kevin Santos (kevin120)<br>
      Adriel Schneider (AdrielSchneider)
    </td>
  </tr>
  <tr>
    <td>6</td>
    <td>
      E6.1 - Registrar Procedimento<br>
      E6.2 - Cancelar Procedimento<br>
      E6.3 - Extrato de Prestação de Serviços
    </td>
    <td>
      Simon(SimonLRS)<br>
      Henrique (hmaltaurodev)
    </td>
  </tr>
  <tr>
    <td>7</td>
    <td>
      E7.1 - Solicitar Liberação de Procedimento<br>
      E7.2 - Liberar Procedimento<br>
      E7.3 - Rejeitar Procedimento
    </td>
    <td>
      Thiago Crestani(Thiagohcrestani)<br>
      Riquelmy (rofontin)
    </td>
  </tr>
  <tr>
    <td>8</td>
    <td>
      E8.1 - Solicitar Reembolso<br>
      E8.2 - Autorizar Reembolso<br>
      E8.3 - Rejeitar Reembolso
    </td>
    <td>
      Bruno Reolon<br>
      Gabriel Sementino
    </td>
  </tr>
  <tr>
    <td>9</td>
    <td>
      E9.1 - Solicitar Pré-Natal<br>
      E9.2 - Cancelar solicitação de Pré-Natal<br>
      E9.3 - Autorizar Pré-Natal
    </td>
    <td>
      Victor Paro (Terrible-Developer)<br>
      Matheus Ferreira (wyvern800)
    </td>
  </tr>
  <tr>
    <td>10</td>
    <td>
      E10.1 - Solicitar Transporte<br>
      E10.2 - Cancelar solicitação de Transporte<br>
      E10.3 - Autorizar Transporte
    </td>
    <td>
      Lucas Baccan (lucasbaccan)<br>
      João Kirst (joaoalexandre2)<br>
      Luiz Fernando Brogliatto Ferreira(luiz0067)
    </td>
  </tr>
</tbody>
</table>

Planilha de Equipes: [link](https://docs.google.com/spreadsheets/d/1NDHDftwt-9-F2ftglRxZueyslExN4CASVx8UtSOvP0k)
