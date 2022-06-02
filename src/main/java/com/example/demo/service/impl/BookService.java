package com.example.demo.service.impl;

import com.example.demo.dto.BookResponse;
import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Book;
import com.example.demo.repo.IBookRepo;
import com.example.demo.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final IBookRepo bookRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FeaturedBookDTO> getAllBookFeatured() {
        List<FeaturedBookDTO> listFeaturedBook = entityManager.createNamedQuery("getFeaturedBook").getResultList();
        return listFeaturedBook;
    }

    @Override
    public Book save(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        Book bookFromDb = bookRepo.findById(book.getId()).orElse(null);
        if (bookFromDb!= null){
            bookFromDb.setName(book.getName());
            bookFromDb.setPrice(book.getPrice());
            bookFromDb.setDescription(book.getDescription());
            bookFromDb.setFrontCoverImage(book.getFrontCoverImage());
            bookFromDb.setBackCoverImage(book.getBackCoverImage());
            bookFromDb.setQuantity(book.getQuantity());
            bookFromDb.setNumberPage(book.getNumberPage());
            bookFromDb.setPublicYear(book.getPublicYear());
            bookFromDb.setAuthorId(book.getAuthorId());
            bookFromDb.setCategoryId(book.getCategoryId());
            bookFromDb.setSupplierId(book.getSupplierId());
            bookFromDb.setPublisherId(book.getPublisherId());
            bookRepo.save(bookFromDb);
        }
        return null;
    }

    @Override
    public FeaturedBookDTO findById(long id) {
        if (id < 0){
            return null;
        }
        String sql ="select b.id id, b.back_cover_image, b.front_cover_image, b.description, b.language,b.name as bookName,b.number_page,b.price,b.public_year,\n" +
                "       b.quantity,b.status as bookStatus,a.name as authorName , c.name as categoryName, p.name as publisherName, s.name as supplierName\n" +
                "from book b inner join author a on a.id = b.author_id\n" +
                "            inner join category c on c.id = b.category_id\n" +
                "            inner join publisher p on p.id = b.publisher_id\n" +
                "            inner join supplier s on s.id = b.supplier_id where b.id = ?1\n";

        Query query = this.entityManager.createNativeQuery(sql.toString(),"FeaturedBookMapping");
        query.setParameter(1,id);
        return (FeaturedBookDTO) query.getSingleResult();
    }

    @Override
    public Book findById2(long id) {
        return bookRepo.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public void updateStatusBook(long id) {
        Book bookFromDb = bookRepo.findById(id).orElse(null);
        if (bookFromDb!= null){
            if (bookFromDb.isStatus()){
                bookFromDb.setStatus(false);
                bookRepo.save(bookFromDb);
            }else {
                bookFromDb.setStatus(true);
                bookRepo.save(bookFromDb);
            }
        }
    }

    @Override
    public BookResponse findPaginatedByCategory(int pageNo, int pageSize, String sortBy, String sortDir,Long categoryId) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Book> books = bookRepo.findByCategoryId(categoryId,pageable);

        // get content for page object
        List<Book> listOfBooks = books.getContent();

        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(listOfBooks);
        bookResponse.setPageNo(books.getNumber());
        bookResponse.setPageSize(books.getSize());
        bookResponse.setTotalElements(books.getTotalElements());
        bookResponse.setTotalPages(books.getTotalPages());
        bookResponse.setLast(books.isLast());

        return bookResponse;
    }


}
