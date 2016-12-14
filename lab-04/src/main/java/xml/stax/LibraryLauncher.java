package xml.stax;

import xml.domain.Author;
import xml.domain.Book;
import xml.domain.Library;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import static java.util.Collections.*;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 11.01.14
 */
public class LibraryLauncher {

    public static void main(String[] args) throws IOException, XMLStreamException {
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

            System.out.println("---");
            writeLibrary(library, System.out);
            System.out.flush();

        } finally {
            is.close();
        }
    }

    private static Library buildLibrary(final InputStream is) throws XMLStreamException, FileNotFoundException {
        Library library = null;
        String title = null;
        Book book = new Book();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = factory.createXMLEventReader(is);
        while (reader.hasNext()) {
            final XMLEvent event = reader.nextEvent();

            if (event.isStartDocument()) {
                library = new Library();

            } else if (event.isEndDocument()) {
                break;

            } else if (event.isStartElement()) {
                final StartElement e = (StartElement) event;

                if ("author".equals(e.getName().getLocalPart())) {
                    Author author = new Author();
                    Attribute a =  e.getAttributeByName(QName.valueOf("name"));
                    author.setName(a.getValue());
                    library.getAuthors().add(author);
                    book.getAuthors().add(author);
                }else if("title".equals(e.getName().getLocalPart())){
                    title = null;
                } else if("book".equals(e.getName().getLocalPart())){
                    book = new Book();
                }

            }else if (event.isEndElement()){
                final EndElement end = (EndElement) event;
               //здесь нужно добавить эту книгу в библиотеку
               if("title".equals(end.getName().getLocalPart())){
                    book.setTitle(title);
                }

                if("book".equals(end.getName().getLocalPart())){
                    library.getBooks().add(book);
                }


            }else if(event.isCharacters()) {
                Characters ch = (Characters)event;
                title = ch.getData();
            }
        }

        return library;
    }

    private static void writeLibrary(final Library library, final OutputStream os) throws XMLStreamException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLEventWriter writer = factory.createXMLEventWriter(os);

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        Attribute a = eventFactory.createAttribute(QName.valueOf("name"),
                "Kennet Beck"
        );
        writer.add(eventFactory.createStartDocument());
        writer.add(eventFactory.createStartElement(QName.valueOf("author"), Collections.singleton(a).iterator(),null));
        writer.add(eventFactory.createEndDocument());
        writer.close();
    }
}
