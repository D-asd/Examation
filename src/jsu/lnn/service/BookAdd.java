package jsu.lnn.service;

import jsu.lnn.Dao.BookDaoImpl;
/**Ìí¼Ó*/
public class BookAdd {
	public BookAdd(){
		BookDaoImpl book = new BookDaoImpl();
		book.bookAdd();
	}

}
