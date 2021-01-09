package jsu.lnn.service;

import jsu.lnn.Dao.BookDaoImpl;
/**ÐÞ¸Ä*/
public class BookUpdate {
	public BookUpdate(){
		BookDaoImpl book = new BookDaoImpl();
		book.bookUpdate();
	}

}
