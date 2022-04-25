COMANDOS PARA CRIAR O DATA BASE TECH4MUSIC:

Primeiro: Escreva no terminal --> "use tech4music" Para criar o db "tech4music".

Segundo: Depois escreva no terminal --> db.createCollection("musicas"), para criar a coleção "musicas".

Terceiro: E Por último, escreva no terminal, o comando abaixo para inserir as musicas na coleção "musicas".

db.musicas.insertMany([{titulo: "Forever",
artista: "Kiss",
album: "Hot in the Shade",
genero: "Rock",
anoLancamento: 1989,
compositor: "Paul Stanley"}, {titulo: "Algo parecido",
artista: "Skank",
album: "Os três primeiros",
genero: "Pop",
anoLancamento: 2018,
compositor: "Samuel Rosa"}, {titulo: "O que me importa",
artista: "Marisa Monte",
album: "Memórias, crônicas e declarações de amor",
genero: "MPB",
anoLancamento: 2000,
compositor: "Jose de Ribamar Cury"}])