package jsu.lnn.service;

import jsu.lnn.Dao.BookDaoImpl;
/**ɾ��*/
public class BookDelete {
	public BookDelete(){
		BookDaoImpl book = new BookDaoImpl();
		book.bookDel();
	}

}
