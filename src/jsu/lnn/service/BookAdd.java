package jsu.lnn.service;

import jsu.lnn.Dao.BookDaoImpl;
/**���*/
public class BookAdd {
	public BookAdd(){
		BookDaoImpl book = new BookDaoImpl();
		book.bookAdd();
	}

}
