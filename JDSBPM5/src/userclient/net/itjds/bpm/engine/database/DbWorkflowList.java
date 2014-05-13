package net.itjds.bpm.engine.database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;

public abstract class DbWorkflowList extends AbstractList
  implements Cloneable, Serializable
{
  public static final int TYPE_PROCESSDEF = 1;
  public static final int TYPE_PROCESSDEFVERSION = 2;
  public static final int TYPE_ACTIVITYDEF = 3;
  public static final int TYPE_ROUTEDEF = 4;
  public static final int TYPE_PROCESSINST = 11;
  public static final int TYPE_ACTIVITYINST = 12;
  public static final int TYPE_ACTIVITYINSTHISTORY = 13;
  protected transient Object[] elementData;
  protected int size;

  public DbWorkflowList()
  {
    this.elementData = new Object[10];
  }

  public DbWorkflowList(Collection c)
  {
    this.size = c.size();

    this.elementData = 
      new Object[(int)Math.min(this.size * 110L / 100L, 
      2147483647L)];
    c.toArray(this.elementData);
  }

  public void trimToSize()
  {
    this.modCount += 1;
    int oldCapacity = this.elementData.length;
    if (this.size < oldCapacity) {
      Object[] oldData = this.elementData;
      this.elementData = new Object[this.size];
      System.arraycopy(oldData, 0, this.elementData, 0, this.size);
    }
  }

  public void ensureCapacity(int minCapacity)
  {
    this.modCount += 1;
    int oldCapacity = this.elementData.length;
    if (minCapacity > oldCapacity) {
      Object[] oldData = this.elementData;
      int newCapacity = oldCapacity * 3 / 2 + 1;
      if (newCapacity < minCapacity)
        newCapacity = minCapacity;
      this.elementData = new Object[newCapacity];
      System.arraycopy(oldData, 0, this.elementData, 0, this.size);
    }
  }

  public int size()
  {
    return this.size;
  }

  public boolean isEmpty()
  {
    return this.size == 0;
  }

  public boolean contains(Object elem)
  {
    return indexOf(elem) >= 0;
  }

  public int indexOf(Object elem)
  {
    if (elem == null)
      for (int i = 0; i < this.size; i++)
        if (this.elementData[i] == null)
          return i;
    else {
      for (int k = 0; k < this.size; k++)
        if (elem.equals(this.elementData[k]))
          return k;
    }
    return -1;
  }

  public int lastIndexOf(Object elem)
  {
    if (elem == null)
      for (int i = this.size - 1; i >= 0; i--)
        if (this.elementData[i] == null)
          return i;
    else {
      for (int j = this.size - 1; j >= 0; j--)
        if (elem.equals(this.elementData[j]))
          return j;
    }
    return -1;
  }

  public Object clone()
  {
    try
    {
      DbWorkflowList v = (DbWorkflowList)super.clone();
      v.elementData = new Object[this.size];
      System.arraycopy(this.elementData, 0, v.elementData, 0, this.size);
      v.modCount = 0;
      return v;
    } catch (CloneNotSupportedException e) {
    }
    throw new InternalError();
  }

  public Object[] toArray()
  {
    Object[] result = new Object[this.size];

    for (int i = 0; i < this.size; i++)
    {
      result[i] = get(i);
    }

    return result;
  }

  public Object[] toArray(Object[] a)
  {
    if (a.length < this.size) {
      a = (Object[])Array.newInstance(a.getClass()
        .getComponentType(), this.size);
    }

    for (int i = 0; i < this.size; i++) {
      a[i] = get(i);
    }

    if (a.length > this.size)
      a[this.size] = null;
    return a;
  }

  public Object get(int index)
  {
    RangeCheck(index);
    prepareGet(index);
    return getWorkflowObject(this.elementData[index]);
  }

  public Object set(int index, Object element)
  {
    RangeCheck(index);

    Object oldValue = this.elementData[index];
    this.elementData[index] = element;
    return oldValue;
  }

  public boolean add(Object o)
  {
    ensureCapacity(this.size + 1);
    this.elementData[(this.size++)] = o;
    return true;
  }

  public void add(int index, Object element)
  {
    if ((index > this.size) || (index < 0)) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + 
        this.size);
    }
    ensureCapacity(this.size + 1);
    System.arraycopy(this.elementData, index, this.elementData, index + 1, this.size - 
      index);
    this.elementData[index] = element;
    this.size += 1;
  }

  public Object remove(int index)
  {
    RangeCheck(index);

    this.modCount += 1;
    Object oldValue = this.elementData[index];

    int numMoved = this.size - index - 1;
    if (numMoved > 0)
      System.arraycopy(this.elementData, index + 1, this.elementData, index, 
        numMoved);
    this.elementData[(--this.size)] = null;

    return oldValue;
  }

  public void clear()
  {
    this.modCount += 1;

    for (int i = 0; i < this.size; i++) {
      this.elementData[i] = null;
    }
    this.size = 0;
  }

  public boolean addAll(Collection c)
  {
    Object[] a = c.toArray();
    int numNew = a.length;
    ensureCapacity(this.size + numNew);
    System.arraycopy(a, 0, this.elementData, this.size, numNew);
    this.size += numNew;
    return numNew != 0;
  }

  public boolean addAll(int index, Collection c)
  {
    if ((index > this.size) || (index < 0)) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + 
        this.size);
    }
    Object[] a = c.toArray();
    int numNew = a.length;
    ensureCapacity(this.size + numNew);

    int numMoved = this.size - index;
    if (numMoved > 0) {
      System.arraycopy(this.elementData, index, this.elementData, index + numNew, 
        numMoved);
    }
    System.arraycopy(a, 0, this.elementData, index, numNew);
    this.size += numNew;
    return numNew != 0;
  }

  public boolean equals(Object o) {
    if (o == this)
      return true;
    return false;
  }

  protected void removeRange(int fromIndex, int toIndex)
  {
    this.modCount += 1;
    int numMoved = this.size - toIndex;

    System.arraycopy(this.elementData, toIndex, this.elementData, fromIndex, 
      numMoved);

    int newSize = this.size - (toIndex - fromIndex);
    while (this.size != newSize)
      this.elementData[(--this.size)] = null;
  }

  private void RangeCheck(int index)
  {
    if (index >= this.size)
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + 
        this.size);
  }

  private void writeObject(ObjectOutputStream s)
    throws IOException
  {
    s.defaultWriteObject();

    s.writeInt(this.elementData.length);

    for (int i = 0; i < this.size; i++)
      s.writeObject(this.elementData[i]);
  }

  private void readObject(ObjectInputStream s)
    throws IOException, ClassNotFoundException
  {
    s.defaultReadObject();

    int arrayLength = s.readInt();
    this.elementData = new Object[arrayLength];

    for (int i = 0; i < this.size; i++)
      this.elementData[i] = s.readObject();
  }

  protected void prepareGet(int index)
  {
  }

  protected abstract Object getWorkflowObject(Object paramObject);
}