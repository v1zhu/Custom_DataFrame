public class SeriesV2<T> implements Series<T> {

     /** linked list */
     private LL<T> seriesData;
     private BST<String, T> seriesDataBST;

     /**
      * Constructs a new Series object.
      *
      * @param _rowNames an array of row names
      * @param _data an array of Integer data
      */
     public SeriesV2(String[] _rowNames, T[] _data) {
          // TODO: Implement constructor
          if (_data == null) {
               throw new NullPointerException("Series(String[] _index, " +
                       "T[] _data): _data can't be null. " +
                       "Terminating the program");
          }
          if (_rowNames != null && _data.length != _rowNames.length) {
               throw new IllegalArgumentException("Series(String[] _index, T[] _data): the length of _index and _data must be the same");
          }
          seriesData = new LL<T>();
          seriesDataBST = new BST<>();

          try {
               if (_rowNames == null) {
                    throw new NullPointerException();
               }

               for (int i = 0; i < _rowNames.length; i++) {
                    if (_rowNames[i] == null || _rowNames[i].equals("")) {
                         throw new IllegalArgumentException("Series(String[] _index, T[] _data): _rowNames is not valid");
                    }
                    seriesData.appendNode(_rowNames[i], _data[i]);
                    seriesDataBST.addNode(_rowNames[i], _data[i]);
               }
          } catch (NullPointerException e) {
               for (int i = 0; i < _data.length; i++) {
                    String lengthName = Integer.toString(i);
                    seriesData.appendNode(lengthName, _data[i]);
                    seriesDataBST.addNode(lengthName, _data[i]);
               }
          }
     }

     /**
      * Returns a string representation of the Series object.
      */
     public String toString() {
          // TODO: Implement toString method
          StringBuilder sb = new StringBuilder("print the series ...\n");
          sb.append("==================\n");
          sb.append(seriesData.toString());
          return sb.toString();

     }

     /**
      * Returns the length of the series object.
      */
     public int getLength() {
          // TODO: Implement getLength method
          return seriesData.getLength();
     }

     /**
      * Returns the row names of this Series object.
      */
     public String[] getRowNames() {
          // TODO: Implement getRowNames method
          String[] allindex = seriesData.getIndexArray();
          return allindex;
     }

     /**
      * Returns the data of this Series object as strings.
      */
     public String[] getData() {
          // TODO: Implement getData method
          String[] alldata = seriesData.getDataArray();
          return alldata;
     }

     /**
      * Adds a new pair of rowName and data at the end of the Series object.
      *
      * @param rn the row name to be added
      * @param d the Integer data value to be added
      */
     public void append(String rn, T d) {
          // TODO: Implement append method

          seriesData.appendNode(rn, d);
          if (rn == null) {
               seriesDataBST.addNode(String.valueOf(seriesDataBST.getSize()), d);
          }
          else {
               seriesDataBST.addNode(rn, d);
          }

     }

     /**
      * Retrieves a data value given a row name.
      *
      * @param rn the row name to search for
      */
     public T loc(String rn) throws NullPointerException, IllegalArgumentException{
          //declare exceptions for if rn is null or an empty string
          if (rn == null) {
               throw new NullPointerException("loc(String rn): rn can't be null");
          }
          if (rn.equals("")) {
               throw new IllegalArgumentException("loc(String rn): rn can't be an empty string");
          }
          BST.BSTNode temp = seriesDataBST.searchNode(rn);
          if (temp == null) {
               return null;
          }
          return (T) temp.getData();

     }

     /**
      * Retrieves multiple data values given an array of row names.
      *
      * @param rn an array of row names to search for
      */
     public T[] loc(String[] rn) throws NullPointerException, IllegalArgumentException {
          // TODO: Implement loc method for multiple row names
          //if rn is null or empty array
          if (rn == null) {
               throw new NullPointerException("loc(String[] rn): rn[] can't be null");
          }
          if (rn.length == 0) {
               throw new IllegalArgumentException("loc(String[] rn): rn[] can't be an empty array");
          }
          //create array same length as rn
          T[] values = (T[]) new Object[rn.length];

          //for each value in rn run through T loc(String rn)
          for (int i = 0; i < rn.length; i++) {
               values[i] = loc(rn[i]);
          }
          return values;
     }

     /**
      * Retrieves a data value based on its integer index.
      *
      * @param ind the index of the data to retrieve
      */
     public T iloc(int ind) {
          // TODO: Implement iloc method
          try{
               if (ind < 0 || ind >= seriesData.getLength()) {
                    throw new IndexOutOfBoundsException();
               }
               String[] alldata = seriesData.getIndexArray();
               String rn = alldata[ind];
               return loc(rn);

          } catch (IndexOutOfBoundsException e) {
               System.out.println("the index" + Integer.toString(ind)+ "is not valid.. returning null");
               return null;
          }
     }

     /**
      * Removes a pair of rowname-data from the Series, given a row name.
      *
      * @param rn the row name of the pair to be removed
      */
     public boolean drop(String rn) throws NullPointerException, IllegalArgumentException{
          // TODO: Implement drop method
          // exceptions for if rn is null or empty string
          if (rn == null) {
               throw new NullPointerException("drop(String rn): rn can't be null");
          }
          if (rn.equals("")) {
               throw new IllegalArgumentException( "drop(String rn): rn can't be an empty String");
          }
          if (seriesData.getLength() == 0) {
               return false;
          }
          String[] alldata = seriesData.getIndexArray();
          boolean exists = false;
          for (int i = 0; i < alldata.length; i++) {
               if (alldata[i].equals(rn)) {
                    exists = true;
                    break;
               }
          }
          if (!exists) {
               return false;
          }
          seriesData.removeNode(rn);
          return true;
     }

     /**
      * Replace any data value that is null with value.
      *
      * @param value the new value to replace null values
      */
     public void fillNull(T value) throws IllegalArgumentException {
          // TODO: Implement fillNull method
          // what to do if value is null
          if (value == null) {
               throw new IllegalArgumentException("fillNull(T value): value can't be null");
          }
          //run through data and replace any null with value
          String[] alldata = seriesData.getDataArray();
          String[] indexdata = seriesData.getIndexArray();
          for (int i = 0; i < alldata.length; i++) {
               if (alldata[i].equals("null")) {
                    seriesData.updateNode(indexdata[i], value);
               }
          }
     }
}


