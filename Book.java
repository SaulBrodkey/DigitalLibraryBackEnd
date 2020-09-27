// Name: Saul Brodkey
// Email: smbrodkey@wisc.edu
// Team: AD
// Role: Back End 2
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: 

public class Book {
	
	private String title;
	private String author;
	private String publisher;
	private int publication_year;
	private String isbn;
	
	//No-argument constructor
	public Book() {
		title = "";
		author = "";
		publisher = "";
		publication_year = 0;
		isbn = "";
	}
	//main constructor
	public Book(String t, String a, String p, int py, String i) {
		title = t;
		author = a;
		publisher = p;
		publication_year = py;
		isbn = i;
	}
	
	//Getter methods
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
	public String getPublisher() { return publisher; }
	public int getPublicationYear() { return publication_year; }
	public String getISBN() { return isbn; }
	
	//Setter methods
	public void setTitle(String title) { this.title = title; }
	public void setAuthor(String author) { this.author = author; }
	public void setPublisher(String publisher) { this.publisher = publisher; }
	public void setPublicationYear(int publication_year) { this.publication_year = publication_year; }
	public void setISBN(String isbn) { this.isbn = isbn; }
	
	public String getCitation() {
		//MLA format is the following order Last Name, First Name. Title of Book. Publisher, Publication Date.
		String first = ""; // we need to break up the first and last names
		String last = "";
		String citation = "";
		
		//for last name, we have to work backwards until we reach a space
	
		for(int lcv = author.length()-1; lcv >= 1; lcv--)
		{
			if(author.substring(lcv,lcv+1).equals(" "))
			{
				last=author.substring(lcv);// if we are at a space, we are, for as much as we care, at the beginning of the first name
				break;//ends loop
			}
			else
			{
				if(lcv ==1)
				{
					last = author; //in this instance, the author is mononymic, like Madonna or Cher
				}
			}
			
		}
		citation += last + ", ";
		//for the first name, we do the same thing, but forward
		if(!(author.equals(last)))
		{
			//if its a mononym, there's no reason check first name
		for(int lcv = 0; lcv < author.length(); lcv++)
		{
			if(author.substring(lcv,lcv+1).equals(" "))
			{
				first = author.substring(0,lcv);
				
				break;//ends loop
			}
		}
			citation +=first + ". "; //adds the first name to citation
		}
		citation+= getTitle()+ ". ";
		citation+= getPublisher() + ", ";
		citation+= getPublicationYear() + ". ";
		return citation;
	}
}
