package fr.solo.awale.server;

import com.diogonunes.jcolor.Attribute;
import com.google.gson.Gson;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import static com.diogonunes.jcolor.Ansi.colorize;

@Controller
public class MessageController {

    @MessageMapping("/send-hole")
    public void receptionMessage(String msg) {
        Gson gson = new Gson();
        SimpleMessage sm = gson.fromJson(msg, SimpleMessage.class);
        System.out.println(colorize("\nNouveau message : " + sm + "\n", Attribute.GREEN_TEXT()));
    }

}
