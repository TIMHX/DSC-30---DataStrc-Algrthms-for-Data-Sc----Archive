/*
  Name: XING HONG
  PID:  A15867895
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Messenger Application Test
 *
 * @author XING HONG
 * @since 1/25/2021
 */
public class MessengerApplicationTest {

    /*
      Messages defined in starter code. Remember to copy and paste these strings to the
      test file if you cannot directly access them. DO NOT change the original declaration
      to public.
     */
    private static final String INVALID_INPUT =
            "The source path given cannot be parsed as photo.";
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";
    private static final String FETCH_DENIED_MSG =
            "This message cannot be fetched because you are not a premium user.";

    /*
      Global test variables. Initialize them in @Before method.
     */
    PremiumUser marina;
    PremiumUser marina2;
    PremiumUser marina3;
    StandardUser tim;
    StandardUser tim2;
    StandardUser tim3;
    MessageExchange room;
    MessageExchange photoroom;

    /*
      The date used in Message and its subclasses. You can directly
      call this in your test methods.
     */
    LocalDate date = LocalDate.now();

    /*
     * Setup
     */
    @Before
    public void setup() {
        marina = new PremiumUser("Marina", "Instructor");
        tim = new StandardUser("Tim", "Student");
        marina2 = new PremiumUser("Marina2", "Instructor");
        tim2 = new StandardUser("Tim2", "Student");
        marina3 = new PremiumUser("Marina3", "Instructor");
        tim3 = new StandardUser("Tim3", "Student");
        room = new ChatRoom();
        photoroom = new PhotoRoom();
    }

