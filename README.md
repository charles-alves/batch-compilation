# Batch Compilation
Batch compiletion é um projeto desenvolvido para o processamento de arquivos batch no formato

```
  001ç1234567891234çPedroç50000
  001ç3245678865434çPauloç40000.99
  002ç2345675434544345çJose da SilvaçRural
  002ç2345675433444345çEduardo PereiraçRural
  003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
  003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
```

e compilado em um arquivo que demonstre a quantidade de clientes, quantidade de vendedores id da venda mais cara e o pior vendedor, o exemplo acima geraria a saída.

```
2ç2ç10çPaulo
```
#Configuração
Toda a configuração da aplicação pode ser encontrada no arquivo application.yml que se encontra no diretório src/main/resources, por padrão o diretório de entrada deve estar em `${userhome}/agibank/data/in` e o arquivo de saída será gerado em `${userhome}/agibank/data/out/batch-compilation.done.dat`

##Execução
Para executar o projeto basta acessar seu diretório e executar o comando
```
// Windows
gradlew.bat clean bootRun

//Linux
.\gradlew clean bootRun
```

##Testes
Os testes podem ser executados de forma semelhante a execução padrão utilizando os comandos
```
// Windows
gradlew.bat clean test

//Linux
.\gradlew clean test
```
