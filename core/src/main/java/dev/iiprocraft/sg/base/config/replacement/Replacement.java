package dev.iiprocraft.sg.base.config.replacement;

/**
 * @author DirectPlan
 */
public class Replacement {

    private final String key, replacement;
    private final ReplacementCharacter replacementCharacter;

    public Replacement(String key, String replacement, ReplacementCharacter replacementCharacter) {
        this.key = key;
        this.replacement = replacement;
        this.replacementCharacter = replacementCharacter;
    }

    public Replacement(String key, String replacement) {
        this(key, replacement, new PercentReplacement());
    }

    public String replace(String message) {
        return message.replace(replacementCharacter.start() + key + replacementCharacter.end(), replacement);
    }
}
