package jsu.lnn.service;

import jsu.lnn.Dao.BookDaoImpl;
/**�޸�*/
public class BookUpdate {
	public BookUpdate(){
		BookDaoImpl book = new BookDaoImpl();
		book.bookUpdate();
	}

}
