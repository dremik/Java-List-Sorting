package it.micheledallerive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *  
 * @author Michele Dalle Rive
 *
 */
public class ListSorting {

	public static <T> void bubbleSort(List<T> list){
		boolean swapped;
		int size = list.size();
		do{
			swapped=false;
			for(int i=0;i<size-1;i++){
				if(tToInt(list.get(i+1))<tToInt(list.get(i))){
					T tmp = list.get(i+1);
					list.set(i+1, list.get(i));
					list.set(i, tmp);
					swapped=true;
				}
			}
			size--;
		}while(swapped);
	}
	
	public static <T> List<T> mergeSort(List<T> list1, List<T> list2){
		List<T> list = new ArrayList<>();
		for(int i=0;i<list1.size();i++){
			list.add(list1.get(i));
		}
		for(int i=0;i<list2.size();i++){
			list.add(list2.get(i));
		}
		bubbleSort(list); // :P
		return list;
	}
	
	public static <T> void insertionSort(List<T> list){
		List<T> finalList = new ArrayList<>();
		finalList.add(list.get(0));
		list.remove(0);
		boolean swapped;
		for(int i=0;i<list.size();i++){
			T item = list.get(i);
			swapped=false;
			for(int j=0;j<finalList.size();j++){
				if(tToInt(finalList.get(j))>tToInt(item)){
					finalList.add(j, item);
					swapped=true;
					break;
				}
			}
			if(!swapped)
				finalList.add(item);
		}
		list.clear();
		for(int i=0;i<finalList.size();i++){
			list.add(finalList.get(i));
		}
	}
	
	// QUICK SORT START
	
	public static <T> void quickSort(List<T> list){
		quickSort(list, 0, list.size()-1);
	}
	
	private static <T> void quickSort(List<T> list, int i1, int i2){
		if(i1<i2+1){
			int p = partition(list,i1,i2);
			quickSort(list,i1,p-1);
			quickSort(list,p+1,i2);
		}
	}
	
	private static <T> int partition(List<T> list, int low, int high){
		Random rand = new Random();
		int p = rand.nextInt((high - low) + 1) + low;
		swap(list, low, p);
		int border = low + 1;
		for (int i = border; i <= high; i++) {
			if (tToInt(list.get(i))<tToInt(list.get(low))) {
				swap(list, i, border++);
			}
		}
		swap(list, low, border-1);
		return border-1;
	}
	
	// QUICK SORT END
	
	public static <T> void countingSort(List<T> list){
		List<T> outputList = new ArrayList<>(list.size());
		T max = getMax(list);
		List<Integer> countingList = new ArrayList<>(tToInt(max));
		for(int i=0;i<tToInt(max);i++)
			countingList.add(0);
		for(int i=0;i<tToInt(list.size());i++)
			outputList.add(null);
		for(T item : list)
			countingList.set((tToInt(item)-1), countingList.get(tToInt(item)-1)+1);
		for(int i=1;i<countingList.size();i++)
			countingList.set(i, countingList.get(i)+countingList.get(i-1));
		for(int i=list.size()-1;i>=0;i--){
			T item = list.get(i);
			int index = countingList.get(tToInt(item)-1);
			outputList.set(index-1, item);
			countingList.set(tToInt(item)-1, index);
		}
		list.clear();
		for(T item : outputList)
			list.add(item);
	}
	
	private static <T> T getMax(List<T> list){
		T max=list.get(0);
		for(T item : list){
			if(tToInt(item)>tToInt(max))
				max=item;
		}
		return max;
	}
	
	private static <T> int tToInt(T v){
		if(v instanceof Character)
			return Character.getNumericValue(((char)v));
		if(v instanceof String)
			if(((String) v).length()==1)
				return Character.getNumericValue(((String)v).charAt(0));				
			else
				return Integer.valueOf(((String)v));
		return (int)v;
	}
	
	private static <T> void swap(List<T> l, int index1, int index2){
		T tmp = l.get(index1);
		l.set(index1, l.get(index2));
		l.set(index2, tmp);
	}
	
}
