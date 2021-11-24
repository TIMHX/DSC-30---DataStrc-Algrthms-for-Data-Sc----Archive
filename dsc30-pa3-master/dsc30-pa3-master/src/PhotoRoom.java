import java.util.ArrayList;

public class PhotoRoom implements MessageExchange {

    // instance variables
    private ArrayList<User> users;
    private ArrayList<Message> log;

    /**
     * private ArrayList<User> users
     * that records all users who joined this chat room.
     * private ArrayList<Message> log
     * that records all messages being sent to this chat room.
     */
    public PhotoRoom() {
        users = new ArrayList<User>();
        log = new ArrayList<Message>();
    }

    /**
     * Method returns the log of this chat room.
     *
     * @return a ArrayList log
     */
    public ArrayList<Message> getLog() {
        return this.log;
    }

    public boolean addUser(User u) {
        if (u instanceof PremiumUser) {
            if (users.contains(u)) {
                return true;
            }
            users.add(u);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method removes the given user u from this room.
     *
     * @param u User to remove.
     */
    public void removeUser(User u) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) == u) {
                users.remove(i);
            }
        }
    }

    /**
     * Method returns the users of this chat room.
     *
     * @return a ArrayList users
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }

    /**
     * Method adds a new message to the log of this chat room.
     * If this message is not a PhotoMessage,
     * deny this operation by returning false. Otherwise,
     * record the message and return true.
     *
     * @param m Message to add.
     * @return a boolean
     */
    public boolean recordMessage(Message m) {
        if (m instanceof PhotoMessage) {
            log.add(m);
            return true;
        } else {
            return false;
        }
    }

}
