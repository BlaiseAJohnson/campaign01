package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


/**
 * A generic template implementation of a singly-lined list structure.
 * @author Written by Blaise Johnson in compliance with preexisting code and unit tests by Isaac Griffith.
 */
public class SinglyLinkedList<E> implements List<E> {

    protected Node listHead = null;
    protected Node listTail = null;
    protected int listSize = 0;

    /**
     * @return first element in the list or null if the list is empty.
     */
    @Override
    public E first() {
        if (listHead == null) {
            return null;
        }
        else {
            return listHead.nodeData;
        }
    }

    /**
     * @return last element in the list or null if the list is empty.
     */
    @Override
    public E last() {
        if (listTail == null) {
            return null;
        }
        else {
            return listTail.nodeData;
        }
    }

    /**
     * Adds the provided element to the end of the list, only if the element is
     * not null.
     *
     * @param element Element to be added to the end of the list.
     */
    @Override
    public void addLast(E element) {
        if (element != null) {
            Node newNode = new Node(element);

            if (isEmpty()) {
                listHead = newNode;
                listTail = newNode;
            } else {
                Node currentNode = listHead;

                while (currentNode.getNextNode() != null) {
                    currentNode = currentNode.getNextNode();
                }

                currentNode.nextNode = newNode;
                listTail = newNode;
            }

            listSize++;
        }
    }

    /**
     * Adds the provided element to the front of the list, only if the element
     * is not null.
     *
     * @param element Element to be added to the front of the list.
     */
    @Override
    public void addFirst(E element) {
        if (element != null) {
            Node newHead = new Node(element);

            newHead.nextNode = listHead;
            listHead = newHead;

            listSize++;
        }
    }

    /**
     * Removes the element at the front of the list.
     *
     * @return Element at the front of the list, or null if the list is empty.
     */
    @Override
    public E removeFirst() { return remove(0); }

    /**
     * Removes the element at the end of the list.
     *
     * @return Element at the end of the list, or null if the list is empty.
     */
    @Override
    public E removeLast() {
        return remove(listSize - 1);
    }

    /**
     * Inserts the given element into the list at the provided index. The
     * element will not be inserted if either the element provided is null or if
     * the index provided is less than 0. If the index is greater than or equal
     * to the current listSize of the list, the element will be added to the end of
     * the list.
     *
     * @param element Element to be added (as long as it is not null).
     * @param index   Index in the list where the element is to be inserted.
     */
    @Override
    public void insert(E element, int index) {
        if (element != null) {
            if (index >= listSize) {
                addLast(element);
            }
            else if (index == 0) {
                addFirst(element);
            }
            else {
                Node newNode = new Node(element);
                Node currentNode = listHead;
                Node previousNode = listHead;

                // Travel to correct index while storing the previous node.
                for (int i = 0; i < index; i++) {
                    previousNode = currentNode;
                    currentNode = currentNode.nextNode;
                }

                // Connect the newNode to the previous node, and the current node to the new node.
                previousNode.nextNode = newNode;
                newNode.nextNode = currentNode;

                // Increment the list size.
                listSize++;
            }
        }
    }

    /**
     * Removes the element at the given index and returns the value.
     *
     * @param index Index of the element to remove
     * @return The value of the element at the given index, or null if the index
     * is greater than or equal to the listSize of the list or less than 0.
     */
    @Override
    public E remove(int index) {
        if (index >= listSize || index < 0) {
            return null;
        }
        else {
            Node currentNode = listHead;
            Node nodeAfterCurrent;
            Node nodeBeforeCurrent = listHead;

            // Travel to the correct node while storing the previous node
            for (int i = 0; i < index; i++) {
                nodeBeforeCurrent = currentNode;
                currentNode = currentNode.nextNode;
            }
            nodeAfterCurrent = currentNode.nextNode;

            // Connect the nodes on either side of the current node, then detach it.
            nodeBeforeCurrent.nextNode = nodeAfterCurrent;
            currentNode.nextNode = null;

            // Update head or tail.
            if (index == 0) {
                listHead = nodeAfterCurrent;
            }
            else if (index == listSize - 1) {
                listTail = nodeBeforeCurrent;
            }

            // Return contents of removed node and decrease list size.
            listSize--;
            return currentNode.nodeData;
        }
    }

    /**
     * Retrieves the value at the specified index. Will return null if the index
     * provided is less than 0 or greater than or equal to the current listSize of
     * the list.
     *
     * @param index Index of the value to be retrieved.
     * @return Element at the given index, or null if the index is less than 0
     * or greater than or equal to the list listSize.
     */
    @Override
    public E get(int index) {
        if (index >= listSize || index < 0) {
            return null;
        }
        else {
            Node currentNode = listHead;

            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNextNode();
            }

            return currentNode.nodeData;
        }
    }

    /**
     * @return The current listSize of the list. Note that 0 is returned for an
     * empty list.
     */
    @Override
    public int size() {
        return listSize;
    }

    /**
     * @return true if there are no items currently stored in the list, false
     * otherwise.
     */
    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    /**
     * Prints the contents of the list in a single line separating each element
     * by a space to the default System.out
     */
    @Override
    public void printList() {
        Node currentNode = listHead;

        while (true) {
            System.out.println(currentNode.nodeData);

            if (currentNode.nextNode == null) { break; }

            currentNode = currentNode.nextNode;
        }
    }

    /**
     * Locates a given element in the list and returns the index of that element.
     */
    @Override
    public int indexOf(E element) {
        if (size() > 0) {
            Node currentNode = listHead;
            int currentIndex = 0;
            boolean elementFound = false;

            while (true) {
                if (currentNode.nodeData.equals(element)) {
                    elementFound = true;
                    break;
                } else if (currentNode.nextNode == null) {
                    break;
                } else {
                    currentNode = currentNode.getNextNode();
                    currentIndex++;
                }
            }

            return elementFound ? currentIndex : -1;
        }

        return -1;
    }

    /**
     * Represents the data container used in the SinglyLinkedList class.
     */
    protected class Node {
        E nodeData;
        Node nextNode;

        Node(E nodeData) {
            this.nodeData = nodeData;
            this.nextNode = null;
        }

        Node getNextNode(){
            return nextNode;
        }
    }
}
