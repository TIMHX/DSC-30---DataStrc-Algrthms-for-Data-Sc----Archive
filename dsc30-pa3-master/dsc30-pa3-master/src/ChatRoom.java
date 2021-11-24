import java.util.ArrayList;

public class ChatRoom implements MessageExchange {

    // instance variables
    private ArrayList<User> users;
    private ArrayList<Message> log;

    /**
     * private ArrayList<User> users
     * that records all users who joined this chat room.
     * private ArrayList<Message> log
     * that records all messages being sent to this chat room.
     */
    public ChatRoom() {
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

    /**
     * Method adds the given user u to this room and returns true.
     *
     * @param u User to add.
     * @return a boolean
     */
    public boolean addUser(User u) {
        if (users.contains(u)) {
            return true;
        }
        users.add(u);
        return true;
    }

    /**
     * Method removes the given user u from this room. No action needed
     * if you cannot find the given user before removal.
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
        return users;
    }

    /**
     * Method adds a new message to the log of this chat room and returns true.
     *
     * @param m Message to add.
     * @return a boolean
     */
    public boolean recordMessage(Message m) {
        log.add(m);
        return true;
    }

}