import java.time.LocalDate;

public abstract class Message {

    // Error message to use in OperationDeniedException
    protected static final String DENIED_USER_GROUP =
            "This operation is disabled in your user group.";

    // instance variables
    private LocalDate date;
    private User sender;
    protected String contents;

    /**
     * Constructor will set the sender and date fields.
     * The date will record the local time when you create this message instance.
     *
     * @param sender is an instance of User
     * @throws IllegalArgumentException when sender is null
     */
    public Message(User sender) {
        if (sender == null) {
            throw new IllegalArgumentException();
        }
        this.date = LocalDate.now();
        this.sender = sender;
    }

    /**
     * Method for returning data
     * @return date that the message was sent
     * */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Method will return the sender of this message.
     *
     * @return sender is an instance of User
     */
    public User getSender() {
        return this.sender;
    }

    public abstract String getContents();

}