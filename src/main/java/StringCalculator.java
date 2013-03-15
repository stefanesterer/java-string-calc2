import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

/**
 * @author xbk
 */
public class StringCalculator
{

    public static int add(String numbers)
    {
        if (numbers.isEmpty())
        {
            return 0;
        }

        String[] numberTokens = parseInputString(numbers);
        return calculateSum(numberTokens);
    }

    private static String[] parseInputString(final String numbers)
    {
        String separatorRegex = "[,\n]";
        String modifiedNumbers = numbers;
        if (hasCustomDelimiter(numbers))
        {
            int endOfFirstLine = numbers.indexOf("\n");
            separatorRegex = parseCustomRegexp(numbers, endOfFirstLine);
            modifiedNumbers = getNumbersWithoutCustomDelimiter(numbers, endOfFirstLine);
        }

        return modifiedNumbers.split(separatorRegex);
    }

    private static String getNumbersWithoutCustomDelimiter(final String numbers, final int endOfFirstLine)
    {
        return numbers.substring(endOfFirstLine + 1);
    }

    private static String parseCustomRegexp(final String numbers, final int endOfFirstLine)
    {
        return numbers.substring(2, endOfFirstLine);
    }

    private static boolean hasCustomDelimiter(final String numbers)
    {
        return numbers.startsWith("//");
    }

    private static Integer calculateSum(final String[] numbersArray)
    {
        Integer sum = 0;
        ArrayList<Integer> negatives = new ArrayList<Integer>();

        for (String token : numbersArray)
        {
            Integer number = Integer.valueOf(token);
            storeNegativeNumber(negatives, number);

            if(number < 1000)
            {
                sum += number;
            }

        }
        verifyNoNegativeNumbers(negatives);
        return sum;
    }

    private static void storeNegativeNumber(final ArrayList<Integer> negatives, final Integer number)
    {
        if (number < 0)
        {
            negatives.add(number);
        }
    }

    private static void verifyNoNegativeNumbers(final ArrayList<Integer> negatives)
    {
        if (!negatives.isEmpty())
        {
            throw new RuntimeException("negatives not allowed: " + StringUtils.join(negatives, ", "));
        }
    }
}
