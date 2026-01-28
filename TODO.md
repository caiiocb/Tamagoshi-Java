# 📝 Planejamento de Desenvolvimento: Tamagotchi JavaFX

Este documento descreve o roteiro de implementação para o jogo Tamagotchi monolítico usando Java e JavaFX.

## 📂 Arquitetura Sugerida (MVC)
- `model`: Lógica de dados e estados.
- `view`: Arquivos `.fxml`.
- `controller`: Classes que controlam a UI.
- `service`: Lógica pesada (Save, Tempo, GameLoop).

---

## 🛠️ Fase 1: Core e Lógica de Negócios (Backend)
*Foco: Criar as regras do jogo e estruturas de dados antes da interface.*

- [ ] **Definir Modelo `Pet`**
    - [ ] Criar classe `Pet` com atributos encapsulados (vida, fome, energia, diversão, higiene).
    - [ ] Implementar **JavaFX Properties** (`IntegerProperty`, `DoubleProperty`) para permitir *Data Binding*.
    - [ ] Adicionar atributo `timestamp` (`long`) para controle da última vez visto.
    - [ ] Adicionar atributo `tipo` (String ou Enum) para diferenciar as espécies.
- [ ] **Implementar Máquina de Estados (State Pattern)**
    - [x] Criar interface `PetState` (métodos: `update()`, `onEnter()`, `onExit()`).
    - [ ] Implementar estados concretos: `Ovo`, `Bebe`, `Adulto`, `Morto`.
    - [ ] Definir regras de transição (Ex: Tempo > X -> Evolui).
- [ ] **Criar Configurações (`GameConfig`)**
    - [ ] Definir constantes globais (Taxa de fome/segundo, XP para evoluir, Limites de status).

## 💾 Fase 2: Persistência e Algoritmos de Tempo
*Foco: O sistema de "vida fora do jogo".*

- [ ] **Sistema de Save/Load (JSON)**
    - [ ] Adicionar dependência (Gson ou Jackson).
    - [ ] Criar `SaveManager`:
        - [ ] Método `save(Pet pet)`: Serializa o objeto e grava `System.currentTimeMillis()`.
        - [ ] Método `load()`: Retorna o objeto `Pet` ou `null` se não existir.
- [ ] **Algoritmo de "Tempo Offline" (Time Manager)**
    - [ ] Criar lógica de inicialização: `Delta = Agora - UltimoSave`.
    - [ ] Converter `Delta` em segundos/minutos.
    - [ ] Loop de simulação: Aplicar a redução de status baseada no tempo que passou.
    - [ ] *Tratamento:* Se o tempo for excessivo, matar o pet automaticamente.
- [ ] **Game Loop (Tempo Real)**
    - [ ] Implementar `Timeline` (JavaFX) ou `AnimationTimer`.
    - [ ] Configurar o "tick" (ex: a cada 1s executa a lógica de redução de status).

## 🎨 Fase 3: Interface Gráfica (Views & Controllers)
*Foco: Implementação das telas solicitadas.*

### 3.1. Infraestrutura UI
- [ ] Criar classe `SceneRouter` ou `ViewFactory` para gerenciar a troca de telas sem duplicar código.

### 3.2. Tela: Menu Principal (`MainMenu`)
- [ ] **Layout:** Título, botões estilizados.
- [ ] **Lógica:**
    - [ ] Botão "Novo Jogo": Leva para Seleção.
    - [ ] Botão "Continuar": Verifica se existe arquivo de save. Se sim, habilita; se não, desabilita/oculta.
    - [ ] Botão "Sair": Fecha a aplicação.

### 3.3. Tela: Seleção de Bichinho (`PetSelection`)
- [ ] **Layout:** 3 opções visualmente distintas (ex: 3 Ovos de cores diferentes).
- [ ] **Lógica:**
    - [ ] Ao clicar, instancia o `Pet` com o tipo específico.
    - [ ] Salva o estado inicial.
    - [ ] Redireciona para Tela de Carregamento.

### 3.4. Tela: Carregamento (`LoadingScreen`)
- [ ] **Layout:** Barra de progresso (`ProgressBar`) e texto de "Dica".
- [ ] **Lógica:**
    - [ ] Usar `Task<Void>` (JavaFX Concurrency) para simular carregamento ou carregar assets pesados.
    - [ ] Ao finalizar (`succeeded`), trocar para a tela do Jogo.

### 3.5. Tela: Jogo Principal (`GameView`)
- [ ] **Layout (HUD):**
    - [ ] Centro: Sprite do Pet (ImageView).
    - [ ] Topo: Barras de Status (Fome, Energia, etc).
    - [ ] Base: Botões de Ação (Comer, Banho, Dormir).
- [ ] **Integração:**
    - [ ] Vincular (*Bind*) as propriedades do `Pet` às Barras de Progresso.
    - [ ] Atualizar a imagem do Pet baseada no `PetState` atual.

## 🧪 Fase 4: Polimento e Finalização
- [ ] **Tratamento de Fechamento**
    - [ ] Interceptar o evento `Stage.setOnCloseRequest`.
    - [ ] Forçar o salvamento dos dados antes de encerrar.
- [ ] **Feedback Visual**
    - [ ] Mudar cor das barras (Verde -> Amarelo -> Vermelho) conforme o nível cai.
    - [ ] Adicionar pequenos sons (opcional).
- [ ] **Testes**
    - [ ] Testar progressão offline (adiantar relógio do sistema).
    - [ ] Testar ciclo de vida completo (Nascer -> Morrer).