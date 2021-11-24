import java.util.ArrayList;

public class PremiumUser extends User {

    // instance variable
    private String customTitle;

    /**
     * Calls the super class with the input parameters. Also initializes the customTitle
     * class variable which is exclusive to the PremiumUser class.
     *
     * @param username
     * @param bio
     */
    public PremiumUser(String username, String bio) {
        super(username, bio);
        customTitle = "Premium";
    }

    /**
     * Fetches all messages from the log of the MessageExchange.
     * This method appends the contents (by calling getContents()) of the messages to a
     * string with a new line character after each and every message.
     *
     * @param me
     * @return out is the string composed of the message contents.
     */
    public String fetchMessage(MessageExchange me) {
        if (me == null || this.rooms.contains(me) == false) {
            throw new IllegalArgumentException();
        }

        String out = new String();
        ArrayList<Message> log = me.getLog();
        for (int i = 0; i < log.size(); i++) {
            String content = log.get(i).getContents();
            out = out + content + "\n";
        }
        return out;
    }

    public MessageExchange createPhotoRoom(ArrayList<User> users) {
        if (users == null) {
            throw new IllegalArgumentException();
        }
        MessageExchange outRoom = new PhotoRoom();
        users.add(this);
        for (int i = 0; i < users.size(); i++) {
            try {
                users.get(i).joinRoom(outRoom);
            } catch (OperationDeniedException e) {
                e.getMessage();
                continue;
            }
        }
        return outRoom;
    }

    /**
     * Returns the username and customTitle in the following format:
     * “<customTitle> username” where customTitle and username are replaced with their values.
     *
     * @return a string
     */
    public String displayName() {
        return "<" + customTitle + "> " + this.username;
    }

    /**
     * Sets the customTitle variable to the new value.
     *
     * @param newTitle
     */
    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }
}
