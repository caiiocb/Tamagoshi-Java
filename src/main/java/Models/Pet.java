package Models;

import Services.States.BaseState;
import Services.States.CelaningState;
import Services.States.DeadState;
import Services.States.EatingState;
import Services.States.IdleState;
import Services.States.SleepingState;

public class Pet {

    public enum PetType { NONE, SKY, EARTH, FIRE, WATER }

    private String name;
    private double happiness = 50, hungry = 50, cleaning = 100, fun = 50, drowsiness = 0;
    private long timestamp = System.currentTimeMillis();

    // Stats de Combate
    private int life = 100, attack = 10, defense = 10, speed = 10;
    private PetType[] type = { PetType.NONE };

    // Inventário
    private int toys, food, soap, coins;

    // Estado
    private String lastState;
    private transient BaseState currentState;

    //metadados
    double hungerFactor = 0.05 , cleanFactor = 0.02 , funFactor = 0.03;

    public Pet(String name, PetType[] type) {
        this.name = name;
        if (type != null) this.type = type;
        this.currentState = new IdleState(this);
    }

    public void update() {
        if (currentState != null) currentState.update();
        else System.out.println("CRITICAL: Current state is null.");
    }

    public void SetState(BaseState newState) {
        if (currentState != null) currentState.exit();
        this.currentState = newState;
        if (this.currentState != null) {
            this.currentState.start();
            this.lastState = this.currentState.name;
        }
    }

    private double clamp(double val) { return Math.max(0, Math.min(100, val)); }
    private int clampInt(int val) { return Math.max(0, Math.min(100, val)); }

    public double getHappiness() { return happiness; }
    public void setHappiness(double val) { this.happiness = clamp(val); }

    public double getHungry() { return hungry; }
    public void setHungry(double val) { this.hungry = clamp(val); }

    public double getCleaning() { return cleaning; }
    public void setCleaning(double val) { this.cleaning = clamp(val); }

    public double getFun() { return fun; }
    public void setFun(double val) { this.fun = clamp(val); }

    public double getDrowsiness() { return drowsiness; }
    public void setDrowsiness(double val) { this.drowsiness = clamp(val); }

    public int getLife() { return life; }
    public void setLife(int val) { this.life = clampInt(val); }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long t) { this.timestamp = t; }

    public PetType[] getType() { return type; }
    public void setType(PetType[] type) { this.type = type; }

    public int getAttack() { return attack; }
    public void setAttack(int v) { this.attack = v; }

    public int getDefense() { return defense; }
    public void setDefense(int v) { this.defense = v; }

    public int getSpeed() { return speed; }
    public void setSpeed(int v) { this.speed = v; }


    public int getCoins() { return coins; }
    public void setCoins(int v) { this.coins = v; }

    public int getSoap() { return soap; }
    public void setSoap(int v) { this.soap = v; }

    public int getFood() { return food; }
    public void setFood(int v) { this.food = v; }
    
    public int getToys() { return toys; }
    public void setToys(int v) { this.toys = v; }

    public void exibir() {
        System.out.printf("Pet: %s | Life: %d | Hungry: %.1f | Fun: %.1f%n", name, life, hungry, fun);
    }

    public void reinitializeAfterLoad(){
        if ( this.type == null) this.type = new PetType[]{ PetType.NONE };

        if (this.lastState == null) this.lastState = "Idle";

        switch (this.lastState) {
            case "Sleeping" -> this.currentState = new SleepingState(this);
            case "Eating" -> this.currentState = new EatingState(this);
            case "Cleaning" -> this.currentState = new CelaningState(this);
            case "Dead" -> this.currentState = new DeadState(this);
            default -> this.currentState = new IdleState(this);
        }
        long now = System.currentTimeMillis();
        long diffMillis = now - this.timestamp;
        
        double secondsOffline = diffMillis / 1000.0;
        
        if(secondsOffline > 0){
            System.out.printf("Tempo offline: " + secondsOffline + " segundos");
            applyOfflineChanges(secondsOffline);
        }
        System.out.println("Pet reinitialized with state: " + this.currentState.name);
        this.currentState.start();
    }

    private void applyOfflineChanges(double seconds) {
        

        boolean wasSleeping = "Sleeping".equals(this.lastState);

        if (wasSleeping) {
            setDrowsiness(getDrowsiness() - (seconds * 0.1));
            setHungry(getHungry() + (seconds * (hungerFactor * 0.5)));
        } else {
            setHungry(getHungry() + (seconds * hungerFactor));
            setCleaning(getCleaning() - (seconds * cleanFactor));
            setFun(getFun() - (seconds * funFactor));
            setDrowsiness(getDrowsiness() + (seconds * 0.05));
        }

        System.out.println("Status atualizados pós-offline.");
    }
}