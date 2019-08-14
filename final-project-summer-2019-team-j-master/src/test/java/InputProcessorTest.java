import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InputProcessorTest {

    @Test
    public void checkOutputForInvalidZip() {
        String zipInput = "abc";
        String foodInput = "sushi";
        InputProcessor inputProcessor = new InputProcessor(zipInput, foodInput);

        String actualOutput = inputProcessor.getOutputString();
        String expectedOutput = "The zipcode you inputted isn't in the right format, please enter a 5 digit US zipcode.";

        assertEquals(expectedOutput, actualOutput);
        assertFalse(inputProcessor.isZipValid());
    }

    @Test
    public void checkOutputForZipDoesntExist() {
        String zipInput = "00000";
        String foodInput = "sushi";
        InputProcessor inputProcessor = new InputProcessor(zipInput, foodInput);

        String actualOutput = inputProcessor.getOutputString();
        String expectedOutput = "We don't have this zipcode in our database!  Please try a different one.";

        assertEquals(expectedOutput, actualOutput);
        assertFalse(inputProcessor.isZipValid());
    }

    @Test
    public void checkOutputForValidZip() {
        String zipInput = "84660";
        String foodInput = "laundromat";
        InputProcessor inputProcessor = new InputProcessor(zipInput, foodInput);

        String actualOutput = inputProcessor.getOutputString();
        String expectedOutput = getExpectedOutputString();

        assertEquals(expectedOutput, actualOutput);
        assertTrue(inputProcessor.isZipValid());
    }

    @Test
    public void checkOutputForValidZipAndNoFoodType() {
        String zipInput = "84660";
        String foodInput = "";
        InputProcessor inputProcessor = new InputProcessor(zipInput, foodInput);

        String actualOutput = inputProcessor.getOutputString();
        String expectedOutput = getExpectedOutputString2();

        assertEquals(expectedOutput, actualOutput);
        assertTrue(inputProcessor.isZipValid());
    }

    private static String getExpectedOutputString() {
        return "The temperature at 84660 is 85 F\n" +
                "\n" +
                "Number of search results nearby: 10\n" +
                "\n" +
                "Name :     Splish Splash Laundromat\n" +
                "Address:  295 S Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.03 Miles\n" +
                "Category: Laundromat\n" +
                "\n" +
                "Name :     Bubble Hut Laundromat\n" +
                "Address:  East Center Street (Hwy 6) \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 1.19 Miles\n" +
                "Category: Laundromat\n" +
                "\n" +
                "Name :     Laundromat\n" +
                "Address:,  Springville, UT 84663 \n" +
                "Distance: 4.44 Miles\n" +
                "Category: Laundromat\n" +
                "\n" +
                "Name :     Laundromat\n" +
                "Address:  650 North Freedom Boulevard \n" +
                "                   Provo, UT 84601 \n" +
                "Distance: 9.42 Miles\n" +
                "Category: Laundromat\n" +
                "\n" +
                "Name :     Cougartown Laundromat\n" +
                "Address:  1524 \n" +
                "                   Provo, UT 84604 \n" +
                "Distance: 10.22 Miles\n" +
                "Category: Laundromat\n" +
                "\n" +
                "Name :     Laundromat\n" +
                "Address:,  Santaquin, UT \n" +
                "Distance: 11.20 Miles\n" +
                "Category: Laundromat\n" +
                "\n" +
                "Name :     Spin Zone Laundromat\n" +
                "Address:  1360 Sandhill Rd \n" +
                "                   Orem, UT 84058 \n" +
                "Distance: 11.87 Miles\n" +
                "Category: Laundromat\n" +
                "\n" +
                "Name :     Suds Laundromat\n" +
                "Address:  98 W  Center \n" +
                "                   Pleasant Grove, UT 84062 \n" +
                "Distance: 18.42 Miles\n" +
                "Category: Laundromat\n" +
                "\n" +
                "Name :     Oasis Dry Cleaning & Laundromat\n" +
                "Address:  85 N Grant Ave  \n" +
                "                   American Fork, UT 84003 \n" +
                "Distance: 20.22 Miles\n" +
                "Category: Laundromat\n" +
                "\n" +
                "Name :     Mountainview Dry Cleaners & Laundromat\n" +
                "Address:  415 S Main St \n" +
                "                   Heber, UT 84032 \n" +
                "Distance: 30.19 Miles\n" +
                "Category: Laundromat\n" +
                "\n";
    }

    private static String getExpectedOutputString2(){
        return "The temperature at 84660 is 85 F\n" +
                "\n" +
                "Number of search results nearby: 30\n" +
                "\n" +
                "Name :     Splish Splash Laundromat\n" +
                "Address:  295 S Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.03 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Cary's Designs Floral\n" +
                "Address:  245 S Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.05 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Glades\n" +
                "Address:  296 S Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.06 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Title West\n" +
                "Address:  200 S  Main \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.06 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Walker Mortuary\n" +
                "Address:  187 S Main St \n" +
                "                   Spanish Fork, UT \n" +
                "Distance: 0.07 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Nebo School District Office\n" +
                "Address:,  Spanish Fork, UT 84660 \n" +
                "Distance: 0.09 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Pier 49 Pizza\n" +
                "Address:  149 S Main \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.10 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Los 2 Portillos\n" +
                "Address:,  Spanish Fork, UT \n" +
                "Distance: 0.11 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Skiba Auto Repair\n" +
                "Address:,  Spanish Fork, UT 84653 \n" +
                "Distance: 0.11 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Barry's Drive In\n" +
                "Address:  100 S Main \n" +
                "                   Spanish Fork, UT \n" +
                "Distance: 0.13 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Segway Of Utah\n" +
                "Address:,  Spanish Fork, UT 84660 \n" +
                "Distance: 0.15 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     The Church of Jesus Christ of Latter-day Saints\n" +
                "Address:  98 S Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.15 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Indulge Salon & Spa\n" +
                "Address:  450 S Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.16 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Dans\n" +
                "Address:,  Spanish Fork, UT 84660 \n" +
                "Distance: 0.17 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     The Cleaning Solution\n" +
                "Address:  414 S Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.18 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Spanish Fork City Park\n" +
                "Address:  100 East 100 South \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.23 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Spanish Fork Fairgrounds\n" +
                "Address:  475 S Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.26 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Huff's Carpet Enterprise\n" +
                "Address:  40 N Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.29 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     The Church of Jesus Christ of Latter-day Saints\n" +
                "Address:  46 N 100 E \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.29 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     MVP Sports\n" +
                "Address:  72 N Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.31 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Farmers Insurance Randy Tuckett\n" +
                "Address:,  Spanish Fork, UT 84660 \n" +
                "Distance: 0.35 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Back Alley Market\n" +
                "Address:  82 N 100 W \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.35 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Quantum Guns\n" +
                "Address:  122 N Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.35 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     ZZZ PAWN SHOP\n" +
                "Address:  189 N Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.41 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Verizion Store\n" +
                "Address:  254 n main \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.47 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Farmers Insurance\n" +
                "Address:  814 S 1040 W \n" +
                "                   Payson, UT 84651 \n" +
                "Distance: 0.48 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Metro by T-Mobile\n" +
                "Address:  279 N Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.49 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Main St Floral and Gifts\n" +
                "Address:,  Spanish Fork, UT 84660 \n" +
                "Distance: 0.49 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     The Church of Jesus Christ of Latter-day Saints\n" +
                "Address:  420 S Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.52 Miles\n" +
                "Category: \n" +
                "\n" +
                "Name :     Castrol Premium Lube Express\n" +
                "Address:  325 N Main St \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.53 Miles\n" +
                "Category: \n" +
                "\n";
}
}
