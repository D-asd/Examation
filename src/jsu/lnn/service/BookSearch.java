package jsu.lnn.service;

import jsu.lnn.Dao.BookDaoImpl;
/**��ѯ*/
public class BookSearch {
	public BookSearch(){
		BookDaoImpl book = new BookDaoImpl();
		book.bookSearch();
	}

}
