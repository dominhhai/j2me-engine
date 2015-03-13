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

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * Circular buffer (FIFO) of a fixed length. Not thread safe. You must NOT use
 * the queue as an Enumeration instance: if you want Enumeration please use
 * {@link #getEnumeration()}.
 * <p/>
 * @author Martin Vysny
 */
public final class Queue implements Enumeration {

    /**
     * The size of the buffer.
     */
    public final int bufferSize;
    /**
     * Circular packet buffer.
     */
    private final Object[] packetBuffer;
    /**
     * Index of packet that is going to be sent next.
     */
    private int lastPacketRemoved = 0;
    /**
     * Index of packet that was recently added.
     */
    private int lastPacketAdded = 0;
    /**
     * If
     * <code>true</code> then the buffer contains at least one item. Used
     * to distinguish between full and empty buffer.
     */
    private boolean containsItems = false;

    /**
     * Creates new buffer.
     * <p/>
     * @param bufferSize
     * fixed size of the buffer. The buffer can contain no more
     * items.
     */
    public Queue(final int bufferSize) {
        super();
        this.bufferSize = bufferSize;
        packetBuffer = new Object[bufferSize];
    }

    /**
     * Clones given queue.
     * <p/>
     * @param other
     * clones this queue.
     */
    public Queue(final Queue other) {
        super();
        this.bufferSize = other.bufferSize;
        this.lastPacketRemoved = other.lastPacketRemoved;
        this.lastPacketAdded = other.lastPacketAdded;
        this.containsItems = other.containsItems;
        packetBuffer = new Object[bufferSize];
        System.arraycopy(other.packetBuffer, 0, packetBuffer, 0, bufferSize);
    }

    /**
     * Returns number of occupied slots in circular buffer array.
     * <p/>
     * @return if zero then no slots are occupied.
     */
    public int occupiedSlots() {
        if (lastPacketRemoved < lastPacketAdded) {
            return lastPacketAdded - lastPacketRemoved;
        }
        if (!containsItems && (lastPacketRemoved == lastPacketAdded)) {
            return 0;
        }
        return lastPacketAdded + bufferSize - lastPacketRemoved;
    }

    /**
     * Retrieves and removes the head of this queue, or
     * <code>null</code> if
     * this queue is empty.
     * <p/>
     * @return Retrieves and removes the head of this queue, or null if this
     * queue is empty.
     */
    public Object poll() {
        if (isEmpty()) {
            return null;
        }
        if (++lastPacketRemoved >= bufferSize) {
            lastPacketRemoved = 0;
        }
        final Object result = packetBuffer[lastPacketRemoved];
        packetBuffer[lastPacketRemoved] = null;
        if (lastPacketRemoved == lastPacketAdded) {
            containsItems = false;
        }
        return result;
    }

    /**
     * Inserts the specified element into this queue, if possible.
     * <p/>
     * @param o
     * the element to insert.
     * @return <code>true</code> if it was possible to add the element to this
     * queue, else
     * <code>false</code>
     */
    public boolean offer(final Object o) {
        if (isFull()) {
            return false;
        }
        if (++lastPacketAdded >= bufferSize) {
            lastPacketAdded = 0;
        }
        packetBuffer[lastPacketAdded] = o;
        containsItems = true;
        return true;
    }

    /**
     * Inserts specified elements into this queue, if possible.
     * <p/>
     * @param other
     * the elements to insert.
     * @return 
     * <code>true</code> if it was possible to add all element to this
     * queue,
     * <code>false</code> if some or all elements have not been
     * added.
     * @throws IllegalArgumentException
     * if trying to add self.
     */
    public boolean offerAll(final Enumeration other) {
        if (other == this) {
            throw new IllegalArgumentException(
                    "Do not use queue as an enumeration"); //$NON-NLS-1$
        }
        for (; other.hasMoreElements();) {
            if (!offer(other.nextElement())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if this queue is full.
     * <p/>
     * @return
     * <code>true</code> if the queue is full,
     * <code>false</code> if
     * it has some room left.
     */
    public boolean isFull() {
        return occupiedSlots() >= bufferSize;
    }

    /**
     * Checks if this queue is empty.
     * <p/>
     * @return <code>true</code> if the queue is empty,
     * <code>false</code>
     * if it contains some elements.
     */
    public boolean isEmpty() {
        return occupiedSlots() == 0;
    }

    /**
     * Returns enumeration of items in this queue. Enumeration is a snapshot of
     * this queue and is further independent.
     * <p/>
     * @return enumerator instance.
     */
    public Enumeration getEnumeration() {
        return new Queue(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Enumeration#hasMoreElements()
     */
    public boolean hasMoreElements() {
        return !isEmpty();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Enumeration#nextElement()
     */
    public Object nextElement() {
        final Object result = poll();
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * Removes all objects from the queue.
     */
    public void clear() {
        lastPacketAdded = lastPacketRemoved;
        containsItems = false;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Queue)) {
            return false;
        }
        final Queue other = (Queue) obj;
        return ArrayUtils.equals(getEnumeration(), other.getEnumeration());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return ArrayUtils.hashCode(getEnumeration());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ArrayUtils.arrayToString(this);
    }

    /**
     * Peeks at the head of the queue and returns head item without removing it
     * from the queue.
     * <p/>
     * @return head item or
     * <code>null</code> if the queue is empty.
     */
    public Object peek() {
        return peek(0);
    }

    /**
     * Peeks at the head of the queue and returns
     * <code>index</code>-th
     * object beneath the head.
     * <p/>
     * @param index
     * the index of the item to retrieve.
     * @return item at required position or
     * <code>null</code> if no such item
     * exists.
     */
    public Object peek(final int index) {
        if (index >= occupiedSlots()) {
            return null;
        }
        final int i = (lastPacketRemoved + 1 + index) % bufferSize;
        final Object result = packetBuffer[i];
        return result;
    }

    /**
     * Removes last item {@link #offer(Object) offered} to the queue. This
     * allows to use the queue as a LIFO stack aswell.
     * <p/>
     * @return last offered item or
     * <code>null</code> if the queue is empty.
     */
    public Object removeLast() {
        if (isEmpty()) {
            return null;
        }
        final Object result = packetBuffer[lastPacketAdded];
        packetBuffer[lastPacketAdded] = null;
        if (--lastPacketAdded < 0) {
            lastPacketAdded = bufferSize - 1;
        }
        if (lastPacketRemoved == lastPacketAdded) {
            containsItems = false;
        }
        return result;
    }
}
