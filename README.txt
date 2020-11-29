-> Abrir o terminal que vai rodar o servidor (precisa ser antes do cliente)

javac -d bin -sourcepath src -cp $(find lib -iname *.jar | xargs | tr " " ":") *.java

cd bin

rmic BandsImpl

export CLASSPATH=$CLASSPATH:/Users/julia/Desktop/teste/trabalho-rmi

rmiregistry &

java -cp . BandsServer &


-> Abrir o terminal que vai rodar o cliente

cd bin

java BandsClient localhost