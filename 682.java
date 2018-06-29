class Solution {
    public int calPoints(String[] ops) {
        Deque<Integer> stack =  new LinkedList<>();
        int num = 0, sum = 0;
        
        for (String s : ops) {
            char c = s.charAt(0);
            if (c == '+') {
                int pre = stack.poll();
                num = pre + stack.peek();
                stack.push(pre);
                stack.push(num);
            } else if (c == 'D') {
                num = 2 * stack.peek();
                stack.push(num);
            } else if (c == 'C') {
                num = 0 - stack.poll();
            } else {
                num = convert(s);
                stack.push(num);
            }
            sum += num;
        }

        /*
        //not valid for "C", "+" situation, because need prev data  
        int pre = 0, cur = 0;
        for (String s : ops) {
            char c = s.charAt(0);
            if (c == '+') {
                num = pre + cur;
            } else if (c == 'D') {
                num = 2 * cur;
            } else if (c == 'C') {
                num = 0 - cur;
                cur = pre;
            } else {
                num = convert(s);
            }
            if (c != 'C') {
                pre = cur;
                cur = num;
            }
            sum += num;
        }
        */
        
        return sum;
    }
    
    private static int convert(String s) {
        char[] ch = s.toCharArray();
        int num = 0;
        boolean negative = false;
        for (char c : ch) {
        //!!integer can be negative and larger than 10
            if (c == '-') {
        		negative = true;
        		continue;
        	}
            num = num * 10 + (c - '0');
        }
        return negative ? - num : num;
    }
}