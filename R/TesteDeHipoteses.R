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

#Neste teste de hipóteses considera-se 0.05 como o nível de significância


#TESTE DE NORMALIDADE
library(nortest) #Teste de lilliefor porque são 3000 valores

lillie.test(ConjuntoaverageTimeGetGrpc)
lillie.test(ConjuntoaverageTimePostGrpc)
lillie.test(ConjuntoaverageTimeGetRest)
lillie.test(ConjuntoaverageTimePostRest)

#Como p-value< 0.05 então temos evidências estatisticas que não seguem uma distribuição normal

#TESTE DE ASSIMETRIA

library(moments)
skewness(ConjuntoaverageTimeGetGrpc)
skewness(ConjuntoaverageTimePostGrpc)
skewness(ConjuntoaverageTimeGetRest)
skewness(ConjuntoaverageTimePostRest)


#Como a skewness foi maior que 1 então as amostras são assimétricas

#Hipótese nula (H0): Não há diferença significativa no desempenho dos dois protocolos.
#Hipótese alternativa (H1): Há diferença significativa no desempenho dos dois protocolos.
valor_p <- wilcox.test(ConjuntoaverageTimePostGrpc,ConjuntoaverageTimePostRest, paired=FALSE)$p.value
valor_p

#Como p-value é praticamente 0 então temos evidências estatisticas de que há diferença significativa no desempenho dos dois protocolos (Rejeitando-se a Hipótese nula)

#Comparando agora os pedidos GET

valor_p <- wilcox.test(ConjuntoaverageTimeGetGrpc,ConjuntoaverageTimeGetRest, paired=FALSE)$p.value
valor_p

#Como p-value é 0.31, ou seja, p-value>0.05 então temos evidências estatisticas de que não há diferença significativa no desempenho dos dois protocolos (Não se rejeita a Hipótese nula)




#Curiosidade: Comparar o pedido POST em gRPC com um pedido GET (Geralmente mais rápido do que um POST) - INUTIL PORQUE DÁ 0

valor_p <- wilcox.test(ConjuntoaverageTimeGetGrpc,ConjuntoaverageTimePostGrpc, paired=TRUE)$p.value
valor_p

#Há diferenças significativas





#GRÁFICO DE BARRAS
#POST-Average


dados <- c(2.93, 500.53)
categorias <- c("gRPC", "REST")

# Cores para as barras
cores <- c("red", "cyan")

# Criar o gráfico de barras com cores diferentes
grafico <- barplot(dados, names.arg = categorias, xlab = "Média", ylab = "Tempo de Resposta (ms)", col = cores,ylim = c(0, max(dados) + 100))

# Adicionar números acima das barras
text(x = grafico, y = dados + 50, labels = dados, pos = 3, col = "black")
# Adicionar legenda
legend("topright", legend = categorias, fill = cores)


#Throughtput


dados <- c(300.1,267.8)
categorias <- c("gRPC", "REST")

# Criar o gráfico de barras com cores diferentes
grafico <- barplot(dados, names.arg = categorias, xlab = "Throughput", ylab = "Pedidos por segundo", col = cores,ylim = c(0, max(dados) + 100))

# Adicionar números acima das barras
text(x = grafico, y = dados + 50, labels = dados, pos = 3, col = "black")

# Adicionar legenda
legend("topright", legend = categorias, fill = cores)


##GET

dados <- c(1.47,1.56)
categorias <- c("gRPC", "REST")

# Criar o gráfico de barras com cores diferentes
grafico <- barplot(dados, names.arg = categorias, xlab = "Média", ylab = "Tempo de Resposta (ms)", col = cores,ylim = c(0, max(dados) + 3))

# Adicionar números acima das barras
text(x = grafico, y = dados + 1, labels = dados, pos = 3, col = "black")

# Adicionar legenda
legend("topright", legend = categorias, fill = cores)


##THORUGEGPUT

dados <- c(299.7,300.5)
categorias <- c("gRPC", "REST")

# Criar o gráfico de barras com cores diferentes
grafico <- barplot(dados, names.arg = categorias, xlab = "Throughput", ylab = "Pedidos por segundo", col = cores,ylim = c(0, max(dados) + 50))

# Adicionar números acima das barras
text(x = grafico, y = dados + 1, labels = dados, pos = 3, col = "black")

# Adicionar legenda
legend("topright", legend = categorias, fill = cores)


