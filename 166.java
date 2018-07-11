public String fractionToDecimal(int numerator, int denominator) {
    //refer sol : https://leetcode.com/problems/fraction-to-recurring-decimal/discuss/51106/My-clean-Java-solution

    //maintain numerator for check repeating
    Map<Long, Integer> map = new HashMap<>();
    boolean sign = false;
    
    //sign
    //res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
    if ((numerator < 0 && denominator > 0) || (denominator < 0 && numerator > 0)) {
        sign = true;
    }

    //turn to long in case of num/den bigger than Integer
    long num = Math.abs((long)numerator);
    long den = Math.abs((long)denominator);
    
    //integral part
    long integer = num / den; 
    long decimal = num % den;
    if (decimal == 0) {
        return sign ? "-" + Long.toString(integer) : Long.toString(integer);
    }
    StringBuilder sb = new StringBuilder(Long.toString(integer) + ".");
    if (sign) sb.insert(0, '-');
    
    //fraction part
    while (decimal != 0) {
        if (map.containsKey(decimal)) {
            sb.insert((int)map.get(decimal), '(');
            sb.append(')');
            return sb.toString();
        }
        map.put(decimal, sb.length());
        // * 10 is necessary, or append will be 0
        sb.append(decimal * 10 / den);
        decimal = decimal * 10 % den;
    }
    return sb.toString();
}