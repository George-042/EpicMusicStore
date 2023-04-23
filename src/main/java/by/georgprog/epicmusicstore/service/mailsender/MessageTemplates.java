package by.georgprog.epicmusicstore.service.mailsender;

public class MessageTemplates {

    private static final String ACTIVATION_MESSAGE =
            "<h1>Hey %s</h1>" +
                    "<h2><a href=%s/auth/activate/%s>Activate</a></h2>";

    public static String getActivationMessage() {
        return ACTIVATION_MESSAGE;
    }
}
