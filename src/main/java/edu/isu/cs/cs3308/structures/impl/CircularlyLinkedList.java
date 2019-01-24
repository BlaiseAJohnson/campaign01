package edu.isu.cs.cs3308.structures.impl;

public class CircularlyLinkedList<E> extends SinglyLinkedList<E> {

    /**
     * Retrieves the value at the specified index.
     *
     * @param index Index of the value to be retrieved.
     * @return Element at the given index, or null if the index is less than 0
     * or greater than or equal to the list listSize.
     */
    @Override
    public E get(int index) {
        if (index < 0) return null;

        Node currentNode = listHead;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }

        return currentNode.nodeData;
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
            Node newNode = new Node(element);

            if (isEmpty()) {
                listHead = newNode;
                listTail = newNode;
                listTail.nextNode = listHead;

                listSize++;
            }
            else {
                newNode.nextNode = listHead;
                listHead = newNode;
                listTail.nextNode = listHead;

                listSize++;
            }
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

                while (currentNode.getNextNode() != listHead && currentNode.getNextNode() != null) {
                    currentNode = currentNode.getNextNode();
                }

                currentNode.nextNode = newNode;
                listTail = newNode;
                listTail.nextNode = listHead;
            }

            listSize++;
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
                listTail.nextNode = listHead;
            }
            else if (index == listSize - 1) {
                listTail = nodeBeforeCurrent;
            }

            // Return contents of removed node and decrease list size.
            listSize--;
            return currentNode.nodeData;
        }
    }

    public void swapWithNext(int indexToSwap, int timesToSwap) {
        if (timesToSwap == 0) {
            return;
        }

        Node currentNode = listHead;
        Node previousNode = listHead;
        for (int i = 0; i < indexToSwap; i ++) {
            previousNode = currentNode;
            currentNode = currentNode.nextNode;
        }
        Node nextNode = currentNode.nextNode;

        previousNode.nextNode = nextNode;
        currentNode.nextNode = nextNode.nextNode;
        nextNode.nextNode = currentNode;

        if (indexToSwap == 0) {
            listHead = nextNode;
        }
        else if (indexToSwap == size() - 1) {
            listHead = currentNode;
            listTail = nextNode;
        }
        else if (indexToSwap == size() - 2) {
            listTail = currentNode;
        }



        swapWithNext((indexToSwap + 1) % size(), timesToSwap - 1);

    }
}
