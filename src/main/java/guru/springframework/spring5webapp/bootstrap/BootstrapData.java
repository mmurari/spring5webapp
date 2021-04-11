package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authRepo;
    private final BookRepository bookRepo;
    private final PublisherRepository publisherRepo;

    public BootstrapData(AuthorRepository authRepo, BookRepository bookRepo, PublisherRepository publisherRepo) {
        this.authRepo = authRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("SFG");
        publisher.setAddressLine1("273 Botany Road, Rosebery");
        publisher.setCity("Sydney");
        publisher.setState("NSW");
        publisher.setZip("2018");

        publisherRepo.save(publisher);

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("1232423");
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authRepo.save(eric);
        bookRepo.save(ddd);
        publisherRepo.save(publisher);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE development without EJB");
        noEJB.setIsbn("23364443");
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authRepo.save(rod);
        bookRepo.save(noEJB);
        publisherRepo.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books:: " + bookRepo.count());
        System.out.println("Number of Books in publisher:: " + publisher.getBooks().size());

    }
}
