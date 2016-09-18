package com.javarush.test.level20.lesson10.bonus04;

import java.io.*;
import java.util.*;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/
public class Solution extends AbstractList<String> implements List<String>, Cloneable, Serializable {

    public static void main(String[] args) {
        List<String> list = (List<String>) new Solution();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
    }

    int size = 0;
    ArrayList<Node<String>> freeParents = new ArrayList<>();
    ArrayList<Node<String>> iter = new ArrayList<>();

    public Solution() {
    }

    private static class Node<String> implements Cloneable, Serializable{
        String item;
        Node<String> prev;
        Node<String> next1;
        Node<String> next2;

        Node(Node<String> prev, String element) {
            this.item = element;
            this.prev = prev;
        }
    }

    @Override
    public boolean add(String e) {
        if (freeParents.size()<=1) {
            Node<String> newNode = new Node<>(null, e);
            freeParents.add(newNode);
            iter.add(newNode);
            size++;
            modCount++;
        }
        else {
            Node<String> parent = freeParents.get(0);
            Node<String> newNode = new Node<>(parent, e);
            size++;
            modCount++;
            freeParents.add(newNode);
            iter.add(newNode);
            if (parent.next1==null) parent.next1=newNode;
            else {
                parent.next2=newNode;
                freeParents.remove(0);
            }
        }
        return true;
    }

    @Override
    public Iterator<String> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<String> {

        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        public Itr() {
            cursor = iter.size()==0 ? -1:0;
        }

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public String next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            if (iter.size()==0)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return iter.get(lastRet=i).item;
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                removeNode(iter.get(lastRet));
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    public String getParent(String value) {

        for (Node<String> node : iter) {
            if (node.item.equals(value)) {
                if (node.prev==null) return null;
                else return node.prev.item;
            }
        }

        return null;
    }

    @Override
    public boolean remove(Object o) {

        String value = (String)o;
        Node<String> findNode = null;
        for (Node<String> node : iter) {
            if (node.item.equals(value)) {
                findNode = node;
                break;
            }
        }

        if (findNode==null) return false;
        else{
            removeNode(findNode);
            return true;
        }

    }

    private void removeNode(Node<String> node) {
        if (node!=null) {
            removeNode(node.next1);
            removeNode(node.next2);
            if (node.prev != null) {
                if (node.prev.next1 == node) node.prev.next1 = null;
                else if (node.prev.next2 == node) node.prev.next2 = null;
                node.prev = null;
            }
            iter.remove(node);
            freeParents.remove(node);
            size--;
            modCount--;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void clear() {
        size = 0;
        freeParents.clear();
        iter.clear();
        modCount = 0;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<String> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        String value = (String) o;
        for (Node<String> node : iter) {
            if (node.item.equals(value)) return true;
        }
        return false;
    }

}
