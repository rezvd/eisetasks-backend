package it.sevenbits.eisetasks.core.service.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for status validation
 */
public final class StatusValidator {
    private static final List<String> STATUSES = new ArrayList<>();


    /**
     * Constructor, which will never be called. Utility class should not have public or default constructor
     */
    private StatusValidator() {
    }

    /**
     * Validates status
     *
     * @param status is the status, which will be checked
     * @return true, if the status is valid, otherwise return false
     */
    public static boolean isValid(final String status) {
        STATUSES.add("inbox");
        STATUSES.add("done");
        return STATUSES.contains(status);
    }
}
