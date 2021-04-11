package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;

@Component
public class BootstrapData implements CommandLineRunner{
    private final AuthorRepository authRepo;
    private final BookRepository bookRepo;

    public BootstrapData(AuthorRepository authRepo, BookRepository bookRepo) {
        this.authRepo = authRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("1232423");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authRepo.save(eric);
        bookRepo.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE development without EJB");
        noEJB.setIsbn("23364443");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authRepo.save(rod);
        bookRepo.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books:: "+bookRepo.count());
    }
}
