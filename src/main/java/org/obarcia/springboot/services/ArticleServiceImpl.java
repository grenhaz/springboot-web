package org.obarcia.springboot.services;

import java.util.List;
import org.hibernate.HibernateException;
import org.obarcia.springboot.components.datatables.DataTablesOrder;
import org.obarcia.springboot.components.datatables.DataTablesResponse;
import org.obarcia.springboot.components.datatables.DataTablesRequest;
import org.obarcia.springboot.exceptions.SaveException;
import org.obarcia.springboot.models.ListPagination;
import org.obarcia.springboot.models.article.Article;
import org.obarcia.springboot.models.article.ArticleLite;
import org.obarcia.springboot.models.article.ArticleSimple;
import org.obarcia.springboot.models.article.Comment;
import org.obarcia.springboot.models.article.CommentLite;
import org.obarcia.springboot.repositories.ArticleLiteRepository;
import org.obarcia.springboot.repositories.ArticleRepository;
import org.obarcia.springboot.repositories.ArticleSimpleRepository;
import org.obarcia.springboot.repositories.CommentLiteRepository;
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
     * Instancia del repositorio de artículos.
     */
    @Autowired
    private ArticleLiteRepository articleLiteRepository;
    /**
     * Instancia del repositorio de artículos.
     */
    @Autowired
    private ArticleSimpleRepository articleSimpleRepository;
    /**
     * Instancia del repositorio de comentarios.
     */
    @Autowired
    private CommentRepository commentRepository;
    /**
     * Instancia del repositorio de comentarios.
     */
    @Autowired
    private CommentLiteRepository commentLiteRepository;
    
    @Override
    @Transactional(readOnly = true)
    public DataTablesResponse<ArticleLite> getArticlesLite(DataTablesRequest req)
    {
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
        List<ArticleLite> records = articleLiteRepository.findAll(pagination);
        DataTablesResponse<ArticleLite> response = new DataTablesResponse<>();
        response.setDraw(req.getDraw());
        response.setData(records);
        response.setRecordsFiltered((long)records.size());
        response.setRecordsTotal(articleLiteRepository.countAll());
        return response;
    }
    @Override
    @Transactional(readOnly = true)
    public DataTablesResponse<CommentLite> getCommentsLite(Integer id, DataTablesRequest req)
    {
        //TODO: return commentLiteRepository.getComments(id, req);
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<ArticleSimple> getArticlesAll(int page, int perPage)
    {
        /*TODO: ListPagination<ArticleSimple> list = articleSimpleRepository.getArticles(page, perPage, null, null);
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
    public ListPagination<ArticleSimple> getArticlesAll(int page, int perPage, String type)
    {
        /*TODO: ListPagination<ArticleSimple> list = articleSimpleRepository.getArticles(page, perPage, null, type);
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
    public ListPagination<ArticleSimple> getArticlesAll(int page, int perPage, String tag, String type)
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
    public ListPagination<ArticleSimple> getArticlesSearch(int page, int perPage, String tag, String search)
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
    @Override
    @Transactional(readOnly = true)
    public List<ArticleSimple> getArticlesImportants(String tag)
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
    public List<ArticleSimple> getArticlesImportants(String tag, String type)
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
    public List<ArticleSimple> getArticlesByType(String tag, String type, int count)
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
    public List<ArticleSimple> getArticlesMoreComments(String tag, int count)
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