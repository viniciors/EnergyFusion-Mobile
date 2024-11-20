# Global Solution 2024 - Projeto EnergyFusion

# Equipe
## Arthur Fenili RM552872
## Enzo Antunes Oliveira RM552185
## Vinicio Raphael Santana RM553813


## Descrição

**EnergyFusion** é um aplicativo Android projetado para comparar a eficiência energética de diversos tipos de eletrodomésticos, incluindo geladeiras, ventiladores, máquinas de café, micro-ondas e máquinas de lavar. Ele permite que os usuários visualizem e gerenciem dados importantes, como eficiência energética, consumo e preço de cada eletrodoméstico, ajudando na tomada de decisões sustentáveis e econômicas.
Este é apenas um protótipo, no qual estamos trabalhando para melhorar e adicionar mais funcionalidades. Nesta versão, é possível visualizar os dados dos elétrodomésticos já existentes no banco de dados para uma análise da eficiência energética.
Além disso, é possível adicionar novos eletrodomésticos, atualizar e excluir os já existentes.

## Funcionalidades
- **Tela de login**: Permite que o usuário faça login com uma conta ou se cadastre com emais e senha.
- **Tela inicial com navegação por ícones**: Apresenta ícones de diferentes tipos de eletrodomésticos. Cada ícone redireciona o usuário para a tela específica do eletrodoméstico selecionado.
- **Detalhamento de eletrodomésticos**: Cada tipo de eletrodoméstico possui uma tela dedicada com detalhes de eficiência energética, consumo e preço.
- **CRUD completo**: Operações de Criar, Ler, Atualizar e Excluir informações de eletrodomésticos, todas integradas ao Firebase Firestore para armazenamento e atualização de dados em tempo real.
- **Integração Firebase**: Uso do Firebase Firestore para salvar e sincronizar dados.
- **Suporte a Atualizações e Exclusão de Dados**: Detalhes de cada eletrodoméstico podem ser atualizados ou excluídos diretamente pela interface do usuário.

## Tecnologias Utilizadas

- **Linguagem**: Kotlin
- **Banco de Dados**: Firebase Firestore
- **Desenvolvimento Android**: Android SDK, Android Studio
- **Gerenciamento de Dependências**: Gradle

## Estrutura do Projeto

- **`MainActivity.kt`**: Controla a tela inicial do aplicativo com os ícones dos eletrodomésticos.
- **Atividades específicas**: Como `FanActivity.kt`, `RefrigeratorActivity.kt`, `CoffeeMakerActivity.kt`, `MicrowaveActivity.kt`, `WashingMachineActivity.kt`, cada uma exibe e gerencia detalhes do tipo específico de eletrodoméstico.
- **Repositório**: `ApplianceRepository.kt` gerencia operações com o Firebase Firestore.
- **Adapters**: `ApplianceAdapter.kt`, `ApplianceIconAdapter.kt`, e `HomeAdapterAppliance.kt` são usados para adaptar dados em listas e visões de itens.

## Configuração do Projeto

### Pré-requisitos

1. **Android Studio**: Baixe e instale a versão mais recente do Android Studio.
2. **Firebase**: Configure um projeto no Firebase e obtenha o arquivo `google-services.json`.
3. **Kotlin**: Certifique-se de que o Kotlin esteja configurado no seu ambiente Android Studio.

### Instruções de Configuração

1. Clone o repositório do projeto para o seu ambiente local.
2. Abra o projeto no Android Studio.
3. Adicione o arquivo `google-services.json` à pasta `app` para integrar com o Firebase.
4. Sincronize o projeto com o Gradle.
5. Conecte-se ao Firebase Firestore e configure as regras de leitura e escrita de acordo com as necessidades do aplicativo.

## Como Executar

1. Abra o Android Studio e selecione "Run" para instalar o aplicativo em um dispositivo ou emulador Android.
2. Faça login com uma conta existente ou crie uma nova conta.(Conta cadastrada -> email:vinicioraphael2012@gmail.com / senha:123456)
2. Navegue pela tela inicial para selecionar o tipo de eletrodoméstico e explore as funcionalidades de CRUD para adicionar, visualizar, atualizar ou excluir informações.

## Contribuição

Este projeto foi desenvolvido com foco em soluções sustentáveis para o uso consciente de energia. Contribuições e sugestões são bem-vindas para aprimorar a experiência do usuário e ampliar o escopo do aplicativo.

## Links Úteis
Vídeo do protótipo:
https://youtu.be/qq-QVpsvZdE

Repositório:
https://github.com/viniciors/EnergyFusion-Mobile.git
