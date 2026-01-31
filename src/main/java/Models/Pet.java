package Models;

public class Pet {
    private String name;
    private int life;
    private int hungry;
    private int cleaning;
    private int fun;
    private long timestamp;

    public void exibir(){
        System.out.println("Nome : " + this.getName());
        System.out.println("Life: " + this.getLife());
        System.out.println("Hungry: " + this.getHungry());
        System.out.println("Fun: " + this.getFun());
    }

    public Pet(String name) {
        this.name = name;
        this.life = 100;
        this.hungry = 80;
        this.cleaning = 100;
        this.fun = 50;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getHungry() {
        return hungry;
    }

    public void setHungry(int hungry) {
        this.hungry = hungry;
    }

    public int getCleaning() {
        return cleaning;
    }

    public void setCleaning(int cleaning) {
        this.cleaning = cleaning;
    }

    public int getFun() {
        return fun;
    }

    public void setFun(int fun) {
        this.fun = fun;
    }
}
