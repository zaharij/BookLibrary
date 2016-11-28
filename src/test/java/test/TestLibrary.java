/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.mycompany.booklibrary.model.dao.impl.DaoImpl;
import com.mycompany.booklibrary.model.impl.LibraryService;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Zakhar
 */
public class TestLibrary {
    private final static String BOOK_NAME_TEST = "Test test";
    private final static String AUTHOR_NAME_TEST = "T. Test";
    private final static int EMPTY_ARRAY = 0;
    private final static int FIRST_ELEMENT_ARRAY = 0;
    private DaoImpl dao = new DaoImpl();
    private LibraryService library = new LibraryService();
    private String A = "aaa";
    private ArrayList<String> testBooks = new ArrayList<String>()
    {{
        add("zzz");
        add("bbb");
        add(A);
    }};
    
    /**
     * tests connection to the db
     *
    @Test
    public void testConnection(){
        dao.addBookToDb(AUTHOR_NAME_TEST, BOOK_NAME_TEST);
        Assert.assertNotEquals(dao.select(BOOK_NAME_TEST).size(), EMPTY_ARRAY);
        dao.removeBookFromDb(BOOK_NAME_TEST);
    } */
    
    @Test
    public void testSorting(){
        library.sortArray(testBooks);
        Assert.assertEquals(testBooks.get(FIRST_ELEMENT_ARRAY), A);
    }
}
