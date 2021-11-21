package fr.solo.awale.server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleMessage {
    @JsonProperty("hole")
    private String hole;
    @JsonProperty("value")
    private String value;

    public SimpleMessage() {
    }

    public SimpleMessage(String hole, String value) {
        this.hole = hole;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Trou n°" + hole + " contenant " + value + " graines";
    }
}

