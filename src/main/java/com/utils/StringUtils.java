package com.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.WordUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

public abstract class StringUtils extends org.apache.commons.lang.StringUtils {

    private static final String FOLDER_SEPARATOR         = "/";

    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

    private static final String TOP_PATH                 = "..";

    private static final String CURRENT_PATH             = ".";

    private static final char   EXTENSION_SEPARATOR      = '.';
    private static final String WRITE_SPACE              = " ";
    private static final String Empty                    = "";
    
    // ---------------------------------------------------------------------
    // General convenience methods for working with Strings
    // ---------------------------------------------------------------------

    /**
     * Check that the given CharSequence is neither <code>null</code> nor of length 0. Note: Will return
     * <code>true</code> for a CharSequence that purely consists of whitespace.
     * <p>
     * 
     * <pre>
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     * </pre>
     * 
     * @param str the CharSequence to check (may be <code>null</code>)
     * @return <code>true</code> if the CharSequence is not null and has length
     * @see #hasText(String)
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * Check that the given String is neither <code>null</code> nor of length 0. Note: Will return <code>true</code> for
     * a String that purely consists of whitespace.
     * 
     * @param str the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not null and has length
     * @see #hasLength(CharSequence)
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    /**
     * Check whether the given CharSequence has actual text. More specifically, returns <code>true</code> if the string
     * not <code>null</code>, its length is greater than 0, and it contains at least one non-whitespace character.
     * <p>
     * 
     * <pre>
     * StringUtils.hasText(null) = false
     * StringUtils.hasText("") = false
     * StringUtils.hasText(" ") = false
     * StringUtils.hasText("12345") = true
     * StringUtils.hasText(" 12345 ") = true
     * </pre>
     * 
     * @param str the CharSequence to check (may be <code>null</code>)
     * @return <code>true</code> if the CharSequence is not <code>null</code>, its length is greater than 0, and it does
     * not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given String has actual text. More specifically, returns <code>true</code> if the string not
     * <code>null</code>, its length is greater than 0, and it contains at least one non-whitespace character.
     * 
     * @param str the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not <code>null</code>, its length is greater than 0, and it does not
     * contain whitespace only
     * @see #hasText(CharSequence)
     */
    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }

    /**
     * Check whether the given CharSequence contains any whitespace characters.
     * 
     * @param str the CharSequence to check (may be <code>null</code>)
     * @return <code>true</code> if the CharSequence is not empty and contains at least 1 whitespace character
     * @see Character#isWhitespace
     */
    public static boolean containsWhitespace(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given String contains any whitespace characters.
     * 
     * @param str the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not empty and contains at least 1 whitespace character
     * @see #containsWhitespace(CharSequence)
     */
    public static boolean containsWhitespace(String str) {
        return containsWhitespace((CharSequence) str);
    }

    /**
     * Trim leading and trailing whitespace from the given String.
     * 
     * @param str the String to check
     * @return the trimmed String
     * @see Character#isWhitespace
     */
    public static String trimWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
            buf.deleteCharAt(0);
        }
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * Trim <i>all</i> whitespace from the given String: leading, trailing, and inbetween characters.
     * 
     * @param str the String to check
     * @return the trimmed String
     * @see Character#isWhitespace
     */
    public static String trimAllWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        int index = 0;
        while (buf.length() > index) {
            if (Character.isWhitespace(buf.charAt(index))) {
                buf.deleteCharAt(index);
            } else {
                index++;
            }
        }
        return buf.toString();
    }

    /**
     * Trim leading whitespace from the given String.
     * 
     * @param str the String to check
     * @return the trimmed String
     * @see Character#isWhitespace
     */
    public static String trimLeadingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
            buf.deleteCharAt(0);
        }
        return buf.toString();
    }

    /**
     * Trim trailing whitespace from the given String.
     * 
     * @param str the String to check
     * @return the trimmed String
     * @see Character#isWhitespace
     */
    public static String trimTrailingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * Trim all occurences of the supplied leading character from the given String.
     * 
     * @param str the String to check
     * @param leadingCharacter the leading character to be trimmed
     * @return the trimmed String
     */
    public static String trimLeadingCharacter(String str, char leadingCharacter) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && buf.charAt(0) == leadingCharacter) {
            buf.deleteCharAt(0);
        }
        return buf.toString();
    }

    /**
     * Trim all occurences of the supplied trailing character from the given String.
     * 
     * @param str the String to check
     * @param trailingCharacter the trailing character to be trimmed
     * @return the trimmed String
     */
    public static String trimTrailingCharacter(String str, char trailingCharacter) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && buf.charAt(buf.length() - 1) == trailingCharacter) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * Test if the given String starts with the specified prefix, ignoring upper/lower case.
     * 
     * @param str the String to check
     * @param prefix the prefix to look for
     * @see String#startsWith
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        if (str.startsWith(prefix)) {
            return true;
        }
        if (str.length() < prefix.length()) {
            return false;
        }
        String lcStr = str.substring(0, prefix.length()).toLowerCase();
        String lcPrefix = prefix.toLowerCase();
        return lcStr.equals(lcPrefix);
    }

    /**
     * Test if the given String ends with the specified suffix, ignoring upper/lower case.
     * 
     * @param str the String to check
     * @param suffix the suffix to look for
     * @see String#endsWith
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        if (str == null || suffix == null) {
            return false;
        }
        if (str.endsWith(suffix)) {
            return true;
        }
        if (str.length() < suffix.length()) {
            return false;
        }

        String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
        String lcSuffix = suffix.toLowerCase();
        return lcStr.equals(lcSuffix);
    }

    /**
     * Test whether the given string matches the given substring at the given index.
     * 
     * @param str the original string (or StringBuffer)
     * @param index the index in the original string to start matching against
     * @param substring the substring to match at the given index
     */
    public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
        for (int j = 0; j < substring.length(); j++) {
            int i = index + j;
            if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Count the occurrences of the substring in string s.
     * 
     * @param str string to search in. Return 0 if this is null.
     * @param sub string to search for. Return 0 if this is null.
     */
    public static int countOccurrencesOf(String str, String sub) {
        if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
            return 0;
        }
        int count = 0, pos = 0, idx = 0;
        while ((idx = str.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
        }
        return count;
    }

    /**
     * Delete all occurrences of the given substring.
     * 
     * @param inString the original String
     * @param pattern the pattern to delete all occurrences of
     * @return the resulting String
     */
    public static String delete(String inString, String pattern) {
        return replace(inString, pattern, "");
    }

    /**
     * Delete any character in a given String.
     * 
     * @param inString the original String
     * @param charsToDelete a set of characters to delete. E.g. "az\n" will delete 'a's, 'z's and new lines.
     * @return the resulting String
     */
    public static String deleteAny(String inString, String charsToDelete) {
        if (!hasLength(inString) || !hasLength(charsToDelete)) {
            return inString;
        }
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            if (charsToDelete.indexOf(c) == -1) {
                out.append(c);
            }
        }
        return out.toString();
    }

    // ---------------------------------------------------------------------
    // Convenience methods for working with formatted Strings
    // ---------------------------------------------------------------------

    /**
     * Quote the given String with single quotes.
     * 
     * @param str the input String (e.g. "myString")
     * @return the quoted String (e.g. "'myString'"), or <code>null<code> if the input was <code>null</code>
     */
    public static String quote(String str) {
        return (str != null ? "'" + str + "'" : null);
    }

    /**
     * Turn the given Object into a String with single quotes if it is a String; keeping the Object as-is else.
     * 
     * @param obj the input Object (e.g. "myString")
     * @return the quoted String (e.g. "'myString'"), or the input object as-is if not a String
     */
    public static Object quoteIfString(Object obj) {
        return (obj instanceof String ? quote((String) obj) : obj);
    }

    /**
     * Unqualify a string qualified by a '.' dot character. For example, "this.name.is.qualified", returns "qualified".
     * 
     * @param qualifiedName the qualified name
     */
    public static String unqualify(String qualifiedName) {
        return unqualify(qualifiedName, '.');
    }

    /**
     * Unqualify a string qualified by a separator character. For example, "this:name:is:qualified" returns "qualified"
     * if using a ':' separator.
     * 
     * @param qualifiedName the qualified name
     * @param separator the separator
     */
    public static String unqualify(String qualifiedName, char separator) {
        return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
    }

    /**
     * Extract the filename from the given path, e.g. "mypath/myfile.txt" -> "myfile.txt".
     * 
     * @param path the file path (may be <code>null</code>)
     * @return the extracted filename, or <code>null</code> if none
     */
    public static String getFilename(String path) {
        if (path == null) {
            return null;
        }
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
    }

    /**
     * Extract the filename extension from the given path, e.g. "mypath/myfile.txt" -> "txt".
     * 
     * @param path the file path (may be <code>null</code>)
     * @return the extracted filename extension, or <code>null</code> if none
     */
    public static String getFilenameExtension(String path) {
        if (path == null) {
            return null;
        }
        int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
    }

    /**
     * Strip the filename extension from the given path, e.g. "mypath/myfile.txt" -> "mypath/myfile".
     * 
     * @param path the file path (may be <code>null</code>)
     * @return the path with stripped filename extension, or <code>null</code> if none
     */
    public static String stripFilenameExtension(String path) {
        if (path == null) {
            return null;
        }
        int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
    }

    /**
     * Apply the given relative path to the given path, assuming standard Java folder separation (i.e. "/" separators);
     * 
     * @param path the path to start from (usually a full file path)
     * @param relativePath the relative path to apply (relative to the full file path above)
     * @return the full file path that results from applying the relative path
     */
    public static String applyRelativePath(String path, String relativePath) {
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        if (separatorIndex != -1) {
            String newPath = path.substring(0, separatorIndex);
            if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
                newPath += FOLDER_SEPARATOR;
            }
            return newPath + relativePath;
        } else {
            return relativePath;
        }
    }

    /**
     * Normalize the path by suppressing sequences like "path/.." and inner simple dots.
     * <p>
     * The result is convenient for path comparison. For other uses, notice that Windows separators ("\") are replaced
     * by simple slashes.
     * 
     * @param path the original path
     * @return the normalized path
     */
    public static String cleanPath(String path) {
        if (path == null) {
            return null;
        }
        String pathToUse = replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);

        // Strip prefix from path to analyze, to not treat it as part of the
        // first path element. This is necessary to correctly parse paths like
        // "file:core/../core/io/Resource.class", where the ".." should just
        // strip the first "core" directory while keeping the "file:" prefix.
        int prefixIndex = pathToUse.indexOf(":");
        String prefix = "";
        if (prefixIndex != -1) {
            prefix = pathToUse.substring(0, prefixIndex + 1);
            pathToUse = pathToUse.substring(prefixIndex + 1);
        }
        if (pathToUse.startsWith(FOLDER_SEPARATOR)) {
            prefix = prefix + FOLDER_SEPARATOR;
            pathToUse = pathToUse.substring(1);
        }

        String[] pathArray = delimitedListToStringArray(pathToUse, FOLDER_SEPARATOR);
        List pathElements = new LinkedList();
        int tops = 0;

        for (int i = pathArray.length - 1; i >= 0; i--) {
            String element = pathArray[i];
            if (CURRENT_PATH.equals(element)) {
                // Points to current directory - drop it.
            } else if (TOP_PATH.equals(element)) {
                // Registering top path found.
                tops++;
            } else {
                if (tops > 0) {
                    // Merging path element with element corresponding to top path.
                    tops--;
                } else {
                    // Normal path element found.
                    pathElements.add(0, element);
                }
            }
        }

        // Remaining top paths need to be retained.
        for (int i = 0; i < tops; i++) {
            pathElements.add(0, TOP_PATH);
        }

        return prefix + collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
    }

    /**
     * Compare two paths after normalization of them.
     * 
     * @param path1 first path for comparison
     * @param path2 second path for comparison
     * @return whether the two paths are equivalent after normalization
     */
    public static boolean pathEquals(String path1, String path2) {
        return cleanPath(path1).equals(cleanPath(path2));
    }

    /**
     * Parse the given <code>localeString</code> into a {@link Locale}.
     * <p>
     * This is the inverse operation of {@link Locale#toString Locale's toString}.
     * 
     * @param localeString the locale string, following <code>Locale's</code> <code>toString()</code> format ("en",
     * "en_UK", etc); also accepts spaces as separators, as an alternative to underscores
     * @return a corresponding <code>Locale</code> instance
     */
    public static Locale parseLocaleString(String localeString) {
        String[] parts = tokenizeToStringArray(localeString, "_ ", false, false);
        String language = (parts.length > 0 ? parts[0] : "");
        String country = (parts.length > 1 ? parts[1] : "");
        String variant = "";
        if (parts.length >= 2) {
            // There is definitely a variant, and it is everything after the country
            // code sans the separator between the country code and the variant.
            int endIndexOfCountryCode = localeString.indexOf(country) + country.length();
            // Strip off any leading '_' and whitespace, what's left is the variant.
            variant = trimLeadingWhitespace(localeString.substring(endIndexOfCountryCode));
            if (variant.startsWith("_")) {
                variant = trimLeadingCharacter(variant, '_');
            }
        }
        return (language.length() > 0 ? new Locale(language, country, variant) : null);
    }

    /**
     * Determine the RFC 3066 compliant language tag, as used for the HTTP "Accept-Language" header.
     * 
     * @param locale the Locale to transform to a language tag
     * @return the RFC 3066 compliant language tag as String
     */
    public static String toLanguageTag(Locale locale) {
        return locale.getLanguage() + (hasText(locale.getCountry()) ? "-" + locale.getCountry() : "");
    }

    // ---------------------------------------------------------------------
    // Convenience methods for working with String arrays
    // ---------------------------------------------------------------------

    /**
     * Append the given String to the given String array, returning a new array consisting of the input array contents
     * plus the given String.
     * 
     * @param array the array to append to (can be <code>null</code>)
     * @param str the String to append
     * @return the new array (never <code>null</code>)
     */
    public static String[] addStringToArray(String[] array, String str) {
        if (ObjectUtils.isEmpty(array)) {
            return new String[] { str };
        }
        String[] newArr = new String[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        newArr[array.length] = str;
        return newArr;
    }

    /**
     * Concatenate the given String arrays into one, with overlapping array elements included twice.
     * <p>
     * The order of elements in the original arrays is preserved.
     * 
     * @param array1 the first array (can be <code>null</code>)
     * @param array2 the second array (can be <code>null</code>)
     * @return the new array (<code>null</code> if both given arrays were <code>null</code>)
     */
    public static String[] concatenateStringArrays(String[] array1, String[] array2) {
        if (ObjectUtils.isEmpty(array1)) {
            return array2;
        }
        if (ObjectUtils.isEmpty(array2)) {
            return array1;
        }
        String[] newArr = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, newArr, 0, array1.length);
        System.arraycopy(array2, 0, newArr, array1.length, array2.length);
        return newArr;
    }

    /**
     * Merge the given String arrays into one, with overlapping array elements only included once.
     * <p>
     * The order of elements in the original arrays is preserved (with the exception of overlapping elements, which are
     * only included on their first occurence).
     * 
     * @param array1 the first array (can be <code>null</code>)
     * @param array2 the second array (can be <code>null</code>)
     * @return the new array (<code>null</code> if both given arrays were <code>null</code>)
     */
    public static String[] mergeStringArrays(String[] array1, String[] array2) {
        if (ObjectUtils.isEmpty(array1)) {
            return array2;
        }
        if (ObjectUtils.isEmpty(array2)) {
            return array1;
        }
        List result = new ArrayList();
        result.addAll(Arrays.asList(array1));
        for (int i = 0; i < array2.length; i++) {
            String str = array2[i];
            if (!result.contains(str)) {
                result.add(str);
            }
        }
        return toStringArray(result);
    }

    /**
     * Turn given source String array into sorted array.
     * 
     * @param array the source array
     * @return the sorted array (never <code>null</code>)
     */
    public static String[] sortStringArray(String[] array) {
        if (ObjectUtils.isEmpty(array)) {
            return new String[0];
        }
        Arrays.sort(array);
        return array;
    }

    /**
     * Copy the given Collection into a String array. The Collection must contain String elements only.
     * 
     * @param collection the Collection to copy
     * @return the String array (<code>null</code> if the passed-in Collection was <code>null</code>)
     */
    public static String[] toStringArray(Collection collection) {
        if (collection == null) {
            return null;
        }
        return (String[]) collection.toArray(new String[collection.size()]);
    }

    /**
     * Copy the given Enumeration into a String array. The Enumeration must contain String elements only.
     * 
     * @param enumeration the Enumeration to copy
     * @return the String array (<code>null</code> if the passed-in Enumeration was <code>null</code>)
     */
    public static String[] toStringArray(Enumeration enumeration) {
        if (enumeration == null) {
            return null;
        }
        List list = Collections.list(enumeration);
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * Trim the elements of the given String array, calling <code>String.trim()</code> on each of them.
     * 
     * @param array the original String array
     * @return the resulting array (of the same size) with trimmed elements
     */
    public static String[] trimArrayElements(String[] array) {
        if (ObjectUtils.isEmpty(array)) {
            return new String[0];
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            String element = array[i];
            result[i] = (element != null ? element.trim() : null);
        }
        return result;
    }

    /**
     * Remove duplicate Strings from the given array. Also sorts the array, as it uses a TreeSet.
     * 
     * @param array the String array
     * @return an array without duplicates, in natural sort order
     */
    public static String[] removeDuplicateStrings(String[] array) {
        if (ObjectUtils.isEmpty(array)) {
            return array;
        }
        Set set = new TreeSet();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        return toStringArray(set);
    }

    /**
     * Take an array Strings and split each element based on the given delimiter. A <code>Properties</code> instance is
     * then generated, with the left of the delimiter providing the key, and the right of the delimiter providing the
     * value.
     * <p>
     * Will trim both the key and value before adding them to the <code>Properties</code> instance.
     * 
     * @param array the array to process
     * @param delimiter to split each element using (typically the equals symbol)
     * @return a <code>Properties</code> instance representing the array contents, or <code>null</code> if the array to
     * process was null or empty
     */
    public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {
        return splitArrayElementsIntoProperties(array, delimiter, null);
    }

    /**
     * Take an array Strings and split each element based on the given delimiter. A <code>Properties</code> instance is
     * then generated, with the left of the delimiter providing the key, and the right of the delimiter providing the
     * value.
     * <p>
     * Will trim both the key and value before adding them to the <code>Properties</code> instance.
     * 
     * @param array the array to process
     * @param delimiter to split each element using (typically the equals symbol)
     * @param charsToDelete one or more characters to remove from each element prior to attempting the split operation
     * (typically the quotation mark symbol), or <code>null</code> if no removal should occur
     * @return a <code>Properties</code> instance representing the array contents, or <code>null</code> if the array to
     * process was <code>null</code> or empty
     */
    public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter, String charsToDelete) {

        if (ObjectUtils.isEmpty(array)) {
            return null;
        }
        Properties result = new Properties();
        for (int i = 0; i < array.length; i++) {
            String element = array[i];
            if (charsToDelete != null) {
                element = deleteAny(array[i], charsToDelete);
            }
            String[] splittedElement = split(element, delimiter);
            if (splittedElement == null) {
                continue;
            }
            result.setProperty(splittedElement[0].trim(), splittedElement[1].trim());
        }
        return result;
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer. Trims tokens and omits empty tokens.
     * <p>
     * The given delimiters string is supposed to consist of any number of delimiter characters. Each of those
     * characters can be used to separate tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using <code>delimitedListToStringArray</code>
     * 
     * @param str the String to tokenize
     * @param delimiters the delimiter characters, assembled as String (each of those characters is individually
     * considered as delimiter).
     * @return an array of the tokens
     * @see StringTokenizer
     * @see String#trim()
     * @see #delimitedListToStringArray
     */
    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer.
     * <p>
     * The given delimiters string is supposed to consist of any number of delimiter characters. Each of those
     * characters can be used to separate tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using <code>delimitedListToStringArray</code>
     * 
     * @param str the String to tokenize
     * @param delimiters the delimiter characters, assembled as String (each of those characters is individually
     * considered as delimiter)
     * @param trimTokens trim the tokens via String's <code>trim</code>
     * @param ignoreEmptyTokens omit empty tokens from the result array (only applies to tokens that are empty after
     * trimming; StringTokenizer will not consider subsequent delimiters as token in the first place).
     * @return an array of the tokens (<code>null</code> if the input String was <code>null</code>)
     * @see StringTokenizer
     * @see String#trim()
     * @see #delimitedListToStringArray
     */
    public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens,
                                                 boolean ignoreEmptyTokens) {

        if (str == null) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, delimiters);
        List tokens = new ArrayList();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                tokens.add(token);
            }
        }
        return toStringArray(tokens);
    }

    /**
     * Take a String which is a delimited list and convert it to a String array.
     * <p>
     * A single delimiter can consists of more than one character: It will still be considered as single delimiter
     * string, rather than as bunch of potential delimiter characters - in contrast to
     * <code>tokenizeToStringArray</code>.
     * 
     * @param str the input String
     * @param delimiter the delimiter between elements (this is a single delimiter, rather than a bunch individual
     * delimiter characters)
     * @return an array of the tokens in the list
     * @see #tokenizeToStringArray
     */
    public static String[] delimitedListToStringArray(String str, String delimiter) {
        return delimitedListToStringArray(str, delimiter, null);
    }

    /**
     * Take a String which is a delimited list and convert it to a String array.
     * <p>
     * A single delimiter can consists of more than one character: It will still be considered as single delimiter
     * string, rather than as bunch of potential delimiter characters - in contrast to
     * <code>tokenizeToStringArray</code>.
     * 
     * @param str the input String
     * @param delimiter the delimiter between elements (this is a single delimiter, rather than a bunch individual
     * delimiter characters)
     * @param charsToDelete a set of characters to delete. Useful for deleting unwanted line breaks: e.g. "\r\n\f" will
     * delete all new lines and line feeds in a String.
     * @return an array of the tokens in the list
     * @see #tokenizeToStringArray
     */
    public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
        if (str == null) {
            return new String[0];
        }
        if (delimiter == null) {
            return new String[] { str };
        }
        List result = new ArrayList();
        if ("".equals(delimiter)) {
            for (int i = 0; i < str.length(); i++) {
                result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
            }
        } else {
            int pos = 0;
            int delPos = 0;
            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
                result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
                pos = delPos + delimiter.length();
            }
            if (str.length() > 0 && pos <= str.length()) {
                // Add rest of String, but not in case of empty input.
                result.add(deleteAny(str.substring(pos), charsToDelete));
            }
        }
        return toStringArray(result);
    }

    /**
     * Convert a CSV list into an array of Strings.
     * 
     * @param str the input String
     * @return an array of Strings, or the empty array in case of empty input
     */
    public static String[] commaDelimitedListToStringArray(String str) {
        return delimitedListToStringArray(str, ",");
    }

    /**
     * Convenience method to convert a CSV string list to a set. Note that this will suppress duplicates.
     * 
     * @param str the input String
     * @return a Set of String entries in the list
     */
    public static Set commaDelimitedListToSet(String str) {
        Set set = new TreeSet();
        String[] tokens = commaDelimitedListToStringArray(str);
        for (int i = 0; i < tokens.length; i++) {
            set.add(tokens[i]);
        }
        return set;
    }

    /**
     * Convenience method to return a Collection as a delimited (e.g. CSV) String. E.g. useful for
     * <code>toString()</code> implementations.
     * 
     * @param coll the Collection to display
     * @param delim the delimiter to use (probably a ",")
     * @param prefix the String to start each element with
     * @param suffix the String to end each element with
     * @return the delimited String
     */
    public static String collectionToDelimitedString(Collection coll, String delim, String prefix, String suffix) {
        if (CollectionUtils.isEmpty(coll)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Iterator it = coll.iterator();
        while (it.hasNext()) {
            sb.append(prefix).append(it.next()).append(suffix);
            if (it.hasNext()) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    /**
     * Convenience method to return a Collection as a delimited (e.g. CSV) String. E.g. useful for
     * <code>toString()</code> implementations.
     * 
     * @param coll the Collection to display
     * @param delim the delimiter to use (probably a ",")
     * @return the delimited String
     */
    public static String collectionToDelimitedString(Collection coll, String delim) {
        return collectionToDelimitedString(coll, delim, "", "");
    }

    /**
     * Convenience method to return a Collection as a CSV String. E.g. useful for <code>toString()</code>
     * implementations.
     * 
     * @param coll the Collection to display
     * @return the delimited String
     */
    public static String collectionToCommaDelimitedString(Collection coll) {
        return collectionToDelimitedString(coll, ",");
    }

    /**
     * Convenience method to return a String array as a delimited (e.g. CSV) String. E.g. useful for
     * <code>toString()</code> implementations.
     * 
     * @param arr the array to display
     * @param delim the delimiter to use (probably a ",")
     * @return the delimited String
     */
    public static String arrayToDelimitedString(Object[] arr, String delim) {
        if (ObjectUtils.isEmpty(arr)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                sb.append(delim);
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * Convenience method to return a String array as a CSV String. E.g. useful for <code>toString()</code>
     * implementations.
     * 
     * @param arr the array to display
     * @return the delimited String
     */
    public static String arrayToCommaDelimitedString(Object[] arr) {
        return arrayToDelimitedString(arr, ",");
    }

    /**
     * Private constructor prevents construction.
     */
    public StringUtils(){
    }

    // Performance testing notes (JDK 1.4, Jul03, scolebourne)
    // Whitespace:
    // Character.isWhitespace() is faster than WHITESPACE.indexOf()
    // where WHITESPACE is a string of all whitespace characters
    //
    // Character access:
    // String.charAt(n) versus toCharArray(), then array[n]
    // String.charAt(n) is about 15% worse for a 10K string
    // They are about equal for a length 50 string
    // String.charAt(n) is about 4 times better for a length 3 string
    // String.charAt(n) is best bet overall
    //
    // Append:
    // String.concat about twice as fast as StringBuffer.append
    // (not sure who tested this)

    /** A table of hex digits */
    private static final char[]   HEX_DIGIT         = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F'                     };

    private static final Pattern  HTML_NUMBER_REGEX = Pattern.compile("&#\\d+;");

    private static final String[] NO_STRINGS        = new String[0];

    /**
     * The empty String <code>""</code>.
     * 
     * @since 2.0
     */
    public static final String    EMPTY             = "";

    /**
     * <p>
     * The maximum size to which the padding constant(s) can expand.
     * </p>
     */
    private static final int      PAD_LIMIT         = 8192;

    /**
     * <p>
     * An array of <code>String</code>s used for padding.
     * </p>
     * <p>
     * Used for efficient space padding. The length of each String expands as needed.
     * </p>
     */
    private static final String[] PADDING           = new String[Character.MAX_VALUE];

    public static final String UPDATE_COLUMN_NULL_FLAG = "##$$##";

    static {
        // space padding is most common, start with 64 chars
        PADDING[32] = "                                                                ";
    }

    // Empty checks
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks if a String is empty ("") or null.
     * </p>
     * 
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     * <p>
     * NOTE: This method changed in Lang version 2.0. It no longer trims the String. That functionality is available in
     * isBlank().
     * </p>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * <p>
     * Checks if a String is not empty ("") and not null.
     * </p>
     * 
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String str) {
        return (str != null && str.length() > 0);
    }

    /**
     * <p>
     * Checks if a String is whitespace, empty ("") or null.
     * </p>
     * 
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 2.0
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }


    /**
     * <p>
     * Checks if a String is not empty (""), not null and not whitespace only.
     * </p>
     * 
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null and not whitespace
     * @since 2.0
     */
    public static boolean isNotBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return false;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return true;
            }
        }
        return false;
    }

    // Trim
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Removes control characters (char &lt;= 32) from both ends of this String, handling <code>null</code> by returning
     * an empty String ("").
     * </p>
     * 
     * <pre>
     * StringUtils.clean(null)          = ""
     * StringUtils.clean("")            = ""
     * StringUtils.clean("abc")         = "abc"
     * StringUtils.clean("    abc    ") = "abc"
     * StringUtils.clean("     ")       = ""
     * </pre>
     * 
     * @see String#trim()
     * @param str the String to clean, may be null
     * @return the trimmed text, never <code>null</code>
     * @deprecated Use the clearer named {@link #trimToEmpty(String)}. Method will be removed in Commons Lang 3.0.
     */
    public static String clean(String str) {
        return (str == null ? EMPTY : str.trim());
    }

    /**
     * <p>
     * Removes control characters (char &lt;= 32) from both ends of this String, handling <code>null</code> by returning
     * <code>null</code>.
     * </p>
     * <p>
     * The String is trimmed using {@link String#trim()}. Trim removes start and end characters &lt;= 32. To strip
     * whitespace use {@link #strip(String)}.
     * </p>
     * <p>
     * To trim your choice of characters, use the {@link #strip(String, String)} methods.
     * </p>
     * 
     * <pre>
     * StringUtils.trim(null)          = null
     * StringUtils.trim("")            = ""
     * StringUtils.trim("     ")       = ""
     * StringUtils.trim("abc")         = "abc"
     * StringUtils.trim("    abc    ") = "abc"
     * </pre>
     * 
     * @param str the String to be trimmed, may be null
     * @return the trimmed string, <code>null</code> if null String input
     */
    public static String trim(String str) {
        return (str == null ? null : str.trim());
    }

    /**
     * <p>
     * Removes control characters (char &lt;= 32) from both ends of this String returning <code>null</code> if the
     * String is empty ("") after the trim or if it is <code>null</code>.
     * <p>
     * The String is trimmed using {@link String#trim()}. Trim removes start and end characters &lt;= 32. To strip
     * whitespace use {@link #stripToNull(String)}.
     * </p>
     * 
     * <pre>
     * StringUtils.trimToNull(null)          = null
     * StringUtils.trimToNull("")            = null
     * StringUtils.trimToNull("     ")       = null
     * StringUtils.trimToNull("abc")         = "abc"
     * StringUtils.trimToNull("    abc    ") = "abc"
     * </pre>
     * 
     * @param str the String to be trimmed, may be null
     * @return the trimmed String, <code>null</code> if only chars &lt;= 32, empty or null String input
     * @since 2.0
     */
    public static String trimToNull(String str) {
        String ts = trim(str);
        return (ts == null || ts.length() == 0 ? null : ts);
    }

    /**
     * <p>
     * Removes control characters (char &lt;= 32) from both ends of this String returning an empty String ("") if the
     * String is empty ("") after the trim or if it is <code>null</code>.
     * <p>
     * The String is trimmed using {@link String#trim()}. Trim removes start and end characters &lt;= 32. To strip
     * whitespace use {@link #stripToEmpty(String)}.
     * </p>
     * 
     * <pre>
     * StringUtils.trimToEmpty(null)          = ""
     * StringUtils.trimToEmpty("")            = ""
     * StringUtils.trimToEmpty("     ")       = ""
     * StringUtils.trimToEmpty("abc")         = "abc"
     * StringUtils.trimToEmpty("    abc    ") = "abc"
     * </pre>
     * 
     * @param str the String to be trimmed, may be null
     * @return the trimmed String, or an empty String if <code>null</code> input
     * @since 2.0
     */
    public static String trimToEmpty(String str) {
        return (str == null ? EMPTY : str.trim());
    }

    // Stripping
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Strips whitespace from the start and end of a String.
     * </p>
     * <p>
     * This is similar to {@link #trim(String)} but removes whitespace. Whitespace is defined by
     * {@link Character#isWhitespace(char)}.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.strip(null)     = null
     * StringUtils.strip("")       = ""
     * StringUtils.strip("   ")    = ""
     * StringUtils.strip("abc")    = "abc"
     * StringUtils.strip("  abc")  = "abc"
     * StringUtils.strip("abc  ")  = "abc"
     * StringUtils.strip(" abc ")  = "abc"
     * StringUtils.strip(" ab c ") = "ab c"
     * </pre>
     * 
     * @param str the String to remove whitespace from, may be null
     * @return the stripped String, <code>null</code> if null String input
     */
    public static String strip(String str) {
        return strip(str, null);
    }

    /**
     * <p>
     * Strips whitespace from the start and end of a String returning <code>null</code> if the String is empty ("")
     * after the strip.
     * </p>
     * <p>
     * This is similar to {@link #trimToNull(String)} but removes whitespace. Whitespace is defined by
     * {@link Character#isWhitespace(char)}.
     * </p>
     * 
     * <pre>
     * StringUtils.strip(null)     = null
     * StringUtils.strip("")       = null
     * StringUtils.strip("   ")    = null
     * StringUtils.strip("abc")    = "abc"
     * StringUtils.strip("  abc")  = "abc"
     * StringUtils.strip("abc  ")  = "abc"
     * StringUtils.strip(" abc ")  = "abc"
     * StringUtils.strip(" ab c ") = "ab c"
     * </pre>
     * 
     * @param str the String to be stripped, may be null
     * @return the stripped String, <code>null</code> if whitespace, empty or null String input
     * @since 2.0
     */
    public static String stripToNull(String str) {
        if (str == null) {
            return null;
        }
        str = strip(str, null);
        return (str.length() == 0 ? null : str);
    }

    /**
     * <p>
     * Strips whitespace from the start and end of a String returning an empty String if <code>null</code> input.
     * </p>
     * <p>
     * This is similar to {@link #trimToEmpty(String)} but removes whitespace. Whitespace is defined by
     * {@link Character#isWhitespace(char)}.
     * </p>
     * 
     * <pre>
     * StringUtils.strip(null)     = ""
     * StringUtils.strip("")       = ""
     * StringUtils.strip("   ")    = ""
     * StringUtils.strip("abc")    = "abc"
     * StringUtils.strip("  abc")  = "abc"
     * StringUtils.strip("abc  ")  = "abc"
     * StringUtils.strip(" abc ")  = "abc"
     * StringUtils.strip(" ab c ") = "ab c"
     * </pre>
     * 
     * @param str the String to be stripped, may be null
     * @return the trimmed String, or an empty String if <code>null</code> input
     * @since 2.0
     */
    public static String stripToEmpty(String str) {
        return (str == null ? EMPTY : strip(str, null));
    }

    /**
     * <p>
     * Strips any of a set of characters from the start and end of a String. This is similar to {@link String#trim()}
     * but allows the characters to be stripped to be controlled.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. An empty string ("") input returns the empty string.
     * </p>
     * <p>
     * If the stripChars String is <code>null</code>, whitespace is stripped as defined by
     * {@link Character#isWhitespace(char)}. Alternatively use {@link #strip(String)}.
     * </p>
     * 
     * <pre>
     * StringUtils.strip(null, *)          = null
     * StringUtils.strip("", *)            = ""
     * StringUtils.strip("abc", null)      = "abc"
     * StringUtils.strip("  abc", null)    = "abc"
     * StringUtils.strip("abc  ", null)    = "abc"
     * StringUtils.strip(" abc ", null)    = "abc"
     * StringUtils.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     * 
     * @param str the String to remove characters from, may be null
     * @param stripChars the characters to remove, null treated as whitespace
     * @return the stripped String, <code>null</code> if null String input
     */
    public static String strip(String str, String stripChars) {
        if (str == null || str.length() == 0) {
            return str;
        }
        str = stripStart(str, stripChars);
        return stripEnd(str, stripChars);
    }

    /**
     * <p>
     * Strips any of a set of characters from the start of a String.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. An empty string ("") input returns the empty string.
     * </p>
     * <p>
     * If the stripChars String is <code>null</code>, whitespace is stripped as defined by
     * {@link Character#isWhitespace(char)}.
     * </p>
     * 
     * <pre>
     * StringUtils.stripStart(null, *)          = null
     * StringUtils.stripStart("", *)            = ""
     * StringUtils.stripStart("abc", "")        = "abc"
     * StringUtils.stripStart("abc", null)      = "abc"
     * StringUtils.stripStart("  abc", null)    = "abc"
     * StringUtils.stripStart("abc  ", null)    = "abc  "
     * StringUtils.stripStart(" abc ", null)    = "abc "
     * StringUtils.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     * 
     * @param str the String to remove characters from, may be null
     * @param stripChars the characters to remove, null treated as whitespace
     * @return the stripped String, <code>null</code> if null String input
     */
    public static String stripStart(String str, String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                start++;
            }
        }
        return str.substring(start);
    }

    /**
     * <p>
     * Strips any of a set of characters from the end of a String.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. An empty string ("") input returns the empty string.
     * </p>
     * <p>
     * If the stripChars String is <code>null</code>, whitespace is stripped as defined by
     * {@link Character#isWhitespace(char)}.
     * </p>
     * 
     * <pre>
     * StringUtils.stripEnd(null, *)          = null
     * StringUtils.stripEnd("", *)            = ""
     * StringUtils.stripEnd("abc", "")        = "abc"
     * StringUtils.stripEnd("abc", null)      = "abc"
     * StringUtils.stripEnd("  abc", null)    = "  abc"
     * StringUtils.stripEnd("abc  ", null)    = "abc"
     * StringUtils.stripEnd(" abc ", null)    = " abc"
     * StringUtils.stripEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     * 
     * @param str the String to remove characters from, may be null
     * @param stripChars the characters to remove, null treated as whitespace
     * @return the stripped String, <code>null</code> if null String input
     */
    public static String stripEnd(String str, String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        if (stripChars == null) {
            while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    // StripAll
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Strips whitespace from the start and end of every String in an array. Whitespace is defined by
     * {@link Character#isWhitespace(char)}.
     * </p>
     * <p>
     * A new array is returned each time, except for length zero. A <code>null</code> array will return
     * <code>null</code>. An empty array will return itself. A <code>null</code> array entry will be ignored.
     * </p>
     * 
     * <pre>
     * StringUtils.stripAll(null)             = null
     * StringUtils.stripAll([])               = []
     * StringUtils.stripAll(["abc", "  abc"]) = ["abc", "abc"]
     * StringUtils.stripAll(["abc  ", null])  = ["abc", null]
     * </pre>
     * 
     * @param strs the array to remove whitespace from, may be null
     * @return the stripped Strings, <code>null</code> if null array input
     */
    public static String[] stripAll(String[] strs) {
        return stripAll(strs, null);
    }

    /**
     * <p>
     * Strips any of a set of characters from the start and end of every String in an array.
     * </p>
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     * <p>
     * A new array is returned each time, except for length zero. A <code>null</code> array will return
     * <code>null</code>. An empty array will return itself. A <code>null</code> array entry will be ignored. A
     * <code>null</code> stripChars will strip whitespace as defined by {@link Character#isWhitespace(char)}.
     * </p>
     * 
     * <pre>
     * StringUtils.stripAll(null, *)                = null
     * StringUtils.stripAll([], *)                  = []
     * StringUtils.stripAll(["abc", "  abc"], null) = ["abc", "abc"]
     * StringUtils.stripAll(["abc  ", null], null)  = ["abc", null]
     * StringUtils.stripAll(["abc  ", null], "yz")  = ["abc  ", null]
     * StringUtils.stripAll(["yabcz", null], "yz")  = ["abc", null]
     * </pre>
     * 
     * @param strs the array to remove characters from, may be null
     * @param stripChars the characters to remove, null treated as whitespace
     * @return the stripped Strings, <code>null</code> if null array input
     */
    public static String[] stripAll(String[] strs, String stripChars) {
        int strsLen;
        if (strs == null || (strsLen = strs.length) == 0) {
            return strs;
        }
        String[] newArr = new String[strsLen];
        for (int i = 0; i < strsLen; i++) {
            newArr[i] = strip(strs[i], stripChars);
        }
        return newArr;
    }

    // Equals
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Compares two Strings, returning <code>true</code> if they are equal.
     * </p>
     * <p>
     * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered to be equal.
     * The comparison is case sensitive.
     * </p>
     * 
     * <pre>
     * StringUtils.equals(null, null)   = true
     * StringUtils.equals(null, "abc")  = false
     * StringUtils.equals("abc", null)  = false
     * StringUtils.equals("abc", "abc") = true
     * StringUtils.equals("abc", "ABC") = false
     * </pre>
     * 
     * @see String#equals(Object)
     * @param str1 the first String, may be null
     * @param str2 the second String, may be null
     * @return <code>true</code> if the Strings are equal, case sensitive, or both <code>null</code>
     */
    public static boolean equals(String str1, String str2) {
        return (str1 == null ? str2 == null : str1.equals(str2));
    }

    /**
     * <p>
     * Compares two Strings, returning <code>true</code> if they are equal ignoring the case.
     * </p>
     * <p>
     * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered equal.
     * Comparison is case insensitive.
     * </p>
     * 
     * <pre>
     * StringUtils.equalsIgnoreCase(null, null)   = true
     * StringUtils.equalsIgnoreCase(null, "abc")  = false
     * StringUtils.equalsIgnoreCase("abc", null)  = false
     * StringUtils.equalsIgnoreCase("abc", "abc") = true
     * StringUtils.equalsIgnoreCase("abc", "ABC") = true
     * StringUtils.equalsIgnoreCase("", null) = false
     * </pre>
     * 
     * @see String#equalsIgnoreCase(String)
     * @param str1 the first String, may be null
     * @param str2 the second String, may be null
     * @return <code>true</code> if the Strings are equal, case insensitive, or both <code>null</code>
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return (str1 == null ? str2 == null : str1.equalsIgnoreCase(str2));
    }

    /**
     * <p>
     * Compares two Strings, returning <code>true</code> if they are equal ignoring the case.
     * </p>
     * <p>
     * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered equal.
     * Comparison is case insensitive.
     * </p>
     * 
     * <pre>
     * StringUtils.equalsIgnoreCase(null, null)   = true
     * StringUtils.equalsIgnoreCase(null, "abc")  = false
     * StringUtils.equalsIgnoreCase("abc", null)  = false
     * StringUtils.equalsIgnoreCase("abc", "abc") = true
     * StringUtils.equalsIgnoreCase("abc", "ABC") = true
     * StringUtils.equalsIgnoreCase("", null) = true
     * StringUtils.equalsIgnoreCase(" ", null) = true
     * StringUtils.equalsIgnoreCase(null,  "") = true
     * </pre>
     * 
     * @see String#equalsIgnoreCase(String)
     * @param str1 the first String, may be null
     * @param str2 the second String, may be null
     * @return <code>true</code> if the Strings are equal, case insensitive, or both <code>null</code>
     */
    public static boolean equalsIgnoreCaseWithNull(String str1, String str2) {
        
        return (isBlank(str1) && isBlank(str2)) || (str1 == null ? isBlank(str2) : str1.equalsIgnoreCase(str2));
        
    }

    // IndexOf
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Finds the first index within a String, handling <code>null</code>. This method uses {@link String#indexOf(int)}.
     * </p>
     * <p>
     * A <code>null</code> or empty ("") String will return <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOf(null, *)         = -1
     * StringUtils.indexOf("", *)           = -1
     * StringUtils.indexOf("aabaabaa", 'a') = 0
     * StringUtils.indexOf("aabaabaa", 'b') = 2
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchChar the character to find
     * @return the first index of the search character, -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int indexOf(String str, char searchChar) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        return str.indexOf(searchChar);
    }

    /**
     * <p>
     * Finds the first index within a String from a start position, handling <code>null</code>. This method uses
     * {@link String#indexOf(int, int)}.
     * </p>
     * <p>
     * A <code>null</code> or empty ("") String will return <code>-1</code>. A negative start position is treated as
     * zero. A start position greater than the string length returns <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOf(null, *, *)          = -1
     * StringUtils.indexOf("", *, *)            = -1
     * StringUtils.indexOf("aabaabaa", 'b', 0)  = 2
     * StringUtils.indexOf("aabaabaa", 'b', 3)  = 5
     * StringUtils.indexOf("aabaabaa", 'b', 9)  = -1
     * StringUtils.indexOf("aabaabaa", 'b', -1) = 2
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchChar the character to find
     * @param startPos the start position, negative treated as zero
     * @return the first index of the search character, -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int indexOf(String str, char searchChar, int startPos) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        return str.indexOf(searchChar, startPos);
    }

    /**
     * <p>
     * Finds the first index within a String, handling <code>null</code>. This method uses
     * {@link String#indexOf(String)}.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOf(null, *)          = -1
     * StringUtils.indexOf(*, null)          = -1
     * StringUtils.indexOf("", "")           = 0
     * StringUtils.indexOf("aabaabaa", "a")  = 0
     * StringUtils.indexOf("aabaabaa", "b")  = 2
     * StringUtils.indexOf("aabaabaa", "ab") = 1
     * StringUtils.indexOf("aabaabaa", "")   = 0
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchStr the String to find, may be null
     * @return the first index of the search String, -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.indexOf(searchStr);
    }

    /**
     * <p>
     * Finds the first index within a String, handling <code>null</code>. This method uses
     * {@link String#indexOf(String, int)}.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>-1</code>. A negative start position is treated as zero. An empty
     * ("") search String always matches. A start position greater than the string length only matches an empty search
     * String.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOf(null, *, *)          = -1
     * StringUtils.indexOf(*, null, *)          = -1
     * StringUtils.indexOf("", "", 0)           = 0
     * StringUtils.indexOf("aabaabaa", "a", 0)  = 0
     * StringUtils.indexOf("aabaabaa", "b", 0)  = 2
     * StringUtils.indexOf("aabaabaa", "ab", 0) = 1
     * StringUtils.indexOf("aabaabaa", "b", 3)  = 5
     * StringUtils.indexOf("aabaabaa", "b", 9)  = -1
     * StringUtils.indexOf("aabaabaa", "b", -1) = 2
     * StringUtils.indexOf("aabaabaa", "", 2)   = 2
     * StringUtils.indexOf("abc", "", 9)        = 3
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchStr the String to find, may be null
     * @param startPos the start position, negative treated as zero
     * @return the first index of the search String, -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int indexOf(String str, String searchStr, int startPos) {
        if (str == null || searchStr == null) {
            return -1;
        }
        // JDK1.2/JDK1.3 have a bug, when startPos > str.length for "", hence
        if (searchStr.length() == 0 && startPos >= str.length()) {
            return str.length();
        }
        return str.indexOf(searchStr, startPos);
    }

    // LastIndexOf
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Finds the last index within a String, handling <code>null</code>. This method uses
     * {@link String#lastIndexOf(int)}.
     * </p>
     * <p>
     * A <code>null</code> or empty ("") String will return <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.lastIndexOf(null, *)         = -1
     * StringUtils.lastIndexOf("", *)           = -1
     * StringUtils.lastIndexOf("aabaabaa", 'a') = 7
     * StringUtils.lastIndexOf("aabaabaa", 'b') = 5
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchChar the character to find
     * @return the last index of the search character, -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int lastIndexOf(String str, char searchChar) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        return str.lastIndexOf(searchChar);
    }

    /**
     * <p>
     * Finds the last index within a String from a start position, handling <code>null</code>. This method uses
     * {@link String#lastIndexOf(int, int)}.
     * </p>
     * <p>
     * A <code>null</code> or empty ("") String will return <code>-1</code>. A negative start position returns
     * <code>-1</code>. A start position greater than the string length searches the whole string.
     * </p>
     * 
     * <pre>
     * StringUtils.lastIndexOf(null, *, *)          = -1
     * StringUtils.lastIndexOf("", *,  *)           = -1
     * StringUtils.lastIndexOf("aabaabaa", 'b', 8)  = 5
     * StringUtils.lastIndexOf("aabaabaa", 'b', 4)  = 2
     * StringUtils.lastIndexOf("aabaabaa", 'b', 0)  = -1
     * StringUtils.lastIndexOf("aabaabaa", 'b', 9)  = 5
     * StringUtils.lastIndexOf("aabaabaa", 'b', -1) = -1
     * StringUtils.lastIndexOf("aabaabaa", 'a', 0)  = 0
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchChar the character to find
     * @param startPos the start position
     * @return the last index of the search character, -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int lastIndexOf(String str, char searchChar, int startPos) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        return str.lastIndexOf(searchChar, startPos);
    }

    /**
     * <p>
     * Finds the last index within a String, handling <code>null</code>. This method uses
     * {@link String#lastIndexOf(String)}.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.lastIndexOf(null, *)          = -1
     * StringUtils.lastIndexOf(*, null)          = -1
     * StringUtils.lastIndexOf("", "")           = 0
     * StringUtils.lastIndexOf("aabaabaa", "a")  = 0
     * StringUtils.lastIndexOf("aabaabaa", "b")  = 2
     * StringUtils.lastIndexOf("aabaabaa", "ab") = 1
     * StringUtils.lastIndexOf("aabaabaa", "")   = 8
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchStr the String to find, may be null
     * @return the last index of the search String, -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int lastIndexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.lastIndexOf(searchStr);
    }

    /**
     * <p>
     * Finds the first index within a String, handling <code>null</code>. This method uses
     * {@link String#lastIndexOf(String, int)}.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>-1</code>. A negative start position returns <code>-1</code>. An
     * empty ("") search String always matches unless the start position is negative. A start position greater than the
     * string length searches the whole string.
     * </p>
     * 
     * <pre>
     * StringUtils.lastIndexOf(null, *, *)          = -1
     * StringUtils.lastIndexOf(*, null, *)          = -1
     * StringUtils.lastIndexOf("aabaabaa", "a", 8)  = 7
     * StringUtils.lastIndexOf("aabaabaa", "b", 8)  = 5
     * StringUtils.lastIndexOf("aabaabaa", "ab", 8) = 4
     * StringUtils.lastIndexOf("aabaabaa", "b", 9)  = 5
     * StringUtils.lastIndexOf("aabaabaa", "b", -1) = -1
     * StringUtils.lastIndexOf("aabaabaa", "a", 0)  = 0
     * StringUtils.lastIndexOf("aabaabaa", "b", 0)  = -1
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchStr the String to find, may be null
     * @param startPos the start position, negative treated as zero
     * @return the first index of the search String, -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int lastIndexOf(String str, String searchStr, int startPos) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.lastIndexOf(searchStr, startPos);
    }

    // Contains
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks if String contains a search character, handling <code>null</code>. This method uses
     * {@link String#indexOf(int)}.
     * </p>
     * <p>
     * A <code>null</code> or empty ("") String will return <code>false</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.contains(null, *)    = false
     * StringUtils.contains("", *)      = false
     * StringUtils.contains("abc", 'a') = true
     * StringUtils.contains("abc", 'z') = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchChar the character to find
     * @return true if the String contains the search character, false if not or <code>null</code> string input
     * @since 2.0
     */
    public static boolean contains(String str, char searchChar) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return (str.indexOf(searchChar) >= 0);
    }

    /**
     * <p>
     * Find the first index within a String, handling <code>null</code>. This method uses {@link String#indexOf(int)}.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>false</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.contains(null, *)     = false
     * StringUtils.contains(*, null)     = false
     * StringUtils.contains("", "")      = true
     * StringUtils.contains("abc", "")   = true
     * StringUtils.contains("abc", "a")  = true
     * StringUtils.contains("abc", "z")  = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchStr the String to find, may be null
     * @return true if the String contains the search character, false if not or <code>null</code> string input
     * @since 2.0
     */
    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return (str.indexOf(searchStr) >= 0);
    }

    // IndexOfAny chars
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Search a String to find the first index of any character in the given set of characters.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>-1</code>. A <code>null</code> or zero length search array will
     * return <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfAny(null, *)                = -1
     * StringUtils.indexOfAny("", *)                  = -1
     * StringUtils.indexOfAny(*, null)                = -1
     * StringUtils.indexOfAny(*, [])                  = -1
     * StringUtils.indexOfAny("zzabyycdxx",['z','a']) = 0
     * StringUtils.indexOfAny("zzabyycdxx",['b','y']) = 3
     * StringUtils.indexOfAny("aba", ['z'])           = -1
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchChars the chars to search for, may be null
     * @return the index of any of the chars, -1 if no match or null input
     * @since 2.0
     */
    public static int indexOfAny(String str, char[] searchChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0) {
            return -1;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            for (int j = 0; j < searchChars.length; j++) {
                if (searchChars[j] == ch) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * <p>
     * Search a String to find the first index of any character in the given set of characters.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>-1</code>. A <code>null</code> search string will return
     * <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfAny(null, *)            = -1
     * StringUtils.indexOfAny("", *)              = -1
     * StringUtils.indexOfAny(*, null)            = -1
     * StringUtils.indexOfAny(*, "")              = -1
     * StringUtils.indexOfAny("zzabyycdxx", "za") = 0
     * StringUtils.indexOfAny("zzabyycdxx", "by") = 3
     * StringUtils.indexOfAny("aba","z")          = -1
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchChars the chars to search for, may be null
     * @return the index of any of the chars, -1 if no match or null input
     * @since 2.0
     */
    public static int indexOfAny(String str, String searchChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length() == 0) {
            return -1;
        }
        return indexOfAny(str, searchChars.toCharArray());
    }

    // IndexOfAnyBut chars
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Search a String to find the first index of any character not in the given set of characters.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>-1</code>. A <code>null</code> or zero length search array will
     * return <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfAnyBut(null, *)           = -1
     * StringUtils.indexOfAnyBut("", *)             = -1
     * StringUtils.indexOfAnyBut(*, null)           = -1
     * StringUtils.indexOfAnyBut(*, [])             = -1
     * StringUtils.indexOfAnyBut("zzabyycdxx",'za') = 3
     * StringUtils.indexOfAnyBut("zzabyycdxx", '')  = 0
     * StringUtils.indexOfAnyBut("aba", 'ab')       = -1
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchChars the chars to search for, may be null
     * @return the index of any of the chars, -1 if no match or null input
     * @since 2.0
     */
    public static int indexOfAnyBut(String str, char[] searchChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0) {
            return -1;
        }
        outer: for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            for (int j = 0; j < searchChars.length; j++) {
                if (searchChars[j] == ch) {
                    continue outer;
                }
            }
            return i;
        }
        return -1;
    }

    /**
     * <p>
     * Search a String to find the first index of any character not in the given set of characters.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>-1</code>. A <code>null</code> search string will return
     * <code>-1</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfAnyBut(null, *)            = -1
     * StringUtils.indexOfAnyBut("", *)              = -1
     * StringUtils.indexOfAnyBut(*, null)            = -1
     * StringUtils.indexOfAnyBut(*, "")              = -1
     * StringUtils.indexOfAnyBut("zzabyycdxx", "za") = 3
     * StringUtils.indexOfAnyBut("zzabyycdxx", "")   = 0
     * StringUtils.indexOfAnyBut("aba","ab")         = -1
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchChars the chars to search for, may be null
     * @return the index of any of the chars, -1 if no match or null input
     * @since 2.0
     */
    public static int indexOfAnyBut(String str, String searchChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length() == 0) {
            return -1;
        }
        for (int i = 0; i < str.length(); i++) {
            if (searchChars.indexOf(str.charAt(i)) < 0) {
                return i;
            }
        }
        return -1;
    }

    // ContainsOnly
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks if the String contains only certain characters.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>false</code>. A <code>null</code> valid character array will return
     * <code>false</code>. An empty String ("") always returns <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.containsOnly(null, *)       = false
     * StringUtils.containsOnly(*, null)       = false
     * StringUtils.containsOnly("", *)         = true
     * StringUtils.containsOnly("ab", '')      = false
     * StringUtils.containsOnly("abab", 'abc') = true
     * StringUtils.containsOnly("ab1", 'abc')  = false
     * StringUtils.containsOnly("abz", 'abc')  = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param valid an array of valid chars, may be null
     * @return true if it only contains valid chars and is non-null
     */
    public static boolean containsOnly(String str, char[] valid) {
        // All these pre-checks are to maintain API with an older version
        if ((valid == null) || (str == null)) {
            return false;
        }
        if (str.length() == 0) {
            return true;
        }
        if (valid.length == 0) {
            return false;
        }
        return indexOfAnyBut(str, valid) == -1;
    }

    /**
     * <p>
     * Checks if the String contains only certain characters.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>false</code>. A <code>null</code> valid character String will return
     * <code>false</code>. An empty String ("") always returns <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.containsOnly(null, *)       = false
     * StringUtils.containsOnly(*, null)       = false
     * StringUtils.containsOnly("", *)         = true
     * StringUtils.containsOnly("ab", "")      = false
     * StringUtils.containsOnly("abab", "abc") = true
     * StringUtils.containsOnly("ab1", "abc")  = false
     * StringUtils.containsOnly("abz", "abc")  = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param validChars a String of valid chars, may be null
     * @return true if it only contains valid chars and is non-null
     * @since 2.0
     */
    public static boolean containsOnly(String str, String validChars) {
        if (str == null || validChars == null) {
            return false;
        }
        return containsOnly(str, validChars.toCharArray());
    }

    // ContainsNone
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks that the String does not contain certain characters.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>true</code>. A <code>null</code> invalid character array will return
     * <code>true</code>. An empty String ("") always returns true.
     * </p>
     * 
     * <pre>
     * StringUtils.containsNone(null, *)       = true
     * StringUtils.containsNone(*, null)       = true
     * StringUtils.containsNone("", *)         = true
     * StringUtils.containsNone("ab", '')      = true
     * StringUtils.containsNone("abab", 'xyz') = true
     * StringUtils.containsNone("ab1", 'xyz')  = true
     * StringUtils.containsNone("abz", 'xyz')  = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param invalidChars an array of invalid chars, may be null
     * @return true if it contains none of the invalid chars, or is null
     * @since 2.0
     */
    public static boolean containsNone(String str, char[] invalidChars) {
        if (str == null || invalidChars == null) {
            return true;
        }
        int strSize = str.length();
        int validSize = invalidChars.length;
        for (int i = 0; i < strSize; i++) {
            char ch = str.charAt(i);
            for (int j = 0; j < validSize; j++) {
                if (invalidChars[j] == ch) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks that the String does not contain certain characters.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>true</code>. A <code>null</code> invalid character array will return
     * <code>true</code>. An empty String ("") always returns true.
     * </p>
     * 
     * <pre>
     * StringUtils.containsNone(null, *)       = true
     * StringUtils.containsNone(*, null)       = true
     * StringUtils.containsNone("", *)         = true
     * StringUtils.containsNone("ab", "")      = true
     * StringUtils.containsNone("abab", "xyz") = true
     * StringUtils.containsNone("ab1", "xyz")  = true
     * StringUtils.containsNone("abz", "xyz")  = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param invalidChars a String of invalid chars, may be null
     * @return true if it contains none of the invalid chars, or is null
     * @since 2.0
     */
    public static boolean containsNone(String str, String invalidChars) {
        if (str == null || invalidChars == null) {
            return true;
        }
        return containsNone(str, invalidChars.toCharArray());
    }

    // IndexOfAny strings
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Find the first index of any of a set of potential substrings.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>-1</code>. A <code>null</code> or zero length search array will
     * return <code>-1</code>. A <code>null</code> search array entry will be ignored, but a search array containing ""
     * will return <code>0</code> if <code>str</code> is not null. This method uses {@link String#indexOf(String)}.
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfAny(null, *)                     = -1
     * StringUtils.indexOfAny(*, null)                     = -1
     * StringUtils.indexOfAny(*, [])                       = -1
     * StringUtils.indexOfAny("zzabyycdxx", ["ab","cd"])   = 2
     * StringUtils.indexOfAny("zzabyycdxx", ["cd","ab"])   = 2
     * StringUtils.indexOfAny("zzabyycdxx", ["mn","op"])   = -1
     * StringUtils.indexOfAny("zzabyycdxx", ["zab","aby"]) = 1
     * StringUtils.indexOfAny("zzabyycdxx", [""])          = 0
     * StringUtils.indexOfAny("", [""])                    = 0
     * StringUtils.indexOfAny("", ["a"])                   = -1
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchStrs the Strings to search for, may be null
     * @return the first index of any of the searchStrs in str, -1 if no match
     */
    public static int indexOfAny(String str, String[] searchStrs) {
        if ((str == null) || (searchStrs == null)) {
            return -1;
        }
        int sz = searchStrs.length;

        // String's can't have a MAX_VALUEth index.
        int ret = Integer.MAX_VALUE;

        int tmp = 0;
        for (int i = 0; i < sz; i++) {
            String search = searchStrs[i];
            if (search == null) {
                continue;
            }
            tmp = str.indexOf(search);
            if (tmp == -1) {
                continue;
            }

            if (tmp < ret) {
                ret = tmp;
            }
        }

        return (ret == Integer.MAX_VALUE) ? -1 : ret;
    }

    /**
     * <p>
     * Find the latest index of any of a set of potential substrings.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>-1</code>. A <code>null</code> search array will return
     * <code>-1</code>. A <code>null</code> or zero length search array entry will be ignored, but a search array
     * containing "" will return the length of <code>str</code> if <code>str</code> is not null. This method uses
     * {@link String#indexOf(String)}
     * </p>
     * 
     * <pre>
     * StringUtils.lastIndexOfAny(null, *)                   = -1
     * StringUtils.lastIndexOfAny(*, null)                   = -1
     * StringUtils.lastIndexOfAny(*, [])                     = -1
     * StringUtils.lastIndexOfAny(*, [null])                 = -1
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["ab","cd"]) = 6
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["cd","ab"]) = 6
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn",""])   = 10
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param searchStrs the Strings to search for, may be null
     * @return the last index of any of the Strings, -1 if no match
     */
    public static int lastIndexOfAny(String str, String[] searchStrs) {
        if ((str == null) || (searchStrs == null)) {
            return -1;
        }
        int sz = searchStrs.length;
        int ret = -1;
        int tmp = 0;
        for (int i = 0; i < sz; i++) {
            String search = searchStrs[i];
            if (search == null) {
                continue;
            }
            tmp = str.lastIndexOf(search);
            if (tmp > ret) {
                ret = tmp;
            }
        }
        return ret;
    }

    // Substring
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Gets a substring from the specified String avoiding exceptions.
     * </p>
     * <p>
     * A negative start position can be used to start <code>n</code> characters from the end of the String.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>null</code>. An empty ("") String will return "".
     * </p>
     * 
     * <pre>
     * StringUtils.substring(null, *)   = null
     * StringUtils.substring("", *)     = ""
     * StringUtils.substring("abc", 0)  = "abc"
     * StringUtils.substring("abc", 2)  = "c"
     * StringUtils.substring("abc", 4)  = ""
     * StringUtils.substring("abc", -2) = "bc"
     * StringUtils.substring("abc", -4) = "abc"
     * </pre>
     * 
     * @param str the String to get the substring from, may be null
     * @param start the position to start from, negative means count back from the end of the String by this many
     * characters
     * @return substring from start position, <code>null</code> if null String input
     */
    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        }

        // handle negatives, which means last n characters
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return EMPTY;
        }

        return str.substring(start);
    }

    /**
     * <p>
     * Gets a substring from the specified String avoiding exceptions.
     * </p>
     * <p>
     * A negative start position can be used to start/end <code>n</code> characters from the end of the String.
     * </p>
     * <p>
     * The returned substring starts with the character in the <code>start</code> position and ends before the
     * <code>end</code> position. All postion counting is zero-based -- i.e., to start at the beginning of the string
     * use <code>start = 0</code>. Negative start and end positions can be used to specify offsets relative to the end
     * of the String.
     * </p>
     * <p>
     * If <code>start</code> is not strictly to the left of <code>end</code>, "" is returned.
     * </p>
     * 
     * <pre>
     * StringUtils.substring(null, *, *)    = null
     * StringUtils.substring("", * ,  *)    = "";
     * StringUtils.substring("abc", 0, 2)   = "ab"
     * StringUtils.substring("abc", 2, 0)   = ""
     * StringUtils.substring("abc", 2, 4)   = "c"
     * StringUtils.substring("abc", 4, 6)   = ""
     * StringUtils.substring("abc", 2, 2)   = ""
     * StringUtils.substring("abc", -2, -1) = "b"
     * StringUtils.substring("abc", -4, 2)  = "ab"
     * </pre>
     * 
     * @param str the String to get the substring from, may be null
     * @param start the position to start from, negative means count back from the end of the String by this many
     * characters
     * @param end the position to end at (exclusive), negative means count back from the end of the String by this many
     * characters
     * @return substring from start position to end positon, <code>null</code> if null String input
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        // handle negatives
        if (end < 0) {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    // Left/Right/Mid
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Gets the leftmost <code>len</code> characters of a String.
     * </p>
     * <p>
     * If <code>len</code> characters are not available, or the String is <code>null</code>, the String will be returned
     * without an exception. An exception is thrown if len is negative.
     * </p>
     * 
     * <pre>
     * StringUtils.left(null, *)    = null
     * StringUtils.left(*, -ve)     = ""
     * StringUtils.left("", *)      = ""
     * StringUtils.left("abc", 0)   = ""
     * StringUtils.left("abc", 2)   = "ab"
     * StringUtils.left("abc", 4)   = "abc"
     * </pre>
     * 
     * @param str the String to get the leftmost characters from, may be null
     * @param len the length of the required String, must be zero or positive
     * @return the leftmost characters, <code>null</code> if null String input
     */
    public static String left(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(0, len);
        }
    }

    /**
     * <p>
     * Gets the rightmost <code>len</code> characters of a String.
     * </p>
     * <p>
     * If <code>len</code> characters are not available, or the String is <code>null</code>, the String will be returned
     * without an an exception. An exception is thrown if len is negative.
     * </p>
     * 
     * <pre>
     * StringUtils.right(null, *)    = null
     * StringUtils.right(*, -ve)     = ""
     * StringUtils.right("", *)      = ""
     * StringUtils.right("abc", 0)   = ""
     * StringUtils.right("abc", 2)   = "bc"
     * StringUtils.right("abc", 4)   = "abc"
     * </pre>
     * 
     * @param str the String to get the rightmost characters from, may be null
     * @param len the length of the required String, must be zero or positive
     * @return the rightmost characters, <code>null</code> if null String input
     */
    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(str.length() - len);
        }
    }

    /**
     * <p>
     * Gets <code>len</code> characters from the middle of a String.
     * </p>
     * <p>
     * If <code>len</code> characters are not available, the remainder of the String will be returned without an
     * exception. If the String is <code>null</code>, <code>null</code> will be returned. An exception is thrown if len
     * is negative.
     * </p>
     * 
     * <pre>
     * StringUtils.mid(null, *, *)    = null
     * StringUtils.mid(*, *, -ve)     = ""
     * StringUtils.mid("", 0, *)      = ""
     * StringUtils.mid("abc", 0, 2)   = "ab"
     * StringUtils.mid("abc", 0, 4)   = "abc"
     * StringUtils.mid("abc", 2, 4)   = "c"
     * StringUtils.mid("abc", 4, 2)   = ""
     * StringUtils.mid("abc", -2, 2)  = "ab"
     * </pre>
     * 
     * @param str the String to get the characters from, may be null
     * @param pos the position to start from, negative treated as zero
     * @param len the length of the required String, must be zero or positive
     * @return the middle characters, <code>null</code> if null String input
     */
    public static String mid(String str, int pos, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0 || pos > str.length()) {
            return EMPTY;
        }
        if (pos < 0) {
            pos = 0;
        }
        if (str.length() <= (pos + len)) {
            return str.substring(pos);
        } else {
            return str.substring(pos, pos + len);
        }
    }

    // SubStringAfter/SubStringBefore
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Gets the substring before the first occurance of a separator. The separator is not returned.
     * </p>
     * <p>
     * A <code>null</code> string input will return <code>null</code>. An empty ("") string input will return the empty
     * string. A <code>null</code> separator will return the input string.
     * </p>
     * 
     * <pre>
     * StringUtils.substringBefore(null, *)      = null
     * StringUtils.substringBefore("", *)        = ""
     * StringUtils.substringBefore("abc", "a")   = ""
     * StringUtils.substringBefore("abcba", "b") = "a"
     * StringUtils.substringBefore("abc", "c")   = "ab"
     * StringUtils.substringBefore("abc", "d")   = "abc"
     * StringUtils.substringBefore("abc", "")    = ""
     * StringUtils.substringBefore("abc", null)  = "abc"
     * </pre>
     * 
     * @param str the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring before the first occurance of the separator, <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringBefore(String str, String separator) {
        if (str == null || separator == null || str.length() == 0) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * <p>
     * Gets the substring after the first occurance of a separator. The separator is not returned.
     * </p>
     * <p>
     * A <code>null</code> string input will return <code>null</code>. An empty ("") string input will return the empty
     * string. A <code>null</code> separator will return the empty string if the input string is not <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.substringAfter(null, *)      = null
     * StringUtils.substringAfter("", *)        = ""
     * StringUtils.substringAfter(*, null)      = ""
     * StringUtils.substringAfter("abc", "a")   = "bc"
     * StringUtils.substringAfter("abcba", "b") = "cba"
     * StringUtils.substringAfter("abc", "c")   = ""
     * StringUtils.substringAfter("abc", "d")   = ""
     * StringUtils.substringAfter("abc", "")    = "abc"
     * </pre>
     * 
     * @param str the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring after the first occurance of the separator, <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringAfter(String str, String separator) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * <p>
     * Gets the substring before the last occurance of a separator. The separator is not returned.
     * </p>
     * <p>
     * A <code>null</code> string input will return <code>null</code>. An empty ("") string input will return the empty
     * string. An empty or <code>null</code> separator will return the input string.
     * </p>
     * 
     * <pre>
     * StringUtils.substringBeforeLast(null, *)      = null
     * StringUtils.substringBeforeLast("", *)        = ""
     * StringUtils.substringBeforeLast("abcba", "b") = "abc"
     * StringUtils.substringBeforeLast("abc", "c")   = "ab"
     * StringUtils.substringBeforeLast("a", "a")     = ""
     * StringUtils.substringBeforeLast("a", "z")     = "a"
     * StringUtils.substringBeforeLast("a", null)    = "a"
     * StringUtils.substringBeforeLast("a", "")      = "a"
     * </pre>
     * 
     * @param str the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring before the last occurance of the separator, <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringBeforeLast(String str, String separator) {
        if (str == null || separator == null || str.length() == 0 || separator.length() == 0) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * <p>
     * Gets the substring after the last occurance of a separator. The separator is not returned.
     * </p>
     * <p>
     * A <code>null</code> string input will return <code>null</code>. An empty ("") string input will return the empty
     * string. An empty or <code>null</code> separator will return the empty string if the input string is not
     * <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.substringAfterLast(null, *)      = null
     * StringUtils.substringAfterLast("", *)        = ""
     * StringUtils.substringAfterLast(*, "")        = ""
     * StringUtils.substringAfterLast(*, null)      = ""
     * StringUtils.substringAfterLast("abc", "a")   = "bc"
     * StringUtils.substringAfterLast("abcba", "b") = "a"
     * StringUtils.substringAfterLast("abc", "c")   = ""
     * StringUtils.substringAfterLast("a", "a")     = ""
     * StringUtils.substringAfterLast("a", "z")     = ""
     * </pre>
     * 
     * @param str the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring after the last occurance of the separator, <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringAfterLast(String str, String separator) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (separator == null || separator.length() == 0) {
            return EMPTY;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1 || pos == (str.length() - separator.length())) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    // Substring between
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Gets the String that is nested in between two instances of the same String.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> tag returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.substringBetween(null, *)            = null
     * StringUtils.substringBetween("", "")             = ""
     * StringUtils.substringBetween("", "tag")          = null
     * StringUtils.substringBetween("tagabctag", null)  = null
     * StringUtils.substringBetween("tagabctag", "")    = ""
     * StringUtils.substringBetween("tagabctag", "tag") = "abc"
     * </pre>
     * 
     * @param str the String containing the substring, may be null
     * @param tag the String before and after the substring, may be null
     * @return the substring, <code>null</code> if no match
     * @since 2.0
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag);
    }

    /**
     * <p>
     * Gets the String that is nested in between two Strings. Only the first match is returned.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> open/close returns
     * <code>null</code> (no match). An empty ("") open/close returns an empty string.
     * </p>
     * 
     * <pre>
     * StringUtils.substringBetween(null, *, *)          = null
     * StringUtils.substringBetween("", "", "")          = ""
     * StringUtils.substringBetween("", "", "tag")       = null
     * StringUtils.substringBetween("", "tag", "tag")    = null
     * StringUtils.substringBetween("yabcz", null, null) = null
     * StringUtils.substringBetween("yabcz", "", "")     = ""
     * StringUtils.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtils.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     * 
     * @param str the String containing the substring, may be null
     * @param open the String before the substring, may be null
     * @param close the String after the substring, may be null
     * @return the substring, <code>null</code> if no match
     * @since 2.0
     */
    public static String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != -1) {
            int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    // Nested extraction
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Gets the String that is nested in between two instances of the same String.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> tag returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.getNestedString(null, *)            = null
     * StringUtils.getNestedString("", "")             = ""
     * StringUtils.getNestedString("", "tag")          = null
     * StringUtils.getNestedString("tagabctag", null)  = null
     * StringUtils.getNestedString("tagabctag", "")    = ""
     * StringUtils.getNestedString("tagabctag", "tag") = "abc"
     * </pre>
     * 
     * @param str the String containing nested-string, may be null
     * @param tag the String before and after nested-string, may be null
     * @return the nested String, <code>null</code> if no match
     * @deprecated Use the better named {@link #substringBetween(String, String)}. Method will be removed in Commons
     * Lang 3.0.
     */
    public static String getNestedString(String str, String tag) {
        return substringBetween(str, tag, tag);
    }

    /**
     * <p>
     * Gets the String that is nested in between two Strings. Only the first match is returned.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> open/close returns
     * <code>null</code> (no match). An empty ("") open/close returns an empty string.
     * </p>
     * 
     * <pre>
     * StringUtils.getNestedString(null, *, *)          = null
     * StringUtils.getNestedString("", "", "")          = ""
     * StringUtils.getNestedString("", "", "tag")       = null
     * StringUtils.getNestedString("", "tag", "tag")    = null
     * StringUtils.getNestedString("yabcz", null, null) = null
     * StringUtils.getNestedString("yabcz", "", "")     = ""
     * StringUtils.getNestedString("yabcz", "y", "z")   = "abc"
     * StringUtils.getNestedString("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     * 
     * @param str the String containing nested-string, may be null
     * @param open the String before nested-string, may be null
     * @param close the String after nested-string, may be null
     * @return the nested String, <code>null</code> if no match
     * @deprecated Use the better named {@link #substringBetween(String, String, String)}. Method will be removed in
     * Commons Lang 3.0.
     */
    public static String getNestedString(String str, String open, String close) {
        return substringBetween(str, open, close);
    }

    // Splitting
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Splits the provided text into an array, using whitespace as the separator. Whitespace is defined by
     * {@link Character#isWhitespace(char)}.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.split(null)       = null
     * StringUtils.split("")         = []
     * StringUtils.split("abc def")  = ["abc", "def"]
     * StringUtils.split("abc  def") = ["abc", "def"]
     * StringUtils.split(" abc ")    = ["abc"]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @return an array of parsed Strings, <code>null</code> if null String input
     */
    public static String[] split(String str) {
        return split(str, null, -1);
    }

    /**
     * <p>
     * Splits the provided text into an array, separators specified. This is an alternative to using StringTokenizer.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> separatorChars splits on
     * whitespace.
     * </p>
     * 
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split("", *)           = []
     * StringUtils.split("abc def", null) = ["abc", "def"]
     * StringUtils.split("abc def", " ")  = ["abc", "def"]
     * StringUtils.split("abc  def", " ") = ["abc", "def"]
     * StringUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @param separatorChars the characters used as the delimiters, <code>null</code> splits on whitespace
     * @return an array of parsed Strings, <code>null</code> if null String input
     */
    public static String[] split(String str, String separatorChars) {
        return split(str, separatorChars, -1);
    }

    /**
     * <p>
     * Splits the provided text into an array, separators specified. This is an alternative to using StringTokenizer.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. A <code>null</code> separatorChars splits on
     * whitespace.
     * </p>
     * 
     * <pre>
     * StringUtils.split(null, *, *)            = null
     * StringUtils.split("", *, *)              = []
     * StringUtils.split("ab de fg", null, 0)   = ["ab", "cd", "ef"]
     * StringUtils.split("ab   de fg", null, 0) = ["ab", "cd", "ef"]
     * StringUtils.split("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
     * StringUtils.split("ab:cd:ef", ":", 2)    = ["ab", "cdef"]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @param separatorChars the characters used as the delimiters, <code>null</code> splits on whitespace
     * @param max the maximum number of elements to include in the array. A zero or negative value implies no limit
     * @return an array of parsed Strings, <code>null</code> if null String input
     */
    public static String[] split(String str, String separatorChars, int max) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()

        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return NO_STRINGS;
        }
        List list = new ArrayList();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match) {
                        if (sizePlus1++ == max) {
                            i = len;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match) {
                        if (sizePlus1++ == max) {
                            i = len;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match) {
                        if (sizePlus1++ == max) {
                            i = len;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                match = true;
                i++;
            }
        }
        if (match) {
            list.add(str.substring(start, i));
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    // Joining
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Concatenates elements of an array into a single String. Null objects or empty strings within the array are
     * represented by empty strings.
     * </p>
     * 
     * <pre>
     * StringUtils.concatenate(null)            = null
     * StringUtils.concatenate([])              = ""
     * StringUtils.concatenate([null])          = ""
     * StringUtils.concatenate(["a", "b", "c"]) = "abc"
     * StringUtils.concatenate([null, "", "a"]) = "a"
     * </pre>
     * 
     * @param array the array of values to concatenate, may be null
     * @return the concatenated String, <code>null</code> if null array input
     * @deprecated Use the better named {@link #join(Object[])} instead. Method will be removed in Commons Lang 3.0.
     */
    public static String concatenate(Object[] array) {
        return join(array, null);
    }

    /**
     * <p>
     * Joins the elements of the provided array into a single String containing the provided list of elements.
     * </p>
     * <p>
     * No separator is added to the joined String. Null objects or empty strings within the array are represented by
     * empty strings.
     * </p>
     * 
     * <pre>
     * StringUtils.join(null)            = null
     * StringUtils.join([])              = ""
     * StringUtils.join([null])          = ""
     * StringUtils.join(["a", "b", "c"]) = "abc"
     * StringUtils.join([null, "", "a"]) = "a"
     * </pre>
     * 
     * @param array the array of values to join together, may be null
     * @return the joined String, <code>null</code> if null array input
     * @since 2.0
     */
    public static String join(Object[] array) {
        return join(array, null);
    }

    /**
     * <p>
     * Joins the elements of the provided array into a single String containing the provided list of elements.
     * </p>
     * <p>
     * No delimiter is added before or after the list. Null objects or empty strings within the array are represented by
     * empty strings.
     * </p>
     * 
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = ""
     * StringUtils.join([null], *)             = ""
     * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtils.join(["a", "b", "c"], null) = "abc"
     * StringUtils.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     * 
     * @param array the array of values to join together, may be null
     * @param separator the separator character to use
     * @return the joined String, <code>null</code> if null array input
     * @since 2.0
     */
    public static String join(Object[] array, char separator) {
        if (array == null) {
            return null;
        }
        int arraySize = array.length;
        int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0].toString().length()) + 1) * arraySize);
        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <p>
     * Joins the elements of the provided array into a single String containing the provided list of elements.
     * </p>
     * <p>
     * No delimiter is added before or after the list. A <code>null</code> separator is the same as an empty String
     * (""). Null objects or empty strings within the array are represented by empty strings.
     * </p>
     * 
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     * 
     * @param array the array of values to join together, may be null
     * @param separator the separator character to use, null treated as ""
     * @return the joined String, <code>null</code> if null array input
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }
        int arraySize = array.length;

        // ArraySize == 0: Len = 0
        // ArraySize > 0: Len = NofStrings *(len(firstString) + len(separator))
        // (Assuming that all Strings are roughly equally long)
        int bufSize = ((arraySize == 0) ? 0 : arraySize
                                              * ((array[0] == null ? 16 : array[0].toString().length()) + ((separator != null) ? separator.length() : 0)));

        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if ((separator != null) && (i > 0)) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <p>
     * Joins the elements of the provided <code>Iterator</code> into a single String containing the provided elements.
     * </p>
     * <p>
     * No delimiter is added before or after the list. Null objects or empty strings within the iteration are
     * represented by empty strings.
     * </p>
     * <p>
     * See the examples here: {@link #join(Object[],char)}.
     * </p>
     * 
     * @param iterator the <code>Iterator</code> of values to join together, may be null
     * @param separator the separator character to use
     * @return the joined String, <code>null</code> if null iterator input
     * @since 2.0
     */
    public static String join(Iterator iterator, char separator) {
        if (iterator == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(256); // Java default is 16, probably too small
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
            if (iterator.hasNext()) {
                buf.append(separator);
            }
        }
        return buf.toString();
    }

    /**
     * <p>
     * Joins the elements of the provided <code>Iterator</code> into a single String containing the provided elements.
     * </p>
     * <p>
     * No delimiter is added before or after the list. A <code>null</code> separator is the same as an empty String
     * ("").
     * </p>
     * <p>
     * See the examples here: {@link #join(Object[],String)}.
     * </p>
     * 
     * @param iterator the <code>Iterator</code> of values to join together, may be null
     * @param separator the separator character to use, null treated as ""
     * @return the joined String, <code>null</code> if null iterator input
     */
    public static String join(Iterator iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(256); // Java default is 16, probably too small
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
            if ((separator != null) && iterator.hasNext()) {
                buf.append(separator);
            }
        }
        return buf.toString();
    }

    /**
     * <p>
     * Deletes all whitespaces from a String as defined by {@link Character#isWhitespace(char)}.
     * </p>
     * 
     * <pre>
     * StringUtils.deleteWhitespace(null)         = null
     * StringUtils.deleteWhitespace("")           = ""
     * StringUtils.deleteWhitespace("abc")        = "abc"
     * StringUtils.deleteWhitespace("   ab  c  ") = "abc"
     * </pre>
     * 
     * @param str the String to delete whitespace from, may be null
     * @return the String without whitespaces, <code>null</code> if null String input
     */
    public static String deleteWhitespace(String str) {
        if (str == null) {
            return null;
        }
        int sz = str.length();
        StringBuffer buffer = new StringBuffer(sz);
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                buffer.append(str.charAt(i));
            }
        }
        return buffer.toString();
    }

    // Replacing
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Replaces a String with another String inside a larger String, once.
     * </p>
     * <p>
     * A <code>null</code> reference passed to this method is a no-op.
     * </p>
     * 
     * <pre>
     * StringUtils.replaceOnce(null, *, *)        = null
     * StringUtils.replaceOnce("", *, *)          = ""
     * StringUtils.replaceOnce("aba", null, null) = "aba"
     * StringUtils.replaceOnce("aba", null, null) = "aba"
     * StringUtils.replaceOnce("aba", "a", null)  = "aba"
     * StringUtils.replaceOnce("aba", "a", "")    = "aba"
     * StringUtils.replaceOnce("aba", "a", "z")   = "zba"
     * </pre>
     * 
     * @see #replace(String text, String repl, String with, int max)
     * @param text text to search and replace in, may be null
     * @param repl the String to search for, may be null
     * @param with the String to replace with, may be null
     * @return the text with any replacements processed, <code>null</code> if null String input
     */
    public static String replaceOnce(String text, String repl, String with) {
        return replace(text, repl, with, 1);
    }

    /**
     * <p>
     * Replaces all occurances of a String within another String.
     * </p>
     * <p>
     * A <code>null</code> reference passed to this method is a no-op.
     * </p>
     * 
     * <pre>
     * StringUtils.replace(null, *, *)        = null
     * StringUtils.replace("", *, *)          = ""
     * StringUtils.replace("aba", null, null) = "aba"
     * StringUtils.replace("aba", null, null) = "aba"
     * StringUtils.replace("aba", "a", null)  = "aba"
     * StringUtils.replace("aba", "a", "")    = "aba"
     * StringUtils.replace("aba", "a", "z")   = "zbz"
     * </pre>
     * 
     * @see #replace(String text, String repl, String with, int max)
     * @param text text to search and replace in, may be null
     * @param repl the String to search for, may be null
     * @param with the String to replace with, may be null
     * @return the text with any replacements processed, <code>null</code> if null String input
     */
    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /**
     * <p>
     * Replaces a String with another String inside a larger String, for the first <code>max</code> values of the search
     * String.
     * </p>
     * <p>
     * A <code>null</code> reference passed to this method is a no-op.
     * </p>
     * 
     * <pre>
     * StringUtils.replace(null, *, *, *)         = null
     * StringUtils.replace("", *, *, *)           = ""
     * StringUtils.replace("abaa", null, null, 1) = "abaa"
     * StringUtils.replace("abaa", null, null, 1) = "abaa"
     * StringUtils.replace("abaa", "a", null, 1)  = "abaa"
     * StringUtils.replace("abaa", "a", "", 1)    = "abaa"
     * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     * 
     * @param text text to search and replace in, may be null
     * @param repl the String to search for, may be null
     * @param with the String to replace with, may be null
     * @param max maximum number of values to replace, or <code>-1</code> if no maximum
     * @return the text with any replacements processed, <code>null</code> if null String input
     */
    public static String replace(String text, String repl, String with, int max) {
        if (text == null || repl == null || with == null || repl.length() == 0 || max == 0) {
            return text;
        }

        StringBuffer buf = new StringBuffer(text.length());
        int start = 0, end = 0;
        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    // Replace, character based
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Replaces all occurrances of a character in a String with another. This is a null-safe version of
     * {@link String#replace(char, char)}.
     * </p>
     * <p>
     * A <code>null</code> string input returns <code>null</code>. An empty ("") string input returns an empty string.
     * </p>
     * 
     * <pre>
     * StringUtils.replaceChars(null, *, *)        = null
     * StringUtils.replaceChars("", *, *)          = ""
     * StringUtils.replaceChars("abcba", 'b', 'y') = "aycya"
     * StringUtils.replaceChars("abcba", 'z', 'y') = "abcba"
     * </pre>
     * 
     * @param str String to replace characters in, may be null
     * @param searchChar the character to search for, may be null
     * @param replaceChar the character to replace, may be null
     * @return modified String, <code>null</code> if null string input
     * @since 2.0
     */
    public static String replaceChars(String str, char searchChar, char replaceChar) {
        if (str == null) {
            return null;
        }
        return str.replace(searchChar, replaceChar);
    }

    /**
     * <p>
     * Replaces multiple characters in a String in one go. This method can also be used to delete characters.
     * </p>
     * <p>
     * For example:<br />
     * <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>.
     * </p>
     * <p>
     * A <code>null</code> string input returns <code>null</code>. An empty ("") string input returns an empty string. A
     * null or empty set of search characters returns the input string.
     * </p>
     * <p>
     * The length of the search characters should normally equal the length of the replace characters. If the search
     * characters is longer, then the extra search characters are deleted. If the search characters is shorter, then the
     * extra replace characters are ignored.
     * </p>
     * 
     * <pre>
     * StringUtils.replaceChars(null, *, *)           = null
     * StringUtils.replaceChars("", *, *)             = ""
     * StringUtils.replaceChars("abc", null, *)       = "abc"
     * StringUtils.replaceChars("abc", "", *)         = "abc"
     * StringUtils.replaceChars("abc", "b", null)     = "ac"
     * StringUtils.replaceChars("abc", "b", "")       = "ac"
     * StringUtils.replaceChars("abcba", "bc", "yz")  = "ayzya"
     * StringUtils.replaceChars("abcba", "bc", "y")   = "ayya"
     * StringUtils.replaceChars("abcba", "bc", "yzx") = "ayzya"
     * </pre>
     * 
     * @param str String to replace characters in, may be null
     * @param searchChars a set of characters to search for, may be null
     * @param replaceChars a set of characters to replace, may be null
     * @return modified String, <code>null</code> if null string input
     * @since 2.0
     */
    public static String replaceChars(String str, String searchChars, String replaceChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length() == 0) {
            return str;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        boolean modified = false;
        for (int i = 0, isize = searchChars.length(); i < isize; i++) {
            char searchChar = searchChars.charAt(i);
            if (replaceChars == null || i >= replaceChars.length()) {
                // delete
                int pos = 0;
                for (int j = 0; j < len; j++) {
                    if (chars[j] != searchChar) {
                        chars[pos++] = chars[j];
                    } else {
                        modified = true;
                    }
                }
                len = pos;
            } else {
                // replace
                for (int j = 0; j < len; j++) {
                    if (chars[j] == searchChar) {
                        chars[j] = replaceChars.charAt(i);
                        modified = true;
                    }
                }
            }
        }
        if (modified == false) {
            return str;
        }
        return new String(chars, 0, len);
    }

    // Overlay
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Overlays part of a String with another String.
     * </p>
     * 
     * <pre>
     * StringUtils.overlayString(null, *, *, *)           = NullPointerException
     * StringUtils.overlayString(*, null, *, *)           = NullPointerException
     * StringUtils.overlayString("", "abc", 0, 0)         = "abc"
     * StringUtils.overlayString("abcdef", null, 2, 4)    = "abef"
     * StringUtils.overlayString("abcdef", "", 2, 4)      = "abef"
     * StringUtils.overlayString("abcdef", "zzzz", 2, 4)  = "abzzzzef"
     * StringUtils.overlayString("abcdef", "zzzz", 4, 2)  = "abcdzzzzcdef"
     * StringUtils.overlayString("abcdef", "zzzz", -1, 4) = IndexOutOfBoundsException
     * StringUtils.overlayString("abcdef", "zzzz", 2, 8)  = IndexOutOfBoundsException
     * </pre>
     * 
     * @param text the String to do overlaying in, may be null
     * @param overlay the String to overlay, may be null
     * @param start the position to start overlaying at, must be valid
     * @param end the position to stop overlaying before, must be valid
     * @return overlayed String, <code>null</code> if null String input
     * @throws NullPointerException if text or overlay is null
     * @throws IndexOutOfBoundsException if either position is invalid
     * @deprecated Use better named {@link #overlay(String, String, int, int)} instead. Method will be removed in
     * Commons Lang 3.0.
     */
    public static String overlayString(String text, String overlay, int start, int end) {
        return new StringBuffer(start + overlay.length() + text.length() - end + 1).append(text.substring(0, start)).append(overlay).append(text.substring(end)).toString();
    }

    /**
     * <p>
     * Overlays part of a String with another String.
     * </p>
     * <p>
     * A <code>null</code> string input returns <code>null</code>. A negative index is treated as zero. An index greater
     * than the string length is treated as the string length. The start index is always the smaller of the two indices.
     * </p>
     * 
     * <pre>
     * StringUtils.overlay(null, *, *, *)            = null
     * StringUtils.overlay("", "abc", 0, 0)          = "abc"
     * StringUtils.overlay("abcdef", null, 2, 4)     = "abef"
     * StringUtils.overlay("abcdef", "", 2, 4)       = "abef"
     * StringUtils.overlay("abcdef", "", 4, 2)       = "abef"
     * StringUtils.overlay("abcdef", "zzzz", 2, 4)   = "abzzzzef"
     * StringUtils.overlay("abcdef", "zzzz", 4, 2)   = "abzzzzef"
     * StringUtils.overlay("abcdef", "zzzz", -1, 4)  = "zzzzef"
     * StringUtils.overlay("abcdef", "zzzz", 2, 8)   = "abzzzz"
     * StringUtils.overlay("abcdef", "zzzz", -2, -3) = "zzzzabcdef"
     * StringUtils.overlay("abcdef", "zzzz", 8, 10)  = "abcdefzzzz"
     * </pre>
     * 
     * @param str the String to do overlaying in, may be null
     * @param overlay the String to overlay, may be null
     * @param start the position to start overlaying at
     * @param end the position to stop overlaying before
     * @return overlayed String, <code>null</code> if null String input
     * @since 2.0
     */
    public static String overlay(String str, String overlay, int start, int end) {
        if (str == null) {
            return null;
        }
        if (overlay == null) {
            overlay = EMPTY;
        }
        int len = str.length();
        if (start < 0) {
            start = 0;
        }
        if (start > len) {
            start = len;
        }
        if (end < 0) {
            end = 0;
        }
        if (end > len) {
            end = len;
        }
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }
        return new StringBuffer(len + start - end + overlay.length() + 1).append(str.substring(0, start)).append(overlay).append(str.substring(end)).toString();
    }

    // Chomping
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Removes one newline from end of a String if it's there, otherwise leave it alone. A newline is &quot;
     * <code>\n</code>&quot;, &quot;<code>\r</code>&quot;, or &quot;<code>\r\n</code>&quot;.
     * </p>
     * <p>
     * NOTE: This method changed in 2.0. It now more closely matches Perl chomp.
     * </p>
     * 
     * <pre>
     * StringUtils.chomp(null)          = null
     * StringUtils.chomp("")            = ""
     * StringUtils.chomp("abc \r")      = "abc "
     * StringUtils.chomp("abc\n")       = "abc"
     * StringUtils.chomp("abc\r\n")     = "abc"
     * StringUtils.chomp("abc\r\n\r\n") = "abc\r\n"
     * StringUtils.chomp("abc\n\r")     = "abc\n"
     * StringUtils.chomp("abc\n\rabc")  = "abc\n\rabc"
     * StringUtils.chomp("\r")          = ""
     * StringUtils.chomp("\n")          = ""
     * StringUtils.chomp("\r\n")        = ""
     * </pre>
     * 
     * @param str the String to chomp a newline from, may be null
     * @return String without newline, <code>null</code> if null String input
     */
    public static String chomp(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        if (str.length() == 1) {
            char ch = str.charAt(0);
            if (ch == '\r' || ch == '\n') {
                return EMPTY;
            } else {
                return str;
            }
        }

        int lastIdx = str.length() - 1;
        char last = str.charAt(lastIdx);

        if (last == '\n') {
            if (str.charAt(lastIdx - 1) == '\r') {
                lastIdx--;
            }
        } else if (last == '\r') {

        } else {
            lastIdx++;
        }
        return str.substring(0, lastIdx);
    }

    /**
     * <p>
     * Removes <code>separator</code> from the end of <code>str</code> if it's there, otherwise leave it alone.
     * </p>
     * <p>
     * NOTE: This method changed in version 2.0. It now more closely matches Perl chomp. For the previous behavior, use
     * {@link #substringBeforeLast(String, String)}. This method uses {@link String#endsWith(String)}.
     * </p>
     * 
     * <pre>
     * StringUtils.chomp(null, *)         = null
     * StringUtils.chomp("", *)           = ""
     * StringUtils.chomp("foobar", "bar") = "foo"
     * StringUtils.chomp("foobar", "baz") = "foobar"
     * StringUtils.chomp("foo", "foo")    = ""
     * StringUtils.chomp("foo ", "foo")   = "foo"
     * StringUtils.chomp(" foo", "foo")   = " "
     * StringUtils.chomp("foo", "foooo")  = "foo"
     * StringUtils.chomp("foo", "")       = "foo"
     * StringUtils.chomp("foo", null)     = "foo"
     * </pre>
     * 
     * @param str the String to chomp from, may be null
     * @param separator separator String, may be null
     * @return String without trailing separator, <code>null</code> if null String input
     */
    public static String chomp(String str, String separator) {
        if (str == null || str.length() == 0 || separator == null) {
            return str;
        }
        if (str.endsWith(separator)) {
            return str.substring(0, str.length() - separator.length());
        }
        return str;
    }

    /**
     * <p>
     * Remove any &quot;\n&quot; if and only if it is at the end of the supplied String.
     * </p>
     * 
     * @param str the String to chomp from, must not be null
     * @return String without chomped ending
     * @throws NullPointerException if str is <code>null</code>
     * @deprecated Use {@link #chomp(String)} instead. Method will be removed in Commons Lang 3.0.
     */
    public static String chompLast(String str) {
        return chompLast(str, "\n");
    }

    /**
     * <p>
     * Remove a value if and only if the String ends with that value.
     * </p>
     * 
     * @param str the String to chomp from, must not be null
     * @param sep the String to chomp, must not be null
     * @return String without chomped ending
     * @throws NullPointerException if str or sep is <code>null</code>
     * @deprecated Use {@link #chomp(String,String)} instead. Method will be removed in Commons Lang 3.0.
     */
    public static String chompLast(String str, String sep) {
        if (str.length() == 0) {
            return str;
        }
        String sub = str.substring(str.length() - sep.length());
        if (sep.equals(sub)) {
            return str.substring(0, str.length() - sep.length());
        } else {
            return str;
        }
    }

    /**
     * <p>
     * Remove everything and return the last value of a supplied String, and everything after it from a String.
     * </p>
     * 
     * @param str the String to chomp from, must not be null
     * @param sep the String to chomp, must not be null
     * @return String chomped
     * @throws NullPointerException if str or sep is <code>null</code>
     * @deprecated Use {@link #substringAfterLast(String, String)} instead (although this doesn't include the separator)
     * Method will be removed in Commons Lang 3.0.
     */
    public static String getChomp(String str, String sep) {
        int idx = str.lastIndexOf(sep);
        if (idx == str.length() - sep.length()) {
            return sep;
        } else if (idx != -1) {
            return str.substring(idx);
        } else {
            return EMPTY;
        }
    }

    /**
     * <p>
     * Remove the first value of a supplied String, and everything before it from a String.
     * </p>
     * 
     * @param str the String to chomp from, must not be null
     * @param sep the String to chomp, must not be null
     * @return String without chomped beginning
     * @throws NullPointerException if str or sep is <code>null</code>
     * @deprecated Use {@link #substringAfter(String,String)} instead. Method will be removed in Commons Lang 3.0.
     */
    public static String prechomp(String str, String sep) {
        int idx = str.indexOf(sep);
        if (idx != -1) {
            return str.substring(idx + sep.length());
        } else {
            return str;
        }
    }

    /**
     * <p>
     * Remove and return everything before the first value of a supplied String from another String.
     * </p>
     * 
     * @param str the String to chomp from, must not be null
     * @param sep the String to chomp, must not be null
     * @return String prechomped
     * @throws NullPointerException if str or sep is <code>null</code>
     * @deprecated Use {@link #substringBefore(String,String)} instead (although this doesn't include the separator).
     * Method will be removed in Commons Lang 3.0.
     */
    public static String getPrechomp(String str, String sep) {
        int idx = str.indexOf(sep);
        if (idx != -1) {
            return str.substring(0, idx + sep.length());
        } else {
            return EMPTY;
        }
    }

    // Chopping
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Remove the last character from a String.
     * </p>
     * <p>
     * If the String ends in <code>\r\n</code>, then remove both of them.
     * </p>
     * 
     * <pre>
     * StringUtils.chop(null)          = null
     * StringUtils.chop("")            = ""
     * StringUtils.chop("abc \r")      = "abc "
     * StringUtils.chop("abc\n")       = "abc"
     * StringUtils.chop("abc\r\n")     = "abc"
     * StringUtils.chop("abc")         = "ab"
     * StringUtils.chop("abc\nabc")    = "abc\nab"
     * StringUtils.chop("a")           = ""
     * StringUtils.chop("\r")          = ""
     * StringUtils.chop("\n")          = ""
     * StringUtils.chop("\r\n")        = ""
     * </pre>
     * 
     * @param str the String to chop last character from, may be null
     * @return String without last character, <code>null</code> if null String input
     */
    public static String chop(String str) {
        if (str == null) {
            return null;
        }
        int strLen = str.length();
        if (strLen < 2) {
            return EMPTY;
        }
        int lastIdx = strLen - 1;
        String ret = str.substring(0, lastIdx);
        char last = str.charAt(lastIdx);
        if (last == '\n') {
            if (ret.charAt(lastIdx - 1) == '\r') {
                return ret.substring(0, lastIdx - 1);
            }
        }
        return ret;
    }

    /**
     * <p>
     * Removes <code>\n</code> from end of a String if it's there. If a <code>\r</code> precedes it, then remove that
     * too.
     * </p>
     * 
     * @param str the String to chop a newline from, must not be null
     * @return String without newline
     * @throws NullPointerException if str is <code>null</code>
     * @deprecated Use {@link #chomp(String)} instead. Method will be removed in Commons Lang 3.0.
     */
    public static String chopNewline(String str) {
        int lastIdx = str.length() - 1;
        if (lastIdx <= 0) {
            return EMPTY;
        }
        char last = str.charAt(lastIdx);
        if (last == '\n') {
            if (str.charAt(lastIdx - 1) == '\r') {
                lastIdx--;
            }
        } else {
            lastIdx++;
        }
        return str.substring(0, lastIdx);
    }

    // Padding
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Repeat a String <code>repeat</code> times to form a new String.
     * </p>
     * 
     * <pre>
     * StringUtils.repeat(null, 2) = null
     * StringUtils.repeat("", 0)   = ""
     * StringUtils.repeat("", 2)   = ""
     * StringUtils.repeat("a", 3)  = "aaa"
     * StringUtils.repeat("ab", 2) = "abab"
     * StringUtils.repeat("a", -2) = ""
     * </pre>
     * 
     * @param str the String to repeat, may be null
     * @param repeat number of times to repeat str, negative treated as zero
     * @return a new String consisting of the original String repeated, <code>null</code> if null String input
     */
    public static String repeat(String str, int repeat) {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null) {
            return null;
        }
        if (repeat <= 0) {
            return EMPTY;
        }
        int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT) {
            return padding(repeat, str.charAt(0));
        }

        int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1:
                char ch = str.charAt(0);
                char[] output1 = new char[outputLength];
                for (int i = repeat - 1; i >= 0; i--) {
                    output1[i] = ch;
                }
                return new String(output1);
            case 2:
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default:
                StringBuffer buf = new StringBuffer(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }

    /**
     * <p>
     * Returns padding using the specified delimiter repeated to a given length.
     * </p>
     * 
     * <pre>
     * StringUtils.padding(0, 'e')  = ""
     * StringUtils.padding(3, 'e')  = "eee"
     * StringUtils.padding(-2, 'e') = IndexOutOfBoundsException
     * </pre>
     * 
     * @param repeat number of times to repeat delim
     * @param padChar character to repeat
     * @return String with repeated character
     * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
     */
    private static String padding(int repeat, char padChar) {
        // be careful of synchronization in this method
        // we are assuming that get and set from an array index is atomic
        String pad = PADDING[padChar];
        if (pad == null) {
            pad = String.valueOf(padChar);
        }
        while (pad.length() < repeat) {
            pad = pad.concat(pad);
        }
        PADDING[padChar] = pad;
        return pad.substring(0, repeat);
    }

    /**
     * <p>
     * Right pad a String with spaces (' ').
     * </p>
     * <p>
     * The String is padded to the size of <code>size</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.rightPad(null, *)   = null
     * StringUtils.rightPad("", 3)     = "   "
     * StringUtils.rightPad("bat", 3)  = "bat"
     * StringUtils.rightPad("bat", 5)  = "bat  "
     * StringUtils.rightPad("bat", 1)  = "bat"
     * StringUtils.rightPad("bat", -1) = "bat"
     * </pre>
     * 
     * @param str the String to pad out, may be null
     * @param size the size to pad to
     * @return right padded String or original String if no padding is necessary, <code>null</code> if null String input
     */
    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }

    /**
     * <p>
     * Right pad a String with a specified character.
     * </p>
     * <p>
     * The String is padded to the size of <code>size</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.rightPad(null, *, *)     = null
     * StringUtils.rightPad("", 3, 'z')     = "zzz"
     * StringUtils.rightPad("bat", 3, 'z')  = "bat"
     * StringUtils.rightPad("bat", 5, 'z')  = "batzz"
     * StringUtils.rightPad("bat", 1, 'z')  = "bat"
     * StringUtils.rightPad("bat", -1, 'z') = "bat"
     * </pre>
     * 
     * @param str the String to pad out, may be null
     * @param size the size to pad to
     * @param padChar the character to pad with
     * @return right padded String or original String if no padding is necessary, <code>null</code> if null String input
     * @since 2.0
     */
    public static String rightPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(padding(pads, padChar));
    }

    /**
     * <p>
     * Right pad a String with a specified String.
     * </p>
     * <p>
     * The String is padded to the size of <code>size</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.rightPad(null, *, *)      = null
     * StringUtils.rightPad("", 3, "z")      = "zzz"
     * StringUtils.rightPad("bat", 3, "yz")  = "bat"
     * StringUtils.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtils.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtils.rightPad("bat", 1, "yz")  = "bat"
     * StringUtils.rightPad("bat", -1, "yz") = "bat"
     * StringUtils.rightPad("bat", 5, null)  = "bat  "
     * StringUtils.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     * 
     * @param str the String to pad out, may be null
     * @param size the size to pad to
     * @param padStr the String to pad with, null or empty treated as single space
     * @return right padded String or original String if no padding is necessary, <code>null</code> if null String input
     */
    public static String rightPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (padStr == null || padStr.length() == 0) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }

    /**
     * <p>
     * Left pad a String with spaces (' ').
     * </p>
     * <p>
     * The String is padded to the size of <code>size<code>.
     * </p>
     * 
     * <pre>
     * StringUtils.leftPad(null, *)   = null
     * StringUtils.leftPad("", 3)     = "   "
     * StringUtils.leftPad("bat", 3)  = "bat"
     * StringUtils.leftPad("bat", 5)  = "  bat"
     * StringUtils.leftPad("bat", 1)  = "bat"
     * StringUtils.leftPad("bat", -1) = "bat"
     * </pre>
     * 
     * @param str the String to pad out, may be null
     * @param size the size to pad to
     * @return left padded String or original String if no padding is necessary, <code>null</code> if null String input
     */
    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    /**
     * <p>
     * Left pad a String with a specified character.
     * </p>
     * <p>
     * Pad to a size of <code>size</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.leftPad(null, *, *)     = null
     * StringUtils.leftPad("", 3, 'z')     = "zzz"
     * StringUtils.leftPad("bat", 3, 'z')  = "bat"
     * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
     * StringUtils.leftPad("bat", 1, 'z')  = "bat"
     * StringUtils.leftPad("bat", -1, 'z') = "bat"
     * </pre>
     * 
     * @param str the String to pad out, may be null
     * @param size the size to pad to
     * @param padChar the character to pad with
     * @return left padded String or original String if no padding is necessary, <code>null</code> if null String input
     * @since 2.0
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return padding(pads, padChar).concat(str);
    }

    /**
     * <p>
     * Left pad a String with a specified String.
     * </p>
     * <p>
     * Pad to a size of <code>size</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.leftPad(null, *, *)      = null
     * StringUtils.leftPad("", 3, "z")      = "zzz"
     * StringUtils.leftPad("bat", 3, "yz")  = "bat"
     * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtils.leftPad("bat", 1, "yz")  = "bat"
     * StringUtils.leftPad("bat", -1, "yz") = "bat"
     * StringUtils.leftPad("bat", 5, null)  = "  bat"
     * StringUtils.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     * 
     * @param str the String to pad out, may be null
     * @param size the size to pad to
     * @param padStr the String to pad with, null or empty treated as single space
     * @return left padded String or original String if no padding is necessary, <code>null</code> if null String input
     */
    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (padStr == null || padStr.length() == 0) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

    // Centering
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Centers a String in a larger String of size <code>size</code> using the space character (' ').
     * <p>
     * <p>
     * If the size is less than the String length, the String is returned. A <code>null</code> String returns
     * <code>null</code>. A negative size is treated as zero.
     * </p>
     * <p>
     * Equivalent to <code>center(str, size, " ")</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.center(null, *)   = null
     * StringUtils.center("", 4)     = "    "
     * StringUtils.center("ab", -1)  = "ab"
     * StringUtils.center("ab", 4)   = " ab "
     * StringUtils.center("abcd", 2) = "abcd"
     * StringUtils.center("a", 4)    = " a  "
     * </pre>
     * 
     * @param str the String to center, may be null
     * @param size the int size of new String, negative treated as zero
     * @return centered String, <code>null</code> if null String input
     */
    public static String center(String str, int size) {
        return center(str, size, ' ');
    }

    /**
     * <p>
     * Centers a String in a larger String of size <code>size</code>. Uses a supplied character as the value to pad the
     * String with.
     * </p>
     * <p>
     * If the size is less than the String length, the String is returned. A <code>null</code> String returns
     * <code>null</code>. A negative size is treated as zero.
     * </p>
     * 
     * <pre>
     * StringUtils.center(null, *, *)     = null
     * StringUtils.center("", 4, ' ')     = "    "
     * StringUtils.center("ab", -1, ' ')  = "ab"
     * StringUtils.center("ab", 4, ' ')   = " ab"
     * StringUtils.center("abcd", 2, ' ') = "abcd"
     * StringUtils.center("a", 4, ' ')    = " a  "
     * StringUtils.center("a", 4, 'y')    = "yayy"
     * </pre>
     * 
     * @param str the String to center, may be null
     * @param size the int size of new String, negative treated as zero
     * @param padChar the character to pad the new String with
     * @return centered String, <code>null</code> if null String input
     * @since 2.0
     */
    public static String center(String str, int size, char padChar) {
        if (str == null || size <= 0) {
            return str;
        }
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        str = leftPad(str, strLen + pads / 2, padChar);
        str = rightPad(str, size, padChar);
        return str;
    }

    /**
     * <p>
     * Centers a String in a larger String of size <code>size</code>. Uses a supplied String as the value to pad the
     * String with.
     * </p>
     * <p>
     * If the size is less than the String length, the String is returned. A <code>null</code> String returns
     * <code>null</code>. A negative size is treated as zero.
     * </p>
     * 
     * <pre>
     * StringUtils.center(null, *, *)     = null
     * StringUtils.center("", 4, " ")     = "    "
     * StringUtils.center("ab", -1, " ")  = "ab"
     * StringUtils.center("ab", 4, " ")   = " ab"
     * StringUtils.center("abcd", 2, " ") = "abcd"
     * StringUtils.center("a", 4, " ")    = " a  "
     * StringUtils.center("a", 4, "yz")   = "yayz"
     * StringUtils.center("abc", 7, null) = "  abc  "
     * StringUtils.center("abc", 7, "")   = "  abc  "
     * </pre>
     * 
     * @param str the String to center, may be null
     * @param size the int size of new String, negative treated as zero
     * @param padStr the String to pad the new String with, must not be null or empty
     * @return centered String, <code>null</code> if null String input
     * @throws IllegalArgumentException if padStr is <code>null</code> or empty
     */
    public static String center(String str, int size, String padStr) {
        if (str == null || size <= 0) {
            return str;
        }
        if (padStr == null || padStr.length() == 0) {
            padStr = " ";
        }
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        str = leftPad(str, strLen + pads / 2, padStr);
        str = rightPad(str, size, padStr);
        return str;
    }

    // Case conversion
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Converts a String to upper case as per {@link String#toUpperCase()}.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.upperCase(null)  = null
     * StringUtils.upperCase("")    = ""
     * StringUtils.upperCase("aBc") = "ABC"
     * </pre>
     * 
     * @param str the String to upper case, may be null
     * @return the upper cased String, <code>null</code> if null String input
     */
    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    /**
     * <p>
     * Converts a String to lower case as per {@link String#toLowerCase()}.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.lowerCase(null)  = null
     * StringUtils.lowerCase("")    = ""
     * StringUtils.lowerCase("aBc") = "abc"
     * </pre>
     * 
     * @param str the String to lower case, may be null
     * @return the lower cased String, <code>null</code> if null String input
     */
    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    /**
     * <p>
     * Capitalizes a String changing the first letter to title case as per {@link Character#toTitleCase(char)}. No other
     * letters are changed.
     * </p>
     * 
     * @param str the String to capitalize, may be null
     * @return the capitalized String, <code>null</code> if null String input
     * @deprecated Use the standardly named {@link #capitalize(String)}. Method will be removed in Commons Lang 3.0.
     */
    public static String capitalise(String str) {
        return capitalize(str);
    }

    /**
     * <p>
     * Uncapitalizes a String changing the first letter to title case as per {@link Character#toLowerCase(char)}. No
     * other letters are changed.
     * </p>
     * <p>
     * For a word based alorithm, see {@link WordUtils#uncapitalize(String)}. A <code>null</code> input String returns
     * <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.uncapitalize(null)  = null
     * StringUtils.uncapitalize("")    = ""
     * StringUtils.uncapitalize("Cat") = "cat"
     * StringUtils.uncapitalize("CAT") = "cAT"
     * </pre>
     * 
     * @param str the String to uncapitalize, may be null
     * @return the uncapitalized String, <code>null</code> if null String input
     * @see WordUtils#uncapitalize(String)
     * @see #capitalize(String)
     * @since 2.0
     */
    public static String uncapitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuffer(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * <p>
     * Uncapitalizes a String changing the first letter to title case as per {@link Character#toLowerCase(char)}. No
     * other letters are changed.
     * </p>
     * 
     * @param str the String to uncapitalize, may be null
     * @return the uncapitalized String, <code>null</code> if null String input
     * @deprecated Use the standardly named {@link #uncapitalize(String)}. Method will be removed in Commons Lang 3.0.
     */
    public static String uncapitalise(String str) {
        return uncapitalize(str);
    }

    /**
     * <p>
     * Swaps the case of a String changing upper and title case to lower case, and lower case to upper case.
     * </p>
     * <ul>
     * <li>Upper case character converts to Lower case</li>
     * <li>Title case character converts to Lower case</li>
     * <li>Lower case character converts to Upper case</li>
     * </ul>
     * <p>
     * For a word based alorithm, see {@link WordUtils#swapCase(String)}. A <code>null</code> input String returns
     * <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.swapCase(null)                 = null
     * StringUtils.swapCase("")                   = ""
     * StringUtils.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
     * <p>
     * NOTE: This method changed in Lang version 2.0. It no longer performs a word based alorithm. If you only use
     * ASCII, you will notice no change. That functionality is available in WordUtils.
     * </p>
     * 
     * @param str the String to swap case, may be null
     * @return the changed String, <code>null</code> if null String input
     */
    public static String swapCase(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        StringBuffer buffer = new StringBuffer(strLen);

        char ch = 0;
        for (int i = 0; i < strLen; i++) {
            ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }
            buffer.append(ch);
        }
        return buffer.toString();
    }

    // Count matches
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Counts how many times the substring appears in the larger String.
     * </p>
     * <p>
     * A <code>null</code> or empty ("") String input returns <code>0</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.countMatches(null, *)       = 0
     * StringUtils.countMatches("", *)         = 0
     * StringUtils.countMatches("abba", null)  = 0
     * StringUtils.countMatches("abba", "")    = 0
     * StringUtils.countMatches("abba", "a")   = 2
     * StringUtils.countMatches("abba", "ab")  = 1
     * StringUtils.countMatches("abba", "xxx") = 0
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param sub the substring to count, may be null
     * @return the number of occurances, 0 if either String is <code>null</code>
     */
    public static int countMatches(String str, String sub) {
        if (str == null || str.length() == 0 || sub == null || sub.length() == 0) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    // Character Tests
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks if the String contains only unicode letters.
     * </p>
     * <p>
     * <code>null</code> will return <code>false</code>. An empty String ("") will return <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isAlpha(null)   = false
     * StringUtils.isAlpha("")     = true
     * StringUtils.isAlpha("  ")   = false
     * StringUtils.isAlpha("abc")  = true
     * StringUtils.isAlpha("ab2c") = false
     * StringUtils.isAlpha("ab-c") = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if only contains letters, and is non-null
     */
    public static boolean isAlpha(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetter(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the String contains only unicode letters and space (' ').
     * </p>
     * <p>
     * <code>null</code> will return <code>false</code> An empty String ("") will return <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isAlphaSpace(null)   = false
     * StringUtils.isAlphaSpace("")     = true
     * StringUtils.isAlphaSpace("  ")   = true
     * StringUtils.isAlphaSpace("abc")  = true
     * StringUtils.isAlphaSpace("ab c") = true
     * StringUtils.isAlphaSpace("ab2c") = false
     * StringUtils.isAlphaSpace("ab-c") = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if only contains letters and space, and is non-null
     */
    public static boolean isAlphaSpace(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if ((Character.isLetter(str.charAt(i)) == false) && (str.charAt(i) != ' ')) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the String contains only unicode letters or digits.
     * </p>
     * <p>
     * <code>null</code> will return <code>false</code>. An empty String ("") will return <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isAlphanumeric(null)   = false
     * StringUtils.isAlphanumeric("")     = true
     * StringUtils.isAlphanumeric("  ")   = false
     * StringUtils.isAlphanumeric("abc")  = true
     * StringUtils.isAlphanumeric("ab c") = false
     * StringUtils.isAlphanumeric("ab2c") = true
     * StringUtils.isAlphanumeric("ab-c") = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if only contains letters or digits, and is non-null
     */
    public static boolean isAlphanumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetterOrDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the String contains only unicode letters, digits or space (<code>' '</code>).
     * </p>
     * <p>
     * <code>null</code> will return <code>false</code>. An empty String ("") will return <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isAlphanumeric(null)   = false
     * StringUtils.isAlphanumeric("")     = true
     * StringUtils.isAlphanumeric("  ")   = true
     * StringUtils.isAlphanumeric("abc")  = true
     * StringUtils.isAlphanumeric("ab c") = true
     * StringUtils.isAlphanumeric("ab2c") = true
     * StringUtils.isAlphanumeric("ab-c") = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if only contains letters, digits or space, and is non-null
     */
    public static boolean isAlphanumericSpace(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if ((Character.isLetterOrDigit(str.charAt(i)) == false) && (str.charAt(i) != ' ')) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the String contains only unicode digits. A decimal point is not a unicode digit and returns false.
     * </p>
     * <p>
     * <code>null</code> will return <code>false</code>. An empty String ("") will return <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = true
     * StringUtils.isNumeric("  ")   = false
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("12 3") = false
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12-3") = false
     * StringUtils.isNumeric("12.3") = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if only contains digits, and is non-null
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the String contains only unicode digits or space (<code>' '</code>). A decimal point is not a unicode
     * digit and returns false.
     * </p>
     * <p>
     * <code>null</code> will return <code>false</code>. An empty String ("") will return <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = true
     * StringUtils.isNumeric("  ")   = true
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("12 3") = true
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12-3") = false
     * StringUtils.isNumeric("12.3") = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if only contains digits or space, and is non-null
     */
    public static boolean isNumericSpace(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if ((Character.isDigit(str.charAt(i)) == false) && (str.charAt(i) != ' ')) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the String contains only whitespace.
     * </p>
     * <p>
     * <code>null</code> will return <code>false</code>. An empty String ("") will return <code>true</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.isWhitespace(null)   = false
     * StringUtils.isWhitespace("")     = true
     * StringUtils.isWhitespace("  ")   = true
     * StringUtils.isWhitespace("abc")  = false
     * StringUtils.isWhitespace("ab2c") = false
     * StringUtils.isWhitespace("ab-c") = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if only contains whitespace, and is non-null
     * @since 2.0
     */
    public static boolean isWhitespace(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    // Defaults
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Returns either the passed in String, or if the String is <code>null</code>, an empty String ("").
     * </p>
     * 
     * <pre>
     * StringUtils.defaultString(null)  = ""
     * StringUtils.defaultString("")    = ""
     * StringUtils.defaultString("bat") = "bat"
     * </pre>
     * 
     * @see String#valueOf(Object)
     * @param str the String to check, may be null
     * @return the passed in String, or the empty String if it was <code>null</code>
     */
    public static String defaultString(String str) {
        return (str == null ? EMPTY : str);
    }

    /**
     * <p>
     * Returns either the passed in String, or if the String is <code>null</code>, an empty String ("").
     * </p>
     * 
     * <pre>
     * StringUtils.defaultString(null, "null")  = "null"
     * StringUtils.defaultString("", "null")    = ""
     * StringUtils.defaultString("bat", "null") = "bat"
     * </pre>
     * 
     * @see String#valueOf(Object)
     * @param str the String to check, may be null
     * @param defaultStr the default String to return if the input is <code>null</code>, may be null
     * @return the passed in String, or the default if it was <code>null</code>
     */
    public static String defaultString(String str, String defaultStr) {
        return (str == null ? defaultStr : str);
    }

    // Reversing
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Reverses a String as per {@link StringBuffer#reverse()}.
     * </p>
     * <p>
     * <A code>null</code> String returns <code>null</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.reverse(null)  = null
     * StringUtils.reverse("")    = ""
     * StringUtils.reverse("bat") = "tab"
     * </pre>
     * 
     * @param str the String to reverse, may be null
     * @return the reversed String, <code>null</code> if null String input
     */
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuffer(str).reverse().toString();
    }

    // Abbreviating
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Abbreviates a String using ellipses. This will turn "Now is the time for all good men" into
     * "Now is the time for..."
     * </p>
     * <p>
     * Specifically:
     * <ul>
     * <li>If <code>str</code> is less than <code>maxWidth</code> characters long, return it.</li>
     * <li>Else abbreviate it to <code>(substring(str, 0, max-3) + "...")</code>.</li>
     * <li>If <code>maxWidth</code> is less than <code>4</code>, throw an <code>IllegalArgumentException</code>.</li>
     * <li>In no case will it return a String of length greater than <code>maxWidth</code>.</li>
     * </ul>
     * </p>
     * 
     * <pre>
     * StringUtils.abbreviate(null, *)      = null
     * StringUtils.abbreviate("", 4)        = ""
     * StringUtils.abbreviate("abcdefg", 6) = "abc..."
     * StringUtils.abbreviate("abcdefg", 7) = "abcdefg"
     * StringUtils.abbreviate("abcdefg", 8) = "abcdefg"
     * StringUtils.abbreviate("abcdefg", 4) = "a..."
     * StringUtils.abbreviate("abcdefg", 3) = IllegalArgumentException
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param maxWidth maximum length of result String, must be at least 4
     * @return abbreviated String, <code>null</code> if null String input
     * @throws IllegalArgumentException if the width is too small
     * @since 2.0
     */
    public static String abbreviate(String str, int maxWidth) {
        return abbreviate(str, 0, maxWidth);
    }

    /**
     * <p>
     * Abbreviates a String using ellipses. This will turn "Now is the time for all good men" into
     * "...is the time for..."
     * </p>
     * <p>
     * Works like <code>abbreviate(String, int)</code>, but allows you to specify a "left edge" offset. Note that this
     * left edge is not necessarily going to be the leftmost character in the result, or the first character following
     * the ellipses, but it will appear somewhere in the result.
     * <p>
     * In no case will it return a String of length greater than <code>maxWidth</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.abbreviate(null, *, *)                = null
     * StringUtils.abbreviate("", 0, 4)                  = ""
     * StringUtils.abbreviate("abcdefghijklmno", -1, 10) = "abcdefg..."
     * StringUtils.abbreviate("abcdefghijklmno", 0, 10)  = "abcdefg..."
     * StringUtils.abbreviate("abcdefghijklmno", 1, 10)  = "abcdefg..."
     * StringUtils.abbreviate("abcdefghijklmno", 4, 10)  = "abcdefg..."
     * StringUtils.abbreviate("abcdefghijklmno", 5, 10)  = "...fghi..."
     * StringUtils.abbreviate("abcdefghijklmno", 6, 10)  = "...ghij..."
     * StringUtils.abbreviate("abcdefghijklmno", 8, 10)  = "...ijklmno"
     * StringUtils.abbreviate("abcdefghijklmno", 10, 10) = "...ijklmno"
     * StringUtils.abbreviate("abcdefghijklmno", 12, 10) = "...ijklmno"
     * StringUtils.abbreviate("abcdefghij", 0, 3)        = IllegalArgumentException
     * StringUtils.abbreviate("abcdefghij", 5, 6)        = IllegalArgumentException
     * </pre>
     * 
     * @param str the String to check, may be null
     * @param offset left edge of source String
     * @param maxWidth maximum length of result String, must be at least 4
     * @return abbreviated String, <code>null</code> if null String input
     * @throws IllegalArgumentException if the width is too small
     * @since 2.0
     */
    public static String abbreviate(String str, int offset, int maxWidth) {
        if (str == null) {
            return null;
        }
        if (maxWidth < 4) {
            throw new IllegalArgumentException("Minimum abbreviation width is 4");
        }
        if (str.length() <= maxWidth) {
            return str;
        }
        if (offset > str.length()) {
            offset = str.length();
        }
        if ((str.length() - offset) < (maxWidth - 3)) {
            offset = str.length() - (maxWidth - 3);
        }
        if (offset <= 4) {
            return str.substring(0, maxWidth - 3) + "...";
        }
        if (maxWidth < 7) {
            throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
        }
        if ((offset + (maxWidth - 3)) < str.length()) {
            return "..." + abbreviate(str.substring(offset), maxWidth - 3);
        }
        return "..." + str.substring(str.length() - (maxWidth - 3));
    }

    // Difference
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Compares two Strings, and returns the portion where they differ. (More precisely, return the remainder of the
     * second String, starting from where it's different from the first.)
     * </p>
     * <p>
     * For example, <code>difference("i am a machine", "i am a robot") -> "robot"</code>.
     * </p>
     * 
     * <pre>
     * StringUtils.difference(null, null) = null
     * StringUtils.difference("", "") = ""
     * StringUtils.difference("", "abc") = "abc"
     * StringUtils.difference("abc", "") = ""
     * StringUtils.difference("abc", "abc") = ""
     * StringUtils.difference("ab", "abxyz") = "xyz"
     * StringUtils.difference("abcde", "abxyz") = "xyz"
     * StringUtils.difference("abcde", "xyz") = "xyz"
     * </pre>
     * 
     * @param str1 the first String, may be null
     * @param str2 the second String, may be null
     * @return the portion of str2 where it differs from str1; returns the empty String if they are equal
     * @since 2.0
     */
    public static String difference(String str1, String str2) {
        if (str1 == null) {
            return str2;
        }
        if (str2 == null) {
            return str1;
        }
        int at = indexOfDifference(str1, str2);
        if (at == -1) {
            return EMPTY;
        }
        return str2.substring(at);
    }

    /**
     * <p>
     * Compares two Strings, and returns the index at which the Strings begin to differ.
     * </p>
     * <p>
     * For example, <code>indexOfDifference("i am a machine", "i am a robot") -> 7</code>
     * </p>
     * 
     * <pre>
     * StringUtils.indexOfDifference(null, null) = -1
     * StringUtils.indexOfDifference("", "") = -1
     * StringUtils.indexOfDifference("", "abc") = 0
     * StringUtils.indexOfDifference("abc", "") = 0
     * StringUtils.indexOfDifference("abc", "abc") = -1
     * StringUtils.indexOfDifference("ab", "abxyz") = 2
     * StringUtils.indexOfDifference("abcde", "abxyz") = 2
     * StringUtils.indexOfDifference("abcde", "xyz") = 0
     * </pre>
     * 
     * @param str1 the first String, may be null
     * @param str2 the second String, may be null
     * @return the index where str2 and str1 begin to differ; -1 if they are equal
     * @since 2.0
     */
    public static int indexOfDifference(String str1, String str2) {
        if (str1 == str2) {
            return -1;
        }
        if (str1 == null || str2 == null) {
            return 0;
        }
        int i;
        for (i = 0; i < str1.length() && i < str2.length(); ++i) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
        }
        if (i < str2.length() || i < str1.length()) {
            return i;
        }
        return -1;
    }

    // Misc
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Find the Levenshtein distance between two Strings.
     * </p>
     * <p>
     * This is the number of changes needed to change one String into another, where each change is a single character
     * modification (deletion, insertion or substitution).
     * </p>
     * <p>
     * This implementation of the Levenshtein distance algorithm is from <a
     * href="http://www.merriampark.com/ld.htm">http://www.merriampark.com/ld.htm</a>
     * </p>
     * 
     * <pre>
     * StringUtils.getLevenshteinDistance(null, *)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance(*, null)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance("","")               = 0
     * StringUtils.getLevenshteinDistance("","a")              = 1
     * StringUtils.getLevenshteinDistance("aaapppp", "")       = 7
     * StringUtils.getLevenshteinDistance("frog", "fog")       = 1
     * StringUtils.getLevenshteinDistance("fly", "ant")        = 3
     * StringUtils.getLevenshteinDistance("elephant", "hippo") = 7
     * StringUtils.getLevenshteinDistance("hippo", "elephant") = 7
     * StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz") = 8
     * StringUtils.getLevenshteinDistance("hello", "hallo")    = 1
     * </pre>
     * 
     * @param s the first String, must not be null
     * @param t the second String, must not be null
     * @return result distance
     * @throws IllegalArgumentException if either String input <code>null</code>
     */
    public static int getLevenshteinDistance(String s, String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        int d[][]; // matrix
        int n; // length of s
        int m; // length of t
        int i; // iterates through s
        int j; // iterates through t
        char s_i; // ith character of s
        char t_j; // jth character of t
        int cost; // cost

        // Step 1
        n = s.length();
        m = t.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];

        // Step 2
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // Step 3
        for (i = 1; i <= n; i++) {
            s_i = s.charAt(i - 1);

            // Step 4
            for (j = 1; j <= m; j++) {
                t_j = t.charAt(j - 1);

                // Step 5
                if (s_i == t_j) {
                    cost = 0;
                } else {
                    cost = 1;
                }

                // Step 6
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
            }
        }

        // Step 7
        return d[n][m];
    }

    /**
     * <p>
     * Gets the minimum of three <code>int</code> values.
     * </p>
     * 
     * @param a value 1
     * @param b value 2
     * @param c value 3
     * @return the smallest of the values
     */
    private static int min(int a, int b, int c) {
        // Method copied from NumberUtils to avoid dependency on subpackage
        if (b < a) {
            a = b;
        }
        if (c < a) {
            a = c;
        }
        return a;
    }

    /**
     * Returns everything after the first occurrence of the given character in s.
     * 
     * @param s The string
     * @param c The character
     * @return Everything after the first occurrence of the given character in s. If the character cannot be found, an
     * empty string is returned.
     */
    public static String afterFirst(final String s, final char c) {
        if (s == null) {
            return null;
        }
        final int index = s.indexOf(c);

        if (index == -1) {
            return "";
        }

        return s.substring(index + 1);
    }

    /**
     * Gets everything after the first path component of a path using a given separator. If the separator cannot be
     * found, an empty String is returned.
     * <p>
     * For example, afterFirstPathComponent("foo:bar:baz", ':') would return "bar:baz" and
     * afterFirstPathComponent("foo", ':') would return "".
     * 
     * @param path The path to parse
     * @param separator The path separator character
     * @return Everything after the first component in the path
     */
    public static String afterFirstPathComponent(final String path, final char separator) {
        return afterFirst(path, separator);
    }

    /**
     * Returns everything after the last occurrence of the given character in s.
     * 
     * @param s The string
     * @param c The character
     * @return Everything after the last occurrence of the given character in s. If the character cannot be found, an
     * empty string is returned.
     */
    public static String afterLast(final String s, final char c) {
        if (s == null) {
            return null;
        }
        final int index = s.lastIndexOf(c);

        if (index == -1) {
            return "";
        }

        return s.substring(index + 1);
    }

    /**
     * Returns everything before the first occurrence of the given character in s.
     * 
     * @param s The string
     * @param c The character
     * @return Everything before the first occurrence of the given character in s. If the character cannot be found, an
     * empty string is returned.
     */
    public static String beforeFirst(final String s, final char c) {
        if (s == null) {
            return null;
        }
        final int index = s.indexOf(c);

        if (index == -1) {
            return "";
        }

        return s.substring(0, index);
    }

    /**
     * Returns everything before the last occurrence of the given character in s.
     * 
     * @param s The string
     * @param c The character
     * @return Everything before the last occurrence of the given character in s. If the character cannot be found, an
     * empty string is returned.
     */
    public static String beforeLast(final String s, final char c) {
        if (s == null) {
            return null;
        }
        final int index = s.lastIndexOf(c);

        if (index == -1) {
            return "";
        }

        return s.substring(0, index);
    }

    /**
     * Gets everything before the last path component of a path using a given separator. If the separator cannot be
     * found, the path itself is returned.
     * <p>
     * For example, beforeLastPathComponent("foo.bar.baz", '.') would return "foo.bar" and
     * beforeLastPathComponent("foo", '.') would return "".
     * 
     * @param path The path to parse
     * @param separator The path separator character
     * @return Everything before the last component in the path
     */
    public static String beforeLastPathComponent(final String path, final char separator) {
        return beforeLast(path, separator);
    }

    /**
     * Capitalizes a string.
     * 
     * @param s The string
     * @return The capitalized string
     */
    public static String capitalize(final String s) {
        if (s == null) {
            return null;
        }
        final char[] chars = s.toCharArray();

        if (chars.length > 0) {
            chars[0] = Character.toUpperCase(chars[0]);
        }

        return new String(chars);
    }

    /**
     * Converts a Java String to an HTML markup string, but does not convert normal spaces to non-breaking space
     * entities (&lt;nbsp&gt;).
     * 
     * @param s The characters to escape
     * @see StringUtils#escapeMarkup(CharSequence, boolean)
     * @return The escaped string
     */
    public static CharSequence escapeMarkup(final CharSequence s) {
        return escapeMarkup(s, false);
    }

    /**
     * Converts a Java String to an HTML markup String by replacing illegal characters with HTML entities where
     * appropriate. Spaces are converted to non-breaking spaces (&lt;nbsp&gt;) if escapeSpaces is true, tabs are
     * converted to four non-breaking spaces, less than signs are converted to &amp;lt; entities and greater than signs
     * to &amp;gt; entities.
     * 
     * @param s The characters to escape
     * @param escapeSpaces True to replace ' ' with nonbreaking space
     * @return The escaped string
     */
    public static CharSequence escapeMarkup(final CharSequence s, final boolean escapeSpaces) {
        return escapeMarkup(s, escapeSpaces, false);
    }

    /**
     * Converts a Java String to an HTML markup String by replacing illegal characters with HTML entities where
     * appropriate. Spaces are converted to non-breaking spaces (&lt;nbsp&gt;) if escapeSpaces is true, tabs are
     * converted to four non-breaking spaces, less than signs are converted to &amp;lt; entities and greater than signs
     * to &amp;gt; entities.
     * 
     * @param s The characters to escape
     * @param escapeSpaces True to replace ' ' with nonbreaking space
     * @param convertToHtmlUnicodeEscapes True to convert non-7 bit characters to unicode HTML (&#...)
     * @return The escaped string
     */
    public static CharSequence escapeMarkup(final CharSequence s, final boolean escapeSpaces,
                                            final boolean convertToHtmlUnicodeEscapes) {
        if (s == null) {
            return null;
        }

        int len = s.length();
        final StringBuffer buffer = new StringBuffer((int) (len * 1.1));

        for (int i = 0; i < len; i++) {
            final char c = s.charAt(i);

            switch (c) {
                case '\t':
                    if (escapeSpaces) {
                        // Assumption is four space tabs (sorry, but that's
                        // just how it is!)
                        buffer.append("&nbsp;&nbsp;&nbsp;&nbsp;");
                    } else {
                        buffer.append(c);
                    }
                    break;

                case ' ':
                    if (escapeSpaces) {
                        buffer.append("&nbsp;");
                    } else {
                        buffer.append(c);
                    }
                    break;

                case '<':
                    buffer.append("&lt;");
                    break;

                case '>':
                    buffer.append("&gt;");
                    break;

                case '&':

                    buffer.append("&amp;");
                    break;

                case '"':
                    buffer.append("&quot;");
                    break;

                case '\'':
                    buffer.append("&#039;");
                    break;

                default:

                    int ci = 0xffff & c;

                    if (
                    // if this is non-printable and not whitespace (TAB, LF, CR)
                    ((ci < 32) && (ci != 9) && (ci != 10) && (ci != 13)) ||
                    // or non-ASCII (XXX: why 160+ ?!) and need to UNICODE escape it
                            (convertToHtmlUnicodeEscapes && (ci > 159))) {
                        buffer.append("&#");
                        buffer.append(Integer.toString(ci));
                        buffer.append(';');
                    } else {
                        // ASCII or whitespace
                        buffer.append(c);
                    }
                    break;
            }
        }

        return buffer;
    }

    /**
     * Gets the first path component of a path using a given separator. If the separator cannot be found, the path
     * itself is returned.
     * <p>
     * For example, firstPathComponent("foo.bar", '.') would return "foo" and firstPathComponent("foo", '.') would
     * return "foo".
     * 
     * @param path The path to parse
     * @param separator The path separator character
     * @return The first component in the path or path itself if no separator characters exist.
     */
    public static String firstPathComponent(final String path, final char separator) {
        if (path == null) {
            return null;
        }
        final int index = path.indexOf(separator);

        if (index == -1) {
            return path;
        }

        return path.substring(0, index);
    }

    /**
     * Converts encoded &#92;uxxxx to unicode chars and changes special saved chars to their original forms.
     * 
     * @param escapedUnicodeString escaped unicode string, like '\u4F60\u597D'.
     * @return The actual unicode. Can be used for instance with message bundles
     */
    public static String fromEscapedUnicode(final String escapedUnicodeString) {
        int off = 0;
        char[] in = escapedUnicodeString.toCharArray();
        int len = in.length;
        char[] out = new char[len];
        char aChar;
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[off++];
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    out[outLen++] = aChar;
                }
            } else {
                out[outLen++] = aChar;
            }
        }
        return new String(out, 0, outLen);
    }

    /**
     * Checks whether the <code>string</code> is considered empty. Empty means that the string may contain whitespace,
     * but no visible characters. "\n\t " is considered empty, while " a" is not.
     * 
     * @param string The string
     * @return True if the string is null or ""
     */
    public static boolean isEmpty(final CharSequence string) {
        return (string == null) || (string.length() == 0) || (string.toString().trim().length() == 0);
    }

    /**
     * Checks whether two strings are equals taken care of 'null' values and treating 'null' same as
     * trim(string).equals("")
     * 
     * @param string1
     * @param string2
     * @return true, if both strings are equal
     */
    public static boolean isEqual(final String string1, final String string2) {
        if ((string1 == null) && (string2 == null)) {
            return true;
        }

        if (isEmpty(string1) && isEmpty(string2)) {
            return true;
        }
        if ((string1 == null) || (string2 == null)) {
            return false;
        }

        return string1.equals(string2);
    }

    /**
     * Joins string fragments using the specified separator
     * 
     * @param separator
     * @param fragments
     * @return combined fragments
     */
    public static String join(final String separator, final List fragments) {
        if (fragments == null) {
            return "";
        }
        return join(separator, (String[]) fragments.toArray(new String[fragments.size()]));
    }

    /**
     * Joins string fragments using the specified separator
     * 
     * @param separator
     * @param fragments
     * @return combined fragments
     */
    public static String join(final String separator, final String[] fragments) {
        if ((fragments == null) || (fragments.length < 1)) {
            // no elements
            return "";
        } else if (fragments.length < 2) {
            // single element
            return fragments[0];
        } else {
            // two or more elements
            StringBuffer buff = new StringBuffer(128);
            if (fragments[0] != null) {
                buff.append(fragments[0]);
            }
            for (int i = 1; i < fragments.length; i++) {
                String fragment = fragments[i];
                if ((fragments[i - 1] != null) || (fragment != null)) {
                    boolean lhsClosed = fragments[i - 1].endsWith(separator);
                    boolean rhsClosed = fragment.startsWith(separator);
                    if (lhsClosed && rhsClosed) {
                        buff.append(fragment.substring(1));
                    } else if (!lhsClosed && !rhsClosed) {
                        if (!StringUtils.isEmpty(fragment)) {
                            buff.append(separator);
                        }
                        buff.append(fragment);
                    } else {
                        buff.append(fragment);
                    }
                }
            }
            return buff.toString();
        }
    }

    /**
     * Gets the last path component of a path using a given separator. If the separator cannot be found, the path itself
     * is returned.
     * <p>
     * For example, lastPathComponent("foo.bar", '.') would return "bar" and lastPathComponent("foo", '.') would return
     * "foo".
     * 
     * @param path The path to parse
     * @param separator The path separator character
     * @return The last component in the path or path itself if no separator characters exist.
     */
    public static String lastPathComponent(final String path, final char separator) {
        if (path == null) {
            return null;
        }

        final int index = path.lastIndexOf(separator);

        if (index == -1) {
            return path;
        }

        return path.substring(index + 1);
    }

    /**
     * Replace all occurrences of one string replaceWith another string.
     * 
     * @param s The string to process
     * @param searchFor The value to search for
     * @param replaceWith The value to searchFor replaceWith
     * @return The resulting string with searchFor replaced with replaceWith
     */
    public static CharSequence replaceAll(final CharSequence s, final CharSequence searchFor, CharSequence replaceWith) {
        if (s == null) {
            return null;
        }

        // If searchFor is null or the empty string, then there is nothing to
        // replace, so returning s is the only option here.
        if ((searchFor == null) || "".equals(searchFor)) {
            return s;
        }

        // If replaceWith is null, then the searchFor should be replaced with
        // nothing, which can be seen as the empty string.
        if (replaceWith == null) {
            replaceWith = "";
        }

        String searchString = searchFor.toString();
        // Look for first occurrence of searchFor
        int matchIndex = search(s, searchString, 0);
        if (matchIndex == -1) {
            // No replace operation needs to happen
            return s;
        } else {
            // Allocate a StringBuffer that will hold one replacement
            // with a
            // little extra room.
            int size = s.length();
            final int replaceWithLength = replaceWith.length();
            final int searchForLength = searchFor.length();
            if (replaceWithLength > searchForLength) {
                size += (replaceWithLength - searchForLength);
            }
            final StringBuffer buffer = new StringBuffer(size + 16);

            int pos = 0;
            do {
                // Append text up to the match
                append(buffer, s, pos, matchIndex);

                // Add replaceWith text
                buffer.append(replaceWith);

                // Find next occurrence, if any
                pos = matchIndex + searchForLength;
                matchIndex = search(s, searchString, pos);
            } while (matchIndex != -1);

            // Add tail of s
            buffer.append(s.subSequence(pos, s.length()));

            // Return processed buffer
            return buffer;
        }
    }

    /**
     * Replace HTML numbers like &#20540 by the appropriate character.
     * 
     * @param str The text to be evaluated
     * @return The text with "numbers" replaced
     */
    public static String replaceHtmlEscapeNumber(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = HTML_NUMBER_REGEX.matcher(str);
        while (matcher.find()) {
            int pos = matcher.start();
            int end = matcher.end();
            int number = Integer.parseInt(str.substring(pos + 2, end - 1));
            char ch = (char) number;
            str = str.substring(0, pos) + ch + str.substring(end);
            matcher = HTML_NUMBER_REGEX.matcher(str);
        }

        return str;
    }

    /**
     * Simpler, faster version of String.split() for splitting on a simple character.
     * 
     * @param s The string to split
     * @param c The character to split on
     * @return The array of strings
     */
    public static String[] split(final String s, final char c) {
        if (s == null || s.length() == 0) {
            return NO_STRINGS;
        }
        final List strings = new ArrayList();
        int pos = 0;
        while (true) {
            int next = s.indexOf(c, pos);
            if (next == -1) {
                strings.add(s.substring(pos));
                break;
            } else {
                strings.add(s.substring(pos, next));
            }
            pos = next + 1;
        }
        final String[] result = new String[strings.size()];
        strings.toArray(result);
        return result;
    }

    /**
     * Strips the ending from the string <code>s</code>.
     * 
     * @param s The string to strip
     * @param ending The ending to strip off
     * @return The stripped string or the original string if the ending did not exist
     */
    public static String stripEnding(final String s, final String ending) {
        if (s == null) {
            return null;
        }

        // Stripping a null or empty string from the end returns the
        // original string.
        if ((ending == null) || "".equals(ending)) {
            return s;
        }
        final int endingLength = ending.length();
        final int sLength = s.length();

        // When the length of the ending string is larger
        // than the original string, the original string is returned.
        if (endingLength > sLength) {
            return s;
        }
        final int index = s.lastIndexOf(ending);
        final int endpos = sLength - endingLength;

        if (index == endpos) {
            return s.substring(0, endpos);
        }

        return s;
    }

    /**
     * Converts the given object to a string. Does special conversion for {@link Throwable throwables} and String arrays
     * of length 1 (in which case it just returns to string in that array, as this is a common thing to have in the
     * Servlet API).
     * 
     * @param object The object
     * @return The string
     */
    public static String toString(final Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof Throwable) {
            return toString((Throwable) object);
        }

        if (object instanceof String) {
            return (String) object;
        }

        if ((object instanceof String[]) && (((String[]) object).length == 1)) {
            return ((String[]) object)[0];
        }

        return object.toString();
    }

    /**
     * Converts a Throwable to a string.
     * 
     * @param throwable The throwable
     * @return The string
     */
    public static String toString(final Throwable throwable) {
        if (throwable != null) {

            String separator = "Exception:";
            String expetionMsg = throwable.getMessage();
            separator = "null";
            if (expetionMsg.indexOf(separator) > -1) {
                expetionMsg = "数据字段空异常,操作无法进行,请重试.";
            }
            return expetionMsg;
        } else {
            return "<Null Throwable>";
        }
    }

    private static void append(final StringBuffer buffer, final CharSequence s, final int from, final int to) {
        if (s instanceof StringBuffer) {
            StringBuffer asb = (StringBuffer) s;
            buffer.append(asb.toString().toCharArray(), from, to - from);
        } else {
            buffer.append(s.subSequence(from, to));
        }
    }

    private static int search(final CharSequence s, final String searchString, final int pos) {
        if (s instanceof String) {
            return ((String) s).indexOf(searchString, pos);
        } else if (s instanceof StringBuffer) {
            return ((StringBuffer) s).indexOf(searchString, pos);
        } else if (s instanceof StringBuffer) {
            return ((StringBuffer) s).indexOf(searchString, pos);
        } else {
            return s.toString().indexOf(searchString, pos);
        }
    }

    /**
     * Convert a nibble to a hex character
     * 
     * @param nibble the nibble to convert.
     * @return hex character
     */
    private static char toHex(final int nibble) {
        return HEX_DIGIT[(nibble & 0xF)];
    }

    /**
     * Calculates the length of string in bytes, uses specified <code>charset</code> if provided.
     * 
     * @param string
     * @param charset (optional) character set to use when converting string to bytes
     * @return length of string in bytes
     */
    public static int lengthInBytes(final String string, final Charset charset) {
        if (string == null) {
            throw new NullPointerException("Argument `string` cannot be null");
        }
        if (charset != null) {
            try {
                return string.getBytes(charset.name()).length;
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("StringResourceStream created with unsupported charset: " + charset.name());
            }
        } else {
            return string.getBytes().length;
        }
    }

    /**
     * Extended {@link String#startsWith(String)} with support for case sensitivity
     * 
     * @param str
     * @param prefix
     * @param caseSensitive
     * @return <code>true</code> if <code>str</code> starts with <code>prefix</code>
     */
    public static boolean startsWith(final String str, final String prefix, final boolean caseSensitive) {
        if (caseSensitive) {
            return str.startsWith(prefix);
        } else {
            return str.toLowerCase().startsWith(prefix.toLowerCase());
        }
    }

    /**
     * returns the zero-based index of a character within a char sequence. this method mainly exists as an faster
     * alternative for <code>sequence.toString().indexOf(ch)</code>.
     * 
     * @param sequence character sequence
     * @param ch character to search for
     * @return index of character within character sequence or <code>-1</code> if not found
     */
    public static int indexOf(final CharSequence sequence, final char ch) {
        if (sequence != null) {
            for (int i = 0; i < sequence.length(); i++) {
                if (sequence.charAt(i) == ch) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * <p>
     * Find the Levenshtein distance between two Strings.
     * </p>
     * <p>
     * This is the number of changes needed to change one String into another, where each change is a single character
     * modification (deletion, insertion or substitution).
     * </p>
     * <p>
     * The previous implementation of the Levenshtein distance algorithm was from <a
     * href="http://www.merriampark.com/ld.htm">http://www.merriampark.com/ld.htm</a>
     * </p>
     * <p>
     * Chas Emerick has written an implementation in Java, which avoids an OutOfMemoryError which can occur when my Java
     * implementation is used with very large strings.<br>
     * This implementation of the Levenshtein distance algorithm is from <a
     * href="http://www.merriampark.com/ldjava.htm">http://www.merriampark.com/ldjava.htm</a>
     * </p>
     * 
     * <pre>
     * StringUtils.getLevenshteinDistance(null, *)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance(*, null)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance("","")               = 0
     * StringUtils.getLevenshteinDistance("","a")              = 1
     * StringUtils.getLevenshteinDistance("aaapppp", "")       = 7
     * StringUtils.getLevenshteinDistance("frog", "fog")       = 1
     * StringUtils.getLevenshteinDistance("fly", "ant")        = 3
     * StringUtils.getLevenshteinDistance("elephant", "hippo") = 7
     * StringUtils.getLevenshteinDistance("hippo", "elephant") = 7
     * StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz") = 8
     * StringUtils.getLevenshteinDistance("hello", "hallo")    = 1
     * </pre>
     * 
     * Copied from Apache commons-lang StringUtils 3.0
     * 
     * @param s the first String, must not be null
     * @param t the second String, must not be null
     * @return result distance
     * @throws IllegalArgumentException if either String input {@code null}
     */
    public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        /*
         * The difference between this impl. and the previous is that, rather than creating and retaining a matrix of
         * size s.length()+1 by t.length()+1, we maintain two single-dimensional arrays of length s.length()+1. The
         * first, d, is the 'current working' distance array that maintains the newest distance cost counts as we
         * iterate through the characters of String s. Each time we increment the index of String t we are comparing, d
         * is copied to p, the second int[]. Doing so allows us to retain the previous cost counts as required by the
         * algorithm (taking the minimum of the cost count to the left, up one, and diagonally up and to the left of the
         * current cost count being calculated). (Note that the arrays aren't really copied anymore, just
         * switched...this is clearly much better than cloning an array or doing a System.arraycopy() each time through
         * the outer loop.) Effectively, the difference between the two implementations is this one does not cause an
         * out of memory condition when calculating the LD over two very large strings.
         */

        int n = s.length(); // length of s
        int m = t.length(); // length of t

        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }

        if (n > m) {
            // swap the input strings to consume less memory
            CharSequence tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }

        int p[] = new int[n + 1]; // 'previous' cost array, horizontally
        int d[] = new int[n + 1]; // cost array, horizontally
        int _d[]; // placeholder to assist in swapping p and d

        // indexes into strings s and t
        int i; // iterates through s
        int j; // iterates through t

        char t_j; // jth character of t

        int cost; // cost

        for (i = 0; i <= n; i++) {
            p[i] = i;
        }

        for (j = 1; j <= m; j++) {
            t_j = t.charAt(j - 1);
            d[0] = j;

            for (i = 1; i <= n; i++) {
                cost = s.charAt(i - 1) == t_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }

            // copy current distance counts to 'previous row' distance counts
            _d = p;
            p = d;
            d = _d;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }

    /**
     * convert byte array to hex string
     * 
     * @param bytes bytes to convert to hexadecimal representation
     * @return hex string
     */
    public static String toHexString(byte[] bytes) {
        final StringBuffer hex = new StringBuffer(bytes.length << 1);

        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            hex.append(toHex(b >> 4));
            hex.append(toHex(b));
        }
        return hex.toString();
    }

    /**
     * @param str
     * @return
     */
    public static String trimToUpperCase(String str) {
        return StringUtils.isBlank(str) ? null : trim(str).toUpperCase();
    }

    public static String convertObjectToStr(Object object) {
        if (object == null) {
            return WRITE_SPACE;
        } else if (object instanceof Number) {
            return object + "";
        } else {
            return object + "";
        }
    }

    public static boolean isNotEmptyTrim(String str) {
        return (str != null && str.trim().length() > 0);
    }

    public static Collection splitAsList(String str, String separatorChars) {
        return Arrays.asList(split(str, separatorChars, -1));
    }

    public static String Replace(String strReplaced, String oldStr, String newStr) {
        int pos = 0;
        int findPos;
        while ((findPos = strReplaced.indexOf(oldStr, pos)) != -1) {
            strReplaced = strReplaced.substring(0, findPos) + newStr + strReplaced.substring(findPos + oldStr.length());
            findPos += newStr.length();
        }
        return strReplaced;
    }

    /*
     * public static String Replace(String str) { String regex = "~!@#$%^&()"; Pattern p = Pattern.compile(regex);
     * Matcher m = p.matcher(str); // Replace(regex, "*", ".+");  }
     */
    /***
     * 如果是空，则返回""
     * 
     * @param str
     * @return
     */
    public static String nullToStr(String str)
    {
        return  str!=null?str:"";
        
    }

    public static String strToNull(String str) {
        if (isEmpty(str) || str.equalsIgnoreCase("N/A")) {
            return null;
        }
        return str;
    }

    public static String[] mergeStringArraysForNotCheckContains(String[] array1, String[] array2) {
        if (ObjectUtils.isEmpty(array1)) {
            return array2;
        }
        if (ObjectUtils.isEmpty(array2)) {
            return array1;
        }
        List result = new ArrayList();
        result.addAll(Arrays.asList(array1));
        for (int i = 0; i < array2.length; i++) {
            result.add(array2[i]);
        }
        return toStringArray(result);
    }

    public static String[] getInfoBySymbol(String str, Character startSymbol, Character endSymbol) {

        List<String> result = new ArrayList<String>();
        char[] chs = str.toCharArray();

        String info = "";
        boolean boo = false;
        for (int i = 0; i < chs.length; i++) {
            if (startSymbol.equals(chs[i])) {
                boo = true;
                continue;
            }
            if (endSymbol.equals(chs[i])) {
                boo = false;
                result.add(info);
                info = "";
            }
            if (boo) {
                info += chs[i];
            }
        }

        return toStringArray(result);
    }

    // 去掉字符串str中 最后的指定字符 deleteChars
    public static String removeLastChar(String str, String deleteChars) {
        if (str.length() > 0 && (str.lastIndexOf(deleteChars) == str.length() - 1)) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public static String encodeByMD5(String str) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error in encode by MD5：" + e);
        }

        byte[] encodedBytes = md.digest(str.getBytes());
        StringBuilder strBuilder = new StringBuilder();
        for (byte each : encodedBytes) {
            int temp = each & 255;
            if (temp < 16 && temp >= 0) {
                strBuilder.append('0').append(Integer.toHexString(temp));
            } else {
                strBuilder.append(Integer.toHexString(temp));
            }
        }
        return strBuilder.toString();
    }

    /**
     * 将字符串前后加上 % 以支持模糊查询 带*则只将*替换成%
     * 
     * @param condition
     * @return
     */
    public static String conditionSqlToLike(String condition) {
        condition = condition.toUpperCase();
        if (condition.contains("*")) {
            // 带有*号
            condition = condition.replace("*", "%");

        }
        condition = "%" + condition + "%";

        return condition;
    }
    
    public static String dbReplace(String str){
    	if (isNotBlank(str)) {
    		str = str.replaceAll("&lt","<");
            str = str.replaceAll("&gt",">");
    	}
        
        return str;
    }
    
    public static String htmlReplace(String str){
    	if (isNotBlank(str)) {
	        str = str.replaceAll("<", "&lt");
	        str = str.replaceAll(">", "&gt");
    	}
    	
        return str;
    }

    public static List<String> combine(String[] a, int num) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        String placeholder1 = "1";
        String placeholder0 = "0";
        String[] b = new String[a.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = i < num ? placeholder1 : placeholder0;
        }

        int point = 0;
        int nextPoint = 0;
        int count = 0;
        int sum = 0;
        String temp;
        while (true) {
            for (int i = b.length - 1; i >= b.length - num; i--) {
                if (placeholder1.equals(b[i])) {
                    sum += 1;
                }
            }

            for (int i = 0; i < b.length; i++) {
                if (placeholder1.equals(b[i])) {
                    point = i;
                    sb.append(a[point]);
                    count++;
                    if (count == num) {
                        break;
                    }
                }
            }

            list.add(sb.toString());

            if (sum == num) {
                break;
            }
            sum = 0;

            for (int i = 0; i < b.length - 1; i++) {
                if (placeholder1.equals(b[i]) && placeholder0.equals(b[i + 1])) {
                    point = i;
                    nextPoint = i + 1;
                    b[point] = placeholder0;
                    b[nextPoint] = placeholder1;
                    break;
                }
            }

            for (int i = 0; i < point - 1; i++) {
                for (int j = i; j < point - 1; j++) {
                    if (placeholder0.equals(b[i])) {
                        temp = b[i];
                        b[i] = b[j + 1];
                        b[j + 1] = temp;
                    }
                }
            }

            sb.setLength(0);
            count = 0;
        }

        return list;
    }

    public static List<String> combinationStrings(String[] strings) {
        Set<String> set = new LinkedHashSet<>();
        for (int i = 1; i <= strings.length; i++) {
            set.addAll(combine(strings, i));
        }
        List<String> result = new ArrayList<>();
        result.addAll(set);
        Collections.sort(result);
        return result;
    }

}
