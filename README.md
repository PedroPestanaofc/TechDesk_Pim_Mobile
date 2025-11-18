TechDeskMobile - Aplicativo Android

TechDeskMobile é um aplicativo Android desenvolvido em Java utilizando Android Studio, projetado para gerenciar chamados e mensagens internas de forma simples e eficiente. A aplicação segue a arquitetura MVC, garantindo separação clara entre Model, View e Controller, facilitando manutenção e escalabilidade.

Funcionalidades
	•	Gerenciamento de Chamados: Criação, atualização, visualização e finalização de chamados diretamente pelo app.
	•	Mensagens Internas: Envio e recebimento de mensagens relacionadas aos chamados.
	•	Autenticação de Usuários: Login seguro com armazenamento de sessão via SessionManager.
	•	Integração com API: Consumo de endpoints RESTful do backend TechDeskMobile para sincronização de dados.
	•	Atualizações em Tempo Real: Visualização rápida de novos chamados ou mensagens.
  •	Integração com IA: Onde na abertura do chamado é disparado a descrição para a IA onde ela cria uma resposta e envia ao detalhes do chamado.
	•	UI Intuitiva e Responsiva: Telas modernas e simples de usar, otimizadas para dispositivos Android.


Tecnologias Utilizadas
	•	Android Studio: IDE oficial para desenvolvimento Android.
	•	Java: Linguagem principal para lógica de aplicação.
	•	MVC (Model-View-Controller): Arquitetura para separar responsabilidades e facilitar manutenção.
	•	Retrofit2: Cliente HTTP para comunicação com a API RESTful.
	•	Gson: Serialização e desserialização de objetos JSON.
	•	SessionManager: Gerenciamento de sessão e armazenamento de dados do usuário.
	•	Utils / Constants: Funções utilitárias e constantes utilizadas em toda a aplicação.


Exemplos de Funcionalidades

Chamados
	•	Visualizar chamados
	•	Criar novos chamados
	•	Atualizar e finalizar chamados
	•	Filtrar chamados por status

Mensagens
	•	Listar mensagens de um chamado
	•	Enviar novas mensagens
	•	Integração direta com o backend via API

Sessão do Usuário
	•	Login e logout
	•	Armazenamento seguro da sessão
	•	Recuperação de dados do usuário em toda a aplicação

Integração com IA 
	•	Cria o chamado
  •	IA faz a requisição com a descrição do chamado
  •	Atualiza as mensagens com uma possível solução