    /*
      Recap: Assert exception without message
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPremiumUserThrowsIAE() {
        marina = new PremiumUser("Marina", null);
        marina = new PremiumUser(null, null);
        marina = new PremiumUser(null, "Instructor");
        tim = new StandardUser("Tim", null);
        tim = new StandardUser(null, null);
        tim = new StandardUser(null, "Student");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBioThrowsIAE() {
        marina.setBio(null);
        tim.setBio(null);
    }

    @Test
    public void testSetBio() {
        marina.setBio("Student");
        tim.setBio("Instructor");

        assertEquals("Student", marina.displayBio());
        assertEquals("Instructor", tim.displayBio());

        marina.setBio("Instructor");
        tim.setBio("Student");
        assertEquals("Student", tim.displayBio());
        assertEquals("Instructor", marina.displayBio());
    }

    @Test
    public void testdisplayname() {
        assertEquals("<Premium> Marina", marina.displayName());
        assertEquals("<Premium> Marina2", marina2.displayName());
        assertEquals("<Premium> Marina3", marina3.displayName());
        assertEquals("Tim", tim.displayName());
        assertEquals("Tim2", tim2.displayName());
        assertEquals("Tim3", tim3.displayName());
    }

    @Test
    public void testsetCustomTitle() {
        marina.setCustomTitle("Epic");
        marina2.setCustomTitle("");
        marina3.setCustomTitle("''");

        assertEquals("<Epic> Marina", marina.displayName());
        assertEquals("<> Marina2", marina2.displayName());
        assertEquals("<''> Marina3", marina3.displayName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testjoinRoomThrowsIAE() {
        try {
            marina.joinRoom(null);
            marina2.joinRoom(null);
            marina3.joinRoom(null);
            tim.joinRoom(null);
            tim2.joinRoom(null);
            tim3.joinRoom(null);
        } catch (OperationDeniedException ode) {
            assertEquals(User.JOIN_ROOM_FAILED, ode.getMessage());
        }
    }

    @Test
    public void testfetchMessage() throws OperationDeniedException {
        marina.joinRoom(room);
        marina2.joinRoom(room);
        tim.joinRoom(room);
        tim2.joinRoom(room);

        marina.sendMessage(room, MessageType.TEXT, "Hi");
        tim.sendMessage(room, MessageType.TEXT, "Hi2");
        marina.sendMessage(room, MessageType.PHOTO, "Hi3.jpg");
        assertEquals("<Premium> Marina [" + date + "]: Hi\n" +
                "Tim [" + date + "]: Hi2\n" +
                "<Premium> Marina [" + date + "]: Picture at Hi3.jpg\n", marina.fetchMessage(room));

        tim2.sendMessage(room, MessageType.TEXT, "Hi4.jpg");
        marina2.sendMessage(room, MessageType.STICKER, "Hi5/jpg");

        assertEquals("<Premium> Marina [" + date + "]: Hi\n" +
                "Tim [" + date + "]: Hi2\n" +
                "<Premium> Marina [" + date + "]: Picture at Hi3.jpg\n" +
                "Tim2 [" + date + "]: Hi4.jpg\n" +
                "<Premium> Marina2 [" + date + "]: Sticker [jpg] from Pack [Hi5]\n", marina.fetchMessage(room));
        assertEquals("<Premium> Marina [" + date + "]: Hi\n" +
                "Tim [" + date + "]: Hi2\n" +
                "<Premium> Marina [" + date + "]: Picture at Hi3.jpg\n" +
                "Tim2 [" + date + "]: Hi4.jpg\n" +
                "<Premium> Marina2 [" + date + "]: Sticker [jpg] from Pack [Hi5]\n", marina2.fetchMessage(room));
        assertEquals("<Premium> Marina [" + date + "]: Hi\n" +
                "Tim [" + date + "]: Hi2\n" +
                FETCH_DENIED_MSG + "\n" +
                "Tim2 [" + date + "]: Hi4.jpg\n" +
                FETCH_DENIED_MSG + "\n", tim.fetchMessage(room));
    }

    @Test
    public void testcreateChatRoom() {
        ArrayList<User> a = new ArrayList<User>(Arrays.asList(tim2, tim3, marina, marina2));
        MessageExchange chat2 = tim.createChatRoom(a);
        assertEquals(5, chat2.getUsers().size());

        ArrayList<User> b = new ArrayList<User>(Arrays.asList(marina3, marina2));
        MessageExchange chat3 = marina.createChatRoom(b);
        assertEquals(3, chat3.getUsers().size());

        ArrayList<User> c = new ArrayList<User>(Arrays.asList(marina3, marina2, tim2));
        MessageExchange chat5 = marina.createChatRoom(c);
        assertEquals(4, chat5.getUsers().size());
    }

    @Test
    public void testcreatePhotoRoom() {
        ArrayList<User> b = new ArrayList<User>(Arrays.asList(marina3, marina2));
        MessageExchange chat4 = marina.createPhotoRoom(b);
        assertEquals(3, chat4.getUsers().size());

        ArrayList<User> c = new ArrayList<User>(Arrays.asList(marina, marina2, tim));
        MessageExchange chat5 = marina3.createPhotoRoom(c);
        assertEquals(3, chat5.getUsers().size());

        ArrayList<User> d = new ArrayList<User>(Arrays.asList(marina3, marina, tim2, tim3));
        MessageExchange chat6 = marina2.createPhotoRoom(d);
        assertEquals(3, chat5.getUsers().size());
    }

    @Test
    public void testaddUserremoveUser() {
        room.addUser(marina);
        room.addUser(marina2);
        room.addUser(marina3);
        assertEquals(3, room.getUsers().size());

        room.removeUser(marina);
        room.removeUser(marina);
        assertEquals(2, room.getUsers().size());

        photoroom.addUser(marina);
        assertTrue(photoroom.addUser(marina2));
        assertEquals(2, room.getUsers().size());

        photoroom.removeUser(marina);
        photoroom.removeUser(marina);
        assertEquals(2, room.getUsers().size());

        assertFalse(photoroom.addUser(tim));
        assertFalse(photoroom.addUser(tim2));
        assertFalse(photoroom.addUser(tim3));
        assertEquals(2, room.getUsers().size());
    }

    @Test
    public void testrecordMessage() throws OperationDeniedException {
        String msg = "hi";
        TextMessage tm = new TextMessage(tim, msg);
        TextMessage tm2 = new TextMessage(marina, msg);
        PhotoMessage tm3 = new PhotoMessage(marina2, "msg.jpg");

        room.recordMessage(tm);
        room.recordMessage(tm2);
        room.recordMessage(tm3);

        assertEquals(3, room.getLog().size());

        StickerMessage tm4 = new StickerMessage(marina3, "msg/jpg");
        room.recordMessage(tm4);
        room.recordMessage(tm4);
        room.recordMessage(tm4);

        assertEquals(6, room.getLog().size());

        photoroom.recordMessage(tm);
        photoroom.recordMessage(tm2);
        photoroom.recordMessage(tm3);

        assertEquals(1, photoroom.getLog().size());

        photoroom.recordMessage(tm3);
        photoroom.recordMessage(tm3);
        photoroom.recordMessage(tm3);
        assertEquals(4, photoroom.getLog().size());
    }

    @Test
    public void testStickergetContents() throws OperationDeniedException {
        StickerMessage tm4 = new StickerMessage(marina3, "msg/jpg");
        StickerMessage tm5 = new StickerMessage(marina2, "msg/jpg");
        StickerMessage tm6 = new StickerMessage(marina, "msg/jpg");

        assertEquals("<Premium> Marina3 [" + date + "]: Sticker [jpg] " +
                "from Pack [msg]", tm4.getContents());
        assertEquals("<Premium> Marina2 [" + date + "]: Sticker [jpg] " +
                "from Pack [msg]", tm5.getContents());
        assertEquals("<Premium> Marina [" + date + "]: Sticker [jpg] from Pack [msg]", tm6.getContents());
    }

    @Test
    public void testjoinRoomThrowsODE2() {
        try {
            marina.joinRoom(photoroom);
            marina.joinRoom(photoroom);
            fail("Exception not thrown");

            marina2.joinRoom(photoroom);
            marina2.joinRoom(photoroom);
            fail("Exception not thrown");

            tim.joinRoom(photoroom);
            tim2.joinRoom(photoroom);
            tim3.joinRoom(photoroom);
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(User.JOIN_ROOM_FAILED, ode.getMessage());
        }
    }

    @Test
    public void testjoinRoom() throws OperationDeniedException {
        marina.joinRoom(photoroom);
        marina2.joinRoom(photoroom);
        marina3.joinRoom(photoroom);

        marina.joinRoom(room);
        marina2.joinRoom(room);
        marina3.joinRoom(room);
        assertEquals(3, photoroom.getUsers().size());
        assertEquals(3, room.getUsers().size());

        tim.joinRoom(room);
        tim2.joinRoom(room);
        tim3.joinRoom(room);
        assertEquals(6, room.getUsers().size());

        assertEquals(2, marina.rooms.size());
        assertEquals(2, marina2.rooms.size());
        assertEquals(2, marina3.rooms.size());

        assertEquals(1, tim.rooms.size());
        assertEquals(1, tim2.rooms.size());
        assertEquals(1, tim3.rooms.size());
    }

    @Test
    public void testquitRoom() throws OperationDeniedException {
        marina.joinRoom(photoroom);
        marina2.joinRoom(photoroom);
        marina3.joinRoom(photoroom);
        marina.quitRoom(photoroom);
        marina2.quitRoom(photoroom);
        assertEquals(1, photoroom.getUsers().size());
        assertEquals(0, marina.rooms.size());
        assertEquals(0, marina2.rooms.size());


        marina.joinRoom(room);
        marina2.joinRoom(room);
        marina3.joinRoom(room);
        marina.quitRoom(room);
        marina2.quitRoom(room);
        assertEquals(1, room.getUsers().size());
        assertEquals(0, marina.rooms.size());
        assertEquals(0, marina2.rooms.size());

        tim.joinRoom(room);
        tim2.joinRoom(room);
        tim3.joinRoom(room);
        tim.quitRoom(room);
        tim2.quitRoom(room);
        assertEquals(2, room.getUsers().size());
        assertEquals(0, tim.rooms.size());
        assertEquals(0, tim2.rooms.size());

        marina.joinRoom(room);
        marina.joinRoom(photoroom);
        assertEquals(3, room.getUsers().size());
        assertEquals(2, marina.rooms.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendMassageThrowsIAE() {
        marina.sendMessage(null, MessageType.PHOTO, "PA02.jpg");
        marina.sendMessage(room, null, "PA02.jpg");
        marina.sendMessage(room, MessageType.PHOTO, null);
        marina.sendMessage(room, MessageType.PHOTO, "PA02.jpg");

        tim.sendMessage(null, MessageType.TEXT, "PA02.jpg");
        tim.sendMessage(room, null, "PA02.jpg");
        tim.sendMessage(room, MessageType.TEXT, null);
        tim.sendMessage(room, MessageType.TEXT, "PA02.jpg");
    }

    @Test
    public void testsendMessageThrowsODE() throws OperationDeniedException {
        marina.joinRoom(photoroom);
        marina.sendMessage(photoroom, MessageType.TEXT, "Ha.jpg");
        marina.sendMessage(photoroom, MessageType.STICKER, "Ha/jpg");
    }

    @Test
    public void testsendMessage() throws OperationDeniedException {
        marina.joinRoom(photoroom);
        marina2.joinRoom(photoroom);
        marina.sendMessage(photoroom, MessageType.PHOTO, "Ha.jpg");
        marina2.sendMessage(photoroom, MessageType.PHOTO, "poo.jpg");

        assertEquals("Ha.jpg", photoroom.getLog().get(0).contents);
        assertEquals("Marina", photoroom.getLog().get(0).getSender().username);

        assertEquals("poo.jpg", photoroom.getLog().get(1).contents);
        assertEquals("Marina2", photoroom.getLog().get(1).getSender().username);
    }

    /*
      Assert exception with message
     */
    @Test
    public void testtextMessageThrowsODE() {
        String msg = null;
        String msg2 = null;
        String msg3 = null;
        for (int i = 0; i < 250; i++) {
            msg += "1";
            msg2 += "popo";
            msg3 += "";
        }
        try {
            TextMessage tm = new TextMessage(tim, msg);
            TextMessage tm2 = new TextMessage(tim, msg2);
            TextMessage tm3 = new TextMessage(marina, msg3);
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(EXCEED_MAX_LENGTH, ode.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTextMessageThrowsIAE() throws OperationDeniedException {
        String msg = null;
        TextMessage tm = new TextMessage(tim, msg);
        TextMessage tm2 = new TextMessage(null, msg);
    }

    @Test
    public void testTextMessagegetContents() throws OperationDeniedException {
        String msg = "hi";
        TextMessage tm = new TextMessage(tim, msg);
        TextMessage tm2 = new TextMessage(marina, msg);
        TextMessage tm3 = new TextMessage(marina2, msg);

        assertEquals("Tim [" + date + "]: hi", tm.getContents());
        assertEquals("<Premium> Marina [" + date + "]: hi", tm2.getContents());
        assertEquals("<Premium> Marina2 [" + date + "]: hi", tm3.getContents());
    }

    @Test
    public void testPhotoMessageThrowsODE() {
        try {
            PhotoMessage pm = new PhotoMessage(marina, "PA02.zip");
            PhotoMessage pm1 = new PhotoMessage(marina2, "PA02.zip");
            PhotoMessage pm2 = new PhotoMessage(marina3, "PA02.zip");

            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }

        try {
            PhotoMessage p1 = new PhotoMessage(tim, "PA02.jpg");
            PhotoMessage p2 = new PhotoMessage(tim2, "PA02.jpg");
            PhotoMessage p3 = new PhotoMessage(tim3, "PA02.jpg");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(PhotoMessage.DENIED_USER_GROUP, ode.getMessage());
        }
    }

    @Test
    public void testPhotoMessage() throws OperationDeniedException {
        PhotoMessage p1 = new PhotoMessage(marina, "PA02.jpg");
        PhotoMessage p2 = new PhotoMessage(marina2, "PA02.jpg");
        PhotoMessage p3 = new PhotoMessage(marina3, "PA02.jpg");

        assertEquals("PA02.jpg", p1.contents);
        assertEquals("PA02.jpg", p2.contents);
        assertEquals("PA02.jpg", p3.contents);
    }

    /*
     * Assert message content without hardcoding the date
     */
    @Test
    public void testTextMessageGetContents() {
        try {
            TextMessage tm = new TextMessage(marina, "A sample text message.");

            // concatenating the current date when running the test
            String expected = "<Premium> Marina [" + date + "]: A sample text message.";
            assertEquals(expected, tm.getContents());
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

}
