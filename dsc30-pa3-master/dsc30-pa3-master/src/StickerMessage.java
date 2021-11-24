public class StickerMessage extends Message {

    // instance variable
    private String packName;

    /**
     * Extract the name of a sticker pack and a sticker from stickerSource.
     * Initialize contents with the sticker name extracted, and initialize
     * packName with the sticker pack name extracted.
     *
     * @param sender        is an instance of User
     * @param stickerSource is a string
     * @throws OperationDeniedException
     * @throws IllegalArgumentException
     */
    public StickerMessage(User sender, String stickerSource)
            throws OperationDeniedException {
        super(sender);

        if (stickerSource == null) {
            throw new IllegalArgumentException();
        }

        if (sender instanceof StandardUser) {
            throw new OperationDeniedException(DENIED_USER_GROUP);
        }

        String[] preString = stickerSource.split("/");
        this.packName = preString[0];
        this.contents = preString[1];
    }

    /**
     * Returns a String in the form:
     * “SenderName [2019-10-29]: Sticker [Questioning] from Pack [yourcraft-8]”
     *
     * @return a string
     */
    public String getContents() {
        String date = this.getDate().toString();
        User sender = this.getSender();
        return (sender.displayName() + " [" + date + "]: Sticker [" + this.contents +
                "] from Pack [" + getPackName() + "]");
    }

    /**
     * Method will return the packName of this message.
     *
     * @return a string
     */
    public String getPackName() {
        return this.packName;
    }
}
