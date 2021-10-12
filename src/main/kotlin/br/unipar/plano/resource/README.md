# Rotina de Cobranças
## Descrição

O objetivo dessa rotina é garantir o registro e o cancelamento de cobranças de contratos de planos de saúde.
## Glossário

**Contrato:** Vínculo envolvendo o titular, o plano e seus dependentes.

**Cobrança:** Documento de Ordem de Pagamento do títular com a empresa contratada, com base nos serviços prestados/usufruídos.


## Modelos

### Processo de Registrar Cobrança

![Processo de Registrar Cobrança](../../../../../../../docs/images/registrar_cobranca.png)

### Processo de Cancelar Cobrança

![Processo de Cancelar Cobrança](../../../../../../../docs/images/cancelar_cobranca.png)

### Modelo de domínio detalhado

![Modelo de domínio](../../../../../../../docs/images/modelo_de_dominio_cobranca.png)


-----------------------
### Setup Inicial


Baixe o [Environment](https://drive.google.com/file/d/1eNK-_kp-VaJ3j95O4KSVd72OtEqXyDvJ/view?usp=sharing) do POSTMAN e use a opção de Importar.

![Importar Environment](../../../../../../../docs/images/import_environment_postman.png)

Baixe a [Collection](https://drive.google.com/file/d/1BMZg6-yI44fkWrrvIqHZbdgmBdrRcSmA/view?usp=sharing) do POSTMAN e use a opção de Importar.

![Importar Collection](../../../../../../../docs/images/import_collection_postman.png)

Após importado, você poderá utilizar as rotinas de cobrança normalmente.

**Atenção!**

As rotinas de ***cancelamento*** e ***buscaPorId*** utilizam o ID da cobrança a partir do environment, lembre-se de atualizar essa variável para o ID da cobrança que você quer manipular.

![Alterar Environment](../../../../../../../docs/images/alterar_environment.png)