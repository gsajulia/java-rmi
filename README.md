# Java RMI - Sistemas Distribuidos

## Compilar

`javac -d bin -sourcepath src -cp $(find lib -iname *.jar | xargs | tr " " ":") *.java`

## Abrir o terminal que vai rodar o servidor (precisa ser antes do cliente)

`cd bin`
`rmic BandsImpl && java BandsServer`

## Abrir o terminal que vai rodar o cliente

`cd bin`
`java BandsClient localhost`
