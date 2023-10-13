
/**
 * Name: Mukize Patrick Habiyaremye
 * Student Number: 4323665
 * Project Name: An Authors Book Store ( Credit Card Validator )
 * 
 */

import java.util.Map;
import java.util.Scanner;

class CreditCardValidator {
    public static void main(String[] args) {

        System.out.println(
                """
                        Hi There!
                        The initial project I was given was a credit card validator.
                        But it will be hard to make a tangible project from just that prompt.
                        So I will be creating an e-commerce for an author who wish to sell their books and integrate the validator in the checkout.
                        Below I will demonstrate the card validation.
                        You can generate a fake card number at https://www.getcreditcardnumbers.com
                        Note: Only Master Card and Visa is supported at the moment.
                        """);

        System.out.print("Please enter your card number: ");
        Scanner kb = new Scanner(System.in);
        String cardNumber = kb.next();

        if (!containsOnlyDigits(cardNumber)) {
            System.out.println("Invalid: The number contains letters.");
            kb.close();
            return;
        }
        int cardNumberLength = cardNumber.length();
        if (cardNumberLength < 12) {
            System.out.println("Invalid: Number too small.");
            kb.close();
            return;
        }
        if (cardNumberLength > 19) {
            System.out.println("Invalid: length too large.");
            kb.close();
            return;
        }

        String issuer = getIssuer(cardNumber);
        if (issuer == "n/a") {
            System.out.println("Invalid: Incorrect issuer or not supported.");
            kb.close();
            return;
        }

        if (!validIssuerLength(cardNumber, issuer)) {
            System.out.println("Invalid: Incorrect length.");
            kb.close();
            return;
        }

        if (!validateWithLuhnAlgorithm(cardNumber)) {
            System.out.println("Invalid: Invalid or not supported.");
            kb.close();
            return;
        }

        System.out.println("Valid.");
        kb.close();
        return;
    }

    public static String validate(String cardNumber) {
        if (cardNumber.length() == 0) {
            return "Invalid: no number.";
        }

        if (!containsOnlyDigits(cardNumber)) {
            return "Invalid: The number contains non-numbers.";
        }
        int cardNumberLength = cardNumber.length();
        if (cardNumberLength < 12) {
            return ("Invalid: Number too small.");
        }
        if (cardNumberLength > 19) {
            return ("Invalid: length too large.");
        }

        String issuer = getIssuer(cardNumber);
        if (issuer == "n/a") {
            return ("Invalid: Incorrect issuer or not supported.");
        }

        if (!validIssuerLength(cardNumber, issuer)) {
            return ("Invalid: Incorrect length.");
        }

        if (!validateWithLuhnAlgorithm(cardNumber)) {
            return ("Invalid: Invalid or not supported.");
        }
        return ("Valid.");
    }

    public static Boolean validateWithLuhnAlgorithm(String cardNumber) {

        String lastDigit = cardNumber.substring(cardNumber.length() - 1, cardNumber.length());
        int checkNumber = Integer.parseInt(lastDigit);
        char[] numbers = cardNumber.substring(0, cardNumber.length() - 1).toCharArray();
        int numbersLength = numbers.length;

        int sum = 0;
        for (int i = numbersLength - 1; i >= 0; i--) {
            int number = Character.getNumericValue(numbers[i]);
            if ((numbersLength - 1 - i) % 2 == 0) {
                int doubled = number * 2;
                if (number > 4) {
                    sum += (number * 2) - 9;
                } else {
                    sum += doubled;
                }
            } else {
                sum += number;
            }
        }

        return (10 - (sum % 10)) == checkNumber;
    }

    public static Boolean validIssuerLength(String cardNumber, String issuer) {
        int cardLength = cardNumber.length();
        switch (issuer) {
            case "Mastercard":
            case "Visa":
                switch (cardLength) {
                    case 13:
                    case 16:
                    case 19:
                        return true;
                    default:
                        return false;
                }
            case "Visa Electron":
                if (cardLength == 16) {
                    return true;
                }
                return false;
        }
        return false;
    }

    public static String getIssuer(String cardNumber) {
        byte oneDigit = Byte.parseByte(cardNumber.substring(0, 1));

        switch (oneDigit) {
            case 2:
            case 4:
            case 5:
            case 6:
                break;
            default:
                return "n/a";
        }

        int sixDigits = Integer.parseInt(cardNumber.substring(0, 6));
        short fourDigits = Short.parseShort(cardNumber.substring(0, 4));
        byte twoDigits = Byte.parseByte(cardNumber.substring(0, 2));

        if (sixDigits == 417500) {
            return "Visa Electron";
        }

        switch (fourDigits) {
            case 4026:
            case 4508:
            case 4844:
            case 4913:
            case 4917:
                return "Visa Electron";
        }

        if (fourDigits >= 2221 && fourDigits <= 2720) {
            return "Mastercard";
        }

        if (twoDigits >= 51 && twoDigits <= 55) {
            return "Mastercard";
        }

        if (oneDigit == 4) {
            return "Visa";
        }

        return "n/a";
    }

    public static Boolean containsOnlyDigits(String cardNumber) {
        try {
            Long.parseLong(cardNumber);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
