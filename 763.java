class Solution {
    /*
        partition character into interval [start, end],
        for current interval [start, end], char interal [ts, te] can overlap or not
        if not add length to res; if overlap, update interval end 
    */
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int[][] map = new int[26][2];
        
        
        char[] ch = S.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            int[] pos = map[ch[i] - 'a'];
            if(pos[0] != -1) {
                pos[1] = i;
            } else {
                pos[0] = i;
                pos[1] = i;
            }
        }
        
        int start = 0, end = 0;
        for (int i = 0; i < ch.length; i++) {
            int ts = map[ch[i] - 'a'][0];
            int te = map[ch[i] - 'a'][1];
            
            if (te == end) {
                //same char with char interval [ts, te] will terminate this part e.g "a.._a_..a"
                if (i < end) {
                    continue;
                }
                res.add(end - start + 1);
                start = te + 1;
                end = te + 1;
            } else {
                end = Math.max(te, end);
            }
        }
        //if last part not added to list
        if (start != ch.length) {
            res.add(end - start + 1);
        }
        return res;
    }
}