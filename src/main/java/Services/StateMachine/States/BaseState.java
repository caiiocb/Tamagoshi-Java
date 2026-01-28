package Services.StateMachine.States;

import Services.StateMachine.StateMachine;

public abstract class BaseState {
    StateMachine stateMachine;
    
    public abstract void start(); // inicia o estado
    public abstract void update(); // atualiza o estado a cada frame
    public abstract void exit(); // limpa o estado ao sair

}
