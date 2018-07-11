/*
M1 : use lastIndexOf 
https://leetcode.com/problems/first-unique-character-in-a-string/discuss/86359/my-4-lines-Java-solution
M2 : Set(maintain visited elements) + Map(keep index, if repeated, remove)
https://leetcode.com/problems/first-unique-character-in-a-string/discuss/86511/Java-One-Pass-Solution-with-LinkedHashMap 
M3 : two pointer, one pass solution
fast : loop all elements, slow : maintain first unique
https://leetcode.com/problems/first-unique-character-in-a-string/discuss/86340/Java-two-pointers-(slow-and-fast)-solution-(18-ms)
*/
//Two pass solution
class Solution {
    public static int firstUniqChar(String s) {
        char[] ch = s.toCharArray();
        int[] map = new int[26];
        
        for (int i = 0; i < ch.length; i++) {
            int index = ch[i] - 'a';
            map[index] = i;
        }
        
        for (int i = 0; i < ch.length; i++) {
            int index = ch[i] - 'a';
            if (map[index] == i) {
            	return i;
            } else {
            	map[index] = -1;
            }
        }
        return -1;
    }
}

//One - Pass
class Solution {
    public static int firstUniqChar(String s) {
        int[] map = new int[26];
        int slow = 0, fast = 0;
        while (fast < s.length()) {
            int index = s.charAt(fast++) - 'a';
            map[index]++;
            while (slow < fast) {
                int res = s.charAt(slow) - 'a';
                if (map[res] > 1) {
                    slow++;
                } else {
                    break;
                }
            }
        }
        return slow < s.length() ? slow : -1;
    }
}