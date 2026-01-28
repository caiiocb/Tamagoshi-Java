package Models;
import Services.StateMachine.StateMachine;


public class Pet {

    public enum PetType { NONE ,SKY, EARTH, FIRE, WATER}

    private String name;
    private double happiness;
    private double hungry;
    private double cleaning;
    private double fun;
    private long timestamp;

    private int life;
    private int attack;
    private int defense;
    private int speed;
    private PetType[] type;

    public StateMachine stateMachine;

    public Pet(String name) {
        this.name = name;
        this.life = 100;
        this.hungry = 50;
        this.cleaning = 100;
        this.fun = 50;
        this.happiness = 50;
        this.timestamp = System.currentTimeMillis();

        this.stateMachine = new StateMachine(this);
        
        this.attack = 10;
        this.defense = 10;
        this.speed = 10;
        this.type = new PetType[] {PetType.NONE};
    }

    public void update() {
        if (stateMachine != null && stateMachine.currentState != null) {
            stateMachine.currentState.update();
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
}
