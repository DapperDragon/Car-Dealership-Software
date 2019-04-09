
public class InsertionSort {

    public static void sort(Comparable[] values) {
        Boolean insertionPositionFound;
        Comparable insertValue;
        Integer size = ArrayUtility.getNumberOfObjects(values);
        for (Integer current = 1; current < size; current++) {
            insertionPositionFound = false;
            Integer insertion = 0;
            while (!insertionPositionFound) {
                if (values[current].compareTo(values[insertion]) > 0) {
                    insertionPositionFound = true;
                    insertValue = values[current];
                    for (Integer index = current; index > insertion; index--) {
                        values[index] = values[index - 1];
                    }
                    values[insertion] = insertValue;
                } else {
                    insertion++;
                    if (insertion == current) {
                        insertionPositionFound = true;
                    }
                }
            }
        }
    }

}