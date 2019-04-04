package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Set;
import java.util.Iterator;
import java.util.ArrayList;

/** A HashSet implementation of a Set
 *
 * @author Steve Coburn
 *
 * @param <E> Datatype of the data to use
 */
public class SetImpl<E> implements Set<E> {

    private int size = 0;
    private int primeNum = 1777;
    private ArrayList<E>[] buckets;

    public SetImpl() {
        buckets = new ArrayList[primeNum];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    /** Hashes the value given as it relates to a prime number
     *
     * @param e the data to hash
     * @return hashed value
     */
    private int hash(E e) {
        return (e.hashCode() & 0x7fffffff) % primeNum;
    }

    @Override
    public void add(E e) {
        if (e != null){
            ArrayList<E> tempBucket = buckets[hash(e)];
            if (!this.contains(e))
                tempBucket.add(e);
            size++;
        }
    }

    @Override
    public void remove(E e) {
        if (e != null) {
            ArrayList<E> tempBucket = buckets[hash(e)];
            tempBucket.remove(e);
            size--;
        }
    }

    @Override
    public boolean contains(E e) {
        if (e == null) return false;
        return buckets[hash(e)].contains(e);
    }

    @Override
    public Iterator<E> iterator() {
        ArrayList<E> list = new ArrayList<E>();
        for (int i = 0; i < buckets.length; i++) {
            list.addAll(buckets[i]);
        }
        return list.iterator();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addAll(Set<E> s) {
        Iterator<E> looper = s.iterator();
        E tempData;
        while (!s.isEmpty()){
            tempData = looper.next();
            add(tempData);
            size++;
        }
    }

    @Override
    public void retainAll(Set<E> s) {
        Iterator<E> looper = s.iterator();
        E tempData;
        while (!s.isEmpty()){
            tempData = looper.next();
            if (!s.contains(tempData)){
                this.remove(tempData);
                size--;
            }
        }
    }

    @Override
    public void removeAll(Set<E> s) {
        Iterator<E> looper = s.iterator();
        E tempData;
        while (!s.isEmpty()){
            tempData = looper.next();
            if (s.contains(tempData)){
                this.remove(tempData);
            }
        }
        size = 0;
    }
}