package dev.iiprocraft.sg.base.config.replacement;

/**
 * @author DirectPlan
 */
public class PercentReplacement implements ReplacementCharacter {

    private final char COMMON_CHAR = '%';

    @Override
    public char start() {
        return COMMON_CHAR;
    }

    @Override
    public char end() {
        return COMMON_CHAR;
    }
}
