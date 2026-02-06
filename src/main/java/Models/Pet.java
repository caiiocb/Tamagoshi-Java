package Models;

import Services.States.BaseState;
import Services.States.IdleState;


public class Pet {

    public enum PetType { NONE ,SKY, EARTH, FIRE, WATER}

    private String name;
    private double happiness;
    private double hungry;
    private double cleaning;
    private double fun;
    private double drowsiness;
    private long timestamp;

    private int life;
    private int attack;
    private int defense;
    private int speed;
    private PetType[] type;

    private transient BaseState currentState;
    private String lastState;

    private int toys;
    private int food;
    private int soap;
    private int coins;

    public Pet(String name, PetType[] type) {
        this.name = name;
        this.life = 100;
        this.hungry = 50;
        this.cleaning = 100;
        this.fun = 50;
        this.happiness = 50;
        this.timestamp = System.currentTimeMillis();

        this.currentState = new IdleState(this);

        this.attack = 10;
        this.defense = 10;
        this.speed = 10;
        this.type = new PetType[] {PetType.NONE};
    }

    public void update() {
        if (currentState != null) {
            currentState.update();
        }
        else{
            System.out.println("Current state is null.");
        }
    }

    public void exibir(){
        System.out.println("Name: " + name);
        System.out.println("Life: " + life);
        System.out.println("Hungry: " + hungry);
        System.out.println("Cleaning: " + cleaning);
        System.out.println("Fun: " + fun);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHappiness() {
        return happiness;
    }

    public void setHappiness(double happiness) {
        if (happiness > 100) this.happiness = 100;
        else if (happiness < 0) this.happiness = 0;
        else this.happiness = happiness;
    }

    public double getHungry() {
        return hungry;
    }

    public void setHungry(double hungry) {
        if (hungry > 100) this.hungry = 100;
        else if (hungry < 0) this.hungry = 0;
        else this.hungry = hungry;
    }

    public double getCleaning() {
        return cleaning;
    }

    public double getDrowsiness() {
        return drowsiness;
    }
    
    public int getCoins() {
        return coins;
    }

    public int getSoap() {
        return soap;
    }

    public int getFood() {
        return food;
    }
    
    public int getToys() {
        return toys;
    }

    public void setToys(int toys) {
        this.toys = toys;
    }
    public void setFood(int food) {
        this.food = food;
    }
    
    public void setSoap(int soap) {
        this.soap = soap;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    
    public void setDrowsiness(double drowsiness) {
        if (drowsiness > 100) this.drowsiness = 100;
        else if (drowsiness < 0) this.drowsiness = 0;
        else this.drowsiness = drowsiness;
    }

    public void setCleaning(double cleaning) {
        if (cleaning > 100) this.cleaning = 100;
        else if (cleaning < 0) this.cleaning = 0;
        else this.cleaning = cleaning;
    }

    public double getFun() {
        return fun;
    }

    public void setFun(double fun) {
        if (fun > 100) this.fun = 100;
        else if (fun < 0) this.fun = 0;
        else this.fun = fun;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        if (life > 100) this.life = 100;
        else if (life < 0) this.life = 0;
        else this.life = life;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public PetType[] getType() {
        return type;
    }

    public void setType(PetType[] type) {
        this.type = type;
    }

    public void SetState(BaseState newState) {
        if (currentState != null) {
            currentState.exit();
        }
        this.currentState = newState;
        if (this.currentState != null) {
            this.currentState.start();
            this.lastState = this.currentState.name;
        }
    }
}
