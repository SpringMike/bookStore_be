package com.example.demo.service.impl;

import com.example.demo.dto.BookResponse;
import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Book;
import com.example.demo.repo.IBookRepo;
import com.example.demo.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final IBookRepo bookRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FeaturedBookDTO> getAllBookFeatured() {
        List<FeaturedBookDTO> listFeaturedBook = entityManager.createNamedQuery("getFeaturedBook").getResultList();
        System.out.println(listFeaturedBook);
        return listFeaturedBook;
    }

    @Override
    public Book save(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        Book bookFromDb = bookRepo.findById(book.getId()).orElse(null);
        if (bookFromDb != null){
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
        String sql ="select b.id  as id,\n" +
                "       b.back_cover_image,\n" +
                "       b.front_cover_image,\n" +
                "       b.description,\n" +
                "       b.language,\n" +
                "       b.name   as bookName,\n" +
                "       b.number_page,\n" +
                "       b.public_year,\n" +
                "       b.quantity,\n" +
                "       b.status as bookStatus,\n" +
                "       a.name   as authorName,\n" +
                "       c.name   as categoryName,\n" +
                "       pu.name   as publisherName,\n" +
                "       s.name   as supplierName,\n" +
                "       b.price as price,\n" +
                "       p.sale as sale,\n" +
                "       b.price - ( CAST(b.price as float) * (CAST(p.sale as float) / 100 )) as newPrice,\n" +
                "       pbl.promotion_id as blackListPromotionId\n" +
                "from promotion p\n" +
                "         right join promotion_categories pc on p.id = pc.promotion_id\n" +
                "         right join category c on c.id = pc.category_id\n" +
                "         right join book b on c.id = b.category_id\n" +
                "         inner join author a on a.id = b.author_id\n" +
                "         inner join publisher pu on pu.id = b.publisher_id\n" +
                "         inner join supplier s on s.id = b.supplier_id\n" +
                "         left join promotion_black_list pbl on b.id = pbl.book_id" +
                "         where b.id=?1";
        Query query = this.entityManager.createNativeQuery(sql.toString(),"FeaturedBookMapping");
        query.setParameter(1,id);
        return (FeaturedBookDTO) query.getSingleResult();
    }

    @Override
    public List<Book> findByPromotion(long promotionId) {
        return bookRepo.findByPromotionId(promotionId);
    }

    @Override
    public List<Book> findByBlackList(long promotionId) {
        return bookRepo.findByBlackList(promotionId);
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

        String sql ="select b.id  as id,\n" +
                "       b.back_cover_image,\n" +
                "       b.front_cover_image,\n" +
                "       b.description,\n" +
                "       b.language,\n" +
                "       b.name   as bookName,\n" +
                "       b.number_page,\n" +
                "       b.public_year,\n" +
                "       b.quantity,\n" +
                "       b.status as bookStatus,\n" +
                "       a.name   as authorName,\n" +
                "       c.name   as categoryName,\n" +
                "       pu.name   as publisherName,\n" +
                "       s.name   as supplierName,\n" +
                "       b.price as price,\n" +
                "       p.sale as sale,\n" +
                "       b.price - ( CAST(b.price as float) * (CAST(p.sale as float) / 100 )) as newPrice,\n" +
                "       pbl.promotion_id as blackListPromotionId\n" +
                "from promotion p\n" +
                "         right join promotion_categories pc on p.id = pc.promotion_id\n" +
                "         right join category c on c.id = pc.category_id\n" +
                "         right join book b on c.id = b.category_id\n" +
                "         inner join author a on a.id = b.author_id\n" +
                "         inner join publisher pu on pu.id = b.publisher_id\n" +
                "         inner join supplier s on s.id = b.supplier_id\n" +
                "         left join promotion_black_list pbl on b.id = pbl.book_id\n" +
                "where b.category_id=?1\n";


        Query query = this.entityManager.createNativeQuery(sql.toString(),"FeaturedBookMapping");
        query.setParameter(1,categoryId);


        List<FeaturedBookDTO> books1 = query.getResultList();

        Page<FeaturedBookDTO> bookDTOS = new PageImpl<FeaturedBookDTO>(books1, pageable,books1.size());

        // get content for page object
        List<FeaturedBookDTO> listOfBooks = bookDTOS.getContent();

        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(listOfBooks);
        bookResponse.setPageNo(bookDTOS.getNumber());
        bookResponse.setPageSize(bookDTOS.getSize());
        bookResponse.setTotalElements(bookDTOS.getTotalElements());
        bookResponse.setTotalPages(bookDTOS.getTotalPages());
        bookResponse.setLast(bookDTOS.isLast());

        return bookResponse;
    }


}
