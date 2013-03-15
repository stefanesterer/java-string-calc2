import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

/**
 * @author xbk
 */
public class StringCalculatorTest
{
    @Test
    public void emptyStringReturnsZero()
    {
        assertEquals(StringCalculator.add(""), 0);
    }

    @Test
    public void oneNumberStringReturnsThisNumber()
    {
        assertEquals(StringCalculator.add("42"), 42);
    }

    @Test
    public void twoNumbersReturnTheSum()
    {
        assertEquals(StringCalculator.add("1,2"), 3);
    }

    @Test
    public void threeNumbersReturnTheSum()
    {
        assertEquals(StringCalculator.add("1,2,3"), 6);
    }

    @Test
    public void twoNumbersSeparatedByNewlinesReturnTheSum()
    {
        assertEquals(StringCalculator.add("1\n3"), 4);
    }

    @Test
    public void threeNumbersSeparatedByNewlinesAndCommasReturnTheSum()
    {
        assertEquals(StringCalculator.add("1\n3,2"), 6);
    }

    @Test
    public void twoNumbersWithCustomDelimiter()
    {
        assertEquals(StringCalculator.add("//x\n1x2"), 3);
    }

    @Test
    public void twoNumbersWithCustomDelimiterWithMoreThanOneChar()
    {
        assertEquals(StringCalculator.add("//stefon\n1stefon2"), 3);
    }

    @Test
    public void numberWithBiggerThanThousandIsIgnored()
    {
        assertEquals(StringCalculator.add("1,1001"), 1);
    }

    @Test
    public void singleNegativeNumbersThrowsIllegalArgumentException()
    {
        try
        {
            StringCalculator.add("-1");
            fail();
        }
        catch (Exception e)
        {
            assertEquals(e.getMessage(), "negatives not allowed: -1");
        }
    }

    @Test
    public void multipleNegativeNumbersThrowsIllegalArgumentException()
    {
        try
        {
            StringCalculator.add("-1,-2");
            fail();
        }
        catch (Exception e)
        {
            assertEquals(e.getMessage(), "negatives not allowed: -1, -2");
        }
    }
}
