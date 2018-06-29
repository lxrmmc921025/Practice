class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        
        //--> Arrays.asList(banned)
        for (String b : banned) {
            String c = convert(b);
            set.add(c);
        }
        
        String[] list = paragraph.split("[ !?',;.]+");
        
        for (String s : list) {
            String c = convert(s);
            if (!set.contains(c)) {
                //--> map.put(s, map.getOrDefault(s, 0) + 1);
                if (!map.containsKey(c)) {
                    map.put(c, 0);
                }
                map.put(c, map.get(c) + 1);
            }
        }
        
        int max = 0;
        String word = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                word = entry.getKey();
            }  
        }
        
        return word;
    }
    
    //--> toLowerCase()
    private String convert(String s) {
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] - 'a' < 0 || ch[i] - 'a' >=26) {
                ch[i] = (char)(ch[i] - 'A' + 'a');
            }
        }
        return new String(ch);
    }
}