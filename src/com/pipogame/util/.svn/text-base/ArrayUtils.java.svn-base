/*
 The Bluetooth Library for client-server communication
 Copyright (C) 2006 Martin Vysny

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 */
package com.pipogame.util;

import java.util.*;

/**
 * Array utilities.
 * <p/>
 * @author Martin Vysny
 */
public final class ArrayUtils {

    private ArrayUtils() {
        // prevent instantiation
    }

    /**
     * Finds first occurence of given object in given vector. To compare objects
     * {@link Object#equals(Object)} is used.
     * <p/>
     * @param vector
* vector
     * @param obj
	  * object to find, may be
     * <code>null</code>.
     * @return index of given object or -1 if the enumeration does not contain
     * the object.
     */
//	public static int indexOf(final Vector vector, final Object obj) {
//		for (int i = 0; i < vector.size(); i++) {
//			final Object other = vector.elementAt(i);
//			if (obj == null) {
//				if (other == null)
//					return i;
//			} else if (obj.equals(other)) {
//				return i;
//			}
//		}
//		return -1;
//	}
    /**
     * Appends all items from given array after last item of the vector.
     * <p/>
     * @param vector
     * here all items will be appended.
     * @param copyFrom
* array to append.
     */
    public static void addAll(Vector vector, Object[] copyFrom) {
        if (copyFrom == null) {
            return;
        }
        vector.ensureCapacity(vector.size() + copyFrom.length);
        for (int i = 0; i < copyFrom.length; i++) {
            vector.addElement(copyFrom[i]);
        }
    }

    /**
     * Appends all items from given array after last item of the vector.
     * <p/>
     * @param vector
     * here all items will be appended.
     * @param copyFrom
* array to append. See {@link #arrayToEnumeration(Object)} for
     * details. Does not support {@link Enumeration}.
     */
//	public static void addAll(Vector vector, Object copyFrom) {
//		if (copyFrom == null)
//			return;
//		if (copyFrom instanceof Object[]) {
//			addAll(vector, (Object[]) copyFrom);
//			return;
//		}
//		if (copyFrom instanceof Enumeration) {
//			final Enumeration e = (Enumeration) copyFrom;
//			for (; e.hasMoreElements();) {
//				vector.addElement(e.nextElement());
//			}
//			return;
//		}
//		final int copyFromLength = getLength(copyFrom);
//		vector.ensureCapacity(vector.size() + copyFromLength);
//		for (int i = 0; i < copyFromLength; i++) {
//			vector.addElement(getElementAt(copyFrom, i));
//		}
//	}
    /**
     * Appends all items from given array after last item of the vector.
     * <p/>
     * @param vector
     * here all items will be appended.
     * @param copyFrom
* vector to append.
     */
//	public static void addAll(Vector vector, Vector copyFrom) {
//		if (copyFrom == null)
//			return;
//		vector.ensureCapacity(vector.size() + copyFrom.size());
//		addAll(vector, copyFrom.elements());
//	}
    /**
     * Compares two enumerations if they contain equal objects in correct order.
     * <p/>
     * @param enum1
* first enumeration
     * @param enum2
* second enumeration
     * @return <
     * code>true</code> if two enumerations match,
     * <code>false</code>
     * otherwise.
     */
//	public static boolean equals(final Enumeration enum1,
//			final Enumeration enum2) {
//		while (true) {
//			if (!enum1.hasMoreElements()) {
//				return !enum2.hasMoreElements();
//			}
//			if (!enum2.hasMoreElements())
//				return false;
//			final Object obj1 = enum1.nextElement();
//			final Object obj2 = enum2.nextElement();
//			if (!equalsObjects(obj1, obj2))
//				return false;
//		}
//	}
    /**
     * The ultimate comparator. Handles
     * <code>null</code>s and arrays
     * correctly.
     * <p/>
     * @param o1
* first object.
     * @param o2
* second object.
     * @return <
     * code>true</code> if objects equals,
     * <code>false</code>
     * otherwise.
     */
//	public static boolean equalsObjects(final Object o1, final Object o2) {
//		if (o1 == null)
//			return o2 == null;
//		if (o2 == null)
//			return false;
//		if (isArray(o1)) {
//			if (!isArray(o2))
//				return false;
//			return equals(o1, o2);
//		}
//		if (isArray(o2))
//			return false;
//		return o1.equals(o2);
//	}
    /**
     * Compares two enumerations if they contain equal objects in correct order.
     * <p/>
     * @param enum1
* first enumeration
     * @param enum2
* second enumeration
     * @return <
     * code>true</code> if two enumerations match,
     * <code>false</code>
     * otherwise.
     */
    public static boolean equals(final Enumeration enum1, final Object[] enum2) {
        int i = 0;
        while (true) {
            if (!enum1.hasMoreElements()) {
                return i >= enum2.length;
            }
            if (i >= enum2.length) {
                return false;
            }
            final Object obj1 = enum1.nextElement();
            final Object obj2 = enum2[i++];
            if (!((obj1 == null) ? obj2 == null : obj1.equals(obj2))) {
                return false;
            }
        }
    }

