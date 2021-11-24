/*
    Name: Xing Hong
    PID:  A15867895
 */

/**
 * An image editor. The image editor will maintain
 * the current image, and provides several painting operations along with an
 * undo/redo functionality.
 * @author Xing Hong
 * @since  1/18/2020
 */
public class ImageEditor {

    /* static constants, feel free to add more if you need */
    private static final int MAX_PIXEL_VALUE      = 255;
    private static final int STACKS_INIT_CAPACITY = 30;

    /* instance variables, feel free to add more if you need */
    private int[][] image;
    private IntStack undo;
    private IntStack redo;
    private int framI;
    private int framJ;

    /**
     * Initialize the editor, setting undo redo and the size of image;
     * @param image is 2d int array
     */
    ImageEditor(int[][] image) {
        this.image = image;
        undo = new IntStack(STACKS_INIT_CAPACITY);
        redo = new IntStack(STACKS_INIT_CAPACITY);

        framI = image.length;
        if (framI > 0){
            framJ = image[0].length;
        }
        if (framI == 0 || framJ == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < framI; i++) {
            if (image[i].length != framJ) {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Returns the image as a 2D array.
     * @return a 2d int array
     */
    int[][] getImage() {
        return this.image;
    }

    /** Duplicate parts in methods that detect IndexOutOfBoundsException
     *  @param i is an int
     *  @param j is an int
     */
    private void outbound(int i, int j) {
        if (i < 0 || i > framI || j < 0 || j > framJ) {
            throw new IndexOutOfBoundsException();
        }

        undo.push(i);
        undo.push(j);
        undo.push(this.image[i][j]);
    }


    /** Scale the given value of color to the pixel at the given position.
     *  The valid range of color is (0 <= color <= 255).
     *  @param i is an int as i position
     *  @param j is an int as y position
     *  @param scaleFactor is a double
     */
    public void scale(int i, int j, double scaleFactor) {
        if (scaleFactor < 0) {
            throw new IllegalArgumentException();
        }
        outbound(i, j);

        redo.clear();
        this.image[i][j] = (int) (this.image[i][j] * scaleFactor);
        if (this.image[i][j] > 255){    //set to 255 if exceeded
            this.image[i][j] = 255;
        }
    }

    /** Assigns a given color value to the pixel at the given position.
     *  The valid range of color is (0 <= color <= 255).
     *  @param i is an int
     *  @param j is an int
     *  @param color is an int
     */
    public void assign(int i, int j, int color) {
        if (color < 0 || color > MAX_PIXEL_VALUE) {
            throw new IllegalArgumentException();
        }
        outbound(i, j);

        redo.clear();
        this.image[i][j] = color;
    }


    /** Assigns 0 value to the pixel at the given position.
     *  The valid range of color is (0 <= color <= 255).
     *  @param i is an int
     *  @param j is an int
     */
    public void delete(int i, int j) {
        outbound(i, j);
        redo.clear();
        this.image[i][j] = 0;
    }

    /** Updates the image by undoing the latest operation. Returns
     * false if there’s no operation to undo, and true if undo was
     * successful. Make sure it does not throw an EmptyStackException.
     * @return a boolean
     */
    public boolean undo() {
        if(undo.isEmpty()){
            return false;
        }

        int color = undo.pop();
        int j = undo.pop();
        int i = undo.pop();
        int original = this.image[i][j];    //save original for redo

        this.image[i][j] = color;

        redo.push(i);       //save into redo
        redo.push(j);
        redo.push(original);
        return true;
    }

    /** Updates the image by redoing the next operation. Returns
     * false if there’s no operation to redo, and true if redo was
     * successful. Make sure it does not throw an EmptyStackException.
     * @return a boolean
     */
    public boolean redo() {
        if(redo.isEmpty()){
            return false;
        }

        int color = redo.pop();
        int j = redo.pop();
        int i = redo.pop();
        int original = this.image[i][j];    //save original for undo

        this.image[i][j] = color;

        undo.push(i);       //save into undo
        undo.push(j);
        undo.push(original);

        return true;
    }
}
