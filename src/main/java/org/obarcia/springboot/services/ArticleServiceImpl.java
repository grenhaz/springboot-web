package org.obarcia.springboot.services;

import java.util.List;
import org.hibernate.HibernateException;
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
        return articleLiteRepository.getArticlesLite(req);
    }
    @Override
    @Transactional(readOnly = true)
    public DataTablesResponse<CommentLite> getCommentsLite(Integer id, DataTablesRequest req)
    {
        return commentLiteRepository.getCommentsLite(id, req);
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<ArticleSimple> getArticlesAll(int page, int perPage)
    {
        ListPagination<ArticleSimple> list = articleSimpleRepository.getArticles(page, perPage, null, null);
        // Obtener el contador de comentarios
        if (list.getRecords() != null) {
            for (ArticleSimple a: list.getRecords()) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<ArticleSimple> getArticlesAll(int page, int perPage, String type)
    {
        ListPagination<ArticleSimple> list = articleSimpleRepository.getArticles(page, perPage, null, type);
        // Obtener el contador de comentarios
        if (list.getRecords() != null) {
            for (ArticleSimple a: list.getRecords()) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<ArticleSimple> getArticlesAll(int page, int perPage, String tag, String type)
    {
        ListPagination<ArticleSimple> list = articleSimpleRepository.getArticles(page, perPage, tag, type);
        // Obtener el contador de comentarios
        if (list.getRecords() != null) {
            for (ArticleSimple a: list.getRecords()) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<ArticleSimple> getArticlesSearch(int page, int perPage, String tag, String search)
    {
        ListPagination<ArticleSimple> list = articleSimpleRepository.getArticlesSearch(page, perPage, tag, search);
        // Obtener el contador de comentarios
        if (list.getRecords() != null) {
            for (ArticleSimple a: list.getRecords()) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<ArticleSimple> getArticlesImportants(String tag)
    {
        List<ArticleSimple> list = articleSimpleRepository.getArticlesImportant(tag, null, 3);
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<ArticleSimple> getArticlesImportants(String tag, String type)
    {
        List<ArticleSimple> list = articleSimpleRepository.getArticlesImportant(tag, type, 3);
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<ArticleSimple> getArticlesByType(String tag, String type, int count)
    {
        List<ArticleSimple> list = articleSimpleRepository.getArticles(0, count, tag, type).getRecords();
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<ArticleSimple> getArticlesMoreComments(String tag, int count)
    {
        List<ArticleSimple> list = articleSimpleRepository.getArticlesMoreComments(tag, count);
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Comment> getComments(int id, int page, int perPage)
    {
        return commentRepository.getComments(id, page, perPage);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Comment> getLastComments(String tag, int count)
    {
        return commentRepository.getLastComments(tag, count);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Comment> getLastCommentsByUser(int id, int count)
    {
        return commentRepository.getLastCommentsByUser(id, count);
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
        return articleRepository.getArticleByTitle(title);
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