    /**
     * Compares two arrays if they contain equal objects in correct order.
     * <p/>
     * @param enum1
* first array
     * @param enum2
* second array
     * @return <
     * code>true</code> if two enumerations match,
     * <code>false</code>
     * otherwise.
     */
    public static boolean equals(final Object[] enum1, final Object[] enum2) {
        if (enum1.length != enum2.length) {
            return false;
        }
        for (int i = 0; i < enum1.length; i++) {
            if (!enum1[i].equals(enum2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares two arrays if they contain equal objects in correct order. For
     * details please see {@link #arrayToEnumeration(Object)}.
     * <p/>
     * @param enum1
* first array
     * @param enum2
* second array
     * @return <
     * code>true</code> if two enumerations match,
     * <code>false</code>
     * otherwise.
     * @throws IllegalArgumentException
* if the object is not {@link #isArray(Object) an array}.
     */
    public static boolean equals(final Object enum1, final Object enum2) {
        return equals(arrayToEnumeration(enum1), arrayToEnumeration(enum2));
    }

    /**
     * Computes correct hashcode for given object.
     * <p/>
     * @param object
* the object to compute hash from. It will compute correct
     * hashcode from arrays and enumerations aswell.
     * @return the hashcode,
     * <code>0</code> if
     * <code>null</code> was given.
     */
    public static int hashCode(final Object object) {
        if (object == null) {
            return 0;
        }
        if (!object.getClass().isArray()) {
            return object.hashCode();
        }
        int result = 1;
        for (final Enumeration e = arrayToEnumeration(object); e.hasMoreElements();) {
            final Object o = e.nextElement();
            result = result * 1001 + hashCode(o);
        }
        return result;
    }

    /**
     * Creates an enumeration from given object. If the object is an array of
     * primitive types then the enumeration will enumerate appropriate object
     * equivalents. If the object is a {@link Queue} or a {@link Vector} then
     * their enumerations are returned instead.
     * <p/>
     * @param array
* the object to convert to an enumeration.
     * @return enumeration instance.
     * @throws IllegalArgumentException
* if the object is none of the above.
     */
    public static Enumeration arrayToEnumeration(final Object array) {
        checkIsArray(array, true);
        if (array instanceof Queue) {
            return ((Queue) array).getEnumeration();
        }
        if (array instanceof Vector) {
            return ((Vector) array).elements();
        }
        if (array instanceof Enumeration) {
            return (Enumeration) array;
        }
        return new ArrayEnumeration(array);
    }

    /**
     * Returns true if given object is an array or a {@link Queue}, a
     * {@link Vector} or an {@link Enumeration}.
     * <p/>
     * @param array
* the array instance.
     * @return <
     * code>true</code> if given object is an array,
     * <code>false</code> if it is a regular object or
     * <code>null</code>.
     */
    public static boolean isArray(final Object array) {
        if (array == null) {
            return false;
        }
        if (array.getClass().isArray()) {
            return true;
        }
        return (array instanceof Queue) || (array instanceof Vector) ||
                 (array instanceof Enumeration);
    }

    /**
     * Checks if given object is an array, as per {@link #isArray(Object)}.
     * <p/>
     * @param array
	          * the array to check
     * @param allowEnumeration
* if
     * <code>true</code> then enumeration is allowed as a valid
     * array type.
     * @throws IllegalArgumentException
* if given object is not array.
     */
    public static void checkIsArray(final Object array,
            final boolean allowEnumeration) {
        if (!allowEnumeration && (array instanceof Enumeration)) {
            // throw the exception
        } else {
            if (isArray(array)) {
                return;
            }
        }
        throw new IllegalArgumentException(
                "not an array: " + array == null ? "null" : array //$NON-NLS-1$ //$NON-NLS-2$
                .getClass().toString());
    }
    private static final byte[] BYTE = new byte[0];
    private static final boolean[] BOOL = new boolean[0];
    private static final long[] LONG = new long[0];
    private static final int[] INT = new int[0];
    private static final Object[] OBJ = new Object[0];
    private static final short[] SHORT = new short[0];

    /**
     * <p>
     * Returns length of given array.
     * </p>
     * <p>
     * Note: there is no way to get number of items for Enumeration but to walk
     * through it. This effectively 'destroys' given enumeration instance as it
     * is no longer usable.
     * </p>
     * <p/>
     * @param array
* the array to check. See {@link #arrayToEnumeration(Object)}
     * for details.
     * @return length of an array.
     */
    public static int getLength(final Object array) {
        checkIsArray(array, true);
        final Class c = array.getClass();
        if (c.isInstance(BYTE)) {
            return ((byte[]) array).length;
        } else if (c.isInstance(BOOL)) {
            return ((boolean[]) array).length;
        } else if (c.isInstance(LONG)) {
            return ((long[]) array).length;
        } else if (c.isInstance(INT)) {
            return ((int[]) array).length;
        } else if (c.isInstance(OBJ)) {
            return ((Object[]) array).length;
        } else if (c.isInstance(SHORT)) {
            return ((short[]) array).length;
        } else if (array instanceof Vector) {
            return ((Vector) array).size();
        } else if (array instanceof Queue) {
            return ((Queue) array).occupiedSlots();
        } else {
            int i = 0;
            for (; ((Enumeration) array).hasMoreElements();) {
                i++;
            }
            return i;
        }
    }

    /**
     * Retrieves item from given array.
     * <p/>
     * @param array
* the array instance. For details please see
     *            {@link #arrayToEnumeration(Object)}.
     * @param index
* the index of array.
     * @return object at given index.
     */
    public static Object getElementAt(Object array, int index) {
        checkIsArray(array, false);
        final Class c = array.getClass();
        if (c.isInstance(BYTE)) {
            return new Byte(((byte[]) array)[index]);
        } else if (c.isInstance(BOOL)) {
            return new Boolean(((boolean[]) array)[index]);
        } else if (c.isInstance(LONG)) {
            return new Long(((long[]) array)[index]);
        } else if (c.isInstance(INT)) {
            return new Integer(((int[]) array)[index]);
        } else if (c.isInstance(OBJ)) {
            return ((Object[]) array)[index];
        } else if (c.isInstance(SHORT)) {
            return new Short(((short[]) array)[index]);
        } else if (array instanceof Vector) {
            return ((Vector) array).elementAt(index);
        } else {
            return ((Queue) array).peek(index);
        }
    }

    /**
     * Sets item into given array.
     * <p/>
     * @param array
	* the array instance. For details please see
     *            {@link #arrayToEnumeration(Object)}. Note that {@link Queue}
     * does not support setting of arbitrary item.
     * @param index
	* the index of array.
     * @param object
* object to set.
     */
//	public static void setElementAt(Object array, int index, final Object object) {
//		checkIsArray(array, false);
//		final Class c = array.getClass();
//		if (c.isInstance(BYTE))
//			((byte[]) array)[index] = ((Byte) object).byteValue();
//		else if (c.isInstance(BOOL))
//			((boolean[]) array)[index] = ((Boolean) object).booleanValue();
//		else if (c.isInstance(LONG))
//			((long[]) array)[index] = ((Long) object).longValue();
//		else if (c.isInstance(INT))
//			((int[]) array)[index] = ((Integer) object).intValue();
//		else if (c.isInstance(OBJ))
//			((Object[]) array)[index] = object;
//		else if (c.isInstance(SHORT))
//			((short[]) array)[index] = ((Short) object).shortValue();
//		else if (array instanceof Vector)
//			((Vector) array).setElementAt(object, index);
//		else
//			throw new IllegalArgumentException("Queue is not supported"); //$NON-NLS-1$
//	}
    /**
     * Converts given object into an enumeration. For details see
     * {@link #arrayToEnumeration(Object)}.
     * <p/>
     * @author Martin Vysny
     */
    public static final class ArrayEnumeration implements Enumeration {

        /**
         * Constructor.
         * <p/>
         * @param array
         * array instance.
         */
        ArrayEnumeration(final Object array) {
            super();
            this.array = array;
            length = getLength(array);
        }
        /**
         * The array.
         */
        private final Object array;
        /**
         * The length of given array.
         */
        private final int length;
        /**
         * Item to be offered by the enumeration.
         */
        private int currentItem = 0;

        /*
         * (non-Javadoc)
         *
         * @see java.util.Enumeration#hasMoreElements()
         */
        public boolean hasMoreElements() {
            return currentItem < length;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.util.Enumeration#nextElement()
         */
        public Object nextElement() {
            if (!hasMoreElements()) {
                throw new NoSuchElementException();
            }
            return getElementAt(array, currentItem++);
        }
    }

    /**
     * Clones given array. For details please see
     * {@link #arrayToEnumeration(Object)}.
     * <p/>
     * @param array
     * the array to clone. {@link Enumeration}s are not supported.
     * @param deep
     * if
     * <code>true</code> then sub-arrays are cloned aswell. Note
     * that if you introduce a nesting cycle the function will
     * recurse until stack overflow is reached.
     * @return array of same class but cloned. Returns
     * <code>null</code> if
     * <code>null</code> array was given.
     */
    public static Object clone(final Object array, final boolean deep) {
        if (array == null) {
            return null;
        }
        if (array instanceof Queue) {
            if (!deep) {
                return new Queue((Queue) array);
            }
            final Queue q = (Queue) array;
            final Queue result = new Queue(q.bufferSize);
            for (final Enumeration e = q.getEnumeration(); e.hasMoreElements();) {
                Object obj = e.nextElement();
                if (isArray(obj)) {
                    obj = clone(obj, true);
                }
                result.offer(obj);
            }
            return result;
        }
        final Class c = array.getClass();
        if (c.isInstance(BYTE)) {
            final byte[] a = (byte[]) array;
            final byte[] result = new byte[a.length];
//            for (int i = 0; i < a.length; i++) {
//                result[i] = a[i];
//            }
            System.arraycopy(a, 0, result, 0, a.length);
            return result;
        } else if (c.isInstance(BOOL)) {
            final boolean[] a = (boolean[]) array;
            final boolean[] result = new boolean[a.length];
//            for (int i = 0; i < a.length; i++) {
//                result[i] = a[i];
//            }
            System.arraycopy(a, 0, result, 0, a.length);
            return result;
        } else if (c.isInstance(LONG)) {
            final long[] a = (long[]) array;
            final long[] result = new long[a.length];
//            for (int i = 0; i < a.length; i++) {
//                result[i] = a[i];
//            }
            System.arraycopy(a, 0, result, 0, a.length);
            return result;
        } else if (c.isInstance(INT)) {
            final int[] a = (int[]) array;
            final int[] result = new int[a.length];
//            for (int i = 0; i < a.length; i++) {
//                result[i] = a[i];
//            }
            System.arraycopy(a, 0, result, 0, a.length);
            return result;
        } else if (c.isInstance(OBJ)) {
            final Object[] a = (Object[]) array;
            final Object[] result = new Object[a.length];
            for (int i = 0; i < a.length; i++) {
                Object obj = a[i];
                if (deep && isArray(obj)) {
                    obj = clone(obj, true);
                }
                result[i] = obj;
            }
            return result;
        } else if (c.isInstance(SHORT)) {
            final short[] a = (short[]) array;
            final short[] result = new short[a.length];
//            for (int i = 0; i < a.length; i++) {
//                result[i] = a[i];
//            }
            System.arraycopy(a, 0, result, 0, a.length);
            return result;
        } else if (array instanceof Vector) {
            final Vector a = (Vector) array;
            final Vector result = new Vector(a.size());
            for (int i = 0; i < a.size(); i++) {
                Object obj = a.elementAt(i);
                if (deep && isArray(obj)) {
                    obj = clone(obj, true);
                }
                result.addElement(obj);
            }
            return result;
        } else {
            throw new IllegalArgumentException("Unsupported array class: " //$NON-NLS-1$
                    + c.getName());
        }
    }

    /**
     * Converts given array to a string representation.
     * <p/>
     * @param array
* the array to convert.
     * @return string representation of the array.
     */
    public static String arrayToString(final Object array) {
        if (array == null) {
            return "null"; //$NON-NLS-1$
        }
        checkIsArray(array, true);
        final StringBuffer result = new StringBuffer("["); //$NON-NLS-1$
        for (final Enumeration e = arrayToEnumeration(array); e.hasMoreElements();) {
            final Object obj = e.nextElement();
            if (isArray(obj)) {
                result.append(arrayToString(obj));
            } else {
                if (obj == null) {
                    result.append("null"); //$NON-NLS-1$
                } else {
                    result.append(obj);
                }
            }
            if (e.hasMoreElements()) {
                result.append(", "); //$NON-NLS-1$
            }
        }
        result.append(']');
        return result.toString();
    }
}
