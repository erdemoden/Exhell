package org.woh.exceptions;

/**
 * A custom runtime exception used for unexpected failures during sheet processing.
 * <p>
 * /**
 *  * Throw this when you want to say: "Oh shit, something went really wrong."
 *  */
public class OhSheetException extends RuntimeException{
    public OhSheetException(String message) {
        super(message);
    }
}
