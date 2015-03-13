
package com.pipogame.components;

import com.pipogame.*;
import java.util.Vector;

/**
 * Implement an descending sorted vector.
 * Các object nếu muốn được add vào list thì phải implement giao diện
 * {@link IComparable}.
 *
 * @author Honghai
 * @company B-Gate
 */
public class SortedList extends Vector {
    public SortedList() {
        super();
    }

    public SortedList(int initialCapacity) {
        super(initialCapacity);
    }

    public SortedList(int initialCapacity, int capacityIncrement) {
        super(initialCapacity, capacityIncrement);
    }

    /**
     * Add element then sort the list
     * @param ic
     */
    public void addElement(IComparable ic) {
        super.addElement(ic);
        int i;
        for (i = 0; i < elementCount; i++) {
            if (ic.compareWith((IComparable)elementData[i]) > 0) {
                Object temp = elementData[i];
                elementData[i] = ic;

                for (int j = elementCount - 1; j > i + 1; j--) {
                    elementData[j] = elementData[j - 1];
                }

                elementData[i + 1] = temp;
                break;
            }
        }
    }

    public void addAllElement(IComparable[] objs) {
        for (int i = 0; i < objs.length; i++) {
            addElement(objs[i]);
        }
    }
    
    public void addAllElement(Vector list) {
        for (int i = 0; i < list.size(); i++) {
            addElement(list.elementAt(i));
        }
    }

    public void addElement(Object o) {
        if (Debug.ENABLE) {
            if (!(o instanceof IComparable)) {
                throw new IllegalArgumentException(
                        "Not accept object type of " + o.getClass());
            }
        }

        addElement((IComparable)o);
    }
    
    public void addDistinct(Object o) {
        if (!contains(o)) {
            addElement(o);
        }
    }
}
