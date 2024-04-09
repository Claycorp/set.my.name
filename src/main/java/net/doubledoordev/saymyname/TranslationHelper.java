package net.doubledoordev.saymyname;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslationHelper {

    public static final String ATTRIBUTE_PATTERN = "attribute(?:\\.name)?\\.(\\w+)(?:\\.(\\w+))?";
    public static final String ALEMBIC_ATTRIBUTE_PATTERN = "attribute\\.alembic\\.(\\w+)\\.(\\w+)";
    public static final String BLOCK_PATTERN = "block\\.(\\w+)\\.(\\w+)";
    public static final String EFFECT_PATTERN = "effect\\.(\\w+)\\.(\\w+)";
    public static final String ENTITY_PATTERN = "entity\\.(\\w+)\\.(\\w+)";
    public static final String ITEM_PATTERN = "item\\.(\\w+)\\.(\\w+)";
    public static final String GENERIC_PATTERN = "((?:\\w+\\.)+)(\\w+)";

    public static String createSuggestedTranslation(String key) {
        Pattern[] patterns = {
                Pattern.compile(ALEMBIC_ATTRIBUTE_PATTERN, Pattern.CASE_INSENSITIVE),
                Pattern.compile(ATTRIBUTE_PATTERN, Pattern.CASE_INSENSITIVE),
                Pattern.compile(BLOCK_PATTERN, Pattern.CASE_INSENSITIVE),
                Pattern.compile(EFFECT_PATTERN, Pattern.CASE_INSENSITIVE),
                Pattern.compile(ENTITY_PATTERN, Pattern.CASE_INSENSITIVE),
                Pattern.compile(ITEM_PATTERN, Pattern.CASE_INSENSITIVE),
                Pattern.compile(GENERIC_PATTERN, Pattern.CASE_INSENSITIVE)
        };

        String finalGroup = null;
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(key);
            if (matcher.matches()) {
                if (pattern.equals(Pattern.compile(ALEMBIC_ATTRIBUTE_PATTERN, Pattern.CASE_INSENSITIVE))) {
                    finalGroup = matcher.group(2);
                } else {
                    finalGroup = matcher.group(1);
                }
                if (finalGroup != null) {
                    String[] words = finalGroup.split("[._]");
                    StringBuilder sb = new StringBuilder();
                    for (String word : words) {
                        sb.append(capitalize(word)).append(" ");
                    }
                    return sb.toString().trim();
                }
            }
        }
        return key;
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
