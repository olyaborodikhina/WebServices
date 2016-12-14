package xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.domain.Author;
import xml.domain.Book;
import xml.domain.Library;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.annotation.Documented;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 11.01.14
 */
public class LibraryLauncher {
    public static void main(String[] args) throws Exception {
        final InputStream is = LibraryLauncher.class.getResourceAsStream("/library.xml");
        try {
            final Library library = buildLibrary(is);

            System.out.println("Authors:");
            for (final Author author : library.getAuthors()) {
                System.out.println("  " + author.getName());
            }

            System.out.println();
            System.out.println("Books:");
            for (final Book book : library.getBooks()) {
                final StringBuffer authors = new StringBuffer();

                final Iterator<Author> it = book.getAuthors().iterator();
                while (it.hasNext()) {
                    Author author = it.next();
                    authors.append(author.getName());
                    if (it.hasNext()) {
                        authors.append(", ");
                    }
                }

                System.out.println("  " + book.getTitle() + " (" + String.valueOf(authors) + ")");
            }

        } finally {
            is.close();
        }
    }

    private static Library buildLibrary(final InputStream is) throws Exception {

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        NodeList books = doc.getElementsByTagName("book");
        Library library = new Library();

        for(int i = 0; i < books.getLength();i++) {
            Node bookNode = books.item(i);
            Book book = new Book();
            NodeList mayBeAuthorList = bookNode.getChildNodes();
            for (int j = 0; j < mayBeAuthorList.getLength(); j++) {
                Node mayBeAuthorNode = mayBeAuthorList.item(j);
                if (mayBeAuthorNode.getNodeType() == Node.ELEMENT_NODE) {
                    if (mayBeAuthorNode.getLocalName().equals("author")){
                        Author author = new Author();
                        author.setName(mayBeAuthorNode.getAttributes().item(0).getNodeValue());
                        library.getAuthors().add(author);
                    }
                    if(mayBeAuthorNode.getLocalName().equals("title")) {
                        String title = mayBeAuthorNode.getAttributes().item(0).getNodeValue();
                        book.setTitle(title);
                        library.getBooks().add(book);
                    }

                }
            }
        }

        if(books.item(0).getChildNodes().item(0).getNodeType() == Node.ATTRIBUTE_NODE) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) books.item(0).getChildNodes().item(0);
            final String attributeValue = attr.getNodeValue();
            final String attributeName = attr.getName();
        }
        return library;
    }

}
