public class HashTable <V> {

 private static final Object BRIDGE = new String("[BRIDGE]".toCharArray());
 private int size;
 private int capacity;
 private String[] keys;
 private V[] values;

 // Constructor
 public HashTable() {
  this.size = 0;
  this.capacity = 4;
  this.keys = new String[capacity];
  this.values = (V[]) new Object[capacity];
 }

 //methods
 public String toString() {
  StringBuilder sb = new StringBuilder();
  sb.append("printing the hash table ...\n");
  sb.append("==================\n");

  for (int i = 0; i < this.capacity; i++) {
   sb.append("index:\t" + i + ",\tkey:\t" + this.keys[i]);
   sb.append(",\tdata:\t" + this.values[i] + "\n");
  }
  return sb.toString();
 }

 public int getSize() {
  return this.size;
 }

 public int getCapacity() {
  return this.capacity;
 }

 public String[] getKeyArray() {
  return this.keys;
 }

 public V[] getDataArray() {
  return this.values;
 }

 public String[] getValidKeys() {
  String[] validKeys = new String[this.size];
  int index = 0;
  for (int i = 0; i < this.capacity; i++) {
   if (this.keys[i] != null && this.keys[i] != "" && this.values[i] != BRIDGE) {
    validKeys[index] = this.keys[i];
    index++;
   }
  }
  return validKeys;
 }

 public int getHashIndex(String k) {
  int hashValue = 0;
  for (int i = 0; i < k.length(); i++) {
   int letter = k.charAt(i) - 96;
   hashValue += (hashValue * 27 + letter);
  }
  return hashValue % this.getCapacity();
 }

 public V lookup(String k) throws NullPointerException{
  if (k == null) {
   throw new NullPointerException("lookup(String key): key is null");
  }

  int kindex = getHashIndex(k);
  int startindex = getHashIndex(k);

  if (k.equals(keys[kindex])) {
   return values[kindex];
  }
  if (keys[kindex] == null) {
   return null;
  }

  kindex = (kindex +1) % this.getCapacity();

  while (kindex != startindex) {
   if (k.equals(keys[kindex])) {
    return values[kindex];
   }
   if (keys[kindex] == null) {
    return null;
   }
   kindex = (kindex +1) % this.getCapacity();
  }
  return null;

 }

 public int insert(String k, V v) throws NullPointerException {
  if (k == null) {
   throw new NullPointerException("insert(String k, V v): k is null");
  }
  if (v == null) {
   throw new NullPointerException("insert(String k, V v): v is null");
  }
  int kindex = getHashIndex(k);
  int getindex = inserthelper(kindex, k, v);

  double loading_factor = (double) size/ capacity;
  if (loading_factor >= 0.55) {
   sizeUp();
  }

  return getindex;
 }

 private int inserthelper(int kindex, String k, V v) {

  if (keys[kindex] == null || BRIDGE.equals(keys[kindex])) {
   keys[kindex] = k;
   values[kindex] = v;
   size++;
   return kindex;
  }
  else if (k.equals(keys[kindex])) {
   values[kindex] = v;
   return kindex;
  }
  else {
   return inserthelper((kindex+1) % capacity, k, v);
  }
 }

 private void sizeUp() {
  String[] originalkeys = keys;
  V[] originalvalues = values;
  capacity = capacity * 2;
  keys = new String[capacity];
  values = (V[]) new Object[capacity];
  size = 0;

  for (int i = 0; i < originalkeys.length; i++) {
   if (originalkeys[i] != null && !originalkeys[i].equals(BRIDGE)) {
    insert(originalkeys[i], originalvalues[i]);
   }
  }
 }

 private void sizeDown() {
  String[] originalkeys = keys;
  V[] originalvalues = values;
  capacity = capacity / 2;
  if (capacity < 4) {
   capacity = 4;
  }
  keys = new String[capacity];
  values = (V[]) new Object[capacity];
  size = 0;
  for (int i = 0; i < originalkeys.length; i++) {
   if (originalkeys[i] != null && !originalkeys[i].equals(BRIDGE)) {
    insert(originalkeys[i], originalvalues[i]);
   }
  }
 }

 public int delete(String k) {
  int kindex = getHashIndex(k);
  int startindex = getHashIndex(k);

  if (k.equals(keys[kindex])) {
   keys[kindex] = (String) BRIDGE;
   values[kindex] = null;
   size--;
   double loading_factor = (double) size/ capacity;
   if (loading_factor <= 0.3) {
    sizeDown();
   }
   return kindex;
  }

  if (keys[kindex] == null) {
   return kindex;
  }

  kindex = (kindex+1) % capacity;

  while (kindex != startindex) {
   if (k.equals(keys[kindex])) {
    keys[kindex] = (String) BRIDGE;
    values[kindex] = null;
    size--;
    double loading_factor = (double) size/ capacity;
    if (loading_factor <= 0.3) {
     sizeDown();
    }
    return kindex;
   }

   if (keys[kindex] == null) {
    return kindex;
   }
   kindex = (kindex+1) % capacity;
  }
  return startindex;
 }

}
