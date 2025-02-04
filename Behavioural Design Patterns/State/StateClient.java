interface VendingMachineState {
    void handleRequest();
}

class SelectItem implements VendingMachineState{
    @Override
    public void handleRequest() {
        System.out.println("Selecting Item XXX");
    }
}

class ProceedToPay implements VendingMachineState{
    @Override
    public void handleRequest() {
        System.out.println("Make the payment of xxx $");
    }
}

class ReleaseItem implements VendingMachineState{
    @Override
    public void handleRequest() {
        System.out.println("Releasin Item XXX");
    }
}

class VendingMachineContext{
    VendingMachineState state;

    
    public void setState(VendingMachineState state) {
        this.state = state;
    }

    void request(){
        state.handleRequest();
    }
}
public class StateClient{
    public static void main(String[] args) {
        VendingMachineContext machine = new VendingMachineContext();

        machine.setState(new SelectItem());
        machine.request();

        machine.setState(new ProceedToPay());
        machine.request();

        machine.setState(new ReleaseItem());
        machine.request();
    }
}