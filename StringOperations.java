
public class StringOperations {
    public static boolean uniqueChars(String s) {
        if (s.length() > 256)
            return false;
        int checker = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = 'a' - c;
            int bitIndex = 1 << index;
            if ((checker & (bitIndex)) > 0)
                return false;
            checker |= (bitIndex);
        }
        return true;
    }
    
    public static boolean isPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        
        int[] charCount1 = new int[256];
        int[] charCount2 = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            charCount1[c-'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            charCount2[c-'a']++;
        }
        for (int i = 0; i < 256; i++) {
            if (charCount1[i] != charCount2[i])
                return false;
        }
        return true;
    }
    
    public static char[] convertSpaces (char[] str) {
        int spaceCount = 0;
        
        // First pass to find number of spaces and original string length
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        if (spaceCount*3 > str.length)
            return "no".toCharArray();
        int originalLength = str.length - (2*spaceCount);
        
        // Second pass for replacing
        int j = originalLength - 1;
        for (int i = str.length-1; i >= 0; i--) {
            if (str[j] == ' ') {
                str[i--] = '0';
                str[i--] = '2';
                str[i] = '%';
            }
            str[i] = str[j];
            j--;
        }
        return str;
    }
    
    public static String compressString(String str) {
        StringBuffer newStr = new StringBuffer();
        char current;
        int count;
        
        if (str.length() == 0) {
            return "no";
        } else {
            current = str.charAt(0);
            count = 1;
        }
        
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == current) {
                count++;
            }
            else {
                newStr.append(current);
                newStr.append(count);
                current = str.charAt(i);
                count = 1;
            }
        }
        newStr.append(current);
        newStr.append(count);
        
        String compressed = newStr.toString();
        if (compressed.length() < str.length())
            return compressed;
        else
            return str;
    }
    public static boolean isRotation(String s1, String s2) {
        String s2Twice = s2 + s2;
        if (s2Twice.contains(s1) && (s1.length() == s2.length()))
            return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(compressString("aaaabbcsdd"));
                           
    }
}