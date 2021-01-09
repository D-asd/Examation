package jsu.lnn.service;

import jsu.lnn.Dao.BookDaoImpl;
/**É¾³ý*/
public class BookDelete {
	public BookDelete(){
		BookDaoImpl book = new BookDaoImpl();
		book.bookDel();
	}

}
