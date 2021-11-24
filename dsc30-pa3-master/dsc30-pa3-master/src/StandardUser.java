import java.util.ArrayList;
import java.util.List;

public class StandardUser extends User {

    // Message to append when fetching non-text message
    private static final String FETCH_DENIED_MSG =
            "This message cannot be fetched because you are not a premium user.";
    private static final int LIMIT = 100;

    /**
     * Calls the super class with the input parameters.
     *
     * @param username
     * @param bio
     * @throws IllegalArgumentException
     */
    public StandardUser(String username, String bio) {
        super(username, bio);
    }

    /**
     * For standard users, they can only fetch the latest 1/10 of all the messages
     * in the message exchange. In addition, they can only view the messages that
     * are of type TEXT. This method appends the contents (by calling getContents())
     * of the messages to a string with a new line character after each and every message.
     * If the message turns out to be a non-text message type,
     * it appends the FETCH_DENIED_MSG string instead of the contents of that message.
     * After appending one message, you should switch to a new line.
     *
     * @param me
     * @return out is a string
     * @throws IllegalArgumentException
     */
    public String fetchMessage(MessageExchange me) {
        if (me == null || this.rooms.contains(me) == false) {
            throw new IllegalArgumentException();
        }

        int size;
        String out = new String();
        ArrayList<Message> log = me.getLog();
        if (log.size() < LIMIT) {
            size = log.size();
        } else {
            size = LIMIT;
        }

        for (int i = log.size() - size; i < log.size(); i++) {
            String content;
            if (log.get(i) instanceof TextMessage) {
                content = log.get(i).getContents();
            } else {
                content = FETCH_DENIED_MSG;
            }
            out = out + content + "\n";
        }
        return out;
    }

    /**
     * Returns the username.
     *
     * @return username is a string
     */
    public String displayName() {
        return this.username;
    }
}
