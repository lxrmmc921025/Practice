/*
  https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
  Boyer–Moore majority vote algorithm
*/

public List<Integer> majority(int[] array) {
  List<Integer> res = new ArrayList<>();
  
  //use count to judge status of candidates
  int first = 0, second = 0;
  int count1 = 0, count2 = 0; 
  
  for (int i = 0; i < array.length; i++) {
    //first judge eequal to candidate, then add becasue second may > 0, 
    //first = 0, judge count first, first may equal to second
    if (first == array[i]) {
    	count1++;
    } else if (second == array[i]) {
    	count2++;
    } else if (count1 == 0) {
    	first = array[i];
      count1++;
    } else if (count2 == 0) {
    	second = array[i];
      count2++;
    } else {
    	count1--;
      count2--;
    }
  }
  count1 = count2 = 0;
  for(int i : array) {
  	if (first == i) {
    	count1++;
    } 
    if (second == i) {
    	count2++;
    }
  }
  //1 / 3.0 instead of 1/3
  if (count1 > (1/3.0 * array.length)) res.add(first);
  if (count2 > (1/3.0 * array.length)) res.add(second);
  Collections.sort(res);
  return res;
}