
package net.itjds.usm2.db;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.itjds.usm2.USMException;
import net.itjds.usm2.UsmProxy;
import net.itjds.usm2.db.util.EsbUtil;

import com.opensymphony.xwork2.ActionContext;

/**
 * <p>
 * Title: JDSUSM平台
 * </p>
 * <p>
 * Description: USM EI接口列表代理器，将EI对象列表转换为相应Proxy对象。
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: www.justdos.net
 * </p>
 * 
 * @author wenzhang li
 * @version 2.0
 */
public class USMListProxy extends AbstractList implements Cloneable,
		Serializable {

	private List eiList;
	
	private Class defaultProxyClass;
	private UsmService service;


	/**
	 * Constructs a list containing the elements of the specified ei object
	 * list.
	 * 
	 * @param eiList
	 *            the list whose elements are to be placed into this list.
	 */
	public USMListProxy(List eiList,UsmService service) {
		this.eiList = eiList;
		this.defaultProxyClass=service.getDefaultProxyClass();
		this.service=service;
	}
	
	public USMListProxy(List eiList,Class defaultProxyClass) {
		this.eiList = eiList;
		this.defaultProxyClass=defaultProxyClass;
		
	}


	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list.
	 */
	public int size() {
		return eiList.size();
	}

	/**
	 * Tests if this list has no elements.
	 * 
	 * @return <tt>true</tt> if this list has no elements; <tt>false</tt>
	 *         otherwise.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns <tt>true</tt> if this list contains the specified element.
	 * 
	 * @param elem
	 *            element whose presence in this List is to be tested.
	 * @return <code>true</code> if the specified element is present;
	 *         <code>false</code> otherwise.
	 */
	public boolean contains(Object elem) {
		return indexOf(elem) >= 0;
	}

	/**
	 * Searches for the first occurence of the given argument, testing for
	 * equality using the <tt>equals</tt> method.
	 * 
	 * @param elem
	 *            an object.
	 * @return the index of the first occurrence of the argument in this list;
	 *         returns <tt>-1</tt> if the object is not found.
	 * @see Object#equals(Object)
	 */
	public int indexOf(Object elem) {
		return eiList.indexOf(elem);
	}

	/**
	 * Returns the index of the last occurrence of the specified object in this
	 * list.
	 * 
	 * @param elem
	 *            the desired element.
	 * @return the index of the last occurrence of the specified object in this
	 *         list; returns -1 if the object is not found.
	 */
	public int lastIndexOf(Object elem) {
		return eiList.lastIndexOf(elem);
	}

	/**
	 * Returns an array containing all of the elements in this list in the
	 * correct order.
	 * 
	 * @return an array containing all of the elements in this list in the
	 *         correct order.
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns an array containing all of the elements in this list in the
	 * correct order; the runtime type of the returned array is that of the
	 * specified array. If the list fits in the specified array, it is returned
	 * therein. Otherwise, a new array is allocated with the runtime type of the
	 * specified array and the size of this list.
	 * <p>
	 * If the list fits in the specified array with room to spare (i.e., the
	 * array has more elements than the list), the element in the array
	 * immediately following the end of the collection is set to <tt>null</tt>.
	 * This is useful in determining the length of the list <i>only</i> if the
	 * caller knows that the list does not contain any <tt>null</tt> elements.
	 * 
	 * @param a
	 *            the array into which the elements of the list are to be
	 *            stored, if it is big enough; otherwise, a new array of the
	 *            same runtime type is allocated for this purpose.
	 * @return an array containing the elements of the list.
	 * @throws ArrayStoreException
	 *             if the runtime type of a is not a supertype of the runtime
	 *             type of every element in this list.
	 */
	public Object[] toArray(Object a[]) {
		throw new UnsupportedOperationException();
	}

	public Object get(int index) {
		return this.get(index, null);
	}
	
	// Positional Access Operations

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index
	 *            index of element to return.
	 * @return the element at the specified position in this list.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range <tt>(index
	 *        &lt; 0 || index &gt;= size())</tt>.
	 */
	public Object get(int index,Class viewProxyClass) {
	
		Object defaultproxy=null;
		
		if (index>eiList.size()-1){
			if (this.service!=null){
				try {
					defaultproxy=this.service.create();
				} catch (USMException e) {
					e.printStackTrace();
				}
			}else{
				defaultproxy= this.getProxyInstance(viewProxyClass, Object.class);
			}
		
			
			
			
			//eiList.add(defaultproxy.getDAO());
		}else{
			 Object eiObj = eiList.get(index);
			 defaultproxy= this.getProxyInstance(this.defaultProxyClass, eiObj);
		}
		
		Object viewProxy=null;
	
		if (viewProxyClass==null){
			viewProxyClass=EsbUtil.getCurrClass();
		}
		if (viewProxyClass!=null && !viewProxyClass.equals(defaultProxyClass)){
			viewProxy= this.getProxyInstance(viewProxyClass, defaultproxy);
		}else{
			viewProxy=defaultproxy;
		}
		return viewProxy;
	}
	
	public List getListByClass(Class clazz){
		List list=new  ArrayList();
		for(int k=0;k<this.size();k++){
			list.add(this.get(k, clazz));
		}
		return list;
		
	}
	


	
	

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 * 
	 * @param index
	 *            index of element to replace.
	 * @param element
	 *            element to be stored at the specified position.
	 * @return the element previously at the specified position.
	 * @throws IndexOutOfBoundsException
	 *             if index out of range
	 *             <tt>(index &lt; 0 || index &gt;= size())</tt>.
	 */
	public Object set(int index, Object element) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param o
	 *            element to be appended to this list.
	 * @return <tt>true</tt> (as per the general contract of Collection.add).
	 */
	public boolean add(Object o) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right (adds one to their indices).
	 * 
	 * @param index
	 *            index at which the specified element is to be inserted.
	 * @param element
	 *            element to be inserted.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range
	 *             <tt>(index &lt; 0 || index &gt; size())</tt>.
	 */
	public void add(int index, Object element) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * 
	 * @param index
	 *            the index of the element to removed.
	 * @return the element that was removed from the list.
	 * @throws IndexOutOfBoundsException
	 *             if index out of range <tt>(index
	 *        &lt; 0 || index &gt;= size())</tt>.
	 */
	public Object remove(int index) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after
	 * this call returns.
	 */
	public void clear() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Appends all of the elements in the specified Collection to the end of
	 * this list, in the order that they are returned by the specified
	 * Collection's Iterator. The behavior of this operation is undefined if the
	 * specified Collection is modified while the operation is in progress.
	 * (This implies that the behavior of this call is undefined if the
	 * specified Collection is this list, and this list is nonempty.)
	 * 
	 * @param c
	 *            the elements to be inserted into this list.
	 * @return <tt>true</tt> if this list changed as a result of the call.
	 * @throws NullPointerException
	 *             if the specified collection is null.
	 */
	public boolean addAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Inserts all of the elements in the specified Collection into this list,
	 * starting at the specified position. Shifts the element currently at that
	 * position (if any) and any subsequent elements to the right (increases
	 * their indices). The new elements will appear in the list in the order
	 * that they are returned by the specified Collection's iterator.
	 * 
	 * @param index
	 *            index at which to insert first element from the specified
	 *            collection.
	 * @param c
	 *            elements to be inserted into this list.
	 * @return <tt>true</tt> if this list changed as a result of the call.
	 * @throws IndexOutOfBoundsException
	 *             if index out of range <tt>(index
	 *        &lt; 0 || index &gt; size())</tt>.
	 * @throws NullPointerException
	 *             if the specified Collection is null.
	 */
	public boolean addAll(int index, Collection c) {
		throw new UnsupportedOperationException();
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		return false;
	}
	
	private  Object getProxyInstance(Class clazz,Object eiObj){
		Object defaultproxy=null;
		Constructor con=null;
		try {
			
			for(int k=0;k<clazz.getConstructors().length;k++){
				Constructor constructor=clazz.getConstructors()[k];
				if (constructor.getParameterTypes().length==1){
					Class clazz1=constructor.getParameterTypes()[0];
					if (clazz1.isAssignableFrom(eiObj.getClass())){
						con=constructor;
					}
				}
			}
			
			if (con!=null){
				defaultproxy= con.newInstance(eiObj);
			}
			
		} catch (SecurityException e) {
			e.printStackTrace();
	
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return defaultproxy;
	}

	public Class getDefaultProxyClass() {
		return defaultProxyClass;
	}

}
