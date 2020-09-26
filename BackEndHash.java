// --== CS400 File Header Information ==--
// Name: Saul Brodkey
// Email: smbrodkey@wisc.edu
// Team: AD
// Role: Back End Dev #2
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;

public class BackEndHash {
	private int mycapacity;
	private int mysize;
	private LinkedList<Book>[]hashmap; 
	@SuppressWarnings("unchecked")
	public BackEndHash()
	{
		hashmap = new LinkedList[10];
mycapacity=10
		mysize = 0;
	}
	@SuppressWarnings("unchecked")
	public BackEndHash(int capacity)
	{
		mycapacity = capacity;
		hashmap = new LinkedList[mycapacity];
		mysize = 0;
	}
	//this quick helper method hashes ISBNs quickly
	private int hashIt(Book n)
	{
		return (int)(Math.abs(n.getISBN().hashCode())%mycapacity);
	}
	
	//this helper method will double the array and rehash the array
	private void doubleThis() {
		
		@SuppressWarnings("unchecked")
		LinkedList<Book>[]temp = new LinkedList[mycapacity*2];
		mycapacity*=2; //we double the size
		// we have to rehash the keys now with the greater capacity
		for(int lcv = 0; lcv < hashmap.length; lcv++)
		{
			if(hashmap[lcv]!=null)
			{
				//there is an entry here
				int cnt = 0;
				 for(int lcv1 =0; lcv1 < hashmap[hash].size(); lcv1++)
				{
					//we want to set temp's new rehashed index
					//equal to its corresponding current ISBNs
					temp[hashIt(hashmap[lcv].get(lcv1))] = new LinkedList<Book>();
					temp[hashIt(hashmap[lcv].get(lcv1))].add(hashmap[lcv].get(lcv1));
					
					
				}
				
			}
		}
		hashmap = temp;
	}
	
	
	
	
	//this add(Book n) will add a book to the its hashmap
	//location in the array. This is determined by its ISBN's hashcode
	//absolute valued & modulated by the current capacity. If ISBNs collide, they
	//will be linked together in the LinkedList. This happens less
	//often as the capacity grows
	public void add(Book n)
	{
	 int hash = hashIt(n);
	 //Possibility 1: nothing is in this index yet
	 if(hashmap[hash]==null)
	 {
		 mysize++;
		 hashmap[hash] = new LinkedList<Book>();	
		 hashmap[hash].add(n);
	 }
	 else
	 {
	 //Possibility 2&3: something is in this index
	 if(hashmap[hash]!=null)
	 {
		 //we have to check if this book is duplicate
		 //duplicates will not be inserted
		 int cnt = 0;
		 for(int lcv =0; lcv < hashmap[hash].size(); lcv++)
		 {
			 if(hashmap[hash].get(lcv)!=null&&hashmap[hash].get(lcv).getISBN() == n.getISBN())
			 {
				 break; //this is a duplicate, discard it
			 }
			 
			 cnt++;
		 }
		
		 
		 //if is this part is reached, the index was not null,
		 //nor was n a duplicate. So we will add it to the library
		 hashmap[hash].add(n);
		 mysize++; //we added a book
		
	 	}
	 }
	 //since we added a book, we need to check if we need to expand
	 //our library's capacity. For the sake of minimizing ISBN hashing
	 //collisions. We'll choose a load factor of 80%
	 if((double)((double)mysize/(double)mycapacity)>=0.8)
	 { doubleThis();}
	}
	
	//This will search for a book meeting the specification of the ISBN
	//search term (i). If none match, return null;
	public Book get(String i)
	{
		//oversight on my end hashIt() only works with a Book,
		//when given just an ISBN we have to manually code it
		
		int index = Math.abs(i.hashCode()%mycapacity);
		if(hashmap[index]==null)
		{
			return null; //if nothing matches the index, automatic fail
		}
		else
		{
			int cnt = 0;
			for(int lcv =0; lcv < hashmap[hash].size(); lcv++)
			{
				if(hashmap[index].get(lcv).getISBN().equals(i))
					{
						return hashmap[index].get(lcv);
					}
				cnt++;
			}
		}
		return null; //there was no entry that matched
	}
	//returns size of library
	public int getSize()
	{
		return mysize;
	}
	//this method takes an ISBN and searches the map for the
	//corresponding book, then removes it. Returns the book, else null.
	public Book remove(String i)
	{
		int index = Math.abs(i.hashCode()%mycapacity);
		if(hashmap[index]==null)
		{
			return null; //if nothing matches the index, automatic fail
		}
		else
		{
			int cnt = 0;
			for(int lcv =0; lcv < hashmap[index].size(); lcv++)
			{
				if(hashmap[index].get(lcv)!=null&&hashmap[index].get(lcv).getISBN().equals(i))
					{
						Book save =  hashmap[index].get(lcv); //this is the book we want to remove, saving it for later
						hashmap[index].remove(lcv); //this removes the book in question
						return save; // we can now return and break the loop (no duplicates allowed)
					}
				cnt++;
			}
		}
		return null; //there was no entry that matched
	}
	
	//this method takes an ISBN and searches the map for the
	//corresponding book. It returns true if the book is in the library.
	//false otherwise.
	public boolean contains(String i)
	{
		int index = Math.abs(i.hashCode()%mycapacity);
		if(hashmap[index]==null)
		{
			return false; //if nothing matches the index, automatic fail
		}
		else
		{
			int cnt = 0;
			for(int lcv =0; lcv < hashmap[index].size(); lcv++)
			{
				if(hashmap[index].get(lcv)!=null&&hashmap[index].get(lcv).getISBN().equals(i))
					{
						return true; //a matching ISBN exists. return true.
					}
				cnt++;
			}
		}
		return false; //there was no entry that matched
	}
	
	//empties the library of all books
	public void emptyLibrary()
	{
		@SuppressWarnings("unchecked")
		LinkedList<Book>[] temp = new LinkedList[mycapacity];
		hashmap = temp;
		mysize = 0;
	}
	
}
