class Solution {
    /*
        partition character into interval [start, end],
        for current interval [start, end], char interal [ts, te] can overlap or not
        if not add length to res; if overlap, update interval end 
    */
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int[] map = new int[26];
        
        char[] ch = S.toCharArray();
        //map only maintain last pos of each char
        for (int i = 0; i < ch.length; i++) {
            map[ch[i] - 'a'] = i;
        }
        
        int start = 0, end = 0;
        for (int i = 0; i < ch.length; i++) {
            int ce = map[ch[i] - 'a'];
            
            if (ce == end) {
                res.add(end - start + 1);
                start = ce + 1;
                end = ce + 1;
            } else {
                end = Math.max(ce, end);
            }
        }
        //end for last element must be the length - 1 of string, so last char will be definitly updated
        return res;
    }
}