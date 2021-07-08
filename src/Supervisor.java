
public class Supervisor {

    private String supervisorID;
    private String supervisorName;
    private String[] Intrest;
    private Supervisor next;

    public Supervisor(String supervisorID, String supervisorName, String[] Intrest) {
        this.supervisorID = supervisorID;
        this.supervisorName = supervisorName;
        this.Intrest = Intrest;
    }

    public String getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String[] getIntrest() {
        return Intrest;
    }

    public void setIntrest(String[] Intrest) {
        this.Intrest = Intrest;
    }

    public Supervisor getNext() {
        return next;
    }

    public void setNext(Supervisor next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Supervisor{" + "supervisorID=" + supervisorID + ", supervisorName=" + supervisorName + ", Intrest=" + Intrest + ", next=" + next + '}';
    }

}
