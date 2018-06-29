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
            //update first : we have to get current interval first, 
            //then compare with char interval, cause map maintain last appear position of char
            //so current char interval end in i 
            end = Math.max(end, map[ch[i]-'a']);
            if(end == i){
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        //end for last element must be the length - 1 of string, so last char will be definitly updated
        return res;
    }
}