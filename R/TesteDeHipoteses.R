library(readr)
resultsGETREST <- read_csv("resultsGETREST.csv")
View(resultsGETREST)

resultsRESTPost <- read_csv("resultsREST.csv")
View(resultsRESTPost)


resultsGETGrpc <- read_csv("resultsGETGrpc.csv")
View(resultsGETGrpc)

resultsGRPCPost <- read_csv("resultsGrpc.csv")
View(resultsGRPCPost)

averageTimeGetGrpc<-mean(resultsGETGrpc$elapsed)
averageTimePostGrpc<-mean(resultsGRPCPost$elapsed)
averageTimeGetRest<-mean(resultsGETREST$elapsed)
averageTimePostRest<-mean(resultsRESTPost$elapsed)

ConjuntoaverageTimeGetGrpc<- resultsGETGrpc$elapsed
ConjuntoaverageTimePostGrpc<- resultsGRPCPost$elapsed
ConjuntoaverageTimeGetRest<- resultsGETREST$elapsed
ConjuntoaverageTimePostRest<- resultsRESTPost$elapsed


max(ConjuntoaverageTimeGetGrpc)
max(ConjuntoaverageTimePostGrpc)
max(ConjuntoaverageTimeGetRest)
max(ConjuntoaverageTimePostRest)

min(ConjuntoaverageTimeGetGrpc)
min(ConjuntoaverageTimePostGrpc)
min(ConjuntoaverageTimeGetRest)
min(ConjuntoaverageTimePostRest)

median(ConjuntoaverageTimeGetGrpc)
median(ConjuntoaverageTimeGetRest)

#Neste teste de hip�teses considera-se 0.05 como o n�vel de signific�ncia


#TESTE DE NORMALIDADE
library(nortest) #Teste de lilliefor porque s�o 3000 valores

lillie.test(ConjuntoaverageTimeGetGrpc)
lillie.test(ConjuntoaverageTimePostGrpc)
lillie.test(ConjuntoaverageTimeGetRest)
lillie.test(ConjuntoaverageTimePostRest)

#Como p-value< 0.05 ent�o temos evid�ncias estatisticas que n�o seguem uma distribui��o normal

#TESTE DE ASSIMETRIA

library(moments)
skewness(ConjuntoaverageTimeGetGrpc)
skewness(ConjuntoaverageTimePostGrpc)
skewness(ConjuntoaverageTimeGetRest)
skewness(ConjuntoaverageTimePostRest)


#Como a skewness foi maior que 1 ent�o as amostras s�o assim�tricas

#Hip�tese nula (H0): N�o h� diferen�a significativa no desempenho dos dois protocolos.
#Hip�tese alternativa (H1): H� diferen�a significativa no desempenho dos dois protocolos.
valor_p <- wilcox.test(ConjuntoaverageTimePostGrpc,ConjuntoaverageTimePostRest, paired=FALSE)$p.value
valor_p

#Como p-value � praticamente 0 ent�o temos evid�ncias estatisticas de que h� diferen�a significativa no desempenho dos dois protocolos (Rejeitando-se a Hip�tese nula)

#Comparando agora os pedidos GET

valor_p <- wilcox.test(ConjuntoaverageTimeGetGrpc,ConjuntoaverageTimeGetRest, paired=FALSE)$p.value
valor_p

#Como p-value � 0.31, ou seja, p-value>0.05 ent�o temos evid�ncias estatisticas de que n�o h� diferen�a significativa no desempenho dos dois protocolos (N�o se rejeita a Hip�tese nula)




#Curiosidade: Comparar o pedido POST em gRPC com um pedido GET (Geralmente mais r�pido do que um POST) - INUTIL PORQUE D� 0

valor_p <- wilcox.test(ConjuntoaverageTimeGetGrpc,ConjuntoaverageTimePostGrpc, paired=TRUE)$p.value
valor_p

#H� diferen�as significativas





#GR�FICO DE BARRAS
#POST-Average


dados <- c(2.93, 500.53)
categorias <- c("gRPC", "REST")

# Cores para as barras
cores <- c("red", "cyan")

# Criar o gr�fico de barras com cores diferentes
grafico <- barplot(dados, names.arg = categorias, xlab = "M�dia", ylab = "Tempo de Resposta (ms)", col = cores,ylim = c(0, max(dados) + 100))

# Adicionar n�meros acima das barras
text(x = grafico, y = dados + 50, labels = dados, pos = 3, col = "black")
# Adicionar legenda
legend("topright", legend = categorias, fill = cores)


#Throughtput


dados <- c(300.1,267.8)
categorias <- c("gRPC", "REST")

# Criar o gr�fico de barras com cores diferentes
grafico <- barplot(dados, names.arg = categorias, xlab = "Throughput", ylab = "Pedidos por segundo", col = cores,ylim = c(0, max(dados) + 100))

# Adicionar n�meros acima das barras
text(x = grafico, y = dados + 50, labels = dados, pos = 3, col = "black")

# Adicionar legenda
legend("topright", legend = categorias, fill = cores)


##GET

dados <- c(1.47,1.56)
categorias <- c("gRPC", "REST")

# Criar o gr�fico de barras com cores diferentes
grafico <- barplot(dados, names.arg = categorias, xlab = "M�dia", ylab = "Tempo de Resposta (ms)", col = cores,ylim = c(0, max(dados) + 3))

# Adicionar n�meros acima das barras
text(x = grafico, y = dados + 1, labels = dados, pos = 3, col = "black")

# Adicionar legenda
legend("topright", legend = categorias, fill = cores)


##THORUGEGPUT

dados <- c(299.7,300.5)
categorias <- c("gRPC", "REST")

# Criar o gr�fico de barras com cores diferentes
grafico <- barplot(dados, names.arg = categorias, xlab = "Throughput", ylab = "Pedidos por segundo", col = cores,ylim = c(0, max(dados) + 50))

# Adicionar n�meros acima das barras
text(x = grafico, y = dados + 1, labels = dados, pos = 3, col = "black")

# Adicionar legenda
legend("topright", legend = categorias, fill = cores)


