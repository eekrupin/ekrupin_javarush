package com.javarush.test.level33.lesson15.big01.strategies;

public class FileStorageStrategy implements StorageStrategy{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private int size;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private long bucketSizeLimit = 10000;

    public FileStorageStrategy() {
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            table[i] = new FileBucket();
        }
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    private Entry getEntry(Long key){
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        if (table[index] != null) {
            Entry entry = table[index].getEntry();
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    return entry;
                }
                entry = entry.next;
            }
        }
        return null;
    }

    private int hash(Long k){
        int h = 0;
        h ^= k.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int indexFor(int hash, int length){
        return hash & (length-1);
    }

    @Override
    public boolean containsValue(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                if (entry.value.equals(value)) return true;
                entry = entry.next;
            }
        }
        return false;
    }

    private boolean containsNullValue() {
        FileBucket[] tab = table;
        for (int i = 0; i < tab.length ; i++) {
            if (tab[i]==null) continue;
            for (Entry e = tab[i].getEntry(); e != null; e = e.next)
                if (e.value == null)
                return true;
    }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        if (table[index] != null) {
            Entry entry = table[index].getEntry();
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    entry.value = value;
                    return;
                }
                entry = entry.next;
            }
            addEntry(hash, key, value, index);
        }
        else {
            createEntry(hash, key, value, index);
        }
    }

    private String putForNullKey(String value) {

        if (table[0]!=null) {
            for (Entry e = table[0].getEntry(); e != null; e = e.next) {
                if (e.key == null) {
                    String oldValue = e.value;
                    e.value = value;
                    return oldValue;
                }
            }
        }
        addEntry(0, null, value, 0);
        return null;
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex){
        Entry entry = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, entry));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit) resize(2 * table.length);
    }

    private void resize(int newCapacity){
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    private void transfer(FileBucket[] newTable){
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                Entry next = entry.next;
                int newIndex = indexFor(entry.hash, newTable.length);
                if (newTable[newIndex] == null) {
                    entry.next = null;
                    newTable[newIndex] = new FileBucket();
                }
                else {
                    entry.next = newTable[newIndex].getEntry();
                }
                newTable[newIndex].putEntry(entry);
                entry = next;
            }
            table[i].remove();
        }
    }

    private void createEntry(int hash, Long key, String value, int bucketIndex){
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }

    @Override
    public Long getKey(String value) {
        FileBucket[] tab = table;
        for (int i = 0; i < tab.length ; i++) {
            if (tab[i]==null) continue;
            for (Entry e = tab[i].getEntry(); e != null; e = e.next)
                if ((value == null && e.value == null) || (value.equals(e.value)))
                    return e.key;
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        if (key == null)
            return getForNullKey();
        Entry entry = getEntry(key);

        return null == entry ? null : entry.getValue();
    }

    private String getForNullKey() {
        if (size == 0) {
            return null;
        }

        if (table[0]!=null) {
            for (Entry e = table[0].getEntry(); e != null; e = e.next) {
                if (e.key == null)
                    return e.value;
            }
        }
        return null;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }
}
