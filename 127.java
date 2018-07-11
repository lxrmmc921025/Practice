/*
    word ladeer : BFS2 
    M1 : if list is short, check list one by one
    M2 : if word is short, check 26 letter one by one(Time exceed)
*/
class Solution {
    //M1
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        Queue<String> que = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        que.offer(beginWord);
        visited.add(beginWord);
        
        int len = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                String tmp = que.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    String cur = wordList.get(j);
                    if (!visited.contains(cur) && match(tmp, cur)) {
                        if (cur.equals(endWord)) {
                            return len + 1;
                        }
                        que.offer(cur);
                        visited.add(cur);
                    }
                }
            }
            len++;
        }
        return 0;
    }

    private boolean match(String str1, String str2) {
        int diff = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                diff++;
                if (diff > 1) return false;
            }
        }
        return true;
    }

    //M2
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Queue<String> que = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        que.offer(beginWord);
        visited.add(beginWord);
        
        int len = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                char[] tmp = que.poll().toCharArray();
                for (int j = 0; j < tmp.length; j++) {
                    char c = tmp[j];
                    for (int k = 0; k < 26; k++) {
                        tmp[j] = (char)('a' + k);
                        String str = new String(tmp);
                        if (!visited.contains(str) && wordList.contains(str)) {
                            if (str.equals(endWord)) {
                                return len + 1;
                            }
                            que.offer(str);
                            visited.add(str);
                        }
                    }
                    tmp[j] = c;
                }
            }
            len++;
        }
        return 0;
    }
}