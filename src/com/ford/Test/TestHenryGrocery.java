import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHenryGrocery {

    @Test
    void test1(){
        InputStream inStream = System.in;
        System.setIn(new ByteArrayInputStream("2\nsoup\n3\nbread\n2\n2023-06-19\n".getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream streamIn = new PrintStream(byteArrayOutputStream);
        PrintStream outPrint = System.out;
        System.setOut(streamIn);

        HenryGroceryShop.main(new String[0]);

        System.setIn(inStream);
        System.setOut(outPrint);

        String textVal = byteArrayOutputStream.toString();
        String key = "Bill Amount :";
        String keyVal = textVal.substring(textVal.indexOf(key) + key.length()).trim();
        assertEquals(keyVal, "3.15");
    }

    @Test
    void test2(){
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("2\napple\n6\nmilk\n1\n2023-06-19\n".getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream streamIn = new PrintStream(byteArrayOutputStream);
        PrintStream outPrint = System.out;
        System.setOut(streamIn);

        HenryGroceryShop.main(new String[0]);

        System.setIn(stdin);
        System.setOut(outPrint);

        String textVal = byteArrayOutputStream.toString();
        String key = "Bill Amount :";
        String keyVal = textVal.substring(textVal.indexOf(key) + key.length()).trim();
        assertEquals(keyVal, "1.9");
    }

    @Test
    void testcase3(){
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("2\napple\n6\nmilk\n1\n2023-06-24\n".getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream streamIn = new PrintStream(byteArrayOutputStream);
        PrintStream outPrint = System.out;
        System.setOut(streamIn);

        HenryGroceryShop.main(new String[0]);

        System.setIn(stdin);
        System.setOut(outPrint);

        String textVal = byteArrayOutputStream.toString();
        String key = "Bill Amount :";
        String keyVal = textVal.substring(textVal.indexOf(key) + key.length()).trim();
        assertEquals(keyVal, "1.84");
    }

    @Test
    void test4(){
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("3\napple\n3\nsoup\n2\nbread\n1\n2023-06-24\n".getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream streamIn = new PrintStream(byteArrayOutputStream);
        PrintStream outPrint = System.out;
        System.setOut(streamIn);

        HenryGroceryShop.main(new String[0]);

        System.setIn(stdin);
        System.setOut(outPrint);

        String textVal = byteArrayOutputStream.toString();
        String key = "Bill Amount :";
        String keyVal = textVal.substring(textVal.indexOf(key) + key.length()).trim();
        assertEquals(keyVal, "1.97");
    }
}
