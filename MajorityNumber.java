/*
  Majority Number III
*/
public static List<Integer> majority(int[] array, int k) {
  List<Integer> res = new ArrayList<>();
  Map<Integer, Integer> map = new HashMap<>();

  for (int i = 0; i < array.length; i++) {
    if (!map.containsKey(array[i])) {
      if (map.size() >= k) {
        reduce(map);
      }
      map.put(array[i], 0);
    }
    map.put(array[i], map.get(array[i]) + 1);
  }

  // corner case
  if (map.size() == 0) {
    return res;
  }

  // setZero
  for (Integer i : map.keySet()) {
    map.put(i, 0);
  }

  // count Key
  for (Integer i : array) {
    if (map.containsKey(i)) {
      map.put(i, map.get(i) + 1);
    }
  }

  //
  for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    //find all numbers that occur more than 1/K * L, not exactly majority
    if (entry.getValue() * k > array.length) {
      res.add(entry.getKey());
    }
  }
  return res;
}

//hashmap iteration, if we delete <key, value> during forloop map.entryset,
//may occur "ConcurrentModificationException"
private static void reduce(Map<Integer, Integer> map) {
  for (Integer key : map.keySet()) {
    Integer freq = map.get(key);
    map.put(key, freq - 1);
  }
  Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
  while (iter.hasNext()) {
    Map.Entry<Integer, Integer> cur = iter.next();
    if (cur.getValue() == 0) {
      iter.remove();
    }
  }
}

private static void reduce2(Map<Integer, Integer> counters) {
  Set<Integer> keySet = counters.keySet();
  List<Integer> removeList = new ArrayList<>();
  for (Integer key : keySet) {
    counters.put(key, counters.get(key) - 1);
    if (counters.get(key) == 0) {
      removeList.add(key);
    }
  }
  for (Integer key : removeList) {
    counters.remove(key);
  }
}