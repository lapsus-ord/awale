package fr.solo.awale.server.controllers.commands;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandHistory {
    private Deque<String> history;

    public CommandHistory(){
        history = new ArrayDeque<>();
    }

    public void push(String str){
        history.push(str);
    }

    public String pop(){
        return history.pop();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("--- Historique des commandes : ---\n");
        history.forEach(command -> str.append(command).append("\n"));
        return str.toString();
    }
}
