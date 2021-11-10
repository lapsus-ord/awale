package fr.solo.awale.server;

public class SimpleMessage {
    private String hole;
    private String value;

    public SimpleMessage(String hole, String value) {
        this.hole = hole;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Trou n°" + hole + " contenant " + value + " graines";
    }
}

