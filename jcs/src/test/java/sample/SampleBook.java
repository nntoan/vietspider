package sample;

/** 
 * Author : Nhu Dinh Thuan
 * Jun 6, 2012  
 */
public class SampleBook {
  public static void main(String[] args) {
    int max = 10000;
    for(int i = 0;  i < max ; i++) {
      Book book = new Book(i);
      BookManager.getInstance().storeBookVObj(book);
    }
    
    Book book = BookManager.getInstance().getBookVObj(1009);
    System.out.println("test "+book);
  }
}
