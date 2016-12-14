package xml.stax;

import xml.domain.Author;
import xml.domain.Book;
import xml.domain.Library;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.Iterator;

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
                if (e.getName().equals("book")) {
                    Iterator iterator = e.getAttributes();
                    while (iterator.hasNext()) {
                        Attribute a = (Attribute) iterator.next();
                        if (a.equals("author")) {
                            Author author = new Author();
                            author.setName(a.toString());
                            library.getAuthors().add(author);//
                        }
                    }
                }
            }else if (event.isEndElement()){
               //здесь нужно добавить эту книгу в библиотеку
            }
        }

        return library;
    }

    private static void writeLibrary(final Library library, final OutputStream os) throws XMLStreamException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLEventWriter writer = factory.createXMLEventWriter(os);
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        writer.add(eventFactory.createStartDocument());
        writer.add(eventFactory.createStartElement(QName.valueOf("example"),null,null));
        writer.add(eventFactory.createEndDocument());
        writer.close();
    }
}
