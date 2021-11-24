import java.util.ArrayList;

public abstract class User {

    // Error message to use in OperationDeniedException
    protected static final String JOIN_ROOM_FAILED =
            "Failed to join the chat room.";
    protected static final String INVALID_MSG_TYPE =
            "Cannot send this type of message to the specified room.";

    // instance variables
    protected String username;
    protected String bio;
    protected ArrayList<MessageExchange> rooms;

    /**
     * Initialize the rooms class variable to a new instance of its type.
     * Assign the other two class variables accordingly with the parameters
     * in the constructor.
     *
     * @param username is an instance of User
     * @param bio      is a string
     * @throws IllegalArgumentException
     */
    public User(String username, String bio) {
        if (username == null || bio == null) {
            throw new IllegalArgumentException();
        }
        this.username = username;
        this.bio = bio;
        this.rooms = new ArrayList<MessageExchange>();
    }

    /**
     * This method updates the class variable bio with a new one.
     *
     * @param newBio is a string
     * @throws IllegalArgumentException
     */
    public void setBio(String newBio) {
        if (newBio == null) {
            throw new IllegalArgumentException();
        }
        this.bio = newBio;
    }

    /**
     * Returns the bio.
     *
     * @return a string
     */
    public String displayBio() {
        return this.bio;
    }

    /**
     * This method adds the user to the list of users in the message exchange
     * platform and adds the platform to the list of rooms of this user.
     *
     * @param me is the room
     * @throws OperationDeniedException
     * @throws IllegalArgumentException
     */
    public void joinRoom(MessageExchange me) throws OperationDeniedException {
        if (me == null) {
            throw new IllegalArgumentException();
        }
        if (rooms.contains(me) || me.addUser(this) == false) {
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
        //need further testing
        me.addUser(this);
        rooms.add(me);
    }

    /**
     * Removes the message exchange platform from the list of rooms that
     * this user is a member of and removes the user from the list of users
     * recorded in the MessageExchange object.
     *
     * @param me
     * @throws IllegalArgumentException
     */
    public void quitRoom(MessageExchange me) {
        if (me == null) {
            throw new IllegalArgumentException();
        }
        //further testing
        me.removeUser(this);
        rooms.remove(me);
    }

    /**
     * Creates a new instance of the ChatRoom class. For each user in the users
     * list calls joinRoom method to join the room.
     *
     * @param users
     * @return outRoom
     */
    public MessageExchange createChatRoom(ArrayList<User> users) {
        if (users == null) {
            throw new IllegalArgumentException();
        }
        users.add(this);
        MessageExchange outRoom = new ChatRoom();
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
     * Creates an instance of a message with the correct type specified by the msgType
     * (compare it to the static class variables in the Message class).
     * Record the message instance in the MessageExchange.
     *
     * @param me
     * @param msgType
     * @param contents
     * @throws IllegalArgumentException
     */
    public void sendMessage(MessageExchange me, MessageType msgType, String contents) {
        if (me == null || contents == null || msgType == null
                || this.rooms.contains(me) == false) {
            throw new IllegalArgumentException();
        }

        Message m = null;
        if (msgType == MessageType.TEXT) {
            try {
                m = new TextMessage(this, contents);
            } catch (OperationDeniedException e) {
                e.getMessage();
                return;
            }
        } else if (msgType == MessageType.PHOTO) {
            try {
                m = new PhotoMessage(this, contents);
            } catch (OperationDeniedException e) {
                e.getMessage();
                return;
            }
        } else if (msgType == MessageType.STICKER) {
            try {
                m = new StickerMessage(this, contents);
            } catch (OperationDeniedException e) {
                e.getMessage();
                return;
            }
        }
        boolean out = me.recordMessage(m);
        if (out == false) {
            System.out.println(INVALID_MSG_TYPE);
        }
    }

    public abstract String fetchMessage(MessageExchange me);

    public abstract String displayName();
}
