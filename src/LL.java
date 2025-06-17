public class LL<T>{

     //inner class
     public class LLNode {
          //instance variables
          private String index;
          private T data;
          private LLNode next;

          // default constructor. Sets all instance variables to be null
          public LLNode() {
               this.index = null;
               this.data = null;
               this.next = null;

          }

          // another constructor. Set data and index to be _data and _index each
          public LLNode(String _index, T _data) {
               this.index = _index;
               this.data = _data;

          }

          // return the index that’s stored in this node
          public String getIndex() {
               return index;

          }

          // return the data that’s stored in this node
          public T getData() {
               return data;

          }

          // update the data in this node to d
          public void setData(T d) {
               this.data = d;

          }
     }

     //LL instance variables
     private LLNode head;
     private LLNode tail;
     private int length;

     //LL methods
     public LL() {
          LLNode dummy1 = new LLNode();
          LLNode dummy2 = new LLNode();
          head = dummy1;
          tail = dummy2;
          dummy1.next = dummy2;
          length = 0;
     }

     public String toString() {
          StringBuilder sb = new StringBuilder();
          LLNode current = head.next;
          sb.append("null\t: null\n");
          while (current != tail) {
               sb.append(current.getIndex()).append("\t: ").append(current.getData()).append("\n");
               current = current.next;
          }
          sb.append("null\t: null\n");
          return sb.toString();
     }

     public int getLength() {
          return this.length;

     }

     public String[] getDataArray() {
          String[] dataarray = new String[length];
          LLNode current = head.next;
          int position = 0;
          while (current != tail) {
               if(current.getData() != null) {
                    dataarray[position] = current.getData().toString();
               }
               else {
                    dataarray[position] = "null";
               }
               position++;
               current = current.next;
          }
          return dataarray;

     }

     public String[] getIndexArray() {
          String[] indexarray = new String[length];
          LLNode current = head.next;
          int position = 0;
          while (current != tail) {
               indexarray[position] = current.getIndex();
               position++;
               current = current.next;
          }
          return indexarray;

     }

     public void appendNode(String _index, T _data) {
          LLNode current = head;

          while(current.next != tail) {
               current = current.next;
          }
          if (_index == null || _index.equals("")) {
               String new_index = Integer.toString(this.length);
               LLNode newNode = new LLNode(new_index, _data);
               current.next = newNode;
               newNode.next = tail;
               length++;
          }
          else {
               LLNode newNode = new LLNode(_index, _data);
               current.next = newNode;
               newNode.next = tail;
               length++;
          }
     }

     public LLNode searchNode(String _index) {
          LLNode current = head.next;
          while (current != tail) {
               if (_index != null && current.getIndex().equals(_index)) {
                    return current;
               }
               current = current.next;
          }
          return null;
     }

     public void removeNode(String _index) throws IllegalArgumentException {
          if (head.next == tail) {
               throw new IllegalArgumentException("removeNode(String _index): No node with an index "+ _index + " in the list");
          }
          LLNode current = head.next;
          LLNode previous = head;

          while (_index != null && current != tail && !current.getIndex().equals(_index)) {
               previous = current;
               current = current.next;
          }
          if (current == tail) {
               throw new IllegalArgumentException("removeNode(String _index): No node with an index "+ _index + " in the list");
          }
          previous.next = current.next;
          length--;

     }

     public void updateNode(String _index, T value) throws IllegalArgumentException {
          LLNode current = head.next;
          while (current != tail) {
               if (_index != null && current.getIndex().equals(_index)) {
                    current.setData(value);
                    return;
               }
               current = current.next;
          }
          throw new IllegalArgumentException("updateNode(String _index, T value): No node with an index " + _index + " in the list");
     }


}


