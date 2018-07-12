class RandomizedSet {
    Map<Integer, Integer> map;
    ArrayList<Integer> list;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        } 
        int pos = map.get(val);
        map.remove(val);
        list.set(pos, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        //if list is empty, not element to get
        if (pos < list.size()) {
        	//edit map when remove
            map.put(list.get(pos), pos);
        }
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        if (list.size() == 0) {
            return 0;
        }
        Random ran = new Random();
        int index =	ran.nextInt(list.size());
        return list.get(index);
    }
}