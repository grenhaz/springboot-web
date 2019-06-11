package org.obarcia.springboot.services;

import java.util.List;
import org.hibernate.HibernateException;
import org.obarcia.springboot.components.datatables.DataTablesOrder;
import org.obarcia.springboot.components.datatables.DataTablesResponse;
import org.obarcia.springboot.components.datatables.DataTablesRequest;
import org.obarcia.springboot.exceptions.SaveException;
import org.obarcia.springboot.models.ListPagination;
import org.obarcia.springboot.models.entity.article.Article;
import org.obarcia.springboot.models.entity.article.Comment;
import org.obarcia.springboot.repositories.ArticleRepository;
import org.obarcia.springboot.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de artículos y comentarios.
 * 
 * @author obarcia
 */
@Service
public class ArticleServiceImpl implements ArticleService
{
    /**
     * Instancia del repositorio de artículos.
     */
    @Autowired
    private ArticleRepository articleRepository;
    /**
     * Instancia del repositorio de comentarios.
     */
    @Autowired
    private CommentRepository commentRepository;
    
    @Override
    @Transactional(readOnly = true)
    public DataTablesResponse<Article> getArticlesLite(DataTablesRequest req)
    {
        // TODO: Aplciar filtros
        
        // Orden
        Sort sort = Sort.unsorted();
        for (DataTablesOrder order: req.getOrders()) {
            if (order.getDir() == DataTablesOrder.ORDER_ASC) {
                sort.and(Sort.by(order.getData()).ascending());
            } else {
                sort.and(Sort.by(order.getData()).descending());
            }
        }
        
        // Paginación
        Pageable pagination = PageRequest.of(req.getPage(), req.getLength(), sort);
        
        // Obtener los registros
        List<Article> records = articleRepository.findAll(pagination);
        DataTablesResponse<Article> response = new DataTablesResponse<>();
        response.setDraw(req.getDraw());
        response.setData(records);
        response.setRecordsFiltered((long)records.size());
        response.setRecordsTotal(articleRepository.count());
        return response;
    }
    @Override
    @Transactional(readOnly = true)
    public DataTablesResponse<Comment> getCommentsLite(Integer id, DataTablesRequest req)
    {
        //TODO: return commentLiteRepository.getComments(id, req);
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Article> getArticlesAll(int page, int perPage)
    {
        List<Article> slist = articleRepository.findAll(PageRequest.of(page, perPage, Sort.by("id")));
        
        return createListPagination(page, perPage, articleRepository.count(), slist);
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Article> getArticlesAll(int page, int perPage, String type)
    {
        List<Article> slist = articleRepository.findByType(type, PageRequest.of(page, perPage, Sort.by("id")));
        
        return createListPagination(page, perPage, articleRepository.countByType(type), slist);
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Article> getArticlesAll(int page, int perPage, String tag, String type)
    {
        /*TODO: ListPagination<ArticleSimple> list = articleSimpleRepository.getArticles(page, perPage, tag, type);
        // Obtener el contador de comentarios
        if (list.getRecords() != null) {
            for (ArticleSimple a: list.getRecords()) {
                a.getCommentsCount();
            }
        }
        
        return list;*/
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Article> getArticlesSearch(int page, int perPage, String tag, String search)
    {
        /*TODO: ListPagination<ArticleSimple> list = articleSimpleRepository.getArticlesSearch(page, perPage, tag, search);
        // Obtener el contador de comentarios
        if (list.getRecords() != null) {
            for (ArticleSimple a: list.getRecords()) {
                a.getCommentsCount();
            }
        }
        
        return list;*/
        return null;
    }
    private ListPagination createListPagination(int page, int perPage, long total, List<Article> slist)
    {
        // Obtener el contador de comentarios
        if (slist != null) {
            for (Article a: slist) {
                a.getCommentsCount();
            }
        }
        
        // Listado paginado
        ListPagination list = new ListPagination();
        list.setRecords(slist);
        list.setOffset(page * perPage);
        list.setLimit(perPage);
        list.setTotal(total);
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticlesImportants(String tag)
    {
        /*TODO: List<ArticleSimple> list = articleSimpleRepository.getArticlesImportant(tag, null, 3);
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;*/
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticlesImportants(String tag, String type)
    {
        /*TODO: List<ArticleSimple> list = articleSimpleRepository.getArticlesImportant(tag, type, 3);
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;*/
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticlesByType(String tag, String type, int count)
    {
        /*TODO: List<ArticleSimple> list = articleSimpleRepository.getArticles(0, count, tag, type).getRecords();
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;*/
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticlesMoreComments(String tag, int count)
    {
        /*TODO: List<ArticleSimple> list = articleSimpleRepository.getArticlesMoreComments(tag, count);
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;*/
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Comment> getComments(int id, int page, int perPage)
    {
        //TODO: return commentRepository.findByArticle(id, PageRequest.of(page, perPage, Sort.by("publish").descending()));
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Comment> getLastComments(String tag, int count)
    {
        //TODO: return commentRepository.getLastComments(tag, count);
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Comment> getLastCommentsByUser(int id, int count)
    {
        return commentRepository.findByUser(id, PageRequest.of(0, count, Sort.by("publish").descending()));
    }
    @Override
    @Transactional(readOnly = true)
    public Article getArticle(int id)
    {
        return articleRepository.findById(id).get();
    }
    @Override
    @Transactional(readOnly = true)
    public Comment getComment(int id)
    {
        return commentRepository.findById(id).get();
    }
    @Override
    @Transactional(readOnly = true)
    public Article getArticleByTitle(String title)
    {
        return articleRepository.findByTitle(title);
    }
    @Override
    @Transactional
    public void save(Article article) throws SaveException
    {
        try {
            articleRepository.save(article);
        } catch (HibernateException e) {
            throw new SaveException();
        }
    }
    @Override
    @Transactional
    public void save(Comment comment) throws SaveException
    {
        try {
            commentRepository.save(comment);
        } catch (HibernateException e) {
            throw new SaveException();
        }
    }
}