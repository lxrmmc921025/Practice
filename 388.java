class Solution {
    public int lengthLongestPath(String input) {
        //split into string array
        String[] str = input.split("\n");
        Deque<Integer> stack = new LinkedList<>();
        //return max - 1, therefore, initial should be 1
        int max = 1, len = 0;
        for (String s : str) {
            int level = 0;
            int i = 0;
            //int level = s.lastIndexOf("\t")+1;
            while (s.startsWith("\t", i)) {
                level++;
                i += 1;
            }
            while (stack.size() > level) {
                len -= (stack.pop() + 1);
            }
            int strlen = s.length() - i;
            len += strlen + 1;
            
            //only file can countlen, need check there
            if(s.indexOf(".") > 0){
                max = Math.max(max, len);
            }
            stack.push(strlen);
        }
        return max - 1;
    }
}