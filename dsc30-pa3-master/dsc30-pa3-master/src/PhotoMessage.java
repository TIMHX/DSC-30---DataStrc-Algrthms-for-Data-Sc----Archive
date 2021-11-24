public class PhotoMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The source path given cannot be parsed as photo.";

    // input validation criteria
    private static final String[] ACCEPTABLE_EXTENSIONS =
            new String[] {"jpg", "jpeg", "gif", "png", "tif", "tiff", "raw"};

    // instance variable
    private String extension;

    /**
     * Helper method to detect whethrt the source contains one of the extensions in
     * ACCEPTABLE_EXTENSIONS
     *
     * @param source is a String
     * @return a boolean
     */
    public boolean detectExtension(String source) {
        for (int i = 0; i < ACCEPTABLE_EXTENSIONS.length; i++) {
            if (source.toLowerCase().endsWith(ACCEPTABLE_EXTENSIONS[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Initialize contents with the argument photoSource, and make the file
     * extension extracted from photoSource to lowercase and store it to
     * the variable extension.
     *
     * @param sender      is an instance of User
     * @param photoSource is a string
     * @throws OperationDeniedException
     * @throws IllegalArgumentException
     */
    public PhotoMessage(User sender, String photoSource)
                        throws OperationDeniedException {
        super(sender);

        if (sender instanceof StandardUser) {
            throw new OperationDeniedException(DENIED_USER_GROUP);
        }

        if (photoSource == null) {
            throw new IllegalArgumentException();
        }

        if (!detectExtension(photoSource)) {
            throw new OperationDeniedException(INVALID_INPUT);
        }

        for (int i = 0; i < ACCEPTABLE_EXTENSIONS.length; i++) {
            if (photoSource.toLowerCase().endsWith(ACCEPTABLE_EXTENSIONS[i])) {
                extension = ACCEPTABLE_EXTENSIONS[i];
            }
        }
        this.contents = photoSource;
    }

    /**
     * Returns a String in the form:
     * “SenderName [2020-01-15]: Picture at image/example.tif”
     *
     * @return a string
     */
    public String getContents() {
        String date = this.getDate().toString();
        User sender = this.getSender();
        return (sender.displayName() + " [" + date + "]: Picture at " + this.contents);
    }

    /**
     * Method will return the extension of this message.
     *
     * @return a string
     */
    public String getExtension() {
        return this.extension;
    }

}
