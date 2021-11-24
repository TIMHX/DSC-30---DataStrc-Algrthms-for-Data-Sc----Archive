public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";

    // input validation criteria
    private static final int MAX_TEXT_LENGTH = 500;

    /**
     * Initialize contents with the argument text.
     *
     * @param sender is an instance of User
     * @param text   is a string
     * @throws OperationDeniedException
     * @throws IllegalArgumentException
     */
    public TextMessage(User sender, String text)
            throws OperationDeniedException {
        super(sender);
        if (text == null) {
            throw new IllegalArgumentException();
        }
        if (text.length() > MAX_TEXT_LENGTH) {
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        }
        this.contents = text;
    }

    /**
     * Returns a String in the form:
     * “SenderName [2020-01-15]: A sample text message.”
     *
     * @return a string
     */
    public String getContents() {
        String date = this.getDate().toString();
        User sender = this.getSender();
        return (sender.displayName() + " [" + date + "]: " + this.contents);
    }

}
