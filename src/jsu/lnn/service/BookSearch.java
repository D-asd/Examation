package jsu.lnn.service;

import jsu.lnn.Dao.BookDaoImpl;
/**≤È—Ø*/
public class BookSearch {
	public BookSearch(){
		BookDaoImpl book = new BookDaoImpl();
		book.bookSearch();
	}

}
