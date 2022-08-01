public class Call {

    private int whoIsCall;


    public Call (int whoIsCall) {
        this.whoIsCall = whoIsCall;
    }

    @Override
    public String toString() {
        return "звонок от номера " + whoIsCall;
    }
}