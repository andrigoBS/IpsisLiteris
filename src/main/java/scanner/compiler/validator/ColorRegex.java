package scanner.compiler.validator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ColorRegex {
    private enum RegexList{
         NORMAL,
         WORDS("\\b(program", "define", "execute", "is", "variable", "not", "to)\\b"),
         COMMANDS("\\b(set", "get", "put", "while", "loop", "do", "verify)\\b"),
         TYPES("\\b(natural", "real", "char", "boolean", "true", "false)\\b"),
         AGGREGATORS("\\{", "\\}", "\\(", "\\)", "\\[", "\\]"),
         COMPARATORS("==", "!=", ">=", "<=", ">", "<"),
         ARITHMETIC("\\+", "-", "/", "\\*", "%"),
         LOGIC("&", "\\|", "!"),
         SPECIAL("\\.", ",", ":-"),
         STRING("\".*\"|'.*'"),
         COMMENTS(":_.*\\n|:\\{(.|\\s)*\\}:");

        private final List<String> regexes;

        RegexList(String... regex) {
            this.regexes = List.of(regex);
        }

        public boolean contains(String text){
            return Pattern.matches(toString(), text);
        }

        public boolean notEmpty(){
            return !regexes.isEmpty();
        }

        @Override
        public String toString() {
            return String.join("|", regexes);
        }
    }

    private static final Pattern PATTERN = Pattern.compile(
            Arrays.stream(RegexList.values())
                    .filter(RegexList::notEmpty)
                    .map(regexList -> "(?<"+regexList.name()+">"+regexList+")")
                    .collect(Collectors.joining("|")),
            Pattern.CASE_INSENSITIVE
    );

    public static Matcher getMatcher(String text){
        return PATTERN.matcher(text);
    }

    public static String getCss(String text){
        return Arrays.stream(RegexList.values())
                     .filter(regexList -> regexList.contains(text))
                     .findFirst().orElse(RegexList.NORMAL)
                     .name().toLowerCase();
    }
}